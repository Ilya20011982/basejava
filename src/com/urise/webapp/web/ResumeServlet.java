package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.*;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.util.DateUtil;
import com.urise.webapp.util.HtmlUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete" -> {
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            }
            case "view" -> {
                r = storage.get(uuid);
            }
            case "add" -> {
                r = Resume.EMPTY;
            }
            case "edit" -> {
                r = storage.get(uuid);
                for (SectionType type : SectionType.values()) {
                    AbstractSection section = r.getAbstractSection(type);
                    switch (type) {
                        case OBJECTIVE:
                        case PERSONAL:
                            if (section == null) {
                                section = TextSection.EMPTY;
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = ListSection.EMPTY;
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            OrganizationSection orgSection = (OrganizationSection) section;
                            List<Experience> emptyFirstOrganizations = new ArrayList<>();
                            emptyFirstOrganizations.add(Experience.EMPTY);
                            if (orgSection != null) {
                                for (Experience org : orgSection.getExperiences()) {
                                    List<Experience.Position> emptyFirstPositions = new ArrayList<>();
                                    emptyFirstPositions.add(Experience.Position.EMPTY);
                                    emptyFirstPositions.addAll(org.getPositions());
                                    emptyFirstOrganizations.add(new Experience(org.getHomePage(), emptyFirstPositions));
                                }
                            }
                            section = new OrganizationSection(emptyFirstOrganizations);
                    }
                    r.setSection(type, section);
                }
            }
            default -> {
                throw new IllegalArgumentException("Action " + action + " is illegal");
            }
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        final boolean isCreate = (uuid == null || uuid.length() == 0);
        Resume r;
        if (isCreate) {
            r = new Resume(fullName.trim());
        } else {
            r = storage.get(uuid);
            r.setFullName(fullName.trim());
        }
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (HtmlUtil.isEmpty(value)) {
                r.getContacts().remove(type);
            } else {
                r.setContact(type, value.trim());
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (HtmlUtil.isEmpty(value) && values.length < 2) {
                r.getSections().remove(type);
            } else {
                switch (type) {
                    case OBJECTIVE, PERSONAL -> r.setSection(type, new TextSection(value.replaceAll("\\s+ | \\n*", " ").trim()));
                    case ACHIEVEMENT, QUALIFICATIONS -> r.setSection(type, new ListSection(value.trim().replaceAll("\\s+\\n+\\s+", "\n").split("\\n")));
                    case EDUCATION, EXPERIENCE -> {
                        List<Experience> orgs = new ArrayList<>();
                        String[] urls = request.getParameterValues(type.name() + "url");
                        for (int i = 0; i < values.length; i++) {
                            String name = values[i];
                            if (!HtmlUtil.isEmpty(name)) {
                                List<Experience.Position> positions = new ArrayList<>();
                                String pfx = type.name() + i;
                                String[] startDates = request.getParameterValues(pfx + "startDate");
                                String[] endDates = request.getParameterValues(pfx + "endDate");
                                String[] titles = request.getParameterValues(pfx + "title");
                                String[] descriptions = request.getParameterValues(pfx + "description");
                                for (int j = 0; j < titles.length; j++) {
                                    if (!HtmlUtil.isEmpty(titles[j])) {
                                        positions.add(new Experience.Position(DateUtil.parse(startDates[j]), DateUtil.parse(endDates[j]), titles[j], descriptions[j]));
                                    }
                                }
                                orgs.add(new Experience(new Link(name, urls[i]), positions));
                            }
                        }
                        r.setSection(type, new OrganizationSection(orgs));
                    }
                }
            }
        }
        if (fullName.trim().isEmpty()) {
            request.setAttribute("resumes", storage.getAllSorted());
        } else {
            if (isCreate) {
                storage.save(r);
            } else {
                storage.update(r);
            }
        }
        response.sendRedirect("resume");
    }
}



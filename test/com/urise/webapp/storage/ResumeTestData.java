package com.urise.webapp.storage;

import com.urise.webapp.model.*;

import java.time.Month;

public class ResumeTestData {


    public static Resume createResume(String uuid, String fullName) {
        Resume tempResume = new Resume(uuid, fullName);

        tempResume.setContact(ContactType.MAIL, "mail_" + fullName + "@mail.ru");
        tempResume.setContact(ContactType.PHONE, "+7(495)" + uuid);
        tempResume.setContact(ContactType.HOME_PHONE, "8(347)" + uuid);
        tempResume.setContact(ContactType.SKYPE, "spype_" + fullName);
        tempResume.setContact(ContactType.MOBILE, "+7963" + uuid);
        tempResume.setContact(ContactType.LINKEDIN, "myLinkedIn_" + fullName);
        tempResume.setContact(ContactType.GITHUB, "MyGitHub_" + fullName);
        tempResume.setContact(ContactType.STACKOVERFLOW, "MyStackOverflow_" + fullName);
        tempResume.setContact(ContactType.HOME_PAGE, "myHomePage_" + fullName + ".org");

        tempResume.setSection(SectionType.OBJECTIVE, new TextSection("Objective_" + uuid));
        tempResume.setSection(SectionType.PERSONAL, new TextSection("Personal_" + fullName));
        tempResume.setSection(SectionType.ACHIEVEMENT, new ListSection("Achivment_1", "Achivment_2", "Achivment_3"));
        tempResume.setSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "Python3"));
        tempResume.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Experience("Experience_" + fullName, "http://Experience_" + uuid + ".ru",
                                new Experience.Position(2015, Month.JANUARY, "position1", "content1"),
                                new Experience.Position(2010, Month.MARCH, 2015, Month.JANUARY, "position2", "content2"))));
        tempResume.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Experience("Institute", null,
                                new Experience.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Experience.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Experience("Organization12", "http://Organization12.ru")));
        tempResume.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Experience("Organization2", "http://Organization2.ru",
                                new Experience.Position(2019, Month.JANUARY, "position1", "content1"))));
        return tempResume;
    }
}

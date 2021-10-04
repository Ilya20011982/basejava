package com.urise.webapp.storage;

import com.urise.webapp.model.*;

import java.time.Month;

public class ResumeTestData {


    public static Resume createResume(String uuid, String fullName) {
        Resume tempResume = new Resume(uuid, fullName);

        tempResume.addContact(ContactType.MAIL, "mail_" + fullName + "@mail.ru");
        tempResume.addContact(ContactType.PHONE, "+7(495)" + uuid);
        tempResume.addContact(ContactType.HOME_PHONE, "8(347)" + uuid);
        tempResume.addContact(ContactType.SKYPE, "spype_" + fullName);
        tempResume.addContact(ContactType.MOBILE, "+7963" + uuid);
        tempResume.addContact(ContactType.LINKEDIN, "myLinkedIn_" + fullName);
        tempResume.addContact(ContactType.GITHUB, "MyGitHub_" + fullName);
        tempResume.addContact(ContactType.STACKOVERFLOW, "MyStackOverflow_" + fullName);
        tempResume.addContact(ContactType.HOME_PAGE, "myHomePage_" + fullName + ".org");

        tempResume.addSection(SectionType.OBJECTIVE, new TextSection("Objective_" + uuid));
        tempResume.addSection(SectionType.PERSONAL, new TextSection("Personal_" + fullName));
        tempResume.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment_1", "Achivment_2", "Achivment_3"));
        tempResume.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "Python3"));
        tempResume.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Experience("Experience_" + fullName, "http://Experience_" + uuid + ".ru",
                                new Experience.Position(2015, Month.JANUARY, "position1", "content1"),
                                new Experience.Position(2010, Month.MARCH, 2015, Month.JANUARY, "position2", "content2"))));
        tempResume.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Experience("Institute", null,
                                new Experience.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Experience.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Experience("Organization12", "http://Organization12.ru")));
        tempResume.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Experience("Organization2", "http://Organization2.ru",
                                new Experience.Position(2019, Month.JANUARY, "position1", "content1"))));
        return tempResume;
    }
}

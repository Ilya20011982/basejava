package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1, "Name1");
    ;

    private static final String UUID_2 = "uuid2";
    public static final Resume RESUME_2 = new Resume(UUID_2, "Name2");

    private static final String UUID_3 = "uuid3";
    public static final Resume RESUME_3 = new Resume(UUID_3, "Name3");

    private static final String UUID_4 = "uuid4";
    public static final Resume RESUME_4 = new Resume(UUID_4, "Name4");

    static {
        RESUME_1.addContact(ContactType.MAIL, "mail1@mail.ru");
        RESUME_1.addContact(ContactType.PHONE, "891156456");
        RESUME_1.addSection(SectionType.OBJECTIVE, new TextSection("Objective_1"));
        RESUME_1.addSection(SectionType.PERSONAL, new TextSection("Personal_1"));
        RESUME_1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment_1", "Achivment_2", "Achivment_3"));
        RESUME_1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "Python3"));
        RESUME_1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Experience("Experience_1", "http://Experience_1.ru",
                                new Experience.Position(2015, Month.JANUARY, "position1", "content1"),
                                new Experience.Position(2010, Month.MARCH, 2015, Month.JANUARY, "position2", "content2"))));
        RESUME_1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Experience("Institute", null,
                                new Experience.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Experience.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Experience("Organization12", "http://Organization12.ru")));
        RESUME_2.addContact(ContactType.SKYPE, "skype2");
        RESUME_2.addContact(ContactType.PHONE, "22222");
        RESUME_1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Experience("Organization2", "http://Organization2.ru",
                                new Experience.Position(2019, Month.JANUARY, "position1", "content1"))));
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1, "NewName");
        storage.update(resume);
        assertSame(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.get("UUID_4");
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotFound() {
        storage.delete(UUID_4);
    }

    @Test
    public void getAll() {
        List<Resume> expectedResumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        assertEquals(expectedResumes, storage.getAllSorted());
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}
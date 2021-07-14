package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    public Resume[] storage = new Resume[10000];
    private int size = 0;
    int currentIndex;
    private final String error1 = " - ERROR: resume was not found in the database!";

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    private int findResumeInDatabase(String uuid) {
        int i = 0;
        while (i < size && !storage[i].getUuid().equals(uuid)) i++;
        currentIndex = i;
        return currentIndex;
    }

    private boolean resumeIsNotFound (int currentIndex) {
        return storage[currentIndex] == null;
    }

    public void update(Resume r) {
        if (resumeIsNotFound(findResumeInDatabase(r.getUuid()))) {
            System.out.println("Update resume impossible " + r.getUuid() + error1);
        } else {
            storage[currentIndex] = r;
            System.out.println("Resume " + r.getUuid() + " is update!");
        }
    }

    public void save(Resume r) {
        if (size >= storage.length) {
            System.out.println("Save resume impossible " + r.getUuid() + " - ERROR: the database is full!");
            return;
        }
        if (!resumeIsNotFound(findResumeInDatabase(r.getUuid()))) {
            System.out.println("Save resume impossible " + r.getUuid() + " - ERROR: resume is already in the database!");
            return;
        }
        if (resumeIsNotFound(findResumeInDatabase(r.getUuid()))) {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        if (resumeIsNotFound(findResumeInDatabase(uuid))) {
            System.out.println("Get resume impossible " + uuid + error1);
        }
        return storage[currentIndex];
    }

    public void delete(String uuid) {
        System.out.println(uuid);
        if (resumeIsNotFound(findResumeInDatabase(uuid))) {
            System.out.println("Delete resume impossible " + uuid + error1);
            return;
        }
        storage[currentIndex] = storage[size - 1];
        storage[size - 1] = null;
        size--;

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}

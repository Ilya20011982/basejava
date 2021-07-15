package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    public Resume[] storage = new Resume[3];
    private int size = 0;
    private int currentIndex;
    private final String error1 = " - ERROR: resume was not found in the database!";

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        String uuid = r.getUuid();
        if (isResumeNotFound(findResumeInDatabase(uuid))) {
            System.out.println("Update resume impossible " + uuid + error1);
        } else {
            storage[currentIndex] = r;
            System.out.println("Resume " + uuid + " is update!");
        }
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        if (size >= storage.length) {
            System.out.println("Save resume impossible " + uuid + " - ERROR: the database is full!");
            return;
        }
        if (!isResumeNotFound(findResumeInDatabase(uuid))) {
            System.out.println("Save resume impossible " + uuid + " - ERROR: resume is already in the database!");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        if (isResumeNotFound(findResumeInDatabase(uuid))) {
            System.out.println("Get resume impossible " + uuid + error1);
            return null;
        }
        return storage[currentIndex];
    }

    public void delete(String uuid) {
        if (isResumeNotFound(findResumeInDatabase(uuid))) {
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

    private int findResumeInDatabase(String uuid) {
        int i = 0;
        while (i < size && !storage[i].getUuid().equals(uuid)) i++;
        if (i >= size) {
            i = -1;
        }
        currentIndex = i;
        return currentIndex;
    }

    private boolean isResumeNotFound(int currentIndex) {
        return currentIndex < 0;
    }
}

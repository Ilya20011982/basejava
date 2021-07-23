package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        int i = 0;
        while (i < size && !storage[i].getUuid().equals(uuid)) i++;
        if (i >= size) i = -1;
        return i;
    }

    @Override
    protected void insertResume(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void fillDeleteResume(int index) {
        storage[index] = storage[size - 1];
    }
}

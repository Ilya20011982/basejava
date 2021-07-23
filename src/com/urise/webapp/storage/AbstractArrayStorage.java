package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Update resume impossible " + r.getUuid());
        } else {
            storage[index] = r;
            System.out.println("Resume " + r.getUuid() + " is update!");
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        String uuid = r.getUuid();
        if (size >= STORAGE_LIMIT) {
            System.out.println("Save resume impossible " + uuid + " - ERROR: the database is full!");
            return;
        }
        if (index > 0) {
            System.out.println("Save resume impossible " + uuid + " - resume is already in the database!");
        } else {
            insertResume(r, index);
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Delete resume impossible " + uuid + " not exist");
            return;
        }
        fillDeleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume r, int index);

    protected abstract void fillDeleteResume(int index);
}

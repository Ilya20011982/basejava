package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        storage[(int) index] = resume;
    }

    @Override
    public void doSave(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } 
        insertResume(resume, (int) index);
        size++;        
    }

    @Override
    public Resume doGet(Object index) {
        return storage[(int) index];
    }

    @Override
    public void doDelete(Object index) {
        fillDeleteResume((int) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExist(Object index) {
        return (int) index >= 0;
    }

    @Override
    public List<Resume> doAllCopy() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    public int size() {
        return size;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void fillDeleteResume(int index);
}

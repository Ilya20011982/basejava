package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void doUpdate(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    public void doSave(Resume resume, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } 
        insertResume(resume, index);
        size++;        
    }

    @Override
    public Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    public void doDelete(Integer index) {
        fillDeleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    @Override
    public List<Resume> doAllCopy() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public int size() {
        return size;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void fillDeleteResume(int index);
}

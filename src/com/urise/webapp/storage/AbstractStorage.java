package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object desiredValue = getNotExistDesiredValue(resume.getUuid());
        doUpdate(resume, desiredValue);
    }

    public void save(Resume resume) {
        Object desiredValue = getExistDesiredValue(resume.getUuid());
        doSave(resume, desiredValue);
    }


    public Resume get(String uuid) {
        Object desiredValue = getNotExistDesiredValue(uuid);
        return doGet(desiredValue);
    }

    public void delete(String uuid) {
        Object desiredValue = getNotExistDesiredValue(uuid);
        doDelete(desiredValue);
    }

    private Object getNotExistDesiredValue(String uuid) {
        Object desiredValue = getDesiredValue(uuid);
        if (!isExistStorage(desiredValue)) {
            throw new NotExistStorageException(uuid);
        }
        return desiredValue;
    }

    private Object getExistDesiredValue(String uuid) {
        Object desiredValue = getDesiredValue(uuid);
        if (isExistStorage(desiredValue)) {
            throw new ExistStorageException(uuid);
        }
        return desiredValue;
    }

    protected abstract boolean isExistStorage(Object desiredValue);

    protected abstract Object getDesiredValue(String uuid);

    protected abstract void doUpdate(Resume resume, Object desiredValue);

    protected abstract void doSave(Resume resume, Object desiredValue);

    protected abstract Resume doGet(Object desiredValue);

    protected abstract void doDelete(Object desiredValue);


}


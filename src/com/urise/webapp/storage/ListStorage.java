package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected void doUpdate(Resume resume, Object desiredValue) {
        list.set((Integer) desiredValue, resume);
    }

    @Override
    protected void doSave(Resume resume, Object desiredValue) {
        list.add(resume);
    }

    @Override
    protected Resume doGet(Object desiredValue) {
        return list.get((Integer) desiredValue);
    }

    @Override
    protected void doDelete(Object desiredValue) {
        list.remove(((Integer) desiredValue).intValue());
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected boolean isExistStorage(Object desiredValue) {
        return desiredValue != null;
    }

    @Override
    protected Integer getDesiredValue(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)){
                return i;
            }
        }
        return null;
    }
}

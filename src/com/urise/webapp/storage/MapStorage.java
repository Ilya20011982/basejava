package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected boolean isExistStorage(Object searchKey) {
        return resumeMap.containsKey(searchKey.toString());
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        resumeMap.put(searchKey.toString(), resume);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return resumeMap.get(searchKey.toString());
    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeMap.remove(searchKey.toString());
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public Resume[] getAll() {
        return resumeMap.values().toArray(new Resume[resumeMap.size()]);

    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUUIDStorage extends AbstractStorage {
    private Map<String, Resume> uuidMap = new TreeMap<>();

    @Override
    protected boolean isExist(Object searchKey) {
        return uuidMap.containsKey(searchKey.toString());
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        uuidMap.put(searchKey.toString(), resume);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        uuidMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return uuidMap.get(searchKey.toString());
    }

    @Override
    protected void doDelete(Object searchKey) {
        uuidMap.remove(searchKey.toString());
    }

    @Override
    public void clear() {
        uuidMap.clear();
    }

    @Override
    public List<Resume> doAllCopy() {
        return new ArrayList<>(uuidMap.values());
    }

    @Override
    public int size() {
        return uuidMap.size();
    }
}

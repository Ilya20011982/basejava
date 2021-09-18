package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {
    private Map<String, Resume> storageMapUuid = new TreeMap<>();

    @Override
    protected boolean isExist(String searchKey) {
        return storageMapUuid.containsKey(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume resume, String searchKey) {
        storageMapUuid.put(searchKey, resume);
    }

    @Override
    protected void doSave(Resume resume, String searchKey) {
        storageMapUuid.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return storageMapUuid.get(searchKey);
    }

    @Override
    protected void doDelete(String searchKey) {
        storageMapUuid.remove(searchKey);
    }

    @Override
    public void clear() {
        storageMapUuid.clear();
    }

    @Override
    public List<Resume> doAllCopy() {
        return new ArrayList<>(storageMapUuid.values());
    }

    @Override
    public int size() {
        return storageMapUuid.size();
    }
}

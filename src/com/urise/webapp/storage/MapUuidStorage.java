package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {
    private Map<String, Resume> StorageMapUuid = new TreeMap<>();

    @Override
    protected boolean isExist(Object searchKey) {
        return StorageMapUuid.containsKey(searchKey.toString());
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        StorageMapUuid.put(searchKey.toString(), resume);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        StorageMapUuid.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return StorageMapUuid.get(searchKey.toString());
    }

    @Override
    protected void doDelete(Object searchKey) {
        StorageMapUuid.remove(searchKey.toString());
    }

    @Override
    public void clear() {
        StorageMapUuid.clear();
    }

    @Override
    public List<Resume> doAllCopy() {
        return new ArrayList<>(StorageMapUuid.values());
    }

    @Override
    public int size() {
        return StorageMapUuid.size();
    }
}

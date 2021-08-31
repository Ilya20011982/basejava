package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapResumeStorage extends AbstractStorage {
    private Map<String, Resume> StorageMapResume = new TreeMap<>();

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return StorageMapResume.get(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Object resume) {
        StorageMapResume.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Resume r, Object resume) {
        StorageMapResume.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected void doDelete(Object resume) {
        StorageMapResume.remove(((Resume)resume).getUuid());
    }

    @Override
    public void clear() {
        StorageMapResume.clear();
    }

    @Override
    public List<Resume> doAllCopy() {
        return new ArrayList<>(StorageMapResume.values());
    }

    @Override
    public int size() {
        return StorageMapResume.size();
    }
}

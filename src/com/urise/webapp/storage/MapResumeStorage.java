package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private Map<String, Resume> storageMapResume = new TreeMap<>();

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storageMapResume.get(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Resume resume) {
        storageMapResume.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Resume r, Resume resume) {
        storageMapResume.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Resume resume) {
        return resume;
    }

    @Override
    protected void doDelete(Resume resume) {
        storageMapResume.remove(resume.getUuid());
    }

    @Override
    public void clear() {
        storageMapResume.clear();
    }

    @Override
    public List<Resume> doAllCopy() {
        return new ArrayList<>(storageMapResume.values());
    }

    @Override
    public int size() {
        return storageMapResume.size();
    }
}

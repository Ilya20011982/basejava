package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = (r1, r2) -> r1.getUuid().compareTo(r2.getUuid());

    @Override
    public Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "smth");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void insertResume(Resume resume, int index) {
        int insertIndex = -index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = resume;
    }

    @Override
    protected void fillDeleteResume(int index) {
        int shift = size - index - 1;
        if (shift > 0) {
            System.arraycopy(storage, index + 1, storage, index, shift);
        }
    }
}

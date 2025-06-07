package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.arraycopy;
import static java.util.Arrays.binarySearch;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(List.of(Arrays.copyOf(storage, size())));
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        int insertIdx = -(Integer) searchKey - 1;
       System.arraycopy(storage, insertIdx, storage, insertIdx + 1, resumeQuantity - insertIdx);
        storage[insertIdx] = r;
    }


    @Override
    protected void doDelete(Object searchKey) {
        int index = (Integer) searchKey;
        int remPos = resumeQuantity - index - 1;
        if (remPos >= 0) {
            arraycopy(storage, index + 1, storage, index, remPos);
            resumeQuantity--;
        }
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage[(Integer) searchKey] = r;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }


    @Override
    public boolean isExisting(Object searchKey) {
        return searchKey != null && (Integer) searchKey >= 0;
    }


    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return binarySearch(storage, 0, resumeQuantity, searchKey);
    }
}


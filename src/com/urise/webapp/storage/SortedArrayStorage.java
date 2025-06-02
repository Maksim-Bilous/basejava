package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import static java.lang.System.arraycopy;
import static java.util.Arrays.binarySearch;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doSave(Resume r, Object searchKey) {
        int insertPos = -(Integer) searchKey - 1;
        System.arraycopy(storage, insertPos, storage, insertPos + 1, resumeQuantity - insertPos);
        storage[insertPos] = r;
        resumeQuantity++;
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


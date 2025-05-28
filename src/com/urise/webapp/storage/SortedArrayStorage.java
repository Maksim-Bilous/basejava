package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.System.arraycopy;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doSave(Resume r, Object searchKey) {
        int index = ((Integer) searchKey);
        int insertPos = -index - 1;
        System.arraycopy(storage, insertPos, storage, insertPos + 1 , resumeQuantity - insertPos);
        storage[insertPos] = r;
    }

    @Override
    protected void doDelete(Object searchKey) {
        int index = (Integer) searchKey;
        int remPos = resumeQuantity - index - 1;
        if (remPos > 0) {
            arraycopy(storage, index + 1, storage, index, remPos);
        }
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {

    }

    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }

    @Override
    public int findIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, resumeQuantity, searchKey, Comparator.comparing(Resume::getUuid));
    }

    @Override
    public boolean isExisting(Object searchKey) {
        return false;
    }

    @Override
    public boolean isExist(Object searchKey) {

        return false;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return null;
    }
}


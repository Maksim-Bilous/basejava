package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage[resumeQuantity] = r;
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage[(int) searchKey] = storage[resumeQuantity - 1];
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {

    }

    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }

    protected int findIndex(String uuid) {
        for (int i = 0; i < resumeQuantity; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
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


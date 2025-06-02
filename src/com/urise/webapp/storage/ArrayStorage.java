package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage[resumeQuantity++] = r;
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage[(int) searchKey] = storage[resumeQuantity - 1];
        resumeQuantity--;
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
        for (int i = 0; i < resumeQuantity; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}


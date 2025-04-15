package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void remove(String uuid, int index) {
        storage[index] = storage[resumeQuantity - 1];
    }

    @Override
    public void insert(Resume r, int index) {
        storage[resumeQuantity] = r;
    }

    protected int findIndex(String uuid) {
        for (int i = 0; i < resumeQuantity; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}


package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    @Override
    public void update(Resume r) {
        super.update(r);
    }

    @Override
    public Resume get(String uuid) {
        return super.get(uuid);
    }

    @Override
    public void save(Resume r) {
        super.save(r);
    }

    @Override
    public void delete(String uuid) {
        super.delete(uuid);
    }
    @Override
    public void remove(String uuid, int index) {

    }

    @Override
    public void insert(Resume r, int index) {

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


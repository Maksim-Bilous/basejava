package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void clear() {
        super.clear();
    }

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
    public Resume[] getAll() {
        return super.getAll();
    }

    @Override
    public int size() {
        return super.size();
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


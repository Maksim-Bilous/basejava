package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based com.urise.webapp.storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected int resumeQuantity = 0;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    protected void doSave(Resume r, Object searchkey) {
        if (resumeQuantity == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            doSave(r, searchkey);
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, resumeQuantity, null);
        resumeQuantity = 0;
    }



    public int size() {
        return resumeQuantity;
    }

}


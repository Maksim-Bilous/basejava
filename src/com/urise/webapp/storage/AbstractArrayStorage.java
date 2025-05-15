package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based com.urise.webapp.storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int resumeQuantity = 0;

    public void clear() {
        Arrays.fill(storage, 0, resumeQuantity, null);
        resumeQuantity = 0;
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
            System.out.println("Resume updated");

        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (findIndex(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (resumeQuantity > STORAGE_LIMIT) {
         throw new StorageException("StorageOverFlow" , r.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            insert(r, index);
            resumeQuantity++;
        }

    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            remove(index);
            storage[resumeQuantity - 1] = null;
            resumeQuantity--;

        }

    }

    /**
     * @return array, contains only Resumes in com.urise.webapp.storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, resumeQuantity);
    }

    public int size() {
        return resumeQuantity;
    }


    public abstract void remove(int index);

    public abstract void insert(Resume r, int index);

    protected abstract int findIndex(String uuid);
}


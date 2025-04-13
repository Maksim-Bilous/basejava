package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based com.urise.webapp.storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage{
    protected final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int resumeQuantity = 0;

    public void clear() {
        Arrays.fill(storage, 0, resumeQuantity, null);
        resumeQuantity = 0;
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
            System.out.println("Resume updated");
        }else {
            System.out.println("ERROR: " + r.getUuid() + " doesn't exist.");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (findIndex(uuid) == -1) {
            System.out.println("ERROR: " + uuid + " doesn't exist.");
            return null;
        }
        return storage[index];
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (resumeQuantity > STORAGE_LIMIT) {
            System.out.println("ERROR: Cannot save " + r.getUuid() + " because storage overflow.");
        } else if (index != -1) {
            System.out.println("ERROR: " + r.getUuid() + " already exist.");
        } else {
            storage[resumeQuantity] = r;
            resumeQuantity++;
        }

    }
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            storage[index] = storage[resumeQuantity - 1];
            storage[resumeQuantity - 1] = null;
            resumeQuantity--;
        } else {
            System.out.println("ERROR: " + uuid + " doesn't exist.");
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


    public abstract void remove(String uuid, int index);

    public abstract void insert(Resume r, int index);

    protected abstract int findIndex(String uuid);
}


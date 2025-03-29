package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int resumeQuantity = 0;

    public void clear() {
        Arrays.fill(storage, 0, resumeQuantity, null);
        resumeQuantity = 0;
    }

    public void update(Resume r) {
        if (findIndex(r.getUuid()) != -1) {
            System.out.println("Resume updated");
        } else {
            System.out.println("ERROR: " + r.getUuid() + " doesn't exist.");
        }
    }

    public void save(Resume r) {
        findIndex(r.getUuid());
        if (findIndex(r.getUuid()) == -1) {
            if (resumeQuantity < storage.length) {
                storage[resumeQuantity] = r;
                resumeQuantity++;
            } else if (resumeQuantity > storage.length) {
                System.out.println("ERROR: Storage is full.");
            }
        } else {
            System.out.println("ERROR: " + r.getUuid() + " already exist");
        }
    }

    public Resume get(String uuid) {
        for (Resume resume : storage) {
            if (findIndex(uuid) != -1) {
                return resume;
            }
        }
        if (findIndex(uuid) == -1) {
            System.out.println("ERROR: " + uuid + " doesn't exist.");
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < resumeQuantity; i++) {
            if (findIndex(uuid) == i) {
                storage[i] = storage[resumeQuantity - 1];
                storage[resumeQuantity - 1] = null;
                resumeQuantity--;
                break;
            } else if (findIndex(uuid) == -1) {
                System.out.println("ERROR: " + uuid + " doesn't exist.");
            }
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

    public int findIndex(String uuid) {
        for (int i = 0; i < resumeQuantity; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}


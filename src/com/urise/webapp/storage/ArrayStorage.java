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
        int index = findIndex(r.getUuid());
        if (index != -1) {
            storage[index] = null;
            storage[index] = r;
            System.out.println("Resume updated");
        }else {
            System.out.println("ERROR: " + r.getUuid() + " doesn't exist.");
        }
    }

    public void save(Resume r) {
        int saveIndex = findIndex(r.getUuid());
        if (resumeQuantity > STORAGE_LIMIT) {
            System.out.println("ERROR: Cannot save " + r.getUuid() + " because storage is full.");
        } else if (saveIndex != -1) {
            System.out.println("ERROR: " + r.getUuid() + " already exist.");
        } else {
            storage[resumeQuantity] = r;
            resumeQuantity++;
        }
    }

    public Resume get(String uuid) {
        if (findIndex(uuid) != -1) {
            System.out.println(uuid);
        } else {
            System.out.println("ERROR: " + uuid + " doesn't exist.");
        }
        return null;
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

    public int findIndex(String uuid) {
        for (int i = 0; i < resumeQuantity; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}


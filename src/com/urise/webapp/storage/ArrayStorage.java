package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int resumeQuantity = 0;

    public void clear() {
        Arrays.fill(storage, 0, resumeQuantity, null);
        resumeQuantity = 0;
    }

    public void update(Resume r) {
        boolean isExist = false;
        for (int i = 0; i < resumeQuantity; i++) {
            if (r.getUuid().equals(storage[i].getUuid())) {
                System.out.println("Resume updated");
                isExist = true;
                break;
            }
        }
        if (!isExist){
            System.out.println("ERROR: " + r.getUuid() + " doesn't exist." );
        }
    }

    public void save(Resume r) {
        for (int i = 0; i < resumeQuantity; i++) {
            if (r.getUuid().equals(storage[i].getUuid())) {
                System.out.println("ERROR: " + r.getUuid() + " already exist.");
                delete(r.getUuid());
            }
        }
        if (resumeQuantity < storage.length) {
            storage[resumeQuantity] = r;
            resumeQuantity++;
        } else if (resumeQuantity > storage.length) {
            System.out.println("ERROR: Storage is full.");
        }

    }

    public Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null) {
                if (uuid.equals(resume.getUuid())) {
                    return resume;
                }
            } else {
                System.out.println("ERROR: " + uuid + " doesn't exist.");
                break;
            }
        }
        return null;
    }

    public void delete(String uuid) {
        boolean isFound = false;
        for (int i = 0; i < resumeQuantity; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                storage[i] = storage[resumeQuantity - 1];
                storage[resumeQuantity - 1] = null;
                resumeQuantity--;
                isFound = true;
                break;
            }
        }
        if (!isFound) {
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

}


package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void delete(String uuid) {

    }


    public void insert(Resume r, int index) {
        if (index < 0) {
            int insertPos = -index - 1;
            System.arraycopy(storage, insertPos, storage, insertPos + 1, resumeQuantity - insertPos);
            storage[insertPos] = r;
            resumeQuantity++;
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, resumeQuantity, searchKey);
    }
}


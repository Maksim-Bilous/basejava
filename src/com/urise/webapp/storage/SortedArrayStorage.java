package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void remove(String uuid, int index) {
        int remPos = resumeQuantity - index - 1;
            System.arraycopy(storage, index + 1, storage, index, remPos);
            resumeQuantity --;
    }

    @Override
    public void insert(Resume r, int index) {
        index = findIndex(r.getUuid());
        int insertPos = -index - 1;
        System.arraycopy(storage, insertPos, storage, insertPos + 1, resumeQuantity - insertPos);
        storage[insertPos] = r;
        resumeQuantity++;
    }

    @Override
    public int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, resumeQuantity, searchKey);
    }
}


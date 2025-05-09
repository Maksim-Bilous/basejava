package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.System.arraycopy;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void remove(int index) {
        int remPos = resumeQuantity - index - 1;
        if (remPos > 0) {
            arraycopy(storage, index + 1, storage, index, remPos);
        }

    }

    @Override
    public void insert(Resume r, int index) {
        int insertPos = -index - 1;
        System.arraycopy(storage, insertPos, storage, insertPos + 1 , resumeQuantity - insertPos);
        storage[insertPos] = r;
    }

    @Override
    public int findIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, resumeQuantity, searchKey, Comparator.comparing(Resume::getUuid));
    }
}


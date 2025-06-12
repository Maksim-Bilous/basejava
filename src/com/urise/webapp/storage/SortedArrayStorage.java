package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.arraycopy;
import static java.util.Arrays.binarySearch;

public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    protected List<Resume> getALL() {
        return new ArrayList<>(List.of(Arrays.copyOf(storage, resumeQuantity)));
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        insert(r, (Integer) searchKey);
    }


    public void insert(Resume r, int index) {
        int insertIdx = -index - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, resumeQuantity - insertIdx);
        storage[insertIdx] = r;
        resumeQuantity++;
    }


    @Override
    protected void doDelete(Object searchKey) {
        int index = (Integer) searchKey;
        int remPos = resumeQuantity - index - 1;
        if (remPos >= 0) {
            arraycopy(storage, index + 1, storage, index, remPos);
            resumeQuantity--;
        }
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage[(Integer) searchKey] = r;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }


    @Override
    public boolean isExisting(Object searchKey) {
        return searchKey != null && (Integer) searchKey >= 0;
    }


    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume("", uuid);
        return binarySearch(storage, 0, resumeQuantity, searchKey);
    }
}


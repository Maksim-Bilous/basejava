package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ListStorage extends AbstractStorage {


    public void insert(Resume r, int index) {
        if(index <= 0) {
            storage.add(r);
        }
    }

    public void remove(int index) {
        storage.remove(index);
    }

    @Override
    public int findIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return storage.indexOf(searchKey);
    }
}

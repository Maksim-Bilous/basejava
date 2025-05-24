package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public abstract class AbstractStorage implements Storage {


    protected final ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage.set(index, r);
            System.out.println("Resume updated");

        }
    }

    @Override
    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (storage.isEmpty()) {
            insert(r, index);
        } else {
            if (index >= 0 ) {
                throw new ExistStorageException(r.getUuid());
            } else {
                insert(r, index);
            }
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (findIndex(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage.get(index);
    }

    @Override
    public void delete(String uuid) {
       int index = findIndex(uuid);
       Resume r = new Resume(uuid);
       if (index < 0) {
           throw new NotExistStorageException(uuid);
       } else {
           remove(index);
       }

    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    public abstract void remove(int index);

    public abstract void insert(Resume r, int index);

    protected abstract int findIndex(String uuid);
}

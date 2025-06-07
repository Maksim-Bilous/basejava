package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.List;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void update(Resume r) {
        Object searchKey = getNotExistSearchKey(r.getFullName());
        doUpdate(r, searchKey);
        System.out.println("Resume updated");
    }

    @Override
    public final void save(Resume r) {
        Object searchKey = getExistSearchKey(r.getFullName());
        doSave(r, searchKey);
    }

    @Override
    public final Resume get(String fullName) {
        Object searchKey = getNotExistSearchKey(fullName);
        return doGet(searchKey);
    }

    @Override
    public final void delete(String fullName) {
        Object searchKey = getNotExistSearchKey(fullName);
        doDelete(searchKey);

    }

    public abstract List<Resume> getAllSorted();

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    public abstract boolean isExisting(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract Object getSearchKeyName(String fullName);



    private Object getNotExistSearchKey(String fullName) {
        Object searchKey = getSearchKeyName(fullName);
        if (!isExisting(searchKey)) {
            throw new NotExistStorageException(fullName);
        }
        return searchKey;
    }

    private Object getExistSearchKey(String fullName) {
        Object searchKey = getSearchKeyName(fullName);
        if (isExisting(searchKey)) {
            throw new ExistStorageException(fullName);
        }
        return searchKey;
    }

}


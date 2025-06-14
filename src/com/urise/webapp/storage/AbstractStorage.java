package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public abstract class AbstractStorage implements Storage {

    public static final Comparator<Resume> RESUME_COMPARATOR = Comparator.nullsLast(Comparator.comparing(Resume::getUuid).thenComparing(Resume::getFullName));

    @Override
    public final void update(Resume r) {
        Object searchKey = getNotExistSearchKey(r.getUuid());
        doUpdate(r, searchKey);
        System.out.println("Resume updated");
    }

    @Override
    public final void save(Resume r) {
        Object searchKey = getExistSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        Object searchKey = getNotExistSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public final void delete(String uuid) {
        Object searchKey = getNotExistSearchKey(uuid);
        doDelete(searchKey);

    }

    public final List<Resume> getAllSorted() {
        List<Resume> sortedStorage = getALL();
        sortedStorage.sort(RESUME_COMPARATOR);
        return sortedStorage;

    }

    protected abstract List<Resume> getALL();

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    public abstract boolean isExisting(Object searchKey);

    protected abstract Object getSearchKey(String uuid);


    private Object getNotExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExisting(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExisting(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

}


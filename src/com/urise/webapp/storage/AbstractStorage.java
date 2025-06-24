package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract List<Resume> getALL();

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    public abstract boolean isExisting(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    public static final Comparator<Resume> RESUME_COMPARATOR = Comparator.nullsLast(Comparator.comparing(Resume::getUuid).thenComparing(Resume::getFullName));

    @Override
    public final void update(Resume r) {
        LOG.info("Update " + r);
        SK searchKey = getNotExistSearchKey(r.getUuid());
        doUpdate(r, searchKey);
        System.out.println("Resume updated");
    }

    @Override
    public final void save(Resume r) {
        LOG.info("Save " + r);
        SK searchKey = getExistSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getNotExistSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getNotExistSearchKey(uuid);
        doDelete(searchKey);

    }

    public final List<Resume> getAllSorted() {
        LOG.info("Get All Sorted ");
        List<Resume> sortedStorage = getALL();
        sortedStorage.sort(RESUME_COMPARATOR);
        return sortedStorage;

    }


    private SK getNotExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExisting(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExisting(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

}


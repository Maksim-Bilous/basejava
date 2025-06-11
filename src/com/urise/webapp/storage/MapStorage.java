package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private final Map<Object, Resume> mapStorage = new HashMap<>();


    @Override
    public List<Resume> getAllSorted() {
        List<Resume> storageSorted = new ArrayList<>(mapStorage.values());
        storageSorted.sort(Comparator.comparing(Resume :: getUuid).thenComparing(Resume :: getFullName));
        return storageSorted;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        mapStorage.put(searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        mapStorage.remove(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        mapStorage.put(searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return mapStorage.get(searchKey);
    }

    @Override
    public boolean isExisting(Object searchKey) {
        return mapStorage.containsKey(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }
}

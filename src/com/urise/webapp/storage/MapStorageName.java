package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorageName extends AbstractStorage{
    private final Map<Object, Resume> mapName = new HashMap<>();


    @Override
    public List<Resume> getAllSorted() {
        List<Resume> storageSorted = new ArrayList<>(mapName.values());
        storageSorted.sort(Comparator.comparing(Resume :: getUuid).thenComparing(Resume :: getFullName));
        return storageSorted;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        mapName.put(searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        mapName.remove(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        mapName.put(searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return mapName.get(searchKey);
    }

    @Override
    public boolean isExisting(Object searchKey) {
        return mapName.containsKey(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    public int size() {
        return mapName.size();
    }

    @Override
    public void clear() {
        mapName.clear();
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorageName extends AbstractStorage {
    private final Map<String, Resume> mapName = new HashMap<>();


    @Override
    protected List<Resume> getALL() {
        return new ArrayList<>(mapName.values());
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        mapName.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        mapName.remove((String) searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        mapName.replace(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return mapName.get((String) searchKey);
    }

    @Override
    public boolean isExisting(Object searchKey) {
        return searchKey != null && mapName.containsKey((String) searchKey);
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

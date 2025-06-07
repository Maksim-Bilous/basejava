package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorageName extends AbstractStorage{
    private final Map<Object, Resume> mapName = new HashMap<>();


    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(mapName.values());
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
    protected Object getSearchKeyName(String fullName) {
        return fullName;
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

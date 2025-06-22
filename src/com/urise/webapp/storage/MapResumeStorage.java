package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<String> {
    private final Map<String, Resume> mapName = new HashMap<>();


    @Override
    protected List<Resume> getALL() {
        return new ArrayList<>(mapName.values());
    }

    @Override
    protected void doSave(Resume r, String searchKey) {
        mapName.put(searchKey, r);
    }

    @Override
    protected void doDelete(String searchKey) {
        mapName.remove(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, String searchKey) {
        mapName.replace(searchKey, r);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return mapName.get(searchKey);
    }

    @Override
    public boolean isExisting(String searchKey) {
        return mapName.get(searchKey) != null;
    }

    @Override
    protected String getSearchKey(String uuid) {
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

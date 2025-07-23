package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serialize.ObjectStreamSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {

    private final File directory;

    private final ObjectStreamSerializer streamSerialize;

    protected FileStorage(File directory, ObjectStreamSerializer streamSerialize) {
        Objects.requireNonNull(directory, "directory must not be null");

        this.streamSerialize = streamSerialize;
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writeable");
        }
        this.directory = directory;
    }

    @Override
    protected List<Resume> getALL() {
        File[] allFiles = directory.listFiles();
        List<Resume> resumes = new ArrayList<>();
        for (File file : allFiles) {
            resumes.add(doGet(file));
        }
        return resumes;
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("IO Error1", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        try {
            file.delete();
        } catch (Exception e) {
            throw new StorageException("IO Error2", file.getName(), e);
        }
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            streamSerialize.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
            System.out.println("File Updated");
        } catch (IOException e) {
            throw new StorageException("IO Error3", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return streamSerialize.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO Error4", file.getName(), e);
        }
    }

    @Override
    public boolean isExisting(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        Objects.requireNonNull(files);
        for (File file : files) {
            if (file.isFile()) {
                doDelete(file);
            }
        }
    }

    @Override
    public int size() {
        File[] files = directory.listFiles();
        Objects.requireNonNull(files);
        return files.length;
    }
}

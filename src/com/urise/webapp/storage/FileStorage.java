package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializer.ObjectStreamSerializer;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return getFilesList().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doUpdate(r, file);
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
        getFilesList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    private Stream<File> getFilesList() {
        return Arrays.stream(Objects.requireNonNull(directory.listFiles()));
    }
}

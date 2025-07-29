package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serialize.ObjectStreamSerializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private final Path directory;

    private final ObjectStreamSerializer streamSerializer;

    protected PathStorage(String dir, ObjectStreamSerializer streamSerializer) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        this.streamSerializer = streamSerializer;
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Path Path) {
        try {
            streamSerializer.doWrite(r, new BufferedOutputStream(Files.newOutputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("Error update Resume", null, e);
        }
    }

    @Override
    public boolean isExisting(Path Path) {
        return Files.isRegularFile(Path);
    }

    @Override
    protected void doSave(Resume r, Path Path) {
        try {
            Files.createFile(Path);
            doUpdate(r, Path);
        } catch (IOException e) {
            throw new StorageException("Error save R", null, e);
        }
    }

    @Override
    protected Resume doGet(Path Path) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(Files.newInputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("Error get R", null, e);
        }
    }

    @Override
    protected void doDelete(Path Path) {
        try {
            Files.delete(Path);
        } catch (IOException e) {
            throw new StorageException("Error delete R ", null, e);
        }
    }

    protected List<Resume> getALL() {
        return getFilesList().map(this::doGet).collect(Collectors.toList());
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", null, e);
        }
    }

}

package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {

    private final Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory or is not writable");
        }
    }

    @Override
    protected List<Resume> getALL() {
        List<Resume> resumeList = new ArrayList<>();
        return null;
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            doWrite(r, (OutputStream) path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            doWrite(r, new BufferedOutputStream((OutputStream) path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
          return doRead((InputStream) path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isExisting(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Path.of(uuid);
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error" , null);
        }
    }

    @Override
    public int size() {
        return 0;
    }

    protected abstract void doWrite(Resume r, OutputStream path) throws IOException;

    protected abstract Resume doRead(InputStream path) throws IOException;
}

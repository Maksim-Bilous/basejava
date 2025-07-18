package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {

    private final Path directory;

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        String[] list = directory.toFile().list();
        if (list == null) {
            throw new StorageException("Directory read error", null);
        }
        return list.length;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Path.of(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Path Path) {
        try{
            doWrite(r, (OutputStream) Path);
            System.out.println("Path Updated");
        } catch (IOException e) {
            throw new StorageException("Error update Resume" , null, e);
        }
    }

    @Override
    public boolean isExisting(Path Path) {
        return Files.exists(directory);
    }

    @Override
    protected void doSave(Resume r, Path Path) {
        try {
            doWrite(r, (OutputStream) Path);
        } catch (IOException e) {
            throw new StorageException("Error save R ", null, e);
        }
    }

    @Override
    protected Resume doGet(Path Path) {
        try {
            return doRead((InputStream) Path);
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
        try(Stream<Path> walk = Files.list(Paths.get(String.valueOf(directory)))) {
            return (List<Resume>) walk;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

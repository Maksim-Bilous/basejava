package com.urise.webapp.storage;

import com.urise.webapp.ResumeTestData;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;


public abstract class AbstractStorageTest {
    protected final Storage storage;
    protected static final Resume[] emptyStorage = new Resume[0];
    protected static final File STORAGE_DIR = new File("C:\\basejava\\basejava\\storage");

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    protected static final Resume r1;
    protected static final Resume r2;
    protected static final Resume r3;
    protected static final Resume r4;

    static {
        r4 = ResumeTestData.filledResume(UUID_4, "Grigori Kislin");
        r2 = new Resume("Name2", UUID_2);
        r3 = new Resume("Name3", UUID_3);
        r1 = new Resume("Name1", UUID_1);


    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);

    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume("New Name", UUID_3 );
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_3));
    }

    @Test
    public void get() throws Exception {
        assertGet(r1);
        assertGet(r2);
        assertGet(r3);
    }

    @Test
    public void save() throws Exception {
        storage.save(r4);
        assertGet(r4);

    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_2);
        assertSize(2);
        storage.get(UUID_2);

    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();

    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }


    public void assertSize(int size) throws Exception {
        assertEquals(size, storage.size());
    }

    public void assertGet(Resume resume) throws Exception {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

}
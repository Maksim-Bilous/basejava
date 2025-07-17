package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.assertEquals;


public abstract class AbstractStorageTest {
    private final Storage storage;
    protected static final Resume[] emptyStorage = new Resume[0];

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    protected static final Resume r1;
    protected static final Resume r2;
    protected static final Resume r3;
    protected static final Resume r4;

    static {
        r1 = new Resume("Grigoriy Kislin" , UUID_1);
        r2 = new Resume("Name2", UUID_2);
        r3 = new Resume("Name3", UUID_3);
        r4 = new Resume("Name4", UUID_4);


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
        storage.update(r3);
        Assert.assertSame(r3, storage.get(UUID_3));
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
        assertSize(4);

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
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(r1, r2, r3));
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void saveFlowTest() {
        try {
            storage.clear();
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException e) {
            Assert.fail("StorageException выброшено раньше врeмени.");
        }
        storage.save(new Resume("uuid10001"));
    }


    public void assertSize(int size) throws Exception {
        assertEquals(size, storage.size());
    }

    public void assertGet(Resume resume) throws Exception {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

}
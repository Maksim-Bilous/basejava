package com.urise.webapp.storage;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    protected static final Resume[] emptyStorage = new Resume[0];
    protected static final Resume[] expected = new Resume[3];

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    protected static final Resume r1;
    protected static final Resume r2;
    protected static final Resume r3;
    protected static final Resume r4;

    static {
        r1 = new Resume(UUID_1);
        r2 = new Resume(UUID_2);
        r3 = new Resume(UUID_3);
        r4 = new Resume(UUID_4);
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
        Assert.assertEquals(0, storage.size());
        assertSize(0);
        Assert.assertArrayEquals(emptyStorage, storage.getAll());

    }

    @Test
    public void update() throws Exception {
        storage.update(r3);
        Assert.assertSame(r3, storage.get(UUID_3));
    }

    @Test
    public void get() throws Exception {
        assertGet(new Resume(UUID_1));
        assertGet(new Resume(UUID_2));
        assertGet(new Resume(UUID_3));
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
        storage.get(UUID_2);
        assertSize(2);

    }

    @Test
    public void getAll() throws Exception {
        expected[0] = r1;
        expected[1] = r2;
        expected[2] = r3;
        Assert.assertArrayEquals(expected, storage.getAll());
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void overFlowTest() {
        try {
            storage.clear();
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
                if (storage.size() > STORAGE_LIMIT) {
                    Assert.fail("StorageException выброшено раньше врeмени.");
                }
            }
            storage.save(new Resume("uuid10001"));
        } catch (StorageException e) {
            System.out.println("Тест не пройден " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Тест пройден " + e.getMessage());
        }
    }


    public void assertSize(int size) throws Exception {
        Assert.assertEquals(size, storage.size());
    }

    public void assertGet(Resume resume) throws Exception {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

}
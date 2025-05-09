package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());

    }

    @Test
    public void update() throws Exception {
        storage.update(new Resume(UUID_3));
    }

    @Test
    public void get() throws Exception {
       Resume resume = storage.get(UUID_1);
    }

    @Test
    public void save() throws Exception {
        storage.save(new Resume(UUID_4));
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_3);
    }

    @Test
    public void getAll() throws Exception {
        storage.getAll();
    }

    @Test
    public void size() throws Exception {
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception{
        storage.get("dummy");
    }

    @Test
    public void overFlowTest() {
        final int STORAGE_LIMIT = 10000;
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }

            storage.save(new Resume("uuid10001"));

            Assert.fail("Переполнение должно было произойти, но исключение не выброшено!");

        } catch (StorageException e){
            System.out.println("Тест не пройден " +e.getMessage());
        } catch (Exception e) {
            System.out.println("Тест  пройден " + e.getMessage());
        }
    }


}
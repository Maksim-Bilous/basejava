package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }
    public static ArrayStorage storage = new ArrayStorage();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    @Before
    public void setUp() throws Exception {
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void insert() throws Exception{
        storage.save(new Resume(UUID_4));
    }

    @Test
    public void remove() throws Exception{
        storage.delete(UUID_2);
    }

}
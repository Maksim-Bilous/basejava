package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    public static final ArrayStorage storage = new ArrayStorage();

    private static final String UUID_4 = "uuid4";


    @Test
    public void insert() throws Exception {
        storage.save(new Resume(UUID_4));
    }

    @Test
    public void remove() throws Exception {
        storage.delete(UUID_4);
    }

}
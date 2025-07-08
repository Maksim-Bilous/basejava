package com.urise.webapp.storage;

import org.junit.Test;

public class ArrayStorageTest extends AbstractStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    public static final ArrayStorage storage = new ArrayStorage();

    private static final String UUID_4 = "uuid4";


    @Test
    public void Save() throws Exception {
        storage.save(r4);
    }

    @Test
    public void Delete() throws Exception {
        storage.delete(r1.getUuid());
    }

}
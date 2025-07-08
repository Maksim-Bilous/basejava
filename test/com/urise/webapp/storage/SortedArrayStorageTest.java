package com.urise.webapp.storage;

import org.junit.Test;

public class SortedArrayStorageTest extends AbstractStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    public static final SortedArrayStorage storage = new SortedArrayStorage();

    private static final String UUID_4 = "uuid4";


    @Test
    public void save() throws Exception {
        storage.save(r4);
    }

    @Test
    public void delete() throws Exception {
        storage.delete(r3.getUuid());
    }

}
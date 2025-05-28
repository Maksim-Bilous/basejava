package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Test;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    public static final SortedArrayStorage storage = new SortedArrayStorage();

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
package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
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

    protected static final Resume R1;
    protected static final Resume R2;
    protected static final Resume R3;
    protected static final Resume R4;

    static {
        R1 = new Resume(UUID_1, "Grigory Kislin");
        R2 = new Resume(UUID_2, "Maksim Bilous");
        R3 = new Resume(UUID_3, "Violetta Bilous");
        R4 = new Resume(UUID_4, "Name1");

        R1.setContacts(ContactType.MAIL, "mail1@ya.ru");
        R1.setContacts(ContactType.PHONE, "11111");
        R1.setSections(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.setSections(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.setSections(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        R1.setSections(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));

    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);

    }

    @Test
    public void update() throws Exception {
        Resume resume3 = new Resume(UUID_3, "new Name" );
        storage.update(resume3);
        assertEquals(resume3, storage.get(UUID_3));
    }

    @Test
    public void get() throws Exception {
        assertEquals(R1, storage.get(R1.getUuid()));
        assertGet(R2);
        assertGet(R3);
    }

    @Test
    public void save() throws Exception {
        storage.save(R4);
        assertSize(4);
        assertGet(R4);

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


    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

}
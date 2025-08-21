package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public abstract class AbstractStorageTest {
    protected final Storage storage;

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
        R4 = new Resume(UUID_4, "New name");


        List<Period> periods1 = new ArrayList<>();
        periods1.add(new Period(LocalDate.of(2022, 3,1), LocalDate.of(2024, 4, 8), "Автор Проекта" , "Создание, организация и проведение Java онлайн проектов и стажировок."));
        R4.setSections(SectionType.EDUCATION, new Organization(periods1, "BaseJava" , "https://javaops.ru/"));
        R4.setSections(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R4.setSections(SectionType.PERSONAL, new TextSection("Personal data"));
        R4.setSections(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        R4.setSections(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R4.setContacts(ContactType.MAIL, "mail1@ya.ru");
        R4.setContacts(ContactType.PHONE, "11111");
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
        assertGet(R1);
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

    private void assertGet(Resume r) throws Exception {
        assertEquals(r, storage.get(r.getUuid()));
    }

}
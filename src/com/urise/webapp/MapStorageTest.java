package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.MapResumeStorage;
import com.urise.webapp.storage.Storage;

public class MapStorageTest {
    static final Storage MAP_STORAGE = new MapResumeStorage();

    public static void main(String[] args) {
        Resume R1 = new Resume("Violetta Bilous");
        Resume R2 = new Resume("Maksim Bilous");
        Resume R3 = new Resume("Violetta Bilous");

        MAP_STORAGE.save(R1);
        MAP_STORAGE.save(R2);
        MAP_STORAGE.save(R3);

        System.out.println("Get R1: " + MAP_STORAGE.get(R1.getUuid()));
//      System.out.println("Get dummy: " + MAP_STORAGE.get("dummy"));
        System.out.println("Size: " + MAP_STORAGE.size());

        printAll();
        System.out.println("Size: " + MAP_STORAGE.size());

        MAP_STORAGE.delete(R1.getUuid());
        printAll();
        System.out.println("\nGet R2: " + MAP_STORAGE.get(R2.getUuid()));
        System.out.println("Update R2:");
        MAP_STORAGE.update(R2);
        System.out.println("Get R2: " + MAP_STORAGE.get(R2.getUuid()));

        MAP_STORAGE.clear();
        printAll();

        System.out.println("Size: " + MAP_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : MAP_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}

package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;
import com.urise.webapp.storage.Storage;

public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume R1 = new Resume("Maksim Bilous");
        Resume R2 = new Resume("Violetta Bilous");
        Resume R3 = new Resume("Maria Aleksevna");

        ARRAY_STORAGE.save(R1);
        ARRAY_STORAGE.save(R2);
        ARRAY_STORAGE.save(R3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(R1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        printAll();
        ARRAY_STORAGE.delete(R1.getUuid());
        printAll();
        System.out.println("\nGet R2: " + ARRAY_STORAGE.get(R2.getUuid()));
        System.out.println("Update R2:");
        ARRAY_STORAGE.update(R2);
        System.out.println("Get R2: " + ARRAY_STORAGE.get(R2.getUuid()));

        System.out.println("Size: " + ARRAY_STORAGE.size());

        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}

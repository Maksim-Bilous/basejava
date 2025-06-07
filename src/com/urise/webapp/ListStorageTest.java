package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ListStorage;
import com.urise.webapp.storage.Storage;

public class ListStorageTest {
    static final Storage LIST_STORAGE = new ListStorage();

    public static void main(String[] args) {
        Resume R1 = new Resume("UUID1");
        Resume R2 = new Resume("UUID2");
        Resume R3 = new Resume("UUID3");

        LIST_STORAGE.save(R1);
        LIST_STORAGE.save(R2);
        LIST_STORAGE.save(R3);

        System.out.println("Get R1: " + LIST_STORAGE.get(R1.getUuid()));
//      System.out.println("Get dummy: " + LIST_STORAGE.get("dummy"));
        System.out.println("Size: " + LIST_STORAGE.size());

        printAll();
        System.out.println("Size: " + LIST_STORAGE.size());

        LIST_STORAGE.delete(R1.getUuid());
        printAll();
        System.out.println("\nGet R2: " + LIST_STORAGE.get(R2.getUuid()));
        System.out.println("Update R2:");
        LIST_STORAGE.update(R2);
        System.out.println("Get R2: " + LIST_STORAGE.get(R2.getUuid()));

        System.out.println("Size: " + LIST_STORAGE.size());

        LIST_STORAGE.clear();
        printAll();

        System.out.println("Size: " + LIST_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : LIST_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}

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
        Resume R4 = new Resume("UUID4");


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
        LIST_STORAGE.update(R2);
        LIST_STORAGE.clear();
        printAll();

        System.out.println("Size: " + LIST_STORAGE.size());



    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : LIST_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}

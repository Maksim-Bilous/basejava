package com.urise.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>{

    // Unique identifier
    private final String uuid;
    private final String fullName;

    Map<SectionType, Section> sections = new HashMap<>();

    public void setSections(SectionType type, Section section) {
        sections.put(type, section);
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public Resume(String fullName) {
        this(fullName, UUID.randomUUID().toString());
    }


    public Resume(String fullName, String uuid) {
        this.fullName = fullName;
        this.uuid = uuid;
    }


    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public int compareTo(Resume a) {
        return this.uuid.compareTo(a.uuid);
    }

}

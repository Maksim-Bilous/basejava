package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume1 = new Resume("Grigory Kislin");

        List<Period> periods1 = new ArrayList<>();
        periods1.add(new Period(LocalDate.of(2020, 2,12), LocalDate.of(2022, 3, 10), "Автор Проекта" , "Создание, организация и проведение Java онлайн проектов и стажировок."));
        periods1.add(new Period(LocalDate.of(2022, 1,13), LocalDate.of(2023, 4, 16), "Test", "Test"));
        List<String> achievements = new ArrayList<>();
        achievements.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ");
        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");
        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");

        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");

        resume1.setSections(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume1.setSections(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume1.setSections(SectionType.ACHIEVEMENT, new ListSection(achievements));
        resume1.setSections(SectionType.QUALIFICATIONS, new ListSection(qualifications));
        resume1.setSections(SectionType.EXPERIENCE, new Organization(periods1, "Java Online Projects" , "https://javaops.ru/"));

        System.out.println(SectionType.OBJECTIVE.getTitle() + "\n" + resume1.getSection(SectionType.OBJECTIVE) + "\n");
        System.out.println(SectionType.PERSONAL.getTitle() + "\n" + resume1.getSection(SectionType.PERSONAL) + "\n");

        System.out.println(SectionType.ACHIEVEMENT.getTitle());
        System.out.println(resume1.getSection(SectionType.ACHIEVEMENT) + "\n");

        System.out.println(SectionType.QUALIFICATIONS.getTitle());
        System.out.println(resume1.getSection(SectionType.QUALIFICATIONS)  + "\n");

        System.out.println(SectionType.EXPERIENCE.getTitle());
        System.out.println(resume1.getSection(SectionType.EXPERIENCE) + "\n");


    }

    public static Resume filledResume(String uuid , String fullName) {
        Resume r1 = new Resume(uuid, fullName);
        setObjective(r1);
        setPersonal(r1);
        setAchievement(r1);
        setQualifications(r1);
        setExperience(r1);

        return r1;
    }

    private static void setEducation(Resume r1) {

    }

    private static void setExperience(Resume r1) {
        List<Period> periods1 = new ArrayList<>();
        periods1.add(new Period(LocalDate.of(2020, 2,12), LocalDate.of(2022, 3, 10), "Автор Проекта" , "Создание, организация и проведение Java онлайн проектов и стажировок."));

        r1.setSections(SectionType.EXPERIENCE, new Organization(periods1, "Java Online Projects" , "https://javaops.ru/"));
    }

    private static void setQualifications(Resume r1) {
        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");

        r1.setSections(SectionType.QUALIFICATIONS, new ListSection(qualifications));
    }

    private static void setAchievement(Resume r1) {
        List<String> achievements = new ArrayList<>();
        achievements.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ");
        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");
        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");

        r1.setSections(SectionType.ACHIEVEMENT, new ListSection(achievements));
    }

    private static void setPersonal(Resume r1) {
        r1.setSections(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
    }

    private static void setObjective(Resume r1) {
        r1.setSections(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
    }
}

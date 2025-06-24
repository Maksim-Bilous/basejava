package com.urise.webapp;

import com.urise.webapp.model.*;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume1 = new Resume("Grigory Kislin");

        List<Period> periods = new ArrayList<>();


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

        System.out.println(SectionType.OBJECTIVE.getTitle() + "\n" + resume1.getSection(SectionType.OBJECTIVE) + "\n");
        System.out.println(SectionType.PERSONAL.getTitle() + "\n" + resume1.getSection(SectionType.PERSONAL) + "\n");



        System.out.println(SectionType.ACHIEVEMENT.getTitle());
        System.out.println(resume1.getSection(SectionType.ACHIEVEMENT) + "\n");

        System.out.println(SectionType.QUALIFICATIONS.getTitle());
        System.out.println(resume1.getSection(SectionType.QUALIFICATIONS)  + "\n");

        System.out.println(SectionType.EXPERIENCE.getTitle());


    }

}

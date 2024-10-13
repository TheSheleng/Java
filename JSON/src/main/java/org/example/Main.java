package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Створення об'єкта
        Person person = new Person("John Doe", 30);

        // Серіалізація об'єкта в JSON і запис у файл
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("person.json"), person);
            System.out.println("JSON записаний у файл.");

            // Читання JSON з файлу і десеріалізація в об'єкт
            Person deserializedPerson = objectMapper.readValue(new File("person.json"), Person.class);
            System.out.println("Зчитаний JSON: " + objectMapper.writeValueAsString(deserializedPerson));
            System.out.println("Поле name: " + deserializedPerson.getName());
            System.out.println("Поле age: " + deserializedPerson.getAge());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

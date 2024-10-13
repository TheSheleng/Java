package org.example;

public class Greeting {
    // Метод для получения приветствия
    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            return "Hello, stranger!";
        }
        return "Hello, " + name + "!";
    }
}

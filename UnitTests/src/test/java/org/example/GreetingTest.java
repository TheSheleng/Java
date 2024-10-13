package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GreetingTest {
    @Test
    public void testGreet() {
        Greeting greeting = new Greeting();
        assertEquals("Hello, John!", greeting.greet("John"), "Приветствие для 'John' должно быть 'Hello, John!'");
        assertEquals("Hello, stranger!", greeting.greet(""), "Пустое имя должно возвращать 'Hello, stranger!'");
        assertEquals("Hello, stranger!", greeting.greet(null), "Null имя должно возвращать 'Hello, stranger!'");
    }
}
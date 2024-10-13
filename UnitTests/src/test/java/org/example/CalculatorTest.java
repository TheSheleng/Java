package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3), "2 + 3 должно быть 5");
        assertEquals(0, calculator.add(0, 0), "0 + 0 должно быть 0");
        assertEquals(-1, calculator.add(2, -3), "2 + (-3) должно быть -1");
    }

    @Test
    public void testSubtract() {
        Calculator calculator = new Calculator();
        assertEquals(1, calculator.subtract(3, 2), "3 - 2 должно быть 1");
        assertEquals(-2, calculator.subtract(0, 2), "0 - 2 должно быть -2");
        assertEquals(5, calculator.subtract(2, -3), "2 - (-3) должно быть 5");
    }
}
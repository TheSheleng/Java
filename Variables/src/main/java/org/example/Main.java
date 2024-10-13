package org.example;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        // Задание 1: Показать факториалы всех чисел от 0 до 35
        System.out.println("Факториали чисел від 0 до 35:");
        for (int i = 0; i <= 35; i++) {
            System.out.println(i + "! = " + factorial(i));
        }

        // Задание 2: Показать все простые числа от 3 до 10,000,000
        System.out.println("\nПрості числа від 3 до 10,000,000:");
        for (int i = 3; i <= 10_000_000; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    // Метод для вычисления факториала
    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    // Метод для проверки, является ли число простым
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0 || number % 3 == 0) return false;

        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
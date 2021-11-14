package com.example.practice_2;

public class Calculator {

    public operations operation;

    public double solve(double a, double b) {
        double result;

        switch (operation) {
            case Sum:
                result = a + b;
                break;
            case Minus:
                result = a - b;
                break;
            case Multiply:
                result = a * b;
                break;
            case Divide:
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return result;
    }

    public double factorial(double n) {
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public double square(double a) {
        return a * a;
    }

    public double sqrt(double a) {
        return Math.sqrt(a);
    }
}

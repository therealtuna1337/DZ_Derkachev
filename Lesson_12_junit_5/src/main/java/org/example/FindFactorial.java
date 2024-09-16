package org.example;

public class FindFactorial {

    public int factorialCalculator(int f) {

        int result = 1;

        for (int i = 1; i <= f; i++) {
            result *= i;
        }
        if (f == 0) {
            return 1;
        }

        if (f < 0) {
            throw new IllegalArgumentException("Факториал от отрицательного числа вычислить нельзя"); // у меня не получилось придумать способ завершить программу при вычислении факториала от отрицательного числа, кроме как возбуждением ошибки :(
        }
        return result;
    }
}
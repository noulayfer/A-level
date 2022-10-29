package com.fedorenko;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main2 {
    public static void main(String[] args) {

        System.out.println(triangleSquare(4, 5, 8));
        System.out.println(smallestAbs());
        System.out.println(randomEven());
        System.out.println(toBinary(10));
        System.out.println(toBinary2(10));



    }
    public static double triangleSquare(int a, int b, int c) {
        int ab = a + b;
        int ac = a + c;
        int bc = b + c;
        double p = (a * 1.0 + b + c)/2; // формула Герона
        double S = 0;
        if (c < ab && b < ac && a < bc){
            S = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }
        return S;
    }
    public static int smallestAbs() {
        Random random = new Random();
        int a = random.ints().findAny().getAsInt();
        int b = random.ints().findAny().getAsInt();
        int c = random.ints().findAny().getAsInt();
        int result = Math.abs(a) < Math.min(Math.abs(b), Math.abs(c)) ? Math.abs(a) : Math.min(Math.abs(b), Math.abs(c));
        return result;
    }
    public static int smallestAbs2() { // без тернарного оператора (просмотрел условие)
        Random random = new Random();
        int a = random.ints(3).map(x -> Math.abs(x)).min().getAsInt();
        return a;
    }
    public static boolean randomEven() {
        int a = new Random().ints().findAny().getAsInt();
        System.out.println(a);
        return a % 2 == 0;
    }
    public static String toBinary(int a) { // простой вариант
        String str = Integer.toBinaryString(a);
        return str;
    }
    public static String toBinary2(int a) { // вручную
        StringBuilder str = new StringBuilder();
        while (a >= 1){
            str.append(a % 2);
            a = a / 2;
        }
        return str.reverse().toString();
    }

}
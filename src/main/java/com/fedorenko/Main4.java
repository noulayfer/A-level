package com.fedorenko;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main4 {
    public static void main(String[] args) {
//        lastMax();
//        oddElementsToZero();
//        isIncreasing();
//        averageBigger();
        bubbleSort();
    }
    //1
    public static int lastMax() {
        int[] randomArray;
        Random random = new Random();
        randomArray = random.ints(12, -15, 15).toArray();
        System.out.println(Arrays.toString(randomArray));
        int max = Arrays.stream(randomArray).max().getAsInt();
        for (int i = randomArray.length - 1; i >= 0; i--){
            if (randomArray[i] == max){
                System.out.println(i);
                return i;
            }
        }
        return 0;
    }
    //2
    public static void oddElementsToZero(){
        int[] randomArray;
        Random random = new Random();
        randomArray = random.ints(8, 1, 10).toArray();
        System.out.println(Arrays.toString(randomArray));
        for (int i = 0; i < randomArray.length; i++) {
            if (i % 2 == 1){
                randomArray[i] = 0;
            }
        }
        System.out.println(Arrays.toString(randomArray));
    }
    //3
    public static boolean isIncreasing(){
        int[] randomArray;
        Random random = new Random();
        randomArray = random.ints(4, 10, 99).toArray();
        System.out.println(Arrays.toString(randomArray));
        for (int i = 0; i < randomArray.length-1; i++) {
            if (randomArray[i] > randomArray[i + 1]){
                System.out.println("This array is not increasing");
                return false;
            }
        }
        System.out.println("This array is increasing");
        return true;
    }
    //4
    public static void averageBigger(){
        int[] randomArray1;
        int[] randomArray2;
        Random random = new Random();
        randomArray1 = random.ints(5, 0, 5).toArray();
        randomArray2 = random.ints(5, 0, 5).toArray();
        System.out.println(Arrays.toString(randomArray1));
        System.out.println(Arrays.toString(randomArray2));
        double average1 = Arrays.stream(randomArray1).average().getAsDouble();
        double average2 = Arrays.stream(randomArray2).average().getAsDouble();
        if (average1 > average2){
            System.out.println("First array is bigger in average. It is " + average1);
        } else if (average2 > average1) {
            System.out.println("Second array is bigger in average. It is " + average2);
        } else {
            System.out.println("They are the same in average. They both " + average1);
        }
    }
    //additionalTask
    private static void bubbleSort() {
        final Random random = new Random();
        final int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(numbers));

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - i - 1; j++) {
                int firstNumber = numbers[j];
                int secondNumber = numbers[j + 1];
                if (firstNumber > secondNumber) {
                    int temp = firstNumber;
                    firstNumber = secondNumber;
                    secondNumber = temp;
                }
                numbers[j] = firstNumber;
                numbers[j + 1] = secondNumber;
            }
        }
        System.out.println(Arrays.toString(numbers));
    }

}

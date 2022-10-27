package com.fedorenko;

public class Main {
    public static void main(String[] args) {
        // 1
        String firstName = "Daniil";
        String space = " ";
        String lastName = "Fedorenko";
        System.out.println(firstName + space + lastName);
// 2
        int i = 0;
        int y = 5;
        while (i <= 10){
            System.out.println("step - " + i + ", value - " + y);
            i++;
            y += 2;
        }
// 3
        for (i = 0; i <= 10; i++){
            if (i == 3) continue;
            if (i == 6) break;
            System.out.println(i);
        }

    }
}

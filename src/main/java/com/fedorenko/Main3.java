package com.fedorenko;

public class Main3 {
    public static void main(String[] args) {
        firstAndLast("Hello World!");
    }
    public static void firstAndLast(String str){

        char first = str.charAt(0);
        char last = str.charAt(str.length() - 1);

        System.out.printf("first char - %s%n", first);
        System.out.printf("last char - %s", last);
    }

    public static boolean isEnd(String str, String endOfStr){
        return str.endsWith(endOfStr);
    }

    public static boolean isContains(String str, String subStr){
        return str.contains(subStr);
    }

    public static boolean equalsIgnoreCase(String s1, String s2){
        return s1.equalsIgnoreCase(s2);
    }

    public static boolean startWith(String str, String startOfStr){
        return str.startsWith(startOfStr);
    }
}

package com.careerdevs.ui;


import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    private static Scanner scanner = new Scanner(System.in);

    public static String readString(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    public static int ReadInt(String question, int Min, int Max) {
        while (true) {
            System.out.println(question);
            try {
                int answer = scanner.nextInt();
                while(answer> Max || answer<Min){
                    System.out.println("you must enter a valid number, please try again");
                    System.out.println(question);
                    answer=scanner.nextInt();
                    scanner.nextLine();
                }
                return answer;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("you must enter an integer, please try again");

            }

        }
    }
}
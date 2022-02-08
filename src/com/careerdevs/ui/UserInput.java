package com.careerdevs.ui;


import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UserInput {
    private static Scanner scanner = new Scanner(System.in);

    public static String readString(String question) {
        while (true) {
            System.out.print(question);

            try {
                String answer = scanner.nextLine();

                while (answer.isBlank()) {
                    System.out.println(question);
                    answer = scanner.nextLine();
                }
                return answer;
            }catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Entry invalid, please try again");

            }
        }

    }


    public static int ReadInt(String question, int min, int max) {
        while (true) {
            System.out.print(question + "\nNumber (" + min + " - " + max +"): ");

            try {
                int answer = scanner.nextInt();
                while(answer> max || answer<min){
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

    public static boolean yesOrNo(String question){
        while (true){
            System.out.println("\n" + question + "\nYes Or No : ");
            // next line will clear the empty space left on previous scanner readings.
            scanner.nextLine();
            String rawInputString = scanner.nextLine();

            // in case user does not enter anything.
            while (rawInputString.isBlank()) {
                System.out.println("\n" + question + "\nYes Or No : ");
                rawInputString = scanner.nextLine();
            }
            char cleanInput = rawInputString.toLowerCase(Locale.ROOT).trim().charAt(0);
            if(cleanInput == 'y'){
                return true;

            }else if (cleanInput=='n'){
                return false;
            }


        }

    }




}
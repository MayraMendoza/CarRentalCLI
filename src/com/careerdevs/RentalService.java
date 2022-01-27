package com.careerdevs;

import com.careerdevs.ui.UserInput;

import java.util.ArrayList;
import java.util.Scanner;

public class RentalService {
    static int carAvailable = 3;
    static Car[] carStorage = new Car[3];

    public static void main(String[] args) {

//        Car[] carStorage = new Car[3];
        System.out.println("Welcome to the car Rental CLI");
        Car car1 = new Car("Honda", "Accord");
        Car car2 = new Car("Chevy", "Cruze");
        Car car3 = new Car("Toyota", "Corolla");

        carStorage[0] = car1;
        carStorage[1] = car2;
        carStorage[2] = car3;

//        int carAvailable = 3;
        while(carAvailable> 0) {

            int availableCarNum = 0;
            System.out.println("Available Cars: ");
            for (int i = 0; i < carStorage.length; i++) {

                if (carStorage[i].isRented() == false) {
                    availableCarNum++;
                    System.out.println("(" + (i+1) + ") " + carStorage[i].getMake() +" "+ carStorage[i].getModel());
                }
            }
            int userSelection = UserInput.ReadInt("Enter a number to select the car you'd like to rent \nSelection: ", 1, 3);//fix

            carStorage[userSelection - 1].setRented(true); //BUG (IF USER SELECTS 1 TWICE IT WILL SET INDEX 0 TO TRUE TWICE.
            System.out.println("\nThank you! You are now renting the " +carStorage[userSelection - 1].getMake() + " " + carStorage[userSelection - 1].getModel() + "\n");// FIX
            carAvailable--;

            if (carAvailable == 0) {
                endProgramOptions();
            }
        }
    }

    // this method will give the user an option of ending the program or staring all over (reset all cars to available)
    public static String endProgramOptions(){
        System.out.println("Sorry all cars have been rented");
        int userInputEndProgram = UserInput.ReadInt("would you like to end this program or reset car data? (press 1 to end program or press 2 to reset data):", 1, 2);
        if (userInputEndProgram == 2) {
            for (int i = 0; i <carStorage.length; i++) {
                carStorage[i].setRented(false);
                carAvailable++;
            }
        } else {
            System.out.println("Have a nice day!");
        }
        return "Have a nice day"; //fix
    }

}

//                System.out.println("Sorry all cars have been rented");
//                int userInputEndProgram = UserInput.ReadInt("would you like to end this program or reset car data? (press 1 to end program or press 2 to reset data):", 1, 2);
//                if (userInputEndProgram == 2) {
//                    for (int i = 0; i < carStorage.length; i++) {
//                        carStorage[i].setRented(false);
//                        carAvailable++;
//
//                    }
//                } else {
//                    System.out.println("Have a nice day");
//                }

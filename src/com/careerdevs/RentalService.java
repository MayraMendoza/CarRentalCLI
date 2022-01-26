package com.careerdevs;

import com.careerdevs.ui.UserInput;

import java.util.ArrayList;
import java.util.Scanner;

public class RentalService {

    public static void main(String[] args) {

        Car[] carStorage = new Car[3];
        System.out.println("Welcome to the car Rental CLI");
        Car car1 = new Car("Honda", "Accord");
        Car car2 = new Car("Chevy", "Cruze");
        Car car3 = new Car("Toyota", "Corolla");

        carStorage[0] = car1;
        carStorage[1] = car2;
        carStorage[2] = car3;

//        System.out.println(availableCars[0].getMake());
//
////        availableCars[1].setRented(true);
        int carAvailable = 3;
        while(carAvailable> 0) {

            int availableCarNum = 0;
            System.out.println("Available Cars: ");
            for (int i = 0; i < carStorage.length; i++) {

                if (carStorage[i].isRented() == false) {
                    availableCarNum++;
                    System.out.println("(" + availableCarNum + ") " + carStorage[i].getMake() + carStorage[i].getModel());
                }

            }
            int userSelection = UserInput.ReadInt("Enter a number to select the car you'd like to rent \nSelection: ", 1, availableCarNum);
            carStorage[userSelection - 1].setRented(true); //BUG (IF USER SELECTS 1 TWICE IT WILL SET INDEX 0 TO TRUE TWICE.
            carAvailable--;


            if (carAvailable == 0) {
                System.out.println("Sorry all cars have been rented");
                int userInputEndProgram = UserInput.ReadInt("would you like to end this program or reset car data? (press 1 to end program or press 2 to reset data):", 1, 2);
                if (userInputEndProgram == 2) {
                    for (int i = 0; i < carStorage.length; i++) {
                        carStorage[i].setRented(false);
                        carAvailable++;

                    }
                } else {
                    System.out.println("Have a nice day");
                }

            }
        }

    }

}

package com.careerdevs;

import com.careerdevs.ui.UserInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RentalServiceTest {
    private static int carAvailable ;
    private static Car[] carStorage ;
    private static int userMenuOption;

    public static void main(String[] args) {

        // upcoming program - 5 cars ... keep in mind this program should
        // work with as many cars (objects)
        carStorage = new Car[3];

        System.out.println("Welcome to the car Rental CLI");
        Car car1 = new Car("Honda", "Accord");
        Car car2 = new Car("Chevy", "Cruze");
        Car car3 = new Car("Toyota", "Corolla");

        carStorage[0] = car1;
        carStorage[1] = car2;
        carStorage[2] = car3;

        Car[] availableCars = getAvailableCars();
        // rented cards
        Car[] rentedCars = getRentedCars();


        // mainMenu(); method will ask user if they want to rent a car, return a car or exit the program.
        mainMenu();

        if(userMenuOption == 1){


            while(availableCars.length> 0) {

                System.out.println("Available Cars: ");
                for (int i = 0; i < availableCars.length; i++) { //try.filter method

                    System.out.println("(" + (i+1) + ") " + availableCars[i].getMake() +" "+ availableCars[i].getModel());
                }
                int userSelection = UserInput.ReadInt("Enter a number to select the car you'd like to rent ", 1, availableCars.length);//fix

                availableCars[userSelection - 1].setRented(true); //BUG (IF USER SELECTS 1 TWICE IT WILL SET INDEX 0 TO TRUE TWICE.
                System.out.println("\nThank you! You are now renting the " +availableCars[userSelection - 1].getMake() + " " + availableCars[userSelection - 1].getModel() + "\n");// FIX

                availableCars = getAvailableCars();


                // if car equals 0 end programOptions will ask user if they want to reset program or end program
                // if user resets program cars available will change to original available cars.
                if (availableCars.length == 0) {
                    // this will show rented cars
                    rentedCars = getRentedCars();
                    System.out.println("rented cars" );
                    for (int j = 0; j< rentedCars.length; j++){
                        System.out.println("(" + (j+1) + ") " + rentedCars[j].getMake() +" "+ rentedCars[j].getModel());


                    }

                    endProgramOptions();
                    availableCars = getAvailableCars();

                }
            }
        }

    }
    private static int mainMenu(){
        System.out.println("Rental Menu \n1)Rent a car \n2)Return a car \n3)Exit program");
        userMenuOption=UserInput.ReadInt("Please enter option", 1, 3);
        System.out.println(userMenuOption);
        return userMenuOption;
    }

    private static Car[] getAvailableCars(){
        return Arrays.stream(carStorage).filter(car -> !car.isRented()).toArray(Car[]::new);
    }
    private static Car[] getRentedCars(){
        return Arrays.stream(carStorage).filter(car -> car.isRented()).toArray(Car[]::new);
    }

    // this method will give the user an option of ending the program or staring all over (reset all cars to available)
    private static void endProgramOptions(){
        System.out.println("Sorry all cars have been rented");
        int userInputEndProgram = UserInput.ReadInt("would you like to end this program or reset car data? (press 1 to end program or press 2 to reset data):", 1, 2);
        if (userInputEndProgram == 1) {
            System.out.println("Have a nice day!");
            return;
        }
        for (int i = 0; i <carStorage.length; i++) {
            carStorage[i].setRented(false);
        }
    }

}

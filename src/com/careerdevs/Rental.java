package com.careerdevs;

import com.careerdevs.ui.UserInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Rental {
    private static int carAvailable ;
    private static Car[] carStorage ;
    private static int userMenuOption;

    public static void main(String[] args) {

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

        System.out.println(availableCars);

        for( int i = 0; i< availableCars.length; i++){
            System.out.println(carStorage[i].getMake() + carStorage[i].getModel());
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

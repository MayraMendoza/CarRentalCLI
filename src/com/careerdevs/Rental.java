package com.careerdevs;

import com.careerdevs.ui.UserInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Rental {
    private static Car[] carStorage ;
    private static int userMenuOption;
    private static Car[] availableCars; // this is an array of all available cars
    private static Car[] rentedCars;



    public static void main(String[] args) {

        carStorage = new Car[3];

        Car car1 = new Car("Honda", "Accord");
        Car car2 = new Car("Chevy", "Cruze");
        Car car3 = new Car("Toyota", "Corolla");


        carStorage[0] = car1;
        carStorage[1] = car2;
        carStorage[2] = car3;


        // this initializes array of available cars
        availableCars = getAvailableCars();

        // this initializes array of rented cars
        rentedCars = getRentedCars();
        availableCars[1].setRented(true);
        availableCars[2].setRented(true);

//        availableCars = getAvailableCars();
//update


//        carStorage[0].setRented(true);

        availableCars = getAvailableCars();
        printAvailableCars();
        printRentedCars();


        printRentedCars();
        printAvailableCars();

        System.out.println(rentedCars[1].getCustomerName());

//        String UserName = UserInput.readString("Enter Name");
//        System.out.println(UserName);



        // rented cards
//        rentedCars = getRentedCars();



//        System.out.println(availableCars);

//        for( int i = 0; i< availableCars.length; i++){
//            System.out.println(availableCars[i].getMake() + " "+ availableCars[i].getModel());
//        }

//        printAvailableCars();

//        System.out.println("Welcome to Java Car rentals");
//        mainMenu();
//        printRentedCars();




        }


    private static int mainMenu(){
        System.out.println("Would you like to \n1)Rent a car \n2)Return a car ");
        userMenuOption=UserInput.ReadInt("Select a number:" , 1, 2);
        System.out.println(userMenuOption);
        if (userMenuOption == 1 ){
            System.out.println("user selected to rent a car");


        }else if (userMenuOption == 2){
            System.out.println("user selected to return a car");
        }
        return userMenuOption;
    }

    private static Car[] getAvailableCars(){
        return Arrays.stream(carStorage).filter(car -> !car.isRented()).toArray(Car[]::new);

    }
    private static void printAvailableCars(){
        availableCars = getAvailableCars();
        System.out.println("available");
        for( int i = 0; i< availableCars.length; i++){
            System.out.println( i + ") " +availableCars[i].getMake() + " "+ availableCars[i].getModel());
        }


    }
    private static Car[] getRentedCars(){
        return Arrays.stream(carStorage).filter(car -> car.isRented()).toArray(Car[]::new);
    }

    private static void printRentedCars(){
        rentedCars = getRentedCars();
        System.out.println("rented");
        for( int i = 0; i< rentedCars.length; i++) {
            System.out.println(i  + ") " + rentedCars[i].getMake() + " " + rentedCars[i].getModel());
        }
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

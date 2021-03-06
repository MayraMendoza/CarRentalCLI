package com.careerdevs;

import com.careerdevs.ui.UserInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CarRentalTest2 {
    private static int carAvailable;
    private static Car[] carStorage;
    private static int userMenuOption;
    private static Car[] availableCars; // this is an array of all available cars
    private static Car[] rentedCars;


    public static void main(String[] args) {

        carStorage = new Car[3];
        Car car1 = new Car("Honda", "Accord");
        Car car2 = new Car("Chevy", "Cruze");
        Car car3 = new Car("Toyota", "Corolla");

//        car3.setRented(true);

        carStorage[0] = car1;
        carStorage[1] = car2;
        carStorage[2] = car3;


        // this initializes array of available cars
        availableCars = getAvailableCars();

        // this initializes array of rented cars
        rentedCars = getRentedCars();

        mainMenu();


    }


    private static int mainMenu() {
        // variable will hold available car array length
        int availableCarLength = getAvailableCars().length;

        // variable will hold rented car array length
        int rentedCarLength = getRentedCars().length;

        // this condition will check the lengths of each array to determine what will be displayed to the user.
        // ****************** find way to make it "dry"****************** not a priority at the moment.
        if (availableCarLength > 0 && rentedCarLength > 0) {
            System.out.println("Would you like to \n1)Rent a car \n2)Return a car \n3) Exit Program");
            userMenuOption = UserInput.ReadInt("Select a option:", 1, 3);

            // can do a switch here....  ?
            System.out.println(userMenuOption);
            if (userMenuOption == 1) {
                System.out.println("user selected to rent a car");
                //printAvailableCars();
                rentAcar();

            } else if (userMenuOption == 2) {
                System.out.println("user selected to return a car");
                //printRentedCars();
                returnACar();
            } else if( userMenuOption == 3){
                endProgramOption();
            }

        } else if (availableCarLength <= 0) {
            System.out.println("would you like to \n1) Return a car \n2) Exit program ");
            userMenuOption = UserInput.ReadInt("select an option", 1, 2);
            //printRentedCars();
            switch (userMenuOption){
            case 1 -> returnACar();
            case 2 ->endProgramOption();
            }

        } else if (rentedCarLength <= 0) {
            System.out.println("would you like to \n1) Rent a car  \n2) Exit Program ");
            userMenuOption = UserInput.ReadInt("select an option", 1, 2);
            //printAvailableCars();
            switch (userMenuOption){
                case 1 -> rentAcar();
                case 2 ->endProgramOption();
            }

        }
        return userMenuOption;
    }

    // will sort car storage array and place all available cars in a new array
    private static Car[] getAvailableCars() {
        return Arrays.stream(carStorage).filter(car -> !car.isRented()).toArray(Car[]::new);
    }

    // this method will display all available cars
    private static void printAvailableCars() {
        availableCars = getAvailableCars();
        System.out.println("available");
        for (int i = 0; i < availableCars.length; i++) {
            System.out.println((i + 1) + ") " + availableCars[i].getMake() + " " + availableCars[i].getModel());
        }
    }

    // will sort car storage array and place all rented cars in a new array
    private static Car[] getRentedCars() {
        return Arrays.stream(carStorage).filter(car -> car.isRented()).toArray(Car[]::new);
    }

    // this method will display all rented cars
    private static void printRentedCars() {
        rentedCars = getRentedCars();
        System.out.println("rented");
        for (int i = 0; i < rentedCars.length; i++) {
            System.out.println((i + 1) + ") " + rentedCars[i].getMake() + " " + rentedCars[i].getModel());
        }
    }

    private static void rentAcar() {
        System.out.println("printed twice");
        printAvailableCars();
        int selectedRent = UserInput.ReadInt("Enter a number to select an available car you'd like to rent", 1, availableCars.length);
        System.out.println("are you sure you would like to rent the " + availableCars[selectedRent - 1].getMake() + " " + availableCars[selectedRent - 1].getModel() + "?");
        boolean userSelection = UserInput.yesOrNo("Please confirm");
        System.out.println("here");
        if (userSelection== true) {
            System.out.println("yes was selected");
            System.out.println("what name would you like to use to return your rental? ");
            String userName = UserInput.readString("Enter Name: ");
            availableCars[selectedRent - 1].setCustomerName(userName);
            availableCars[selectedRent - 1].setRented(true);

            System.out.println("thank you for renting the " + availableCars[selectedRent - 1].getMake() + " " + availableCars[selectedRent - 1].getModel());

            // this will take user to main menu
            mainMenu();

        } else if (userSelection == false) {
            System.out.println("user selected no");
            // if the user enters no it will call rent a car method that will print a list of available cars
            // and ask user to select from available cars.
            rentAcar();
        }
    }

    private static void returnACar() {
        printRentedCars();

        int selectedRent = UserInput.ReadInt("Enter a number to select the car you'd like to return", 1, rentedCars.length);
        System.out.println("are you sure you would like to return the " + rentedCars[selectedRent - 1].getMake() + " " + rentedCars[selectedRent - 1].getModel() + "?");
        boolean userSelection = UserInput.yesOrNo("Confirm (Y/N): ");
        if (userSelection == true) {
            System.out.println("yes was selected");
            System.out.println("please enter the name you used to the " + rentedCars[selectedRent - 1].getMake() + " " + rentedCars[selectedRent - 1].getModel());
            String userName = UserInput.readString("Enter Name: ");
            String userNameStored = rentedCars[selectedRent - 1].getCustomerName();
            // check if they are matching ----
            System.out.println(userName + userNameStored);
            System.out.println(userName.trim() + userNameStored.trim());
            if (userName.trim().equalsIgnoreCase(userNameStored.trim())) {
                System.out.println("Thank you " + userName + " you have successfully returned the " + rentedCars[selectedRent - 1].getMake() + " " + rentedCars[selectedRent - 1].getModel());
                rentedCars[selectedRent - 1].setRented(false);
                rentedCars[selectedRent - 1].setCustomerName(null);
                mainMenu();
            } else {
                System.out.println("Sorry The name you have enter does not match the name in our system.");
                // this will take user to main menu
                mainMenu();
            }
        } else if (userSelection == false) {
            System.out.println("user selected no");
            // if the user enters no it will call rent a car method that will print a list of available cars
            // and ask user to select from available cars.
            mainMenu();
        }

    }
    private  static void endProgramOption(){
        boolean endProgram = UserInput.yesOrNo("You have selected the option to end this program please confirm");
        if(endProgram){
            System.out.println("Thank you for stopping by");

        }else if(!endProgram){
            mainMenu();
        }



    }
}



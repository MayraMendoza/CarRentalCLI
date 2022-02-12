package com.careerdevs;

import com.careerdevs.ui.UserInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RentalServiceFinal {
    private static int carAvailable;
    private static ArrayList<Car> carStorage;
    private static int userMenuOption;
    private static ArrayList<Car> availableCars; // this is an array of all available cars
    private static ArrayList<Car> rentedCars;


    public static void main(String[] args) {

        carStorage = new ArrayList<>();
        carStorage.add(new Car("Chevy", "Cruze", null));
        carStorage.add(new Car("Honda", "Accord", null));
        carStorage.add(new Car("Toyota", "Corolla", null));



        // this initializes array of available cars
        availableCars = getAvailableCars();

        // this initializes array of rented cars
        rentedCars = getRentedCars();

        mainMenu();
//        System.out.println(carStorage.get(0).isRented());
//        System.out.println(carStorage.get(1).isRented());
//        System.out.println(carStorage.get(2).isRented());
//
//        System.out.println(getAvailableCars().size());
//        System.out.println(getRentedCars().size());






    }


    private static int mainMenu() {
        // variable will hold available car array length
        int availableCarLength = getAvailableCars().size();

        // variable will hold rented car array length
        int rentedCarLength = getRentedCars().size();

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
            System.out.println("would you like to \n1) Rent a car  \n2) Exit Program");
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
    private static ArrayList<Car> getAvailableCars() {
        return carStorage.stream().filter(car -> !car.isRented()).collect(Collectors.toCollection(ArrayList::new));

    }

    // this method will display all available cars
    private static void printAvailableCars() {
        availableCars = getAvailableCars();
        System.out.println("available");
        for (int i = 0; i < availableCars.size(); i++) {
            System.out.println((i + 1) + ") " + availableCars.get(i).getMake() + " " + availableCars.get(i).getModel());
        }
    }

    // will sort car storage array and place all rented cars in a new array
    private static ArrayList<Car> getRentedCars() {
        return carStorage.stream().filter(car -> car.isRented()).collect(Collectors.toCollection(ArrayList<Car>::new));
    }

    // this method will display all rented cars
    private static void printRentedCars() {
        rentedCars = getRentedCars();
        System.out.println("rented");
        for (int i = 0; i < rentedCars.size(); i++) {
            System.out.println((i + 1) + ") " + rentedCars.get(i).getMake() + " " + rentedCars.get(i).getModel());
        }
    }

    private static void rentAcar() {
        System.out.println("printed twice");
        printAvailableCars();
        int selectedRent = UserInput.ReadInt("Enter a number to select an available car you'd like to rent", 1, availableCars.size());
        System.out.println("are you sure you would like to rent the " + availableCars.get(selectedRent - 1).getMake() + " " + availableCars.get(selectedRent - 1).getModel() + "?");
        boolean userSelection = UserInput.yesOrNo("Please confirm");
        System.out.println("here");
        if (userSelection== true) {
            System.out.println("yes was selected");
            System.out.println("what name would you like to use to return your rental? ");
            String userName = UserInput.readString("Enter Name: ");
            availableCars.get(selectedRent - 1).setCustomerName(userName);
            availableCars.get(selectedRent - 1).setRented(true);

            System.out.println("thank you for renting the " + availableCars.get(selectedRent - 1).getMake() + " " + availableCars.get(selectedRent - 1).getModel());

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

        int selectedRent = UserInput.ReadInt("Enter a number to select the car you'd like to return", 1, rentedCars.size());
        System.out.println("are you sure you would like to return the " + rentedCars.get(selectedRent - 1).getMake() + " " + rentedCars.get(selectedRent - 1).getModel() + "?");
        boolean userSelection = UserInput.yesOrNo("Confirm (Y/N): ");
        if (userSelection == true) {
            System.out.println("yes was selected");
            System.out.println("please enter the name you used to the " + rentedCars.get(selectedRent - 1).getMake() + " " + rentedCars.get(selectedRent - 1).getModel());
            String userName = UserInput.readString("Enter Name: ");
            String userNameStored = rentedCars.get(selectedRent - 1).getCustomerName();
            // check if they are matching ----
            System.out.println(userName + userNameStored);
            System.out.println(userName.trim() + userNameStored.trim());
            if (userName.trim().equalsIgnoreCase(userNameStored.trim())) {
                System.out.println("Thank you " + userName + " you have successfully returned the " + rentedCars.get(selectedRent - 1).getMake() + " " + rentedCars.get(selectedRent - 1).getModel());
                rentedCars.get(selectedRent - 1).setRented(false);
                rentedCars.get(selectedRent - 1).setCustomerName(null);
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




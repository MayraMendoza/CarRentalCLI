/**
 * Rental Service - this program has a main menu with options that include rent a car, return a car, create a car and exit program.
 * rent a car and return car options will only show up if there are cars available to be rented or return. This program allows users to rent/return a car
 * if a user selects to return or rent a car it will ask for confirmation and users name that will be stored into Car object. users name will be used as a secondary
 * confirmation that user has rented car and can return specified car.
 *
 * 02/13/22
 * @author Mayra Mendoza
 *
 */
package com.careerdevs;

import com.careerdevs.ui.UserInput;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class RentalServiceFinal {
    private static ArrayList<Car> carStorage;
    private static int userMenuOption;
    private static ArrayList<Car> availableCars; // this is an array of all available cars
    private static ArrayList<Car> rentedCars;


    public static void main(String[] args) {

        // this method initializes arrayList of cars
        carInitializer();

        // main menu will start program and call methods depending on user selection
        mainMenu();

    }


    private static void carInitializer (){
        carStorage = new ArrayList<>();
        carStorage.add(new Car("Chevy", "Cruze"));
        carStorage.add(new Car("Honda", "Accord"));
        carStorage.add(new Car("Toyota", "Corolla"));
        carStorage.add(new Car("Hyundai", "Elantra"));
        carStorage.add(new Car("Ford", "Focus"));


        // this initializes array of available cars
        availableCars = getAvailableCars();

        // this initializes array of rented cars
        rentedCars = getRentedCars();

    }

    private static int mainMenu() {
        // variable will hold available car array length
        int availableCarLength = getAvailableCars().size();

        // variable will hold rented car array length
        int rentedCarLength = getRentedCars().size();

        // this condition will check the lengths of each array to determine what options will be displayed to the user.
        if (availableCarLength > 0 && rentedCarLength > 0) {
            System.out.println("Would you like to \n1) Rent a car \n2) Return a car \n3) Create newCar \n4) Exit Program");
            userMenuOption = UserInput.ReadInt("Select a option:", 1, 4);

            switch (userMenuOption){
                case 1 -> rentAcar();
                case 2 -> returnACar();
                case 3 ->createNewCar();
                case 4 -> endProgramOption();
            }

        } else if (availableCarLength <= 0) {
            System.out.println("would you like to \n1) Return a car \n2) create new car \n3) Exit program ");
            userMenuOption = UserInput.ReadInt("select an option", 1, 3);
            //printRentedCars();
            switch (userMenuOption){
                case 1 -> returnACar();
                case 2 -> createNewCar();
                case 3 -> endProgramOption();
            }

        } else if (rentedCarLength <= 0) {
            System.out.println("would you like to \n1) Rent a car  \n2) create new car \n3) Exit Program");
            userMenuOption = UserInput.ReadInt("select an option", 1, 3);
            //printAvailableCars();
            switch (userMenuOption){
                case 1 -> rentAcar();
                case 2 -> createNewCar();
                case 3 ->endProgramOption();
            }
        }
        return userMenuOption;
    }

    // will sort car storage array and place all available cars in a new array
    private static ArrayList<Car> getAvailableCars() {
        return carStorage.stream().filter(car -> !car.isRented()).collect(Collectors.toCollection(ArrayList::new));
    }

    // getRentedCars will sort car storage array and place all rented cars in a new array
    private static ArrayList<Car> getRentedCars() {
        return carStorage.stream().filter(car -> car.isRented()).collect(Collectors.toCollection(ArrayList<Car>::new));
    }

    // this method will display all available cars
    private static void printAvailableCars() {
        availableCars = getAvailableCars();
        System.out.println("available");
        for (int i = 0; i < availableCars.size(); i++) {
            System.out.println((i + 1) + ") " + availableCars.get(i).getMake() + " " + availableCars.get(i).getModel());
        }
    }

    // this method will display all rented cars
    private static void printRentedCars() {
        rentedCars = getRentedCars();
        System.out.println("rented");
        for (int i = 0; i < rentedCars.size(); i++) {
            System.out.println((i + 1) + ") " + rentedCars.get(i).getMake() + " " + rentedCars.get(i).getModel());
        }
    }

    /*
    rentAcar will allow users to rent a car from available options. This method will  ask for user to confirm option before storing user's name
    in the appropriate car object. If the user does not confirm it will redirect them to main menu.
     */
    private static void rentAcar() {
        System.out.println("printed twice");
        printAvailableCars();
        int selectedRent = UserInput.ReadInt("Enter a number to select an available car you'd like to rent", 1, availableCars.size());
        System.out.println("are you sure you would like to rent the " + availableCars.get(selectedRent - 1).getMake() + " " + availableCars.get(selectedRent - 1).getModel() + "?");
        boolean userSelection = UserInput.yesOrNo("Please confirm");

        if (userSelection) {
            System.out.println("what name would you like to use to return your rental? ");
            String userName = UserInput.readString("Enter Name: ");
            availableCars.get(selectedRent - 1).setCustomerName(userName);
            availableCars.get(selectedRent - 1).setRented(true);

            System.out.println("thank you for renting the " + availableCars.get(selectedRent - 1).getMake() + " " + availableCars.get(selectedRent - 1).getModel());

            // this will take user to main menu
            mainMenu();

        } else if (!userSelection) {
            System.out.println("user selected no");
            // if the user enters no it will call rent a car method that will print a list of available cars
            // and ask user to select from available cars.
            rentAcar();
        }
    }


    /*
    returnAcar will allow users to return a car from rented car options. This method will ask for user to confirm option before asking for user's name
    and comparing to the one in the appropriate Car object. If the user does not confirm it will redirect them to main menu.
     */
    private static void returnACar() {
        printRentedCars();

        int selectedRent = UserInput.ReadInt("Enter a number to select the car you'd like to return", 1, rentedCars.size());
        System.out.println("are you sure you would like to return the " + rentedCars.get(selectedRent - 1).getMake() + " " + rentedCars.get(selectedRent - 1).getModel() + "?");
        boolean userSelection = UserInput.yesOrNo("Please confirm ");
        if (userSelection) {
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
        } else if (!userSelection) {
            System.out.println("user selected no");
            // if the user enters no it will call rent a car method that will print a list of available cars
            // and ask user to select from available cars.
            mainMenu();
        }
    }

    // createNewCar method will allow users to add a car object to carStorage arraylist. users will be ask for a make and model& confirmation.
    private static void createNewCar(){
        // to create new car we will ask for make and model
        //carStorage.add(new Car("Chevy", "Cruze", null));
        String newCarMake = UserInput.readString("Please enter vehicle Make: ");
        String newCarModel = UserInput.readString("Please enter vehicle model: ");
        System.out.println("Are you sure you want to add this vehicle? "+ newCarMake +" "+newCarModel);
        boolean userConfirmation = UserInput.yesOrNo("Please confirm");
        System.out.println(userConfirmation);
        if (userConfirmation){
            // add new car to carStorage arraylist
            carStorage.add(new Car(newCarMake, newCarModel));
            //update available cars
            availableCars = getAvailableCars();
            mainMenu();

        }else if(!userConfirmation){
            System.out.println("You are being redirected to the main menu... ");
            mainMenu();
        }

        // update rented car array.
        rentedCars = getRentedCars();
    }

    // endProgramOption method will allow user to end program and end recursion.
    private  static void endProgramOption(){
        boolean endProgram = UserInput.yesOrNo("You have selected the option to end this program please confirm");
        if(endProgram){
            System.out.println("Thank you for stopping by");

        }else if(!endProgram){
            mainMenu();
        }
    }
}




package com.careerdevs;

import com.careerdevs.ui.UserInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CarRentalTest {
    private static int carAvailable ;
    private static Car[] carStorage ;
    private static int userMenuOption;
    private static Car[] availableCars; // this is an array of all available cars
    private static Car[] rentedCars;



    public static void main(String[] args) {

        carStorage = new Car[3];

        Car car1 = new Car("Honda", "Accord");
        Car car2 = new Car("Chevy", "Cruze");
        Car car3 = new Car("Toyota", "Corolla");

        car3.setRented(false);


        carStorage[0] = car1;
        carStorage[1] = car2;
        carStorage[2] = car3;


        // this initializes array of available cars
        availableCars = getAvailableCars();

        // this initializes array of rented cars
        rentedCars = getRentedCars();



//        printAvailableCars();
//        printRentedCars();
         mainMenu();


//        System.out.println("length of available cars");
//        System.out.println(getAvailableCars().length);
//
//        int availableCarLength = getAvailableCars().length;
//
//        System.out.println("length of rented cars ");
//        System.out.println(getRentedCars().length);
//
//
//        int rentedCarLength = getRentedCars().length;
//        if (rentedCarLength > 0){
//            System.out.println("greater than zero");
//        } else{
//            System.out.println("no rented cars");
//        }



    }




    private static int mainMenu(){
        // variable will hold available car array length
        int availableCarLength = getAvailableCars().length;

        // variable will hold rented car array length
        int rentedCarLength = getRentedCars().length;

        // this condition will check the lengths of each array to determine what will be displayed to the user.
        // ****************** find way to make it "dry"****************** not a priority at the moment.
        if(availableCarLength > 0 && rentedCarLength>0){
            System.out.println("Would you like to \n1)Rent a car \n2)Return a car ");
            userMenuOption=UserInput.ReadInt("Select a number:" , 1, 2);
            System.out.println(userMenuOption);
            if (userMenuOption == 1 ){
                System.out.println("user selected to rent a car");
                printAvailableCars();


            }else if (userMenuOption == 2){
                System.out.println("user selected to return a car");
                printRentedCars();
            }


        } else if( availableCarLength <= 0 ) {
            System.out.println("would you like to \n1) Return a car ");
            userMenuOption = UserInput.ReadInt("select number", 1, 1);
            printRentedCars();
            System.out.println(userMenuOption);
        } else if(rentedCarLength <= 0){
            System.out.println("would you like to \n1) Rent a car ");
            userMenuOption = UserInput.ReadInt("select number", 1, 1);
            printAvailableCars();
            System.out.println(userMenuOption);
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
            System.out.println( (i +1) + ") " +availableCars[i].getMake() + " "+ availableCars[i].getModel());
        }
        int selectedRent = UserInput.ReadInt("Enter a number to select an available car you'd like to rent", 1, availableCars.length);

        System.out.println("are you sure you would like to rent the " + availableCars[selectedRent -1].getMake() + " "+ availableCars[selectedRent -1].getModel() +"?");
        String userSelection = yesOrNo();
        if (userSelection == "yes"){
            System.out.println("yes was selected");
            System.out.println("what name would you like to use to return your rental? ");
            String userName = UserInput.readString("Enter Name: ");
            availableCars[selectedRent -1].setCustomerName(userName);
            availableCars[selectedRent -1].setRented(true);

            // this will take user to main menu
            mainMenu();


        } else if (userSelection== "no"){
            System.out.println("user selected no");
        }


    }

    private static void rentAcar(){

    }




    private static String yesOrNo() {
        String userConfirmation = UserInput.readString("Confirm (y/n)");
        String userAnswerValid = "No";
        String userConfirmation1 = "z";

        while( userAnswerValid == "No"){
            if (userConfirmation.trim().equalsIgnoreCase("yes") || userConfirmation.trim().equalsIgnoreCase("y")) {
                userAnswerValid = "yes";
                userConfirmation1 = "yes";

            } else if (userConfirmation.trim().equalsIgnoreCase("no") || userConfirmation.trim().equalsIgnoreCase("n")) {
                 userAnswerValid = "yes";
                userConfirmation1 = "no";
            } else {
                System.out.println("please enter a valid entry");
                userConfirmation = UserInput.readString("Confirm (y/n)");

            }
        } return userConfirmation1;
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
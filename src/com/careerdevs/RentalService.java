package com.careerdevs;

import java.util.ArrayList;
import java.util.Scanner;

public class RentalService {
    public static void main(String[] args) {
        System.out.println("Welcome to the car Rental CLI");
        Car car1 = new Car("Honda", "Accord");



        Scanner scanner = new Scanner(System.in);


//    String [] carsAvailable = {" Honda Accord", "Chevy Cruze", "Toyota Corolla"};
        ArrayList<String> availableCars = new ArrayList<>();

        availableCars.add("Honda Accord");
        availableCars.add("Chevy Cruze");
        availableCars.add("Toyota Corolla");

        ArrayList<String> rentedCars = new ArrayList<>();
        while (availableCars.size() != 0) {
            System.out.println("available cars");
            for (int i = 0; i < availableCars.size(); i++) {
                System.out.println(i + 1 + ")" + availableCars.get(i));
            }

            System.out.println("Enter a number to select the car you'd like to rent \nSelection: ");
            int inputCarSelection = scanner.nextInt();
            scanner.nextLine();
            if (inputCarSelection == 1 || inputCarSelection == 2 || inputCarSelection == 3) {
                rentedCars.add(availableCars.get(inputCarSelection - 1));
                availableCars.remove(inputCarSelection - 1);
            }

//        while (inputCarSelection != 1 || inputCarSelection != 2 || inputCarSelection != 3 ){
//            System.out.println("com.careerdevs.Car selection invalid, please try again");
//            System.out.println("Enter a number to select the car you'd like to rent \nSelection: ");
//            inputCarSelection = scanner.nextInt();
//            scanner.nextLine();
//        }
////            if(inputCarSelection == 1 || inputCarSelection ==2 || inputCarSelection ==3 ){
////                availableCars.remove(inputCarSelection-1);
////                rentedCars.add(availableCars.get(inputCarSelection-1));
////            }

//            System.out.println("available cars");
//            for (int i = 0; i < availableCars.size(); i++) {
//                System.out.println(i + 1 + ")" + availableCars.get(i));
//            }
            System.out.println("rented cars");
            for (int i = 0; i < rentedCars.size(); i++) {
                System.out.println(i + 1 + ")" + rentedCars.get(i));
            }

        }
        System.out.println("Sorry all cars have been rented");

    }
}

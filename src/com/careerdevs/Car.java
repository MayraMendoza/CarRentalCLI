package com.careerdevs;

public class Car {

    private String make;
    private String model;
    private boolean rented ;
    private boolean available;
    private String customerName;

    public Car (String make, String model, String customerName){
        this.make = make;
        this.model = model;
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String setCustomerName(String customerName) {

        return this.customerName = customerName;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // constructor


    @Override
    public String toString() {
        return "com.careerdevs.Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", rented=" + rented +
                ", available=" + available +
                '}';
    }
}

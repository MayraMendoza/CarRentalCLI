package com.careerdevs;

public class Car {

    private String make;
    private String model;
    private boolean isRented ;
    private int costPerDay;
    private int daysRented;
    private String customerName;

    public Car (String make, String model, String customerName){
        this.make = make;
        this.model = model;
        this.customerName = customerName;

    }
    public Car(String make, String model, String customerName, int costPerDay, int daysRented){
        this.make = make;
        this.model = model;
        this.customerName = customerName;
        this.costPerDay = costPerDay;
        this.daysRented = daysRented;

    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public int getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(int costPerDay) {
        this.costPerDay = costPerDay;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", isRented=" + isRented +
                ", costPerDay=" + costPerDay +
                ", daysRented=" + daysRented +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */

package ucf.assignments;

import java.util.Currency;

//This class creates the object Item
public class Item {

    //Instance variables
    private Currency value;
    private String serialNumber;
    private String name;

    //Setter
    public Item(Currency value, String serialNumber, String name) {
        //if true set the value
        if(validateValue()) {
            this.value = value;
        }
        //if true set the value
        if(validateSerialNumber()) {
            this.serialNumber = serialNumber;
        }
        //if true set the value
        if(validateName()) {
            this.name = name;
        }
    }

    //Getter for value
    public Currency getValue() {
        return this.value;
    }

    //Getter for serial number
    public String getSerialNumber() {
        return this.serialNumber;
    }

    //Getter for name
    public String getName() {
        return this.name;
    }

    //Validation for value
    public Boolean validateValue() {
        Boolean isValid = false;
        //if the currency code is "USD" return true
        if(this.value.getCurrencyCode() == "USD") {
            isValid = true;
        }
        return isValid;
    }

    //Validation for serial number
    public Boolean validateSerialNumber() {
        Boolean isValid = false;
        //if the serial number is 10 in length and is only letters and numbers return true
        if(this.serialNumber.length() == 10 && this.serialNumber.matches("[a-zA-Z0-9]*")){
            isValid = true;
        }
        return isValid;
    }

    //Validation for name
    public Boolean validateName() {
        Boolean isValid = false;
        //if 2 <= name <= 256 then return true
        if(this.name.length() >= 2 && this.name.length() <= 256) {
            isValid = true;
        }
        return isValid;
    }
}

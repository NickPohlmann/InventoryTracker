/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */

package ucf.assignments;

import javax.naming.Name;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

//This class creates the object Item
public class Item {

    //Instance variables
    private BigDecimal value;
    private String serialNumber;
    private String name;

    //Constructor
    public Item(BigDecimal value, String serialNumber, String name) {
        setValue(value);
        setSerialNumber(serialNumber);
        setName(name);
    }

    //Setter for value
    public void setValue(BigDecimal value) {
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    //Getter for value
    public BigDecimal getValue() {
        return this.value;
    }

    //Setter for serial number
    public void setSerialNumber(String serialNumber) {
        if(validateSerialNumber(serialNumber)) {
            this.serialNumber = serialNumber;
        }
    }

    //Getter for serial number
    public String getSerialNumber() {
        return this.serialNumber;
    }

    //Setter for name
    public void setName(String name) {
        if(validateName(name)) {
            this.name = name;
        }
    }

    //Getter for name
    public String getName() {
        return this.name;
    }

    //Validation for serial number
    public Boolean validateSerialNumber(String serialNumber) {
        Boolean isValid = false;
        //if the serial number is 10 in length and is only letters and numbers return true
        if(serialNumber.length() == 10 && serialNumber.matches("[a-zA-Z0-9]*")){
            isValid = true;
        }
        return isValid;
    }

    //Validation for name
    public Boolean validateName(String name) {
        Boolean isValid = false;
        //if 2 <= name <= 256 then return true
        if(name.length() >= 2 && name.length() <= 256) {
            isValid = true;
        }
        return isValid;
    }

}

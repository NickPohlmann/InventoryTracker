/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    //This will test the setter and getter of value
    @Test
    void setAndGetValue() {
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item expected = new Item(value, "1234567890", "test");
        Item actual = new Item(BigDecimal.valueOf(1), "0000000000" , "filler");
        actual.setValue(value);
        assertEquals(expected.getValue(), actual.getValue());
    }

    //This will test the setter and getter of serial number
    @Test
    void setAndGetSerialNumber() {
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item expected = new Item(value, "1234567890", "test");
        Item actual = new Item(BigDecimal.valueOf(1), "0000000000" , "filler");
        actual.setSerialNumber("1234567890");
        assertEquals(expected.getSerialNumber(), actual.getSerialNumber());
    }

    //This will test the setter and getter of name
    @Test
    void setAndGetName() {
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item expected = new Item(value, "1234567890", "test");
        Item actual = new Item(BigDecimal.valueOf(1), "0000000000" , "filler");
        actual.setName("test");
        assertEquals(expected.getName(), actual.getName());
    }

    //This will test the validate Serial Number with all numbers
    @Test
    void testValidateSerialNumberForOnlyNums() {
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "1234567890", "test");
        Boolean actual = item.validateSerialNumber(item.getSerialNumber());
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    //This will test the validate Serial Number with all letter
    @Test
    void testValidateSerialNumberForOnlyLetters() {
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "asdfghjklp", "test");
        Boolean actual = item.validateSerialNumber(item.getSerialNumber());
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    //This will test the validate Serial Number with mix of nums and letters
    @Test
    void testValidateSerialNumberForNumAndLetters() {
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "1a3c5b7n9p", "test");
        Boolean actual = item.validateSerialNumber(item.getSerialNumber());
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    //This will test the validate Serial Number with special characters
    @Test
    void testValidateSerialNumberSpecialChars() {
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "123456789@", "test");
        Boolean actual = item.validateSerialNumber("123456789@");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    //This will test the validate Serial Number when str is less than 10
    @Test
    void testValidateSerialNumberForLessThan10() {
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "1234567890", "test");
        Boolean actual = item.validateSerialNumber("12345");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    //This will test the validate Serial Number when str is more than 10
    @Test
    void testValidateSerialNumberForMoreThan10() {
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "1234567890", "test");
        Boolean actual = item.validateSerialNumber("1234567890asdfgh");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    //This will test validateName for the right number of chars
    @Test
    void testValidateNameForGoodCharCount() {
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "1234567890", "test");
        Boolean actual = item.validateName(item.getName());
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    //This will test validateName for char count less than 2
    @Test
    void testValidateNameForLessThan2() {
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "1234567890", "test");
        Boolean actual = item.validateName("a");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    //This will test validateName for char count more than 256
    @Test
    void testValidateNameForMoreThan256() {
        String name = "";
        for (int i = 0; i < 300; i++) {
            name += "a";
        }
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "1234567890", "test");
        Boolean actual = item.validateName(name);
        Boolean expected = false;
        assertEquals(expected, actual);
    }
}

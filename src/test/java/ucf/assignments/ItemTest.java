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
        //Create the expected by manually inputting all values and not changing
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item expected = new Item(value, "1234567890", "test");
        //Create the actual by manually inputting the values and then change value, using setValue
        Item actual = new Item(BigDecimal.valueOf(1), "0000000000" , "filler");
        actual.setValue(value);
        assertEquals(expected.getValue(), actual.getValue());
    }

    //This will test the setter and getter of serial number
    @Test
    void setAndGetSerialNumber() {
        //Create the expected by manually inputting all values and not changing
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item expected = new Item(value, "1234567890", "test");
        //Create the actual by manually inputting the values and then change serial number, using setSerialNumber
        Item actual = new Item(BigDecimal.valueOf(1), "0000000000" , "filler");
        actual.setSerialNumber("1234567890");
        assertEquals(expected.getSerialNumber(), actual.getSerialNumber());
    }

    //This will test the setter and getter of name
    @Test
    void setAndGetName() {
        //Create the expected by manually inputting all values and not changing
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item expected = new Item(value, "1234567890", "test");
        //Create the actual by manually inputting the values and then change name, using setName
        Item actual = new Item(BigDecimal.valueOf(1), "0000000000" , "filler");
        actual.setName("test");
        assertEquals(expected.getName(), actual.getName());
    }

    //This will test the validate Serial Number with all numbers
    @Test
    void testValidateSerialNumberForOnlyNums() {
        //Create item manually and then validate the Serial Number
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "1234567890", "test");
        Boolean actual = item.validateSerialNumber(item.getSerialNumber());
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    //This will test the validate Serial Number with all letter
    @Test
    void testValidateSerialNumberForOnlyLetters() {
        //Create item manually and then validate the Serial Number
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "asdfghjklp", "test");
        Boolean actual = item.validateSerialNumber(item.getSerialNumber());
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    //This will test the validate Serial Number with mix of nums and letters
    @Test
    void testValidateSerialNumberForNumAndLetters() {
        //Create item manually and then validate the Serial Number
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "1a3c5b7n9p", "test");
        Boolean actual = item.validateSerialNumber(item.getSerialNumber());
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    //This will test the validate Serial Number with special characters
    @Test
    void testValidateSerialNumberSpecialChars() {
        //Create item manually and then validate the Serial Number
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "123456789@", "test");
        Boolean actual = item.validateSerialNumber("123456789@");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    //This will test the validate Serial Number when str is less than 10
    @Test
    void testValidateSerialNumberForLessThan10() {
        //Create item manually and then validate the Serial Number
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "1234567890", "test");
        Boolean actual = item.validateSerialNumber("12345");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    //This will test the validate Serial Number when str is more than 10
    @Test
    void testValidateSerialNumberForMoreThan10() {
        //Create item manually and then validate the Serial Number
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "1234567890", "test");
        Boolean actual = item.validateSerialNumber("1234567890asdfgh");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    //This will test validateName for the right number of chars
    @Test
    void testValidateNameForGoodCharCount() {
        //Create item manually and then validate name
        BigDecimal value = BigDecimal.valueOf(5.67);
        Item item = new Item(value, "1234567890", "test");
        Boolean actual = item.validateName(item.getName());
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    //This will test validateName for char count less than 2
    @Test
    void testValidateNameForLessThan2() {
        //Create item manually and then validate a string that will "act" as the name to test validateName
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
        //This for loop is creating a string with more than 256 characters to test against validateName
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

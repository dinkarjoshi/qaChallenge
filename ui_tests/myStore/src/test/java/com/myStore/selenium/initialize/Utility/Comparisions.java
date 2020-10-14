package com.myStore.selenium.initialize.Utility;

public class Comparisions {

    public boolean compareIgnoreCase(String expected, String actual){
        return expected.equalsIgnoreCase(actual);
    }

    public boolean compare(String expected, String actual){
        return expected.equals(actual);
    }
}

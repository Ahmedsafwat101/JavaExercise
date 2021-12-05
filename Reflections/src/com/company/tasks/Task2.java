package com.company.tasks;

import com.company.utils.Helper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //Get reference to Test class
        Class<?> myClass = Test.class;

        //Make instance of the class constructor
        Object myObjectClass = myClass.getDeclaredConstructor().newInstance();

        // for accessing private field
        Field[] allFields = myClass.getDeclaredFields(); // accessing all fields
        Arrays.stream(allFields).forEach(

                field -> {
                    if (Helper.checkModifierPropertiesType(field.getModifiers()).equals("Private")) {
                        field.setAccessible(true);
                        try {
                            //Get value
                            int val = (int) field.get(myObjectClass);
                            System.out.println("Old Value is :" + val);
                            //Set Value
                            field.set(myObjectClass, 2);
                            val = (int) field.get(myObjectClass);
                            System.out.println("New Value is :" + val);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

    }

}

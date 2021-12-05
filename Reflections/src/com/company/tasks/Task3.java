package com.company.tasks;

import com.company.utils.Helper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        //Get reference to Test class

        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        Class<?> myClass = Class.forName(Helper.getPrivateFieldValue(path));

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
                            field.set(myObjectClass, 3);
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

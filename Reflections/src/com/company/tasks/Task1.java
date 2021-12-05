package com.company.tasks;

import com.company.utils.Helper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Task1 {

    public static void main(String[] args) throws ClassNotFoundException {
        //Get reference to Test class
        Class myObjectClass = Class.forName("com.company.tasks.Test");

        System.out.println("Public Fields:");

        // for accessing fields
        Field[] publicFields = myObjectClass.getFields(); // accessing public fields only
        Arrays.stream(publicFields).forEach(
                field -> System.out.println(Helper.checkModifierPropertiesType(field.getModifiers()) + "," + field.getType().getSimpleName() + "," + field.getName())
        );

        System.out.println("\nAll Fields:");

        // for accessing fields
        Field[] allFields = myObjectClass.getDeclaredFields(); // accessing all fields
        Arrays.stream(allFields).forEach(
                field -> System.out.println(Helper.checkModifierPropertiesType(field.getModifiers()) + "," + field.getType().getSimpleName() + "," + field.getName())
        );
    }

}

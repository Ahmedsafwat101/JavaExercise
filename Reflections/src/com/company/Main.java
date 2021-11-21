package com.company;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //Get reference to Test class
        Class myObjectClass = Test.class;

        System.out.println("Public Fields:");

        // for accessing fields
        Field[] publicFields = myObjectClass.getFields(); // accessing public fields only
        Arrays.stream(publicFields).forEach(
                field -> System.out.println(checkModifierPropertiesType(field.getModifiers()) + "," + field.getType().getSimpleName() + "," + field.getName())
        );

        System.out.println("\nAll Fields:");

        // for accessing fields
        Field[] allFields = myObjectClass.getDeclaredFields(); // accessing all fields
        Arrays.stream(allFields).forEach(
                field -> System.out.println(checkModifierPropertiesType(field.getModifiers()) + "," + field.getType().getSimpleName() + "," + field.getName())
        );

    }

    private static String checkModifierPropertiesType(int modifier) {
        if (Modifier.isProtected(modifier)) {
            return "Protected";
        } else if (Modifier.isPrivate(modifier)) {
            return "Private";
        } else if (Modifier.isPublic(modifier)) {
            return "Public";
        }
        return "";
    }
}

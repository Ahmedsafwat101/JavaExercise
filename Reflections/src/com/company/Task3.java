package com.company;

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
        Class<?> myClass = Class.forName(getPrivateFieldValue(path));

        //Make instance of the class constructor
        Object myObjectClass = myClass.getDeclaredConstructor().newInstance();

        // for accessing private field
        Field[] allFields = myClass.getDeclaredFields(); // accessing all fields
        Arrays.stream(allFields).forEach(

                field -> {
                    if (checkModifierPropertiesType(field.getModifiers()).equals("Private")) {
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



    private static String getPrivateFieldValue(String path){
        String fileNameWithOutExt = "com/company/Test.class".replaceFirst("[.][^.]+$", "");
        fileNameWithOutExt = fileNameWithOutExt.replace("/",".");
        System.out.println(fileNameWithOutExt);
        return fileNameWithOutExt;
    }
}

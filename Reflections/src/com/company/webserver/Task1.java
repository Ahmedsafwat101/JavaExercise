package com.company.webserver;

import com.company.HttpMirror;
import com.company.customannotaions.ABBAS;
import com.company.utils.Helper;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Task1 {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {

        Set<Class> classSet = findAllClassesUsingReflectionsLibrary("com.company.tasks");
        for (Class myClass : classSet) {

            System.out.println(myClass.getSimpleName());
            Object myObjectClass = myClass.getDeclaredConstructor().newInstance();
            // Check if the class has my custom annotation
            if (myObjectClass.getClass().isAnnotationPresent(ABBAS.class))
                System.out.println(myClass.getSimpleName() + "has Annotation Abbas :)");

            // accessing all fields
            Field[] publicFields = myObjectClass.getClass().getDeclaredFields();
            Arrays.stream(publicFields).forEach(

                    field -> System.out.println(Helper.checkModifierPropertiesType(field.getModifiers()) + "," + field.getType().getSimpleName() + "," + field.getName())
            );

            System.out.println(myObjectClass);
        }

        HttpMirror.start();

    }

    public static Set<Class> findAllClassesUsingReflectionsLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName, Scanners.SubTypes.filterResultsBy(s -> true));
        return new HashSet<>(reflections.getSubTypesOf(Object.class));
    }

}

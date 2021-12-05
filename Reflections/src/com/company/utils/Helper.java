package com.company.utils;

import java.lang.reflect.Modifier;

public class Helper {
     public static String getPrivateFieldValue(String path){
        String fileNameWithOutExt = path.replaceFirst("[.][^.]+$", ".java");
        fileNameWithOutExt = path.replaceFirst("[.][^.]+$", "src");

         fileNameWithOutExt = fileNameWithOutExt.replace("/",".");
        System.out.println(fileNameWithOutExt);
        return fileNameWithOutExt;
    }

    public static String checkModifierPropertiesType(int modifier) {
        if (Modifier.isProtected(modifier)) {
            return "Protected";
        } else if (Modifier.isPrivate(modifier)) {
            return "Private";
        } else {
            return Modifier.isPublic(modifier) ? "Public" : "";
        }
    }
}

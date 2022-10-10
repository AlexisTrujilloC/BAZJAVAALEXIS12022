package com.wizeline.entregabletres.utils;

import com.wizeline.entregabletres.enums.Lenguaje;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static boolean isNullOrEmpty(Object data) {
        boolean nullOrEmpty = false;
        if (isNull(data)) {
            nullOrEmpty = true;
        } else {
            if (String.valueOf(data).trim().isEmpty()) {
                nullOrEmpty = true;
            }
        }
        return nullOrEmpty;
    }
    public static boolean isNull(Object data) {
        boolean nullOrEmpty = false;
        if (data == null) {
            nullOrEmpty = true;
        }
        return nullOrEmpty;
    }

    public static String getLenguaje(Lenguaje country){
        Map<Lenguaje,String> countries =new HashMap<>();
        countries.put(Lenguaje.SPANISH,"es");
        countries.put(Lenguaje.ENGLISH,"en");
        countries.put(Lenguaje.FRENCH,"fr");
        countries.put(Lenguaje.GERMAN,"de");
        countries.put(Lenguaje.PORTUGUESE,"pt");
        countries.put(Lenguaje.CZECH,"cs");
        return countries.get(country);
    }
}

package br.com.pucminas.apiproducer.utils;

import java.util.Locale;

public final class Helpers {

    public static String getEnumValue(String value) {
        return value != null ? value.replace("_"," ").toLowerCase(Locale.ROOT) : "";
    }

    public static boolean validateStringEquals(String val, String val2){
        return val.toLowerCase(Locale.ROOT).equalsIgnoreCase(
                getEnumValue(val2));
    }

    public static boolean validateIntegerEquals(Integer val, String val2){
        return val.equals(
                Integer.parseInt(val2)
        );
    }

    public static boolean validateIntegerEquals(Integer val, Integer val2){
        return val.equals(
                val2
        );
    }

    private Helpers(){}

}

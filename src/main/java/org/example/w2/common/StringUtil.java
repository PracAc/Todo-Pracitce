package org.example.w2.common;

public class StringUtil {

    public static int getInt(String str, int defaultValue){

        if(str == null || str.length() == 0){
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        }catch (Exception e){
            return defaultValue;
        }
    }
}

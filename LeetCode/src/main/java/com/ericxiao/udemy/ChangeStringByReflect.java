package com.ericxiao.udemy;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class ChangeStringByReflect {

    final static Logger log= Logger.getGlobal();

    public static void main(String args[]){
        try {
            String s = "hello world";
            log.info("s = " + s);
            Field valueFieldOfString = String.class.getDeclaredField("value");
            valueFieldOfString.setAccessible(true);
            char[] value = (char[]) valueFieldOfString.get(s);
            value[5] = '_';
            log.info("s = " + s);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

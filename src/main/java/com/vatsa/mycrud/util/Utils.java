package com.vatsa.mycrud.util;

import java.util.UUID;

public class Utils {
    
    public static String getLogToken() {
        return UUID.randomUUID().toString();
    }
}
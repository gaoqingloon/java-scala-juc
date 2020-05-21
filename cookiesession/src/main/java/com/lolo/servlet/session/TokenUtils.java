package com.lolo.servlet.session;

import java.util.UUID;

public class TokenUtils {

    public static String getToken() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        String token = getToken();
        System.out.println(token);
    }
}

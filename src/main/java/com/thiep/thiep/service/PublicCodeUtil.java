package com.thiep.thiep.service;

import java.security.SecureRandom;

public class PublicCodeUtil {
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom R = new SecureRandom();

    public static String gen(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) sb.append(CHARS.charAt(R.nextInt(CHARS.length())));
        return sb.toString();
    }
}

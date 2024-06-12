package com.xworkz.finalproject.source;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String PASSWORD="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*-+=";
    private static final int PASSWORDLENGTH=16;
    private static final SecureRandom secure=new SecureRandom();
    public static String generatePassword()
    {
        StringBuilder stringBuilder=new StringBuilder(PASSWORDLENGTH);
        for (int i=0;i<PASSWORDLENGTH;i++)
        {
            stringBuilder.append(PASSWORD.charAt(secure.nextInt(PASSWORD.length())));
        }
        return stringBuilder.toString();
    }

}

package com.edsoncosta.paymentSystem.utils;

import java.security.SecureRandom;

public class RandomString {

    private static final String CHARACTERS="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";

    public static String generateRandomString(int length)
    {
        SecureRandom secureRandom=new SecureRandom();
        StringBuilder stringBuilder=new StringBuilder();

        for(int i=0;i<length;i++)
        {
            int index=secureRandom.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(index));
        }

        return stringBuilder.toString();
    }

}

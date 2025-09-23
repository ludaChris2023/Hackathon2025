package com.example.numberdemo;

import java.util.Random;

public class OTPGenerator {
    public static String generate(int digits) {
        //Instantiate Random Number Generator
        Random random = new Random(System.currentTimeMillis());

        //Generate OTP (Adding 4 random digits to otp)
        String otp = "";
        for(int i = 0; i < digits; i++) {
            otp += random.nextInt(10);
        }

        //Return the otp
        return otp;
    }    
}

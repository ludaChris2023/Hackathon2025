package com.example.numberdemo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController

public class NumberController {
    @RequestMapping("/get-OTP")
    public String generateOTP(){
        //Call the OTP Generator and return it
        return OTPGenerator.generate(4);
    }
}

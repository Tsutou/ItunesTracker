package com.example.nijimac103.itunestracker.service.util;

import java.util.Random;

public class CalcUtils {

    public static int getRand(int num) {
        Random rand = new Random();
        return rand.nextInt(num);
    }
}

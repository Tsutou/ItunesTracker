package com.example.nijimac103.itunestracker.service.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

public class LocationUtil {

    public static String getCountry(Context context) {
        Resources rs = context.getResources();
        Configuration cf = rs.getConfiguration();
        String country = cf.locale.getCountry();
        return country;
    }
}

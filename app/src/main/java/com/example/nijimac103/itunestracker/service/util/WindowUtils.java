package com.example.nijimac103.itunestracker.service.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.ContextMenu;
import android.view.Display;

public class WindowUtils {

    public static String getWindowWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        return String.valueOf(point.x);
    }
}

package com.example.nijimac103.itunestracker.service.util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.nijimac103.itunestracker.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class FragmentUtils {

    public static void insertFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                                @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void insertFragmentToActivity(int parentLayoutId, @NonNull FragmentManager fragmentManager,
                                                @NonNull Fragment fragment, String tag) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(parentLayoutId, fragment, tag);
        transaction.commit();
    }

    public static void insertFragmentAddBackStack(int parentLayoutId,
                                                  @NonNull FragmentManager fragmentManager,
                                                  @NonNull Fragment fragment,
                                                  String tag,
                                                  String backStack) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction
                .add(parentLayoutId, fragment, tag)
                .addToBackStack(backStack)
                .replace(parentLayoutId, fragment, null)
                .commit();

    }

    public static void setArgsToFragment(Fragment fragment, String key, String value) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);

        fragment.setArguments(bundle);
    }

    public static String getArgsOfPreFragment(Fragment fragment, String key) {
        Bundle bundle = fragment.getArguments();
        String url = bundle.getString(key);

        return url;
    }
}

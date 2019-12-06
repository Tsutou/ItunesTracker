package jp.co.geisha.itunestracker.service.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import jp.co.geisha.itunestracker.R

import com.google.common.base.Preconditions.checkNotNull

object FragmentUtils {

    fun insertFragmentToActivity(fragmentManager: FragmentManager,
                                 fragment: Fragment, frameId: Int) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }

    fun insertFragmentToActivity(parentLayoutId: Int, fragmentManager: FragmentManager,
                                 fragment: Fragment, tag: String) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.add(parentLayoutId, fragment, tag)
        transaction.commit()
    }

    fun insertFragmentAddBackStack(parentLayoutId: Int,
                                   fragmentManager: FragmentManager,
                                   fragment: Fragment,
                                   tag: String,
                                   backStack: String) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction
                .add(parentLayoutId, fragment, tag)
                .addToBackStack(backStack)
                .replace(parentLayoutId, fragment, null)
                .commit()

    }

    fun setArgsToFragment(fragment: Fragment, key: String, value: String) {
        val bundle = Bundle()
        bundle.putString(key, value)

        fragment.arguments = bundle
    }

    fun getArgsOfPreFragment(fragment: Fragment, key: String): String? {
        val bundle = fragment.arguments!!

        return bundle.getString(key)
    }
}

package jp.co.geisha.diggin.util

import java.util.Random

object CalcUtils {

    fun getRand(num: Int): Int {
        val rand = Random()
        return rand.nextInt(num)
    }
}

package jp.co.geisha.itunestracker.service.util

import java.util.Random

object CalcUtils {

    fun getRand(num: Int): Int {
        val rand = Random()
        return rand.nextInt(num)
    }
}

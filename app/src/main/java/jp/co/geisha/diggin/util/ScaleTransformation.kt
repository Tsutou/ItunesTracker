package jp.co.geisha.diggin.util

import android.graphics.Bitmap
import android.graphics.Matrix

/*
*Picassoの画像倍率を調整するクラス
*/
class ScaleTransformation(private val scale: Float) : com.squareup.picasso.Transformation {

    override fun transform(source: Bitmap?): Bitmap? {
        source ?: return null

        if (scale == 1f) {
            return source
        }

        val matrix = Matrix().apply {
            setScale(
                    scale,
                    scale
            )
        }

        val result = Bitmap.createBitmap(
                source,
                0,
                0,
                source.width,
                source.height,
                matrix,
                true
        )

        source.recycle()

        return result
    }


    override fun key(): String =
            "ScaleTransformation(scale = $scale)"

}
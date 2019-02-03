package jp.co.geisha.itunestracker.service.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import jp.wasabeef.picasso.transformations.CropSquareTransformation

/**
 * PicassoのUtil
 */
object PicassoUtil {

    //そのまま(url)
    fun loadPicasso(url: String, view: ImageView) {

        Picasso.get().load(url).into(view)
    }

    //そのまま + error画像(url)
    fun loadPicasso(url: String, error: Drawable, view: ImageView) {

        Picasso.get().load(url).placeholder(error).error(error).into(view)
    }

    //そのまま(drawableリソース)
    fun loadPicasso(drawableRes: Int, error: Drawable, view: ImageView) {

        Picasso.get().load(drawableRes).placeholder(error).error(error).into(view)
    }

    //四角に切り抜く
    fun loadPicassoWithCropSquareTransformation(url: String, error: Drawable, view: ImageView) {

        Picasso.get().load(url).transform(CropSquareTransformation()).error(error).into(view)
    }

    //三角に切り抜く
    fun loadPicassoWithCropCircleTransformation(url: String, view: ImageView) {

        Picasso.get().load(url).transform(CropCircleTransformation()).into(view)
    }

    //指定したサイズにスケールする
    fun loadPicassoToFit(url: String, error: Drawable, view: ImageView, f: Float) {

        Picasso.get().load(url).transform(ScaleTransformation(f)).error(error).into(view)
    }

}

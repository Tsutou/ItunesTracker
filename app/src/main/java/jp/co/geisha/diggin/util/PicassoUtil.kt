package jp.co.geisha.diggin.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import jp.wasabeef.picasso.transformations.CropSquareTransformation

object PicassoUtil {

    fun loadPicasso(url: String, view: ImageView)
            = Picasso.get().load(url).into(view)

    fun loadPicasso(url: String, error: Drawable, view: ImageView)
            = Picasso.get().load(url).placeholder(error).error(error).into(view)

    fun loadPicasso(drawableRes: Int, error: Drawable, view: ImageView)
            = Picasso.get().load(drawableRes).placeholder(error).error(error).into(view)

    fun loadPicassoWithCropSquareTransformation(url: String, error: Drawable, view: ImageView)
            = Picasso.get().load(url).transform(CropSquareTransformation()).error(error).into(view)

    fun loadPicassoWithCropCircleTransformation(url: String, view: ImageView)
            = Picasso.get().load(url).transform(CropCircleTransformation()).into(view)

    fun loadPicassoToFit(url: String, view: ImageView, f: Float)
            = Picasso.get().load(url).transform(ScaleTransformation(f)).into(view)
}

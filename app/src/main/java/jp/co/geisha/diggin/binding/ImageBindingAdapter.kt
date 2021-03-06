package jp.co.geisha.diggin.binding

import androidx.databinding.BindingAdapter
import android.widget.ImageView
import jp.co.geisha.diggin.util.PicassoUtil

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        PicassoUtil.loadPicasso(imageUrl, view)
    }

    @JvmStatic
    @BindingAdapter("circleImageUrl")
    fun loadImageWithCircleTransform(view: ImageView, imageUrl: String) {
        PicassoUtil.loadPicassoWithCropCircleTransformation(imageUrl, view)
    }
}

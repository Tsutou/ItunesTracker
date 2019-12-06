package jp.co.geisha.itunestracker.service.binding

import androidx.databinding.BindingAdapter
import android.widget.ImageView
import jp.co.geisha.itunestracker.service.util.PicassoUtil

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("circleImageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        PicassoUtil.loadPicassoWithCropCircleTransformation(imageUrl, view)
    }
}

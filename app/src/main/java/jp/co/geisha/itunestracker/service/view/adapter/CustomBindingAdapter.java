package jp.co.geisha.itunestracker.service.view.adapter;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import jp.co.geisha.itunestracker.service.util.PicassoUtil;

public class CustomBindingAdapter {
    //xmlに定義する際のBindingAdapter
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("circleImageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        PicassoUtil.INSTANCE.loadPicassoWithCropCircleTransformation(imageUrl, view);
    }

}

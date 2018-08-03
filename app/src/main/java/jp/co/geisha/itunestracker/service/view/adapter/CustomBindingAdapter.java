package jp.co.geisha.itunestracker.service.view.adapter;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import jp.co.geisha.itunestracker.R;
import jp.co.geisha.itunestracker.service.view.MainActivity;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class CustomBindingAdapter {
    //xmlに定義する際のBindingAdapter
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .transform(new CropCircleTransformation())
                .into(view);
    }

}

package jp.co.geisha.diggin.view

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager

/**
 * ViewPagerに子要素の高さに合わせる実装を実現するための拡張
 * @see <a href="https://stackoverflow.com/questions/8394681/android-i-am-unable-to-have-viewpager-wrap-content">参考リンク</a>
 */
class WrapContentHeightViewPager : ViewPager {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val mHeightMeasureSpec: Int
        var height = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            val h = child.measuredHeight
            if (h > height) height = h
        }

        mHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)

        super.onMeasure(widthMeasureSpec, mHeightMeasureSpec)
    }
}
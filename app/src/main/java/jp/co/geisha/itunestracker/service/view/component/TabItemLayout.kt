package jp.co.geisha.itunestracker.service.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout

class TabItemLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        val params = layoutParams

        if (params is ViewGroup.MarginLayoutParams) {
            if (selected) {
                params.topMargin = 0
            } else {
                params.topMargin = 10
            }
        }
        layoutParams = params
    }
}

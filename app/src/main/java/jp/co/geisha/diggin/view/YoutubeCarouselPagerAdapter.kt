package jp.co.geisha.diggin.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import jp.co.geisha.diggin.api.entity.YouTubeResponse
import jp.co.geisha.diggin.callback.MusicVideoClickCallback


class YoutubeCarouselPagerAdapter : PagerAdapter() {

    private var callback: MusicVideoClickCallback? = null
    private var rows = arrayListOf<YouTubeResponse.Data>()

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

    override fun getCount(): Int = rows.size

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val row = rows[position]
        val itemView = YoutubeCarouselItem(container.context, null)
        return itemView.apply {
//            bindItemData(row)
//            setOnClickListener { callback?.onClick(row) }
//            container.addView(this)
        }
    }

    fun bindData(rows: List<YouTubeResponse.Data>) {
        this.rows.clear()
        this.rows.addAll(rows)
        notifyDataSetChanged()
    }

    fun setCallback(callback: MusicVideoClickCallback) {
        this.callback = callback
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        if (obj is View) {
            container.removeView(obj)
        }
    }

    inner class YoutubeCarouselItem(context: Context, attributeSet: AttributeSet?) : ConstraintLayout(context, attributeSet) {

        init {
//            LayoutInflater.from(context).inflate(R.layout.list_item_store_carousel, this)
        }

        fun bindItemData(row: YouTubeResponse.Data) {

        }
    }
}
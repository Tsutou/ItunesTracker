package jp.co.geisha.diggin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import jp.co.geisha.diggin.R
import jp.co.geisha.diggin.api.entity.YouTubeResponse
import jp.co.geisha.diggin.callback.MusicVideoClickCallback
import jp.co.geisha.diggin.databinding.YoutubeCarouselItemBinding


class YoutubeCarouselPagerAdapter : PagerAdapter() {

    private var clickCallback: MusicVideoClickCallback? = null
    private var rows = arrayListOf<YouTubeResponse.Data>()

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

    override fun getCount(): Int = rows.size

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val row = rows[position]
        val itemView = DataBindingUtil.inflate<YoutubeCarouselItemBinding>(LayoutInflater.from(container.context), R.layout.youtube_carousel_item, container, false)
        return itemView.apply {
            youtubeData = row
            callback = clickCallback
            executePendingBindings()
            container.addView(root)
        }.root
    }

    fun bindData(rows: List<YouTubeResponse.Data>) {
        this.rows.clear()
        this.rows.addAll(rows)
        notifyDataSetChanged()
    }

    fun setCallback(callback: MusicVideoClickCallback) {
        this.clickCallback = callback
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        if (obj is View) {
            container.removeView(obj)
        }
    }
}
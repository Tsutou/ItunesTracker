package jp.co.geisha.diggin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.geisha.diggin.R
import jp.co.geisha.diggin.api.entity.YouTubeResponse
import jp.co.geisha.diggin.callback.MusicVideoClickCallback
import kotlinx.android.synthetic.main.youtube_carousel_item.view.*

class YouTubeCarouselViewHolder(itemView: View, private val listener: MusicVideoClickCallback) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun newInstance(parent: ViewGroup, listener: MusicVideoClickCallback): YouTubeCarouselViewHolder {
            return YouTubeCarouselViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.youtube_carousel_item, parent, false),
                    listener
            )
        }
    }

    fun bindData(data: List<YouTubeResponse.Data>) {
        val pagerAdapter = YoutubeCarouselPagerAdapter()
        itemView.viewPager.apply {
            adapter = pagerAdapter.apply {
                bindData(data)
                setCallback(listener)
            }
            offscreenPageLimit = data.size
        }
        itemView.itemsIndicator.setViewPager(itemView.viewPager)
    }
}
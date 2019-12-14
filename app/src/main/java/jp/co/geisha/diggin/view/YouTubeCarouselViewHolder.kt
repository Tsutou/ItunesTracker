package jp.co.geisha.diggin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.geisha.diggin.R
import jp.co.geisha.diggin.api.entity.YouTubeResponse
import jp.co.geisha.diggin.callback.MusicVideoClickCallback

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
//        itemView.youtubeItemViewPager.apply {
//            adapter = pagerAdapter.apply {
//                bindData(section.rows)
//                setCallback(listener)
//            }
//            offscreenPageLimit = rows.size
//        }
//        itemView.storeItemsIndicator.setViewPager(itemView.storeItemsViewPager)
    }
}
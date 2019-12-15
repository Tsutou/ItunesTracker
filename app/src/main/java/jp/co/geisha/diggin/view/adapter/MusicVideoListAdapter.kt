package jp.co.geisha.diggin.view.adapter

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import jp.co.geisha.diggin.YOUTUBE_CAROUSEL_POSITION
import jp.co.geisha.diggin.databinding.ArtistListItemBinding
import jp.co.geisha.diggin.callback.MusicVideoClickCallback
import jp.co.geisha.diggin.api.entity.ItunesData
import jp.co.geisha.diggin.api.entity.YouTubeResponse
import jp.co.geisha.diggin.view.YouTubeCarouselViewHolder
import androidx.recyclerview.widget.StaggeredGridLayoutManager

internal class MusicVideoListAdapter(private val musicVideoClickCallback: MusicVideoClickCallback)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        YOUTUBE,
        ITUNES
    }

    private var youtubeDataList: ArrayList<YouTubeResponse.Data> = arrayListOf()
    private var itunesDataVideoList: ArrayList<ItunesData> = arrayListOf()

    fun setYoutubeDataList(youtubeDataList: List<YouTubeResponse.Data>) {
        this.youtubeDataList.let { list ->
            list.clear()
            list.addAll(youtubeDataList)
        }
        notifyItemChanged(0)
    }

    fun setItunesDataList(itunesDataList: List<ItunesData>) {
        this.itunesDataVideoList.let { list ->
            list.clear()
            list.addAll(itunesDataList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): RecyclerView.ViewHolder {
        return when (viewtype) {
            ViewType.YOUTUBE.ordinal -> {
                YouTubeCarouselViewHolder.newInstance(parent, musicVideoClickCallback)
            }
            else -> {
                val binding = DataBindingUtil.inflate<ArtistListItemBinding>(LayoutInflater.from(parent.context), jp.co.geisha.diggin.R.layout.artist_list_item, parent, false)
                binding.callback = musicVideoClickCallback
                ArtistViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is YouTubeCarouselViewHolder -> {
                val layoutParams = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
                layoutParams.isFullSpan = true
                holder.bindData(youtubeDataList)
            }
            is ArtistViewHolder -> {
                holder.binding.itunesData = itunesDataVideoList[position]
                holder.binding.executePendingBindings()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            YOUTUBE_CAROUSEL_POSITION -> ViewType.YOUTUBE.ordinal
            else -> ViewType.ITUNES.ordinal
        }
    }

    override fun getItemCount(): Int {
        return itunesDataVideoList.size
    }

    internal class ArtistViewHolder(
            val binding: ArtistListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

package jp.co.geisha.diggin.view.adapter

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import jp.co.geisha.diggin.R
import jp.co.geisha.diggin.databinding.ArtistListItemBinding
import jp.co.geisha.diggin.callback.ArtistClickCallback
import jp.co.geisha.diggin.api.entity.ItunesData

internal class ArtistVideoAdapter(private val artistClickCallback: ArtistClickCallback?)
    : RecyclerView.Adapter<ArtistVideoAdapter.ArtistViewHolder>() {

    private var itunesDataVideoList: ArrayList<ItunesData> = arrayListOf()

    fun setArtistList(itunesDataList: List<ItunesData>) {
        this.itunesDataVideoList.let { list ->
            list.clear()
            list.addAll(itunesDataList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ArtistViewHolder {
        val binding = DataBindingUtil.inflate<ArtistListItemBinding>(LayoutInflater.from(parent.context), R.layout.artist_list_item, parent, false)
        binding.callback = artistClickCallback

        return ArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.binding.artist = itunesDataVideoList[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return itunesDataVideoList.size
    }

    internal class ArtistViewHolder(
            val binding: ArtistListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

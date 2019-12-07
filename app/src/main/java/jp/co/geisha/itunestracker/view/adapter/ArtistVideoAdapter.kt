package jp.co.geisha.itunestracker.view.adapter

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import jp.co.geisha.itunestracker.R
import jp.co.geisha.itunestracker.databinding.ArtistListItemBinding
import jp.co.geisha.itunestracker.callback.ArtistClickCallback
import jp.co.geisha.itunestracker.api.entity.Artist

internal class ArtistVideoAdapter(private val artistClickCallback: ArtistClickCallback?)
    : RecyclerView.Adapter<ArtistVideoAdapter.ArtistViewHolder>() {

    private var artistVideoList: ArrayList<Artist> = arrayListOf()

    fun setArtistList(artistList: List<Artist>) {
        this.artistVideoList.let { list ->
            list.clear()
            list.addAll(artistList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ArtistViewHolder {
        val binding = DataBindingUtil.inflate<ArtistListItemBinding>(LayoutInflater.from(parent.context), R.layout.artist_list_item, parent, false)
        binding.callback = artistClickCallback

        return ArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.binding.artist = artistVideoList[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return artistVideoList.size
    }

    internal class ArtistViewHolder(
            val binding: ArtistListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

package jp.co.geisha.itunestracker.service.view.adapter

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import jp.co.geisha.itunestracker.R
import jp.co.geisha.itunestracker.databinding.ArtistListItemBinding
import jp.co.geisha.itunestracker.service.callback.ArtistClickCallback
import jp.co.geisha.itunestracker.service.model.Artist
import java.util.Objects

internal class ArtistAdapter(private val artistClickCallback: ArtistClickCallback?) : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    private var artistList: ArrayList<Artist> = arrayListOf()

    fun setArtistList(artistList: List<Artist>) {
        this.artistList.clear()
        this.artistList.addAll(artistList)
        notifyDataSetChanged()
    }

    //継承したインナークラスのViewholderをレイアウトとともに生成
    //bindするビューにコールバックを設定 -> ビューホルダーを返す
    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ArtistViewHolder {
        val binding = DataBindingUtil.inflate<ArtistListItemBinding>(LayoutInflater.from(parent.context), R.layout.artist_list_item, parent, false)
        binding.callback = artistClickCallback

        return ArtistViewHolder(binding)
    }


    //ViewHolderをDataBindする
    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.binding.artist = artistList[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    //インナークラスにViewHolderを継承し、project_list_item.xml に対する Bindingを設定
    internal class ArtistViewHolder (val binding: ArtistListItemBinding) : RecyclerView.ViewHolder(binding.root)

}

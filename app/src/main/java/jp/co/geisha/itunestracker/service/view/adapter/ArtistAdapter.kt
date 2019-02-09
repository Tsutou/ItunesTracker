package jp.co.geisha.itunestracker.service.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import jp.co.geisha.itunestracker.R
import jp.co.geisha.itunestracker.databinding.ArtistListItemBinding
import jp.co.geisha.itunestracker.service.callback.ArtistClickCallback
import jp.co.geisha.itunestracker.service.model.Artist
import java.util.Objects

internal class ArtistAdapter(private val artistClickCallback: ArtistClickCallback?) : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    private var artistList: List<Artist>? = null

    fun setArtistList(artistList: List<Artist>, isReload: Boolean?) = if (this.artistList == null) {
        this.artistList = artistList

        //positionStartの位置からitemCountの範囲において、データの変更があったことを登録されているすべてのobserverに通知する。
        notifyItemRangeInserted(0, artistList.size)

    } else {

        val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun getOldListSize(): Int {
                return this@ArtistAdapter.artistList?.size ?: 0
            }

            override fun getNewListSize(): Int {
                return artistList.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return this@ArtistAdapter.artistList!![oldItemPosition].trackId == artistList[newItemPosition].trackId
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                try {
                    val (_, _, _, _, trackId, _, _, _, _, _, _, _, _, previewUrl) = artistList[newItemPosition]
                    val (_, _, _, _, trackId1, _, _, _, _, _, _, _, _, previewUrl1) = artistList[oldItemPosition]

                    return trackId == trackId1 && previewUrl == previewUrl1 && isReload!!
                } catch (e: IndexOutOfBoundsException) {
                    //IndexOutOfBoundsExceptionがたまに起こるのでキャッチしたらfalseを返す
                    e.printStackTrace()
                    return false
                }

            }
        })

        this.artistList = artistList

        result.dispatchUpdatesTo(this)
    }

    //継承したインナークラスのViewholderをレイアウトとともに生成
    //bindするビューにコールバックを設定 -> ビューホルダーを返す
    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ArtistViewHolder {
        val binding = DataBindingUtil
                .inflate<ArtistListItemBinding>(LayoutInflater.from(parent.context), R.layout.artist_list_item, parent, false)

        binding.callback = artistClickCallback

        return ArtistViewHolder(binding)
    }


    //ViewHolderをDataBindする
    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.binding.artist = artistList!![position]
        holder.binding.executePendingBindings()
    }

    //リストのサイズを返す
    override fun getItemCount(): Int {
        return if (artistList == null) 0 else artistList!!.size
    }

    //インナークラスにViewHolderを継承し、project_list_item.xml に対する Bindingを設定
    internal class ArtistViewHolder (val binding: ArtistListItemBinding) : RecyclerView.ViewHolder(binding.root)

}

package com.example.nijimac103.itunestracker.service.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.nijimac103.itunestracker.R;
import com.example.nijimac103.itunestracker.databinding.ArtistListItemBinding;
import com.example.nijimac103.itunestracker.service.callback.ArtistClickCallback;
import com.example.nijimac103.itunestracker.service.model.Artist;

import java.util.List;
import java.util.Objects;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>{

    List<? extends Artist> artistList;

    @Nullable
    private final ArtistClickCallback artistClickCallback;

    public ArtistAdapter(@Nullable ArtistClickCallback articleListCallback){
        this.artistClickCallback = articleListCallback;
    }

    public void setArtistList(final List<? extends Artist> artistList){

        if(this.artistList == null){
            this.artistList = artistList;

            //positionStartの位置からitemCountの範囲において、データの変更があったことを登録されているすべてのobserverに通知する。
            notifyItemRangeInserted(0,artistList.size());

        }else{
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback(){
                @Override
                public int getOldListSize() {
                    return ArtistAdapter.this.artistList.size();
                }

                @Override
                public int getNewListSize() {
                    return artistList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ArtistAdapter.this.artistList.get(oldItemPosition).trackId == artistList.get(newItemPosition).trackId;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Artist artist = artistList.get(newItemPosition);
                    Artist old = artistList.get(oldItemPosition);

                    return artist.trackId == old.trackId && Objects.equals(artist.previewUrl, old.previewUrl);
                }
            });

            this.artistList = artistList;

            result.dispatchUpdatesTo(this);
        }
    }

    //継承したインナークラスのViewholderをレイアウトとともに生成
    //bindするビューにコールバックを設定 -> ビューホルダーを返す
    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        ArtistListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.artist_list_item, parent, false);

        binding.setCallback(artistClickCallback);

        return new ArtistViewHolder(binding);
    }


    //ViewHolderをDataBindする
    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        holder.binding.setArtist(artistList.get(position));
        holder.binding.executePendingBindings();
    }

    //リストのサイズを返す
    @Override
    public int getItemCount() {
        return artistList == null ? 0 : artistList.size();
    }

    //インナークラスにViewHolderを継承し、project_list_item.xml に対する Bindingを設定
    static class ArtistViewHolder extends RecyclerView.ViewHolder {

        final ArtistListItemBinding binding;

        public ArtistViewHolder(ArtistListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
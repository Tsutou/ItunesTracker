package com.example.nijimac103.itunestracker.service.view.adapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import com.example.nijimac103.itunestracker.service.callback.ArtistClickCallback;
import com.example.nijimac103.itunestracker.service.model.Artist;

import java.util.List;
import java.util.Objects;

public class ArtistListAdapter extends RecyclerView.Adapter<ArtistListAdapter.ArtistListViewHolder>{

    List<? extends Artist> artistList;

    @Nullable
    private final ArtistClickCallback artistClickCallback;

    private ArtistListAdapter(@Nullable ArtistClickCallback articleListCallback){
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
                    return ArtistListAdapter.this.artistList.size();
                }

                @Override
                public int getNewListSize() {
                    return artistList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ArtistListAdapter.this.artistList.get(oldItemPosition).trackId == artistList.get(newItemPosition).trackId;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Artist artist = artistList.get(newItemPosition);
                    Artist old = artistList.get(oldItemPosition);

                    return artist.trackId == old.trackId && Objects.equals(artist.previewUrl, old.previewUrl);
                }
            })

        }
    }


}

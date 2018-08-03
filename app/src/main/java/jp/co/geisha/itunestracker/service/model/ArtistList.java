package jp.co.geisha.itunestracker.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistList {
    @SerializedName("results")
    public List<Artist> results;
}

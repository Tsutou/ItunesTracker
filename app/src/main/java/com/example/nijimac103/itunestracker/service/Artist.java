package com.example.nijimac103.itunestracker.service;

public class Artist {
    public String wrapperType;
    public String kind;
    public int artistId;
    public int collectionId;
    public int trackId;
    public String artistName;
    public String collectionName;
    public String trackName;
    public String collectionCensoredName;
    public String trackCensoredName;
    public String artistViewUrl;
    public String collectionViewUrl;
    public String trackViewUrl;
    public String previewUrl;
    public String artworkUrl30;
    public String artworkUrl60;
    public String artworkUrl100;
    public long collectionPrice;
    public long trackPrice;
    public String releaseDate;
    public String collectionExplicitness;
    public int discCount;
    public int discNumber;
    public int trackCount;
    public int trackNumber;
    public int trackTimeMillis;
    public String country;
    public String currency;
    public String primaryGenreName;

    public Artist() {
    }

    public Artist(String name) {
        this.artistName = name;
    }

}

package jp.co.geisha.itunestracker.service.model;

import java.io.Serializable;

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
    public float collectionPrice;
    public float trackPrice;
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

    //    レスポンス事例
    //    {"wrapperType":"track", "kind":"song", "artistId":316069, "collectionId":256936779,
    //      "trackId":256937038, "artistName":"Alicia Keys", "collectionName":"Songs In A Minor",
    //      "trackName":"Fallin'", "collectionCensoredName":"Songs In A Minor", "trackCensoredName":"Fallin'",
    //      "artistViewUrl":"https://itunes.apple.com/us/artist/alicia-keys/316069?uo=4",
    //      "collectionViewUrl":"https://itunes.apple.com/us/album/fallin/256936779?i=256937038&uo=4",
    //      "trackViewUrl":"https://itunes.apple.com/us/album/fallin/256936779?i=256937038&uo=4",
    //      "previewUrl":"https://audio-ssl.itunes.apple.com/apple-assets-us-std-000001/Music/2e/50/11/mzm.eoucdgtx.aac.p.m4a",
    //      "artworkUrl30":"https://is3-ssl.mzstatic.com/image/thumb/Music/v4/a7/51/fa/a751fad8-cc1b-1d20-736a-bca200c88f15/source/30x30bb.jpg",
    //      "artworkUrl60":"https://is3-ssl.mzstatic.com/image/thumb/Music/v4/a7/51/fa/a751fad8-cc1b-1d20-736a-bca200c88f15/source/60x60bb.jpg",
    //      "artworkUrl100":"https://is3-ssl.mzstatic.com/image/thumb/Music/v4/a7/51/fa/a751fad8-cc1b-1d20-736a-bca200c88f15/source/100x100bb.jpg",
    //      "collectionPrice":9.99, "trackPrice":1.29, "releaseDate":"2001-04-02T07:00:00Z", "collectionExplicitness":"notExplicit",
    //      "trackExplicitness":"notExplicit", "discCount":1, "discNumber":1, "trackCount":16, "trackNumber":4, "trackTimeMillis":210200,
    //      "country":"USA", "currency":"USD", "primaryGenreName":"R&B/Soul", "isStreamable":true}

}

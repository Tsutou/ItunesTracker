package jp.co.geisha.itunestracker.service.model

import com.google.gson.annotations.SerializedName

data class ArtistList (
    @SerializedName("results")
    var results: List<Artist>
)
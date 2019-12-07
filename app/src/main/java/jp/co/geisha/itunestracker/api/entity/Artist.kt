package jp.co.geisha.itunestracker.api.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Artist(
        val artistName: String,
        val trackCensoredName: String,
        val previewUrl: String = "",
        val artworkUrl30: String = "",
        val artworkUrl60: String = "",
        val artworkUrl100: String = "",
        val primaryGenreName: String = ""
) {
    @JsonClass(generateAdapter = true)
    data class Result (
            @Json(name = "results")
            val data: List<Artist>
    )
}

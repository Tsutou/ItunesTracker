package jp.co.geisha.diggin.api.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItunesData(
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
            val data: List<ItunesData>
    )
}

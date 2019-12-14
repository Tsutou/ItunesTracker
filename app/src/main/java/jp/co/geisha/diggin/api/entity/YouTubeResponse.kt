package jp.co.geisha.diggin.api.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class YouTubeResponse(
        val items: List<Data>
){
    @JsonClass(generateAdapter = true)
    data class Data(
            val etag: String,
            val id: Id,
            val kind: String,
            val snippet: Snippet
    )

    @JsonClass(generateAdapter = true)
    data class Id(
            val kind: String,
            val videoId: String
    )

    @JsonClass(generateAdapter = true)
    data class Snippet(
            val channelId: String,
            val channelTitle: String,
            val description: String,
            val liveBroadcastContent: String,
            val publishedAt: String,
            val thumbnails: Thumbnails,
            val title: String
    )

    @JsonClass(generateAdapter = true)
    data class Thumbnails(
            val default: Default,
            val high: High,
            val medium: Medium
    ){
        @JsonClass(generateAdapter = true)
        data class Default(
                val height: Int,
                val url: String,
                val width: Int
        )

        @JsonClass(generateAdapter = true)
        data class High(
                val height: Int,
                val url: String,
                val width: Int
        )

        @JsonClass(generateAdapter = true)
        data class Medium(
                val height: Int,
                val url: String,
                val width: Int
        )
    }
}

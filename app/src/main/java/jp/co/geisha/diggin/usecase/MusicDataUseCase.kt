package jp.co.geisha.diggin.usecase

import jp.co.geisha.diggin.api.entity.ItunesData
import jp.co.geisha.diggin.api.entity.YouTubeResponse
import jp.co.geisha.diggin.data.structure.Music

class MusicDataUseCase {

    enum class Type(val rawValue: Int) {
        YOUTUBE(0),
        ITUNES(1)
    }

    fun convert(data: YouTubeResponse.Data) = Music(
            data.id.videoId.hashCode(),
            data.snippet.title,
            data.snippet.thumbnails.high.url,
            data.id.videoId,
            data.snippet.channelTitle,
            Type.YOUTUBE.rawValue
    )

    fun convert(data: ItunesData) = Music(
            data.previewUrl.hashCode(),
            data.trackCensoredName,
            data.artworkUrl100,
            data.previewUrl,
            data.artistName,
            Type.ITUNES.rawValue
    )
}
package jp.co.geisha.diggin.callback

import jp.co.geisha.diggin.api.entity.ItunesData
import jp.co.geisha.diggin.api.entity.YouTubeResponse

interface MusicVideoClickCallback {
    fun onClick(itunesData: ItunesData)
    fun onClick(youtubeData: YouTubeResponse.Data)
}

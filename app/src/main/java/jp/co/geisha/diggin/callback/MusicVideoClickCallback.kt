package jp.co.geisha.diggin.callback

import jp.co.geisha.diggin.api.entity.ItunesData

interface MusicVideoClickCallback {
    fun onClick(itunesData: ItunesData)
}

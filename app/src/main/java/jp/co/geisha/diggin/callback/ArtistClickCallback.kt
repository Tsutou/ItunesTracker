package jp.co.geisha.diggin.callback

import jp.co.geisha.diggin.api.entity.ItunesData

interface ArtistClickCallback {
    fun onClick(itunesData: ItunesData)
}

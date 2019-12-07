package jp.co.geisha.diggin.callback

import jp.co.geisha.diggin.api.entity.Artist

interface ArtistClickCallback {
    fun onClick(artist: Artist)
}

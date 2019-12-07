package jp.co.geisha.itunestracker.callback

import jp.co.geisha.itunestracker.api.entity.Artist

interface ArtistClickCallback {
    fun onClick(artist: Artist)
}

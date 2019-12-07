package jp.co.geisha.itunestracker

//TAG
const val ARTIST_LIST_FRAGMENT_TAG = "ArtistVideoListFragment"
const val ARTIST_TABS_FRAGMENT_TAG = "ArtistTabsFragment"
const val ARTIST_VIDEO_VIEW_FRAGMENT_TAG = "ArtistVideoViewFragment"

//Retrofitインターフェース(APIリクエストを管理)
const val HTTPS_API_ITUNES_URL = "https://itunes.apple.com/"

const val URL = "URL"

//検索制御定数
const val DELAY_MINUTES = 60000
const val MAX_REQUEST_PER_MINUTE = 20
const val ZERO = 0
const val LIMIT = 100
const val MUSIC_VIDEO = "musicVideo"

//広告バナー
const val BANNER_SCRIPT = "" +
        "<div style =\"text-align:center;\">" +
        "<div id='ibb-widget-root'>" +
        "</div>" +
        "</div>" +
        "<script>(function(t,e,i,d){var o=t.getElementById(i)," +
        "n=t.createElement(e);" +
        "o.style.height=50;" +
        "o.style.width=320;" +
        "o.style.display='inline-block';" +
        "n.id='ibb-widget'," +
        "n.setAttribute('src',('https:'===t.location.protocol?'https://':'http://')+d)," +
        "n.setAttribute('width','320')," +
        "n.setAttribute('height','50')," +
        "n.setAttribute('frameborder','0')," +
        "n.setAttribute('scrolling','no'),o.appendChild(n)})" +
        "(document,'iframe','ibb-widget-root',\"banners.itunes.apple.com/banner.html?" +
        "partnerId=" +
        "&aId=" +
        "&bt=promotional" +
        "&at=Music" +
        "&st=apple_music" +
        "&c=jp" +
        "&l=ja-JP" +
        "&w=320" +
        "&h=50" +
        "&rs=1\");" +
        "</script>"

object ConstArrays {
    @JvmField
    val DEFAULT_ARTIST_LIST = arrayOf("alicia keys", "lady gaga", "michael jackson", "beatles", "stevie wonder", "eric clapton", "beyonce", "james brown", "sting", "oasis", "2pac", "Nas", "bob marley", "billy joel", "elton john", "bruno mars", "joe", "justin timberlake", "TLC", "SWV", "blackstreet", "jackson5", "ed sheeran", "boyz 2 men", "india arie", "talor swift", "norah jones", "frank sinatra", "marvin gaye", "mariah carey", "diana ross", "jamiroquai", "john lenon", "tuxedo")
}
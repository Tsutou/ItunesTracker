package jp.co.geisha.itunestracker.service.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import jp.co.geisha.itunestracker.service.view.fragment.ArtistListFragment
import timber.log.Timber

/**
 * Pager Adapter
 *
 */
class ArtistPagerAdapter
/**
 * コンストラクタ
 *
 * @param fragmentManager
 */
(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    /**
     * タブ
     */
    private var rows: List<String>? = null

    init {
        Timber.d("construct")
    }

    /**
     * ページのタイトルを取得
     *
     * @param position ページ番号
     * @return タイトル
     */
    override fun getPageTitle(position: Int): CharSequence? {
        Timber.d("getPageTitle")
        return requireNotNull(rows)[position]
    }

    /**
     * ページ総数の取得
     *
     * @return ページ総数
     */
    override fun getCount(): Int {
        Timber.d("getCount")
        return if (rows != null) {
            requireNotNull(rows).size
        } else {
            0
        }
    }

    /**
     * ページのフラグメントを取得
     *
     * @param position ページ番号
     * @return ページのフラグメント
     */
    override fun getItem(position: Int): Fragment {
        Timber.d("getItem")
        return ArtistListFragment()
    }

    /**
     * 要素の更新
     *
     * @param rows row
     */
    fun updateItems(rows: List<String>) {
        this.rows = rows
        notifyDataSetChanged()
    }

}

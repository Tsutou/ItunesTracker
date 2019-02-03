package jp.co.geisha.itunestracker.service.view.fragment

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.co.geisha.itunestracker.R
import jp.co.geisha.itunestracker.service.util.DigginTabLayoutManager
import jp.co.geisha.itunestracker.service.view.adapter.ArtistPagerAdapter
import timber.log.Timber

/**
 * タブレイアウトの要素出し分け用フラグメントクラス
 *
 * @author katsuya
 * @since 2.0.0
 */
class ArtistTabsFragment : Fragment() {

    private var viewPager: ViewPager? = null
    private var inflater: LayoutInflater? = null
    //    private var viewModel: TabLayoutViewModel? = null
    private var digginTabLayoutManager: DigginTabLayoutManager? = null
    internal var binding: jp.co.geisha.itunestracker.databinding.FragmentTablayoutBinding? = null
    private var tabLayout: TabLayout? = null
    private val targetPosition = 0
//
//    /**
//     * タブ読み込みに失敗した場合、リロードボタンを押した際の処理
//     */
//    private val reloadListener = View.OnClickListener { viewModel!!.refresh(true) }

    companion object {

        val ARTIST_TABS_TAG = "ArtistTabsFragment"

    }

    /**
     * フラグメント生成時に呼ばれる
     *
     * @param context フラグメント生成したアクティビティのコンテキスト
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Timber.d("onAttach")
    }

    /**
     * フラグメントのビューを描画する
     *
     * @param inflater           レイアウトの拡張用
     * @param container          親ビューのUI
     * @param savedInstanceState 保存している状態
     * @return フラグメントを描画したビュー
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("onCreateView")
        this.inflater = inflater
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tablayout, container, false)

        tabLayout = binding?.tabLayout

        viewPager = binding?.pager

        return binding?.root

    }

    /**
     * ActivityのonCreateが呼ばれた直後に呼ばれる
     *
     * @param savedInstanceState 保存している状態
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.d("onActivityCreated")

        // view pagerに出力する要素を設定
        tabLayout!!.setupWithViewPager(viewPager)

        val themesPagerAdapter = ArtistPagerAdapter(childFragmentManager)
        viewPager!!.adapter = themesPagerAdapter

        digginTabLayoutManager = DigginTabLayoutManager(
                this.inflater,
                this.context,
                tabLayout,
                binding?.selectLine
        )

        val rows = listOf<String>("R&B","SOUL","HIPHOP","RAGGAE")

        Timber.d("onTabChanged: %s", rows.size)
        themesPagerAdapter.updateItems(rows)

        if (activity != null && digginTabLayoutManager != null) {
            digginTabLayoutManager?.setTabSelectCallbacks()
            digginTabLayoutManager?.setTabLayout(rows, targetPosition, null, requireNotNull(viewPager))
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Timber.d("onActivityResult:requestCode:$requestCode resultCode:$resultCode")

        if (resultCode == RESULT_OK) {
            Timber.d("onActivityResult:RESULT_OK")
        }
    }

    /**
     * 指定したタブへ遷移する
     *
     * @param pageIndex ターゲットタブID
     */
    fun moveTargetTab(pageIndex: Int) {

        // ビューの描写待ちをしてからタブを移動
        Handler().postDelayed(
                {
                    requireNotNull(viewPager).setCurrentItem(pageIndex, false)
                    requireNotNull(tabLayout).setScrollPosition(pageIndex, 0f, true)
                    requireNotNull(tabLayout).getTabAt(pageIndex)?.select();
                }, 100)

    }
}

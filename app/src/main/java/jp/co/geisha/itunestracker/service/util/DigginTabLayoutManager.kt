package jp.co.geisha.itunestracker.service.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import jp.co.geisha.itunestracker.R
import kotlinx.android.synthetic.main.tab_layout.view.*
import timber.log.Timber

const val TAB_START_POSITION = 0
const val ONLY_DEFAULT_TAB = 1

/**
 * タブレイアウトのUIを制御するクラス
 * @author Tsutou Takehara
 * @args inflater layoutInflater
 * @args activityContext Context
 * @args tabLayout タブレイアウト
 * @args tabLine タブの下線
 */
class DigginTabLayoutManager(private val inflater: LayoutInflater?,
                             activityContext: Context?,
                             private val tabLayout: TabLayout?,
                             private val tabLine: View?) {

    private val context: Context? = activityContext
    private val tabs: ViewGroup? = tabLayout?.getChildAt(TAB_START_POSITION) as ViewGroup

    /**
     * タブレイアウトをセットする
     * @args rows 表示しているデータのRow
     * @args index タブの場所
     * @args listener タブの長押しリスナー
     * @args pager 表示しているタブのViewPager
     */
    @SuppressLint("InflateParams")
    fun setTabLayout(rows: List<String>,
                     index: Int,
                     listener: View.OnLongClickListener?,
                     pager: ViewPager) {

        if (inflater == null || tabLayout == null) {
            return
        }
        //タブが一つしかないときはIndexは0にする
        val pageIndex = if (tabLayout.tabCount == ONLY_DEFAULT_TAB) TAB_START_POSITION else index

        tabLayout.visibility = View.VISIBLE

        // カスタムタブレイアウトの適用
        for (i in TAB_START_POSITION until tabLayout.tabCount) {
            val baseTab = tabLayout.getTabAt(i)
            val newTab = inflater.inflate(R.layout.tab_layout, null)
            val tabText: TextView = newTab.tab_title

            // 場所の指定がある場合そのタブを選択状態に変更
            if (i == pageIndex) {
                setActive(tabText, i, requireNotNull(tabLine))
            } else {
                setInActive(tabText, i, requireNotNull(tabLine))
            }

            tabText.text = rows[i]

            if (baseTab != null) {
                baseTab.customView = newTab
            }

        }

        // listenerがあれば、タブをロングクリックでタブ編集ページへ遷移
        if (listener != null && tabs != null) {
            for (i in TAB_START_POSITION until tabs.childCount) {
                tabs.getChildAt(i).setOnLongClickListener(listener)
            }
        }
        // ページとタブの移動
        moveTargetTab(pageIndex, pager)

    }


    /**
     * タブ選択時
     * @args tabText タブのタイトルテキスト
     * @args position タブの場所
     * @args tabLine タブレイアウトの下線
     */
    private fun setActive(tabText: TextView, position: Int, tabLine: View) {

        if (tabs == null || context == null) {
            return
        }
        tabs.getChildAt(position).setBackgroundResource(R.drawable.shape_select_tab_rounded_corners)

        tabText.setTextAppearance(R.style.TextAppearance_Tabs_Selected)

        tabLine.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
    }

    /**
     * 特集タブ選択時
     * @args tabText タブのタイトルテキスト
     * @args position タブの場所
     * @args tabLine タブレイアウトの下線
     */
    private fun setActiveFeature(tabText: TextView, position: Int, tabLine: View) {
        if (tabs == null || context == null) {
            return
        }
        tabs.getChildAt(position).setBackgroundResource(R.drawable.shape_select_tab_feature_rounded_corners)

        tabText.setTextAppearance(R.style.TextAppearance_Tabs_Selected)

        tabLine.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
    }

    /**
     * タブ未選択時
     * @args tabText タブのタイトルテキスト
     * @args position タブの場所
     * @args tabLine タブレイアウトの下線
     */
    private fun setInActive(tabText: TextView, position: Int, tabLine: View) {
        if (tabs == null || context == null) {
            return
        }
        tabs.getChildAt(position).setBackgroundResource(R.drawable.shape_unselect_tab_rounded_corners)

        tabText.setTextAppearance(R.style.TextAppearance_Tabs)

    }

    /**
     * 指定したタブへ遷移する
     * @args pageIndex タブの位置
     * @args pager 表示しているタブのViewPager
     */
    private fun moveTargetTab(pageIndex: Int, pager: ViewPager) {

        if (tabLayout == null) {
            return
        }
        // ビューの描写待ちをしてからタブを移動
        Handler().postDelayed(
                {
                    pager.setCurrentItem(pageIndex, false)
                    tabLayout.setScrollPosition(pageIndex, 0f, true)
                    //tabLayout.getTabAt(pageIndex).select();
                }, 100)

    }

    /**
     * カスタムタブレイアウトの選択時イベント動作設定
     * @args activity 走っているアクティビティ
     * @args TAG GAのタグ
     */
    fun setTabSelectCallbacks() {
        if (tabLayout == null) {
            return
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {

                val view = tab.customView
                if (view != null) {
                    val tabTitle = view.findViewById<TextView>(R.id.tab_title)

                    setActive(tabTitle, tab.position, requireNotNull(tabLine))

                    Timber.d("addOnTabSelectedListener:onTabSelected:" + tabTitle.text)
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

                val view = tab.customView
                if (view != null) {
                    val tabTitle = view.findViewById<TextView>(R.id.tab_title)

                    setInActive(tabTitle, tab.position, requireNotNull(tabLine))
                    Timber.d("addOnTabSelectedListener:onTabUnselected:" + tabTitle.text)
                }

            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                Timber.d("addOnTabSelectedListener:onTabReselected:")

            }
        })
    }

}
package wizorle.drawer.listview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.yinglan.scrolllayout.ScrollLayout
import kotlinx.android.synthetic.main.activity_list.*
import wizorle.drawer.R
import wizorle.drawer.util.ScreenUtil

/**
 * Created by 何人执笔？ on 2018/7/30.
 * liushengping
 * 站在巨人的肩膀上
 * https://github.com/yingLanNull/ScrollLayout
 */
class ListUpActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initUI()
        setListener()
    }

    private fun initUI(){
        mScrollLayout.setMinOffset(0)
        mScrollLayout.setMaxOffset((ScreenUtil.getScreenHeight(this) /2))
        mScrollLayout.setExitOffset(ScreenUtil.dip2px(this, 40f))
        mScrollLayout.setIsSupportExit(true)
        mScrollLayout.setAllowHorizontalScroll(true)
        mScrollLayout.setOnScrollChangedListener(mOnScrollChangedListener)
        mScrollLayout.getBackground().alpha = 0
        mScrollLayout.setToExit()
        list_view.adapter= ListviewAdapter(this)
    }

    private val mOnScrollChangedListener: ScrollLayout.OnScrollChangedListener=object: ScrollLayout.OnScrollChangedListener {
        override fun onScrollProgressChanged(currentProgress: Float) {
            if (currentProgress >= 0) {
                var precent = 255 * currentProgress
                if (precent > 255) {
                    precent = 255f
                } else if (precent < 0) {
                    precent = 0f
                }
                mScrollLayout.getBackground().alpha = 255 - precent.toInt()
            }
            if (text_foot.visibility == View.VISIBLE)
                text_foot.visibility = View.GONE
        }

        override fun onScrollFinished(currentStatus: ScrollLayout.Status?) {
            if (currentStatus == ScrollLayout.Status.EXIT) {
                text_foot.visibility = View.VISIBLE
            }
        }

        override fun onChildScroll(top: Int) {

        }
    }


    private fun setListener(){
        root.setOnClickListener(View.OnClickListener { mScrollLayout.scrollToExit() })
        text_foot.setOnClickListener(View.OnClickListener { mScrollLayout.scrollToOpen() })
    }
}
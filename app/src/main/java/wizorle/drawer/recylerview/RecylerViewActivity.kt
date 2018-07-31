package wizorle.drawer.recylerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.yinglan.scrolllayout.ScrollLayout
import kotlinx.android.synthetic.main.activity_recylerview.*
import wizorle.drawer.R
import wizorle.drawer.util.ScreenUtil

/**
 * Created by 何人执笔？ on 2018/7/31.
 * liushengping
 * 站在巨人的肩膀上
 * https://github.com/yingLanNull/ScrollLayout
 */

class RecylerViewActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recylerview)
        initUI()
        setListener()
    }


    private fun initUI(){
        scroll_r.setMinOffset(0)
        scroll_r.setMaxOffset((ScreenUtil.getScreenHeight(this)/2) )
        scroll_r.setExitOffset(ScreenUtil.dip2px(this, 50f))
        scroll_r.setIsSupportExit(true)
        scroll_r.setAllowHorizontalScroll(true)
        scroll_r.setOnScrollChangedListener(mOnScrollChangedListener)
        scroll_r.getBackground().alpha = 0
        scroll_r.setToExit()

        recy_view.adapter=RecyclerViewAdapter(this)
        recy_view.layoutManager=(StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL))
    }

    private var mOnScrollChangedListener:ScrollLayout.OnScrollChangedListener=object: ScrollLayout.OnScrollChangedListener {
        override fun onScrollProgressChanged(currentProgress: Float) {
            if (currentProgress >= 0) {
                var precent = 255 * currentProgress
                if (precent > 255) {
                    precent = 255f
                } else if (precent < 0) {
                    precent = 0f
                }
                scroll_r.getBackground().setAlpha(255 - precent.toInt())
            }
            if (text_foot_r.visibility == View.VISIBLE)
                text_foot_r.visibility = View.GONE
        }

        override fun onScrollFinished(currentStatus: ScrollLayout.Status?) {
            if (currentStatus == ScrollLayout.Status.EXIT) {
                text_foot_r.visibility = View.VISIBLE
            }
        }

        override fun onChildScroll(top: Int) {
        }
    }
    private fun setListener(){
        root_r.setOnClickListener(View.OnClickListener {
            scroll_r.scrollToExit()
        })
        text_foot_r.setOnClickListener(View.OnClickListener {
            scroll_r.scrollToOpen()
        })
    }
}
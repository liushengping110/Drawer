package wizorle.drawer.title

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.yinglan.scrolllayout.ScrollLayout
import kotlinx.android.synthetic.main.activity_titleup.*
import wizorle.drawer.R
import wizorle.drawer.util.ScreenUtil
import wizorle.drawer.listview.ListviewAdapter

/**
 * Created by 何人执笔？ on 2018/7/30.
 * liushengping
 * 站在巨人的肩膀上
 * https://github.com/yingLanNull/ScrollLayout
 */
class TitleUpActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_titleup)
        initUI()
        setListener()
    }

    private fun initUI(){
        ScrollLayout.setMinOffset(0)
        ScrollLayout.setMaxOffset((ScreenUtil.getScreenHeight(this) /2))
        ScrollLayout.setExitOffset(ScreenUtil.dip2px(this, 45f))
        ScrollLayout.setIsSupportExit(true)
        ScrollLayout.setAllowHorizontalScroll(true)
        ScrollLayout.setOnScrollChangedListener(mOnScrollChangedListener)
        ScrollLayout.getBackground().alpha = 0
        ScrollLayout.setToExit()
        listview.adapter= ListviewAdapter(this)
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
                ScrollLayout.getBackground().alpha = 255 - precent.toInt()
            }
//            if (text_foot.visibility == View.VISIBLE)
//                text_foot.visibility = View.GONE
        }

        override fun onScrollFinished(currentStatus: ScrollLayout.Status?) {
            if (currentStatus == com.yinglan.scrolllayout.ScrollLayout.Status.EXIT) {
//                text_foot.visibility = View.VISIBLE
            }
        }

        override fun onChildScroll(top: Int) {

        }
    }


    private fun setListener(){
        c_root.setOnClickListener(View.OnClickListener { ScrollLayout.scrollToExit() })//关闭
        text_up_title.setOnClickListener(View.OnClickListener {ScrollLayout.scrollToOpen()  })
    }
}
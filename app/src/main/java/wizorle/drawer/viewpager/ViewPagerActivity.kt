package wizorle.drawer.viewpager

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.yinglan.scrolllayout.ScrollLayout
import kotlinx.android.synthetic.main.activity_viewpager.*
import wizorle.drawer.R

/**
 * Created by 何人执笔？ on 2018/7/30.
 * liushengping
 * 站在巨人的肩膀上
 * https://github.com/yingLanNull/ScrollLayout
 */
class ViewPagerActivity:AppCompatActivity(){

    private var adapter: ViewPagerAdapter?=null;
    private var mAllAddressList:ArrayList<Address>?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)
        initGirlUrl()
        initUI()
        setListener()
    }

    private fun initGirlUrl(){
        mAllAddressList= arrayListOf()
        for (i in 0 until 5){
            var beam= Address()
            beam.imageUrl= Constant.ImageUrl[i]
            beam.desContent= Constant.DesContent[i]
            mAllAddressList!!.add(beam)
        }
    }
    private fun initUI(){
        toolbar.background.alpha=0
        toolbar.setNavigationIcon(R.mipmap.action_bar_return)
        toolbar.setTitle("我是标题")
        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeButtonEnabled(true)

        scroll_down_layout.setOnScrollChangedListener(mOnScroll)
        scroll_down_layout.background.alpha=0

        adapter= ViewPagerAdapter(this, mAllAddressList)
        adapter!!.setOnClickItemListener(onItemClickListener)

        view_pager.adapter=adapter
        view_pager.addOnPageChangeListener(pageOnChangListener)
        text_view.text=mAllAddressList!![0].desContent
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private var mOnScroll:ScrollLayout.OnScrollChangedListener=object: ScrollLayout.OnScrollChangedListener {
        override fun onScrollProgressChanged(currentProgress: Float) {
            if (currentProgress >= 0) {
                var precent = 255 * currentProgress
                if (precent > 255) {
                    precent = 255f
                } else if (precent < 0) {
                    precent = 0f
                }
                scroll_down_layout.getBackground().setAlpha(255 - precent.toInt())
                toolbar.background.alpha = 255 - precent.toInt()
            }
        }

        override fun onScrollFinished(currentStatus: ScrollLayout.Status?) {
            if (currentStatus == ScrollLayout.Status.EXIT) {//关闭

            }
        }

        override fun onChildScroll(top: Int) {

        }
    }

    private var onItemClickListener: ViewPagerAdapter.OnClickItemListenerImpl =object: ViewPagerAdapter.OnClickItemListenerImpl {
        override fun onClickItem(item: View?, position: Int) {
            if (scroll_down_layout.getCurrentStatus() == ScrollLayout.Status.OPENED) {
                scroll_down_layout.scrollToClose()
            } else {
                Toast.makeText(this@ViewPagerActivity,"当前点击"+position,Toast.LENGTH_LONG).show()
            }
        }
    }


    private var pageOnChangListener:ViewPager.OnPageChangeListener=object: ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            text_view.setText(mAllAddressList!!.get(position).getDesContent())
        }
    }


    private fun setListener(){
        btn_open.setOnClickListener(View.OnClickListener {
            scroll_down_layout.scrollToOpen()
        })
        btn_close.setOnClickListener(View.OnClickListener {
            scroll_down_layout.scrollToExit()
        })
    }
}
package wizorle.drawer.half

import android.content.Context
import android.support.v4.view.ViewCompat
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import wizorle.drawer.R

/**
 * Created by 何人执笔？ on 2018/7/27.
 * liushengping
 */
class DrawerLayout(context: Context?, attrs: AttributeSet?): LinearLayout(context, attrs) {

    private var dragHelper:ViewDragHelper?=null;
    private var mDragView: View?=null;
    private var contentView:View?=null;
    private var dragRange:Int?=null;




    private var callBack:ViewDragHelper.Callback=object :ViewDragHelper.Callback(){
        override fun tryCaptureView(child: View?, pointerId: Int): Boolean {
            return child==mDragView
        }

        override fun onViewPositionChanged(changedView: View?, left: Int, top: Int, dx: Int, dy: Int) {
            contentView!!.layout(0, top + mDragView!!.getHeight(), width, top + mDragView!!.getHeight() + dragRange!!)
        }

        override fun clampViewPositionVertical(child: View?, top: Int, dy: Int): Int {
            val topBound = height - dragRange!! - mDragView!!.getHeight()
            val bottomBound = height - mDragView!!.getHeight()
            return Math.min(Math.max(topBound, top), bottomBound)
        }

        override fun getViewVerticalDragRange(child: View?): Int {
            return dragRange!!
        }

        override fun onViewReleased(releasedChild: View?, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            if (yvel > 0) {
                smoothToBottom()
            } else if (yvel < 0) {
                smoothToTop()
            }
        }
    }
    init {
        dragHelper= ViewDragHelper.create(this ,callBack)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        mDragView=findViewById(R.id.dragView);
        contentView=findViewById(R.id.contentView);
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        dragRange = contentView!!.getMeasuredHeight()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        mDragView!!.layout(0, height - mDragView!!.getHeight(), width, height)
        contentView!!.layout(0, height, width, height + dragRange!!)
    }

    override fun onInterceptHoverEvent(event: MotionEvent?): Boolean {
        return dragHelper!!.shouldInterceptTouchEvent(event)
    }

    fun smoothToTop() {
        if (dragHelper!!.smoothSlideViewTo(mDragView, paddingLeft, height - dragRange!! - mDragView!!.getHeight())) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        dragHelper!!.processTouchEvent(event)
        return true
    }

    fun smoothToBottom(){
        if (dragHelper!!.smoothSlideViewTo(mDragView, paddingLeft, height - mDragView!!.getHeight())) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    override fun computeScroll() {
        if (dragHelper!!.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

}
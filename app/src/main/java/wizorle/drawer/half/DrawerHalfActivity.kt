package wizorle.drawer.half

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_drawerhalf.*
import wizorle.drawer.R

/**
 * Created by 何人执笔？ on 2018/7/27.
 * liushengping
 */
class DrawerHalfActivity:AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawerhalf)
        initUI()
    }

    fun initUI(){
        open.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                drawLayout.smoothToTop()
            }
        })
        close.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                drawLayout.smoothToBottom()
            }
        })
    }



}
package wizorle.drawer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import wizorle.drawer.half.DrawerHalfActivity
import wizorle.drawer.listview.ListUpActivity
import wizorle.drawer.recylerview.RecylerViewActivity
import wizorle.drawer.title.TitleUpActivity
import wizorle.drawer.viewpager.ViewPagerActivity

/**
 * Created by 何人执笔？ on 2018/7/27.
 * liushengping
 */
class MainActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    fun initUI(){
        btn_one.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, DrawerHalfActivity::class.java))
        })
        btn_two.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, ListUpActivity::class.java))
        })
        btn_three.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, TitleUpActivity::class.java))
        })
        btn_four.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, ViewPagerActivity::class.java))
        })
        btn_five.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,RecylerViewActivity::class.java))
        })
    }

    private var list:View.OnClickListener=View.OnClickListener {
        Toast.makeText(this,"One",Toast.LENGTH_LONG).show()
    }

    private var listener:View.OnClickListener=object:View.OnClickListener{
        override fun onClick(v: View?) {
            Toast.makeText(this@MainActivity,"Two",Toast.LENGTH_LONG).show()
        }
    }
}
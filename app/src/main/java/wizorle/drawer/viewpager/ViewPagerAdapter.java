package wizorle.drawer.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import wizorle.drawer.R;


public class ViewPagerAdapter extends PagerAdapter {
    private List<Address> url;
    private Context mContext;
    public ViewPagerAdapter(Context context, List<Address> url) {
        mContext = context;
        this.url=url;
    }

    @Override
    public int getCount() {
        return url.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 必须要实现的方法
     * 每次滑动的时实例化一个页面,ViewPager同时加载3个页面,假如此时你正在第二个页面，向左滑动，
     * 将实例化第4个页面
     **/
    public  ImageView imageView;
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        imageView = new ImageView(mContext);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView .setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ((ViewPager) container).addView(imageView);
        Glide.with(mContext).load(url.get(position).getImageUrl()).error(R.mipmap.ic_launcher).into(imageView);
        imageView.setOnClickListener(new onClickListener(position));
        return imageView;
    }


    /**
     * 必须要实现的方法
     * 滑动切换的时销毁一个页面，ViewPager同时加载3个页面,假如此时你正在第二个页面，向左滑动，
     * 将销毁第1个页面
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView imageView = (ImageView) object;
        if (imageView == null){
            return;
        }
        Glide.clear(imageView);
        ((ViewPager) container).removeView(imageView);
    }

    class onClickListener implements View.OnClickListener{
        public int position;
        public onClickListener(int position){
            this.position=position;
        }
        @Override
        public void onClick(View v) {
            if(mOnClickItemListener!=null){
                mOnClickItemListener.onClickItem(v,position);
            }
        }
    }

    public void setOnClickItemListener(OnClickItemListenerImpl onClickItemListener) {
        mOnClickItemListener = onClickItemListener;
    }

    public OnClickItemListenerImpl mOnClickItemListener;
    public interface OnClickItemListenerImpl {
        void onClickItem(View item, int position);
    }
}
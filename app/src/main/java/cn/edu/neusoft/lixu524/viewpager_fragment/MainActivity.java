package cn.edu.neusoft.lixu524.viewpager_fragment;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import cn.edu.neusoft.lixu524.viewpager_fragment.Fragment.Fragment1;
import cn.edu.neusoft.lixu524.viewpager_fragment.Fragment.Fragment2;
import cn.edu.neusoft.lixu524.viewpager_fragment.Fragment.Fragment3;

public class MainActivity extends AppCompatActivity {

    private Fragment[] views;
    private ViewPager viewPager;
    private TextView tv_f1,tv_f2,tv_f3,tv_tab1,tv_tab2,tv_tab3;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
        viewPager.setAdapter(new Adapter(getSupportFragmentManager()));
        setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }

    private void init() {
        final TextView tv_fragment[]={(TextView) findViewById(R.id.tv_f1),(TextView) findViewById(R.id.tv_f2),(TextView) findViewById(R.id.tv_f3)};
        final TextView tv_tab[]={(TextView) findViewById(R.id.tab_1),(TextView) findViewById(R.id.tab_2),(TextView) findViewById(R.id.tab_3)};
        initTab();

        viewPager= (ViewPager) findViewById(R.id.viewPager);
        //监听viewpager的状态：滑动、被选中页、滑动状态改变
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ClearTab();
                tv_fragment[position].setTextColor(getResources().getColor(R.color.tab_color_select));
                tv_tab[position].setBackgroundColor(getResources().getColor(R.color.tab_color_select));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        views=new Fragment[3];
        views[0]= new Fragment1();
        views[1]= new Fragment2();
        views[2]= new Fragment3();
    }

    class Adapter extends FragmentPagerAdapter{
        public Adapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            //返回对应的fragment对象
            return views[position];
        }
        @Override
        public int getCount() {
            return views.length;
        }
    }
    //对tab标签初始化
    public void initTab(){
        String tab_name[]={"页面一","页面二","页面三"};

        tv_f1= (TextView) findViewById(R.id.tv_f1);
        tv_f2= (TextView) findViewById(R.id.tv_f2);
        tv_f3= (TextView) findViewById(R.id.tv_f3);

        tv_f1.setText(tab_name[0]);
        tv_f2.setText(tab_name[1]);
        tv_f3.setText(tab_name[2]);

        tv_tab1= (TextView) findViewById(R.id.tab_1);
        tv_tab2= (TextView) findViewById(R.id.tab_2);
        tv_tab3= (TextView) findViewById(R.id.tab_3);
    }
    //在页面滑动后会改变顶部的标签颜色，那就需要先将标签颜色都改为未选中的样式，再对被选中的页面标签设置选中样式
    public void ClearTab(){
        tv_tab1.setBackgroundColor(getResources().getColor(R.color.tab_color_normal));
        tv_tab2.setBackgroundColor(getResources().getColor(R.color.tab_color_normal));
        tv_tab3.setBackgroundColor(getResources().getColor(R.color.tab_color_normal));
        tv_f1.setTextColor(getResources().getColor(R.color.f_color_normal));
        tv_f2.setTextColor(getResources().getColor(R.color.f_color_normal));
        tv_f3.setTextColor(getResources().getColor(R.color.f_color_normal));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static void setStatusBarColor(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        //取消状态栏透明
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(statusColor);
        //设置系统状态栏处于可见状态
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        //让view不根据系统窗口来调整自己的布局
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
    }
}

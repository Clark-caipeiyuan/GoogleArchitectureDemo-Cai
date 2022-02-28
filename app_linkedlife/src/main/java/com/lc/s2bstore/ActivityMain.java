package com.lc.s2bstore;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lc.s2bstore.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import google.architecture.common.base.ARouterPath;
import google.architecture.common.base.BaseActivity;
import google.architecture.common.base.BaseFragment;
import google.architecture.common.widget.NoScrollViewPager;
/**
 * @ClassName: ActivityMain
 * @Description: 测试主页面
 * @Author: clark
 * @Date: 2022/2/28 5:27 下午
 */
public class ActivityMain extends BaseActivity {

    ActivityMainBinding mainBinding;
    private NoScrollViewPager mPager;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private FragmentAdapter mAdapter;

    public BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.navigation_home) {
                mPager.setCurrentItem(0);
                return true;
            } else if (i == R.id.navigation_dashboard) {
                mPager.setCurrentItem(1);
                return true;
            } else if (i == R.id.navigation_notifications) {
                mPager.setCurrentItem(2);
                return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mainBinding = DataBindingUtil.setContentView(ActivityMain.this, R.layout.activity_main);

         mainBinding.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
         mPager = mainBinding.containerPager;

        BaseFragment fragmentNews = (BaseFragment) ARouter.getInstance().build(ARouterPath.NewsListFgt).navigation();
        BaseFragment fragmentGirls = (BaseFragment) ARouter.getInstance().build( ARouterPath.GirlsListFgt).navigation();
        BaseFragment fragmentAbout = (BaseFragment) ARouter.getInstance().build( ARouterPath.AboutFgt ).navigation();

        mFragments.add(fragmentNews);
        mFragments.add(fragmentGirls);
        mFragments.add(fragmentAbout);

        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mainBinding.setViewPaAdapter(mAdapter);

    }
}

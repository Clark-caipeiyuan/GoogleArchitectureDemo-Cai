package com.lc.s2bstore;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import google.architecture.common.base.BaseFragment;

/**
 * @ClassName: FragmentAdapter
 * @Description: Fragments适配器
 * @Author: clark
 * @Date: 2022/2/28 5:27 下午
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> mFragments;

    public FragmentAdapter(FragmentManager fm, List<BaseFragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }


    @Override
    public int getCount() {

        return  mFragments != null ? mFragments.size() : 0;
    }


}

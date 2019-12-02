package com.example.sojun_timeplanner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sojun_timeplanner.Fragments.FridayFragment;
import com.example.sojun_timeplanner.Fragments.MondayFragment;
import com.example.sojun_timeplanner.Fragments.SaturdayFragment;
import com.example.sojun_timeplanner.Fragments.SundayFragment;
import com.example.sojun_timeplanner.Fragments.ThursdayFragment;
import com.example.sojun_timeplanner.Fragments.TuesdayFragment;
import com.example.sojun_timeplanner.Fragments.WednesdayFragment;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter{
    ArrayList<Fragment> fragments;
    String titles[]=new String[]{"월","화","수","목","금","토","일"};
    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new MondayFragment());
        fragments.add(new TuesdayFragment());
        fragments.add(new WednesdayFragment());
        fragments.add(new ThursdayFragment());
        fragments.add(new FridayFragment());
        fragments.add(new SaturdayFragment());
        fragments.add(new SundayFragment());

    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

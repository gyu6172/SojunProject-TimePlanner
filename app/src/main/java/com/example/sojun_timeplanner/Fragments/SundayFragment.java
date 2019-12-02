package com.example.sojun_timeplanner.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sojun_timeplanner.R;
import com.example.sojun_timeplanner.View.SundayView;

public class SundayFragment extends Fragment {

    public static SundayView sundayView;

    public SundayFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =LayoutInflater.from(getContext()).inflate(R.layout.fragment_sunday,null);
        sundayView = v.findViewById(R.id.SundayView);
        return v;
    }

    public static void draw(float startAngle, float swipeAngle, String color){
        sundayView.setCount(sundayView.getCount()+1);
        sundayView.colorArray.add(color);
        sundayView.startAngleArray.add(startAngle);
        sundayView.swipeAngleArray.add(swipeAngle);
        sundayView.invalidate();
    }
    public void refresh(){
        sundayView.invalidate();
    }
}

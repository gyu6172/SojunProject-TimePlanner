package com.example.sojun_timeplanner.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sojun_timeplanner.R;
import com.example.sojun_timeplanner.View.WednesdayView;

public class WednesdayFragment extends Fragment {

    public static WednesdayView wednesdayView;

    public WednesdayFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =LayoutInflater.from(getContext()).inflate(R.layout.fragment_wednesday,null);
        wednesdayView = v.findViewById(R.id.WednesdayView);
        return v;
    }

    public static void draw(float startAngle, float swipeAngle, String color){
        wednesdayView.setCount(wednesdayView.getCount()+1);
        wednesdayView.colorArray.add(color);
        wednesdayView.startAngleArray.add(startAngle);
        wednesdayView.swipeAngleArray.add(swipeAngle);
        wednesdayView.invalidate();
    }
    public void refresh(){
        wednesdayView.invalidate();
    }
}

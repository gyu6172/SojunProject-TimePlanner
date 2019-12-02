package com.example.sojun_timeplanner.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sojun_timeplanner.R;
import com.example.sojun_timeplanner.View.SaturdayView;

public class SaturdayFragment extends Fragment {

    public static SaturdayView saturdayView;

    public SaturdayFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =LayoutInflater.from(getContext()).inflate(R.layout.fragment_saturday,null);
        saturdayView = v.findViewById(R.id.SatudayView);
        return v;
    }

    public static void draw(float startAngle, float swipeAngle, String color){
        saturdayView.setCount(saturdayView.getCount()+1);
        saturdayView.colorArray.add(color);
        saturdayView.startAngleArray.add(startAngle);
        saturdayView.swipeAngleArray.add(swipeAngle);
        saturdayView.invalidate();
    }
    public void refresh(){
        saturdayView.invalidate();
    }
}

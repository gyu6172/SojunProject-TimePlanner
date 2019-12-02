package com.example.sojun_timeplanner.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sojun_timeplanner.R;
import com.example.sojun_timeplanner.View.ThursdayView;

public class ThursdayFragment extends Fragment {

    public static ThursdayView thursdayView;

    public ThursdayFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =LayoutInflater.from(getContext()).inflate(R.layout.fragment_thursday,null);
        thursdayView = v.findViewById(R.id.ThursdayView);
        return v;
    }

    public static void draw(float startAngle, float swipeAngle, String color){
        thursdayView.setCount(thursdayView.getCount()+1);
        thursdayView.colorArray.add(color);
        thursdayView.startAngleArray.add(startAngle);
        thursdayView.swipeAngleArray.add(swipeAngle);
        thursdayView.invalidate();
    }
    public void refresh(){
        thursdayView.invalidate();
    }
}

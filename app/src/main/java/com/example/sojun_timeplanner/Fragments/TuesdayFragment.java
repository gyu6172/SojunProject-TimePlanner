package com.example.sojun_timeplanner.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sojun_timeplanner.R;
import com.example.sojun_timeplanner.View.TuesdayView;

public class TuesdayFragment extends Fragment {

    public static TuesdayView tuesdayView;

    public TuesdayFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =LayoutInflater.from(getContext()).inflate(R.layout.fragment_tuesday,null);
        tuesdayView = v.findViewById(R.id.TuesdayView);

        return v;
    }
    public static void draw(float startAngle, float swipeAngle, String color){
        tuesdayView.setCount(tuesdayView.getCount()+1);
        tuesdayView.colorArray.add(color);
        tuesdayView.startAngleArray.add(startAngle);
        tuesdayView.swipeAngleArray.add(swipeAngle);
        tuesdayView.invalidate();
    }
    public void refresh(){
        tuesdayView.invalidate();
    }
}

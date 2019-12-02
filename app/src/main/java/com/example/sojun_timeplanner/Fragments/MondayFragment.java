package com.example.sojun_timeplanner.Fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sojun_timeplanner.R;
import com.example.sojun_timeplanner.View.MondayView;

public class MondayFragment extends Fragment {

    public static MondayView mondayView;
    RelativeLayout relativeMemo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public MondayFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =LayoutInflater.from(getContext()).inflate(R.layout.fragment_monday,null);
        mondayView = v.findViewById(R.id.mondayView);
        relativeMemo = v.findViewById(R.id.relativeMemo);
        return v;
    }

    public static void draw(float startAngle, float swipeAngle, String color){
        mondayView.setCount(mondayView.getCount()+1);
        mondayView.colorArray.add(color);
        mondayView.startAngleArray.add(startAngle);
        mondayView.swipeAngleArray.add(swipeAngle);
        mondayView.invalidate();
    }

    public static void refresh(){
        mondayView.invalidate();
    }
}

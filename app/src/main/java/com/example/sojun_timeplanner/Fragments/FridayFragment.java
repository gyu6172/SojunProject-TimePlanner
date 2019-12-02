package com.example.sojun_timeplanner.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sojun_timeplanner.R;
import com.example.sojun_timeplanner.View.FridayView;

public class FridayFragment extends Fragment {

    public static FridayView fridayView;

    public FridayFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =LayoutInflater.from(getContext()).inflate(R.layout.fragment_friday,null);
        fridayView = v.findViewById(R.id.FridayView);
        return v;
    }
    public static void draw(float startAngle, float swipeAngle, String color){
        fridayView.setCount(fridayView.getCount()+1);
        fridayView.colorArray.add(color);
        fridayView.startAngleArray.add(startAngle);
        fridayView.swipeAngleArray.add(swipeAngle);
        fridayView.invalidate();
    }
    public void refresh(){
        fridayView.invalidate();
    }

}

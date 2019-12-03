package com.example.sojun_timeplanner.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.sojun_timeplanner.R;
import com.example.sojun_timeplanner.View.ThursdayView;

import java.util.ArrayList;

public class ThursdayFragment extends Fragment {

    public static ThursdayView thursdayView;
    public static RelativeLayout relativeMemo;
    public static ArrayList<String> memoArray = new ArrayList<String>();
    public static ArrayList fromHourArray = new ArrayList();
    public static ArrayList fromMinuteArray = new ArrayList();
    public static ArrayList toHourArray = new ArrayList();
    public static ArrayList toMinuteArray = new ArrayList();

    public ThursdayFragment() {

    }

    public static RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    public static TextView memoText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =LayoutInflater.from(getContext()).inflate(R.layout.fragment_thursday,null);
        thursdayView = v.findViewById(R.id.ThursdayView);
        relativeMemo = v.findViewById(R.id.relativeMemo);
        return v;
    }

    public static void draw(float startAngle, float swipeAngle, String color){
        thursdayView.setCount(thursdayView.getCount()+1);
        thursdayView.colorArray.add(color);
        thursdayView.startAngleArray.add(startAngle);
        thursdayView.swipeAngleArray.add(swipeAngle);
        thursdayView.invalidate();
    }

    public static void addMemo(int TopMargin, int LeftMargin, View view, String memo){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.leftMargin = LeftMargin;
        params.topMargin = TopMargin;

        relativeMemo.addView(view, params);

        memoArray.add(memo);

    }

    public static String checkMemo(int time){
        int i;
        int fromH, fromM;
        int toH, toM;
        int from, to;
        for (i=0;i<thursdayView.getCount();i++){
            fromH = (int)fromHourArray.get(i);
            fromM = (int)fromMinuteArray.get(i);
            toH = (int)toHourArray.get(i);
            toM = (int)toMinuteArray.get(i);

            from = fromH * 60 + fromM;
            to = toH * 60 + toM;

            if(from < to){
                if(from <= time && time <= to){
                    return memoArray.get(i);
                }
            }
            else{
                if(from <= time || time <= to){
                    return memoArray.get(i);
                }
            }
        }
        return null;
    }
}

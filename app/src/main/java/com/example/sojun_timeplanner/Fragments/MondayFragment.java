package com.example.sojun_timeplanner.Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sojun_timeplanner.MainActivity;
import com.example.sojun_timeplanner.R;
import com.example.sojun_timeplanner.View.MondayView;

import java.util.ArrayList;

public class MondayFragment extends Fragment{

    public static MondayView mondayView;
    public static RelativeLayout relativeMemo;
    public static ArrayList fromHourArray = new ArrayList();
    public static ArrayList fromMinuteArray = new ArrayList();
    public static ArrayList toHourArray = new ArrayList();
    public static ArrayList toMinuteArray = new ArrayList();
    public static ArrayList<String> memoArray = new ArrayList<String>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public MondayFragment() {

    }

    public static RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    public static TextView memoText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =LayoutInflater.from(getContext()).inflate(R.layout.fragment_monday,null);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "ASDADF", Toast.LENGTH_SHORT).show();
            }
        });
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

    public static void addMemo(int TopMargin, int LeftMargin, View view, String memo) {
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
        for (i=0;i<mondayView.getCount();i++){
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

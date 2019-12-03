package com.example.sojun_timeplanner.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.sojun_timeplanner.MainActivity;
import com.example.sojun_timeplanner.R;
import com.example.sojun_timeplanner.View.ThursdayView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ThursdayFragment extends Fragment {

    public static ThursdayView thursdayView;
    public static RelativeLayout relativeMemo;
    public static ArrayList<String> memoArray = new ArrayList<String>();
    public static ArrayList fromHourArray = new ArrayList();
    public static ArrayList fromMinuteArray = new ArrayList();
    public static ArrayList toHourArray = new ArrayList();
    public static ArrayList toMinuteArray = new ArrayList();
    public static ArrayList<TextView> textViewArray = new ArrayList<TextView>();

    MainActivity mainActivity;

    public ThursdayFragment() {

    }

    public static RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    public static TextView memoText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =LayoutInflater.from(getContext()).inflate(R.layout.fragment_thursday,null);
        thursdayView = v.findViewById(R.id.ThursdayView);
        relativeMemo = v.findViewById(R.id.relativeMemo);
        v.setOnClickListener(view -> {
            SimpleDateFormat HOUR = new SimpleDateFormat("HH");
            SimpleDateFormat MINUTE = new SimpleDateFormat("mm");

            int format_HOUR = Integer.parseInt(HOUR.format(System.currentTimeMillis()));
            int format_MINUTE = Integer.parseInt(MINUTE.format(System.currentTimeMillis()));

            int format = format_HOUR*60 + format_MINUTE;

            int i;

            int fromhour, fromminute;
            int tohour, tominute;

            int from, to;

            for (i=0;i<thursdayView.getCount();i++){
                fromhour = (int)fromHourArray.get(i);
                fromminute = (int)fromMinuteArray.get(i);
                tohour = (int)toHourArray.get(i);
                tominute = (int)toMinuteArray.get(i);

                from = fromhour*60 + fromminute;
                to = tohour*60 + tominute;

                if(from < to){
                    if(from <= format && format <= to){
                        mainActivity.setMemoTextView(memoArray.get(i));
                    }
                }
                else {
                    if (from <= format || format <= to) {
                        mainActivity.setMemoTextView(memoArray.get(i));
                    }
                }
            }

        });
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
}

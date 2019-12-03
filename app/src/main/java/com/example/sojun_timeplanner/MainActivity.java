package com.example.sojun_timeplanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sojun_timeplanner.Fragments.FridayFragment;
import com.example.sojun_timeplanner.Fragments.MondayFragment;
import com.example.sojun_timeplanner.Fragments.SaturdayFragment;
import com.example.sojun_timeplanner.Fragments.SundayFragment;
import com.example.sojun_timeplanner.Fragments.ThursdayFragment;
import com.example.sojun_timeplanner.Fragments.TuesdayFragment;
import com.example.sojun_timeplanner.Fragments.WednesdayFragment;
import com.example.sojun_timeplanner.View.FridayView;
import com.example.sojun_timeplanner.View.MondayView;
import com.example.sojun_timeplanner.View.SaturdayView;
import com.example.sojun_timeplanner.View.SundayView;
import com.example.sojun_timeplanner.View.ThursdayView;
import com.example.sojun_timeplanner.View.TuesdayView;
import com.example.sojun_timeplanner.View.WednesdayView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapter mAdapter;

    Spinner color_Spinner;
    TimePicker from_Timepicker;
    TimePicker to_Timepicker;

    TextInputEditText memoEditText;
    Button add_Button;
    Button delete_Button;

    String color;
    String memo;
    String textColor;
    int marginTop, marginLeft;
    int fromTimeWrittenMinute;
    int toTimeWrittenMinute;
    int middleTimeOfFromTo;

    int from_hour, from_minute;
    int to_hour, to_minute;
    int textViewSize;
    int pagerPosition;

    ConstraintLayout constraintLayout;
    ConstraintLayout parentConstraint;

    public static TextView memoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.ViewPager);

        color_Spinner = findViewById(R.id.color_spinner);
        from_Timepicker = findViewById(R.id.from_timepicker);
        to_Timepicker = findViewById(R.id.to_timepicker);
        memoEditText = findViewById(R.id.memo_input);
        add_Button = findViewById(R.id.add_Button);
        parentConstraint = findViewById(R.id.parentConstraint);
        memoTextView = findViewById(R.id.memoText);

        delete_Button = findViewById(R.id.deleteBtn);

        constraintLayout = findViewById(R.id.ConstraintLayout);

        viewPager.bringToFront();
        color_Spinner.bringToFront();

        mAdapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(7);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                memoTextView.setText("");

            }

            @Override
            public void onPageSelected(int position) {
                pagerPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });



        add_Button.setOnClickListener(v->{
            color = color_Spinner.getSelectedItem().toString();
            memo = memoEditText.getText().toString();

            from_hour = from_Timepicker.getHour();
            from_minute = from_Timepicker.getMinute();

            to_hour = to_Timepicker.getHour();
            to_minute = to_Timepicker.getMinute();

            textViewSize = setTextSize(from_hour, to_hour);
            textColor = setTextColor(color,textViewSize);

            Toast.makeText(this, textColor+textViewSize, Toast.LENGTH_SHORT).show();

            fromTimeWrittenMinute = from_hour*60 + from_minute;
            toTimeWrittenMinute = to_hour*60 + to_minute;

            middleTimeOfFromTo = middle_of_time(fromTimeWrittenMinute, toTimeWrittenMinute);

            marginTop = setMarginTOP(middleTimeOfFromTo/60, middleTimeOfFromTo%60);
            marginLeft = setMarginLeft(middleTimeOfFromTo/60, middleTimeOfFromTo%60);



            TextView textView = new TextView(getApplicationContext());
            textView.setTextColor(Color.parseColor(textColor));
            textView.setTextSize(textViewSize);
            textView.setText(memo);

            float startAngle = circleAngle(from_hour,from_minute);
            float endAngle = circleAngle(to_hour,to_minute);
            float swipeAngle = swipeAngle(startAngle,endAngle);

            switch(pagerPosition){
                case 0:
                    MondayFragment.fromHourArray.add(from_hour);
                    MondayFragment.fromMinuteArray.add(from_minute);
                    MondayFragment.toHourArray.add(to_hour);
                    MondayFragment.toMinuteArray.add(to_minute);
                    MondayFragment.textViewArray.add(textView);

                    MondayFragment.draw(startAngle,swipeAngle,color);
                    MondayFragment.addMemo(marginTop,marginLeft,textView, memo);
                    break;

                case 1:
                    TuesdayFragment.fromHourArray.add(from_hour);
                    TuesdayFragment.fromMinuteArray.add(from_minute);
                    TuesdayFragment.toHourArray.add(to_hour);
                    TuesdayFragment.toMinuteArray.add(to_minute);
                    TuesdayFragment.textViewArray.add(textView);

                    TuesdayFragment.draw(startAngle,swipeAngle,color);
                    TuesdayFragment.addMemo(marginTop,marginLeft,textView, memo);
                    break;

                case 2:
                    WednesdayFragment.fromHourArray.add(from_hour);
                    WednesdayFragment.fromMinuteArray.add(from_minute);
                    WednesdayFragment.toHourArray.add(to_hour);
                    WednesdayFragment.toMinuteArray.add(to_minute);
                    WednesdayFragment.textViewArray.add(textView);

                    WednesdayFragment.draw(startAngle,swipeAngle,color);
                    WednesdayFragment.addMemo(marginTop,marginLeft,textView, memo);
                    break;

                case 3:
                    ThursdayFragment.fromHourArray.add(from_hour);
                    ThursdayFragment.fromMinuteArray.add(from_minute);
                    ThursdayFragment.toHourArray.add(to_hour);
                    ThursdayFragment.toMinuteArray.add(to_minute);
                    ThursdayFragment.textViewArray.add(textView);

                    ThursdayFragment.draw(startAngle,swipeAngle,color);
                    ThursdayFragment.addMemo(marginTop,marginLeft,textView, memo);
                    break;

                case 4:
                    FridayFragment.fromHourArray.add(from_hour);
                    FridayFragment.fromMinuteArray.add(from_minute);
                    FridayFragment.toHourArray.add(to_hour);
                    FridayFragment.toMinuteArray.add(to_minute);
                    FridayFragment.textViewArray.add(textView);

                    FridayFragment.draw(startAngle,swipeAngle,color);
                    FridayFragment.addMemo(marginTop,marginLeft,textView, memo);
                    break;

                case 5:
                    SaturdayFragment.fromHourArray.add(from_hour);
                    SaturdayFragment.fromMinuteArray.add(from_minute);
                    SaturdayFragment.toHourArray.add(to_hour);
                    SaturdayFragment.toMinuteArray.add(to_minute);
                    SaturdayFragment.textViewArray.add(textView);

                    SaturdayFragment.draw(startAngle,swipeAngle,color);
                    SaturdayFragment.addMemo(marginTop,marginLeft,textView, memo);
                    break;

                case 6:
                    SundayFragment.fromHourArray.add(from_hour);
                    SundayFragment.fromMinuteArray.add(from_minute);
                    SundayFragment.toHourArray.add(to_hour);
                    SundayFragment.toMinuteArray.add(to_minute);
                    SundayFragment.textViewArray.add(textView);

                    SundayFragment.draw(startAngle,swipeAngle,color);
                    SundayFragment.addMemo(marginTop,marginLeft,textView, memo);
                    break;
            }
        });

        delete_Button.setOnClickListener(v->{

            CharSequence[] list;

            switch (pagerPosition){
                case 0:
                    list = (CharSequence[]) MondayFragment.memoArray.toArray(new String[MondayFragment.memoArray.size()]);
                    break;

                case 1:
                    list = (CharSequence[]) TuesdayFragment.memoArray.toArray(new String[TuesdayFragment.memoArray.size()]);
                    break;

                case 2:
                    list = (CharSequence[]) WednesdayFragment.memoArray.toArray(new String[WednesdayFragment.memoArray.size()]);
                    break;

                case 3:
                    list = (CharSequence[]) ThursdayFragment.memoArray.toArray(new String[ThursdayFragment.memoArray.size()]);
                    break;

                case 4:
                    list = (CharSequence[]) FridayFragment.memoArray.toArray(new String[FridayFragment.memoArray.size()]);
                    break;

                case 5:
                    list = (CharSequence[]) SaturdayFragment.memoArray.toArray(new String[SaturdayFragment.memoArray.size()]);
                    break;

                default:
                    list = (CharSequence[]) SundayFragment.memoArray.toArray(new String[SundayFragment.memoArray.size()]);
                    break;
            }

            final List SelectedItems = new ArrayList();

            int defaulitems = 0;
            SelectedItems.add(defaulitems);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("계획 삭제하기");

            builder.setSingleChoiceItems(list, defaulitems, (dialogInterface, which) -> {
                SelectedItems.clear();
                SelectedItems.add(which);
            });

            switch (pagerPosition){
                case 0:
                    builder.setPositiveButton("삭제", (dialogInterface, which) -> {

                        int index = (int) SelectedItems.get(0);

                        for (int i=0;i<MondayFragment.memoArray.size();i++){

                            if(MondayFragment.memoArray.get(index) == MondayFragment.memoArray.get(i)){
                                MondayFragment.fromHourArray.remove(i);
                                MondayFragment.fromMinuteArray.remove(i);
                                MondayFragment.toHourArray.remove(i);
                                MondayFragment.toMinuteArray.remove(i);

                                MondayFragment.memoArray.remove(i);
                                MondayFragment.textViewArray.get(i).setText("");
                                MondayFragment.textViewArray.remove(i);

                                MondayFragment.mondayView.colorArray.remove(i);
                                MondayFragment.mondayView.startAngleArray.remove(i);
                                MondayFragment.mondayView.swipeAngleArray.remove(i);
                                MondayFragment.mondayView.setCount(MondayFragment.mondayView.getCount()-1);
                                MondayFragment.mondayView.invalidate();
                            }

                        }
                        Toast.makeText(MainActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    });
                    builder.setNegativeButton("취소", (dialogInterface, which) -> {
                    });
                    builder.show();
                    break;

                case 1:
                    builder.setPositiveButton("삭제", (dialogInterface, which) -> {

                        int index = (int) SelectedItems.get(0);

                        for (int i=0;i<TuesdayFragment.memoArray.size();i++){

                            if(TuesdayFragment.memoArray.get(index) == TuesdayFragment.memoArray.get(i)){
                                TuesdayFragment.fromHourArray.remove(i);
                                TuesdayFragment.fromMinuteArray.remove(i);
                                TuesdayFragment.toHourArray.remove(i);
                                TuesdayFragment.toMinuteArray.remove(i);

                                TuesdayFragment.memoArray.remove(i);
                                TuesdayFragment.textViewArray.get(i).setText("");
                                TuesdayFragment.textViewArray.remove(i);

                                TuesdayFragment.tuesdayView.colorArray.remove(i);
                                TuesdayFragment.tuesdayView.startAngleArray.remove(i);
                                TuesdayFragment.tuesdayView.swipeAngleArray.remove(i);
                                TuesdayFragment.tuesdayView.setCount(TuesdayFragment.tuesdayView.getCount()-1);
                                TuesdayFragment.tuesdayView.invalidate();
                            }

                        }
                        Toast.makeText(MainActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    });
                    builder.setNegativeButton("취소", (dialogInterface, which) -> {
                    });
                    builder.show();
                    break;


                case 2:
                    builder.setPositiveButton("삭제", (dialogInterface, which) -> {

                        int index = (int) SelectedItems.get(0);

                        for (int i=0;i<WednesdayFragment.memoArray.size();i++){

                            if(WednesdayFragment.memoArray.get(index) == WednesdayFragment.memoArray.get(i)){
                                WednesdayFragment.fromHourArray.remove(i);
                                WednesdayFragment.fromMinuteArray.remove(i);
                                WednesdayFragment.toHourArray.remove(i);
                                WednesdayFragment.toMinuteArray.remove(i);

                                WednesdayFragment.memoArray.remove(i);
                                WednesdayFragment.textViewArray.get(i).setText("");
                                WednesdayFragment.textViewArray.remove(i);

                                WednesdayFragment.wednesdayView.colorArray.remove(i);
                                WednesdayFragment.wednesdayView.startAngleArray.remove(i);
                                WednesdayFragment.wednesdayView.swipeAngleArray.remove(i);
                                WednesdayFragment.wednesdayView.setCount(WednesdayFragment.wednesdayView.getCount()-1);
                                WednesdayFragment.wednesdayView.invalidate();
                            }

                        }
                        Toast.makeText(MainActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    });
                    builder.setNegativeButton("취소", (dialogInterface, which) -> {
                    });
                    builder.show();
                    break;


                case 3:
                    builder.setPositiveButton("삭제", (dialogInterface, which) -> {

                        int index = (int) SelectedItems.get(0);

                        for (int i=0;i<ThursdayFragment.memoArray.size();i++){

                            if(ThursdayFragment.memoArray.get(index) == ThursdayFragment.memoArray.get(i)){
                                ThursdayFragment.fromHourArray.remove(i);
                                ThursdayFragment.fromMinuteArray.remove(i);
                                ThursdayFragment.toHourArray.remove(i);
                                ThursdayFragment.toMinuteArray.remove(i);

                                ThursdayFragment.memoArray.remove(i);
                                ThursdayFragment.textViewArray.get(i).setText("");
                                ThursdayFragment.textViewArray.remove(i);

                                ThursdayFragment.thursdayView.colorArray.remove(i);
                                ThursdayFragment.thursdayView.startAngleArray.remove(i);
                                ThursdayFragment.thursdayView.swipeAngleArray.remove(i);
                                ThursdayFragment.thursdayView.setCount(ThursdayFragment.thursdayView.getCount()-1);
                                ThursdayFragment.thursdayView.invalidate();
                            }

                        }
                        Toast.makeText(MainActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    });
                    builder.setNegativeButton("취소", (dialogInterface, which) -> {
                    });
                    builder.show();
                    break;


                case 4:
                    builder.setPositiveButton("삭제", (dialogInterface, which) -> {

                        int index = (int) SelectedItems.get(0);

                        for (int i=0;i<FridayFragment.memoArray.size();i++){

                            if(FridayFragment.memoArray.get(index) == FridayFragment.memoArray.get(i)){
                                FridayFragment.fromHourArray.remove(i);
                                FridayFragment.fromMinuteArray.remove(i);
                                FridayFragment.toHourArray.remove(i);
                                FridayFragment.toMinuteArray.remove(i);

                                FridayFragment.memoArray.remove(i);
                                FridayFragment.textViewArray.get(i).setText("");
                                FridayFragment.textViewArray.remove(i);

                                FridayFragment.fridayView.colorArray.remove(i);
                                FridayFragment.fridayView.startAngleArray.remove(i);
                                FridayFragment.fridayView.swipeAngleArray.remove(i);
                                FridayFragment.fridayView.setCount(FridayFragment.fridayView.getCount()-1);
                                FridayFragment.fridayView.invalidate();
                            }

                        }
                        Toast.makeText(MainActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    });
                    builder.setNegativeButton("취소", (dialogInterface, which) -> {
                    });
                    builder.show();
                    break;


                case 5:
                    builder.setPositiveButton("삭제", (dialogInterface, which) -> {

                        int index = (int) SelectedItems.get(0);

                        for (int i=0;i<SaturdayFragment.memoArray.size();i++){

                            if(SaturdayFragment.memoArray.get(index) == SaturdayFragment.memoArray.get(i)){
                                SaturdayFragment.fromHourArray.remove(i);
                                SaturdayFragment.fromMinuteArray.remove(i);
                                SaturdayFragment.toHourArray.remove(i);
                                SaturdayFragment.toMinuteArray.remove(i);

                                SaturdayFragment.memoArray.remove(i);
                                SaturdayFragment.textViewArray.get(i).setText("");
                                SaturdayFragment.textViewArray.remove(i);

                                SaturdayFragment.saturdayView.colorArray.remove(i);
                                SaturdayFragment.saturdayView.startAngleArray.remove(i);
                                SaturdayFragment.saturdayView.swipeAngleArray.remove(i);
                                SaturdayFragment.saturdayView.setCount(SaturdayFragment.saturdayView.getCount()-1);
                                SaturdayFragment.saturdayView.invalidate();
                            }

                        }
                        Toast.makeText(MainActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    });
                    builder.setNegativeButton("취소", (dialogInterface, which) -> {
                    });
                    builder.show();
                    break;


                case 6:
                    builder.setPositiveButton("삭제", (dialogInterface, which) -> {

                        int index = (int) SelectedItems.get(0);

                        for (int i=0;i<SundayFragment.memoArray.size();i++){

                            if(SundayFragment.memoArray.get(index) == SundayFragment.memoArray.get(i)){
                                SundayFragment.fromHourArray.remove(i);
                                SundayFragment.fromMinuteArray.remove(i);
                                SundayFragment.toHourArray.remove(i);
                                SundayFragment.toMinuteArray.remove(i);

                                SundayFragment.memoArray.remove(i);
                                SundayFragment.textViewArray.get(i).setText("");
                                SundayFragment.textViewArray.remove(i);

                                SundayFragment.sundayView.colorArray.remove(i);
                                SundayFragment.sundayView.startAngleArray.remove(i);
                                SundayFragment.sundayView.swipeAngleArray.remove(i);
                                SundayFragment.sundayView.setCount(SundayFragment.sundayView.getCount()-1);
                                SundayFragment.sundayView.invalidate();
                            }

                        }
                        Toast.makeText(MainActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    });
                    builder.setNegativeButton("취소", (dialogInterface, which) -> {
                    });
                    builder.show();
                    break;


            }
        });
    }

    private float circleAngle(int hour, int minute){    //시작시간을 받아 canvas의 시작 각도를 리턴하는 함수
        if(hour>=6){
            return (float) ((hour-6)*15 + (0.25*minute));
        }
        else{
            return (float) ((hour*15)+270 + (0.25*minute));
        }
    }

    private float swipeAngle(float startAngle, float EndAngle){  //startAngle과 EndAngle을 받으면 canvas의 swipeAngle을 리턴하는 함수
        if(startAngle > EndAngle){
            return (360-startAngle) + EndAngle;
        }

        else{
            return EndAngle - startAngle;
        }
    }

    public int middle_of_time(int from, int to){        //시작시간과 종료시간을 분 단위로 입력받아 중간시간을 분 단위로 출력

        int tmp = 1440-from+to;

        if (from <= to){
            return (from + to)/2;
        }

        else{
            if(1440-from > to){
                return from+(tmp/2);
            }
            else if(1440-from < to){
                return to-(tmp/2);
            }

            else{
                return 0;
            }
        }
    }

    public String setTextColor(String backgroundColor, int textViewSize){

        if(textViewSize <= 20){
            return "BLACK";
        }

        else{
            if(backgroundColor == "RED" || backgroundColor == "BLUE" || backgroundColor == "MAGENTA"){
                return "WHITE";
            }
            else{
                return "BLACK";
            }
        }

    }

    public int setMarginTOP(int hour, int minute){
        if(0<hour && hour<12){
            return (int) ((hour*50) + 200 + (minute*0.83));
        }
        else if(12<hour && hour<24){
            return (int) ((24 - hour)*50 + 200 + (minute * 0.83));
        }
        else if(hour == 0){
            return 200;
        }
        else{
            return 800;
        }
    }

    public int setMarginLeft(int hour, int minute){
        if(6<hour && hour<18){
            return (int) ((18-hour)*50 + 200 + (minute*0.83));
        }
        else if(18<hour || hour<6){
            if(hour>18){
                return (int) ((hour-18)*50 + 200 + (minute*0.83));
            }
            else if(hour<6){
                return (int) ((hour*50)+500 + (minute*0.83));
            }
        }
        else if(hour == 18){
            return 200;
        }
        else if(hour == 6){
            return 800;
        }
        return 0;
    }

    public int setTextSize(int from_hour, int to_hour){
        if(to_hour > from_hour){
            int gap = to_hour-from_hour;
            if(gap >= 6){
                return 30;
            }
            else if (gap >= 4) {
                return 25;
            }
            else if(gap >= 2){
                return 20;
            }
        }
        else if(to_hour < from_hour){
            int gap = 24-to_hour + from_hour;
            if(gap >= 6){
                return 30;
            }
            else if (gap >= 4) {
                return 25;
            }
            else if(gap >= 2){
                return 20;
            }
        }
        return 20;
    }

    public static void setMemoTextView(String memo){
        memoTextView.setText(memo);
    }

}

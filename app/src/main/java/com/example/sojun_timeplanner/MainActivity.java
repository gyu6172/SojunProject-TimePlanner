package com.example.sojun_timeplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapter mAdapter;

    Spinner color_Spinner;
    TimePicker from_Timepicker;
    TimePicker to_Timepicker;

    TextInputEditText memoEditText;
    Button add_Button;

    MondayView mondayView;
    TuesdayView tuesdayView;
    WednesdayView wednesdayView;
    ThursdayView thursdayView;
    FridayView fridayView;
    SaturdayView saturdayView;
    SundayView sundayView;

    MondayFragment mondayFragment;
    TuesdayFragment tuesdayFragment;
    WednesdayFragment wednesdayFragment;
    ThursdayFragment thursdayFragment;
    FridayFragment fridayFragment;
    SaturdayFragment saturdayFragment;
    SundayFragment sundayFragment;

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

    Button updateBtn;
    String nowMemo;
    TextView nowTodo;


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
        nowTodo = findViewById(R.id.memoText);

        updateBtn = findViewById(R.id.updateBtn);

        constraintLayout = findViewById(R.id.ConstraintLayout);

        viewPager.bringToFront();
        color_Spinner.bringToFront();

        mAdapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);



        updateBtn.setOnClickListener(v->{
            SimpleDateFormat HOUR = new SimpleDateFormat("HH");
            SimpleDateFormat MINUTE = new SimpleDateFormat("mm");

            int format_HOUR = Integer.parseInt(HOUR.format(System.currentTimeMillis()));
            int format_MINUTE = Integer.parseInt(MINUTE.format(System.currentTimeMillis()));

            switch (pagerPosition){
                case 0:
                    nowMemo = MondayFragment.checkMemo(format_HOUR*60+format_MINUTE);
                    nowTodo.setText(nowMemo);
                    break;

                case 1:
                    nowMemo = TuesdayFragment.checkMemo(format_HOUR*60+format_MINUTE);
                    nowTodo.setText(nowMemo);
                    break;

                case 2:
                    nowMemo = WednesdayFragment.checkMemo(format_HOUR*60+format_MINUTE);
                    nowTodo.setText(nowMemo);
                    break;

                case 3:
                    nowMemo = ThursdayFragment.checkMemo(format_HOUR*60+format_MINUTE);
                    nowTodo.setText(nowMemo);
                    break;

                case 4:
                    nowMemo = FridayFragment.checkMemo(format_HOUR*60+format_MINUTE);
                    nowTodo.setText(nowMemo);
                    break;

                case 5:
                    nowMemo = SaturdayFragment.checkMemo(format_HOUR*60+format_MINUTE);
                    nowTodo.setText(nowMemo);
                    break;

                case 6:
                    nowMemo = SundayFragment.checkMemo(format_HOUR*60+format_MINUTE);
                    nowTodo.setText(nowMemo);
                    break;
            }



        });




        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                nowTodo.setText("");
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
            textColor = setTextColor(color);

            from_hour = from_Timepicker.getHour();
            from_minute = from_Timepicker.getMinute();

            to_hour = to_Timepicker.getHour();
            to_minute = to_Timepicker.getMinute();

            fromTimeWrittenMinute = from_hour*60 + from_minute;
            toTimeWrittenMinute = to_hour*60 + to_minute;

            middleTimeOfFromTo = middle_of_time(fromTimeWrittenMinute, toTimeWrittenMinute);

            marginTop = setMarginTOP(middleTimeOfFromTo/60, middleTimeOfFromTo%60);
            marginLeft = setMarginLeft(middleTimeOfFromTo/60, middleTimeOfFromTo%60);

            textViewSize = setTextSize(from_hour, to_hour);

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

                    MondayFragment.draw(startAngle,swipeAngle,color);
                    MondayFragment.addMemo(marginTop,marginLeft,textView, memo);
                    break;
                case 1:
                    TuesdayFragment.draw(startAngle,swipeAngle,color);
                    TuesdayFragment.addMemo(marginTop,marginLeft,textView, memo);
                    break;
                case 2:
                    WednesdayFragment.draw(startAngle,swipeAngle,color);
                    WednesdayFragment.addMemo(marginTop,marginLeft,textView, memo);
                    break;
                case 3:
                    ThursdayFragment.draw(startAngle,swipeAngle,color);
                    ThursdayFragment.addMemo(marginTop,marginLeft,textView, memo);
                    break;
                case 4:
                    FridayFragment.draw(startAngle,swipeAngle,color);
                    FridayFragment.addMemo(marginTop,marginLeft,textView, memo);
                    break;
                case 5:
                    SaturdayFragment.draw(startAngle,swipeAngle,color);
                    SaturdayFragment.addMemo(marginTop,marginLeft,textView, memo);
                    break;
                case 6:
                    SundayFragment.draw(startAngle,swipeAngle,color);
                    SundayFragment.addMemo(marginTop,marginLeft,textView, memo);
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

    public String setTextColor(String backgroundColor){

        switch (backgroundColor){

            case "RED":

            case "BLUE":
                return "WHITE";

            case "GREEN":

            case "YELLOW":

            case "CYAN":

            case "GRAY":
                return "BLACK";

            default:    //MAGENTA
                return "WHITE";


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
        return 0;
    }
}

package com.example.sojun_timeplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
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
    int from_hour, from_minute;
    int to_hour, to_minute;
    int pagerPosition;

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

        color_Spinner.bringToFront();

        mAdapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

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
                Toast.makeText(MainActivity.this, ""+MondayFragment.mondayView.getCount(), Toast.LENGTH_SHORT).show();
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

            float startAngle = circleAngle(from_hour,from_minute);
            float endAngle = circleAngle(to_hour,to_minute);
            float swipeAngle = swipeAngle(startAngle,endAngle);

            switch(pagerPosition){
                case 0:
                    MondayFragment.draw(startAngle,swipeAngle,color);
                    break;
                case 1:
                    TuesdayFragment.draw(startAngle,swipeAngle,color);
                    break;
                case 2:
                    WednesdayFragment.draw(startAngle,swipeAngle,color);
                    break;
                case 3:
                    ThursdayFragment.draw(startAngle,swipeAngle,color);
                    break;
                case 4:
                    FridayFragment.draw(startAngle,swipeAngle,color);
                    break;
                case 5:
                    SaturdayFragment.draw(startAngle,swipeAngle,color);
                    break;
                case 6:
                    SundayFragment.draw(startAngle,swipeAngle,color);
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

    public int marginLeft(int middle){

        switch (middle){

            case 0:
                return 584;

            case 1:
                return 648;

            case 2:
                return 712;

            case 3:
                return 776;

            case 4:
                return 840;

            case 5:
                return 904;

            case 6:
                return 968;

            case 7:
                return 904;

            case 8:
                return 840;

            case 9:
                return 776;

            case 10:
                return 712;

            case 11:
                return 648;

            case 12:
                return 584;

            case 13:
                return 520;

            case 14:
                return 456;

            case 15:
                return 392;

            case 16:
                return 328;

            case 17:
                return 264;

            case 18:
                return 200;

            case 19:
                return 264;

            case 20:
                return 328;

            case 21:
                return 392;

            case 22:
                return 456;

            default:
                return 520;
        }
    }

    public int marginTop(int middle){

        switch (middle){

            case 0:
                return 200;

            case 1:
                return 264;

            case 2:
                return 328;

            case 3:
                return 392;

            case 4:
                return 456;

            case 5:
                return 520;

            case 6:
                return 584;

            case 7:
                return 648;

            case 8:
                return 712;

            case 9:
                return 776;

            case 10:
                return 840;

            case 11:
                return 904;

            case 12:
                return 968;

            case 13:
                return 904;

            case 14:
                return 840;

            case 15:
                return 776;

            case 16:
                return 712;

            case 17:
                return 648;

            case 18:
                return 584;

            case 19:
                return 520;

            case 20:
                return 456;

            case 21:
                return 392;

            case 22:
                return 328;

            default:
                return 264;
        }
    }



}

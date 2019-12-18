package com.example.sadafx.tasky_proj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction transaction;

    Button sat;
    Button sun;
    Button mon;
    Button tue;
    Button wed;
    Button thu;
    Button fri;

    ImageView menu;

    Animation openMenu;
    Animation closeMenu;

    FrameLayout menuFrame;

    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_WEEK);

        findViews();
        animationUtils();
        firstShowToday();
        onClicks();
    }

    void findViews(){
        sat = (Button) findViewById(R.id.seven);
        sun = (Button) findViewById(R.id.one);
        mon = (Button) findViewById(R.id.two);
        tue = (Button) findViewById(R.id.three);
        wed = (Button) findViewById(R.id.four);
        thu = (Button) findViewById(R.id.five);
        fri = (Button) findViewById(R.id.six);

        menu = (ImageButton) findViewById(R.id.menu);
        menuFrame = (FrameLayout) findViewById(R.id.menu_frame);
    }

    void animationUtils(){
        openMenu = AnimationUtils.loadAnimation(this, R.anim.open_menu);
        closeMenu = AnimationUtils.loadAnimation(this, R.anim.close_menu);
    }

    void onClicks(){
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuFrame.getVisibility()==View.VISIBLE) {
                    menuFrame.startAnimation(closeMenu);
                    menuFrame.setVisibility(View.GONE);
                    menu.setScaleY(1);
                }
                else {
                    menuFrame.setVisibility(View.VISIBLE);
                    menuFrame.startAnimation(openMenu);
                    menu.setScaleY(-1);
                }
            }
        });
    }

    void colorDayButtonsToDefault(){
        sat.setBackgroundResource(R.drawable.button_day_not_clicked);
        sun.setBackgroundResource(R.drawable.button_day_not_clicked);
        mon.setBackgroundResource(R.drawable.button_day_not_clicked);
        tue.setBackgroundResource(R.drawable.button_day_not_clicked);
        wed.setBackgroundResource(R.drawable.button_day_not_clicked);
        thu.setBackgroundResource(R.drawable.button_day_not_clicked);
        fri.setBackgroundResource(R.drawable.button_day_not_clicked);
    }

    void colorDayButtonsTextToDefault(){
        sat.setTextColor(Color.parseColor("#000000"));
        sun.setTextColor(Color.parseColor("#000000"));
        mon.setTextColor(Color.parseColor("#000000"));
        tue.setTextColor(Color.parseColor("#000000"));
        wed.setTextColor(Color.parseColor("#000000"));
        thu.setTextColor(Color.parseColor("#000000"));
        fri.setTextColor(Color.parseColor("#000000"));
    }

    void past(){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, new PreviousDayFragment());
        transaction.commit();
    }

    void today(){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, new TodayFragment());
        transaction.commit();
    }

    void future(){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, new NextDayFragment());
        transaction.commit();
    }

    void firstShowToday(){
        if (day == 7){
            sat.setText("Today");
            sat.setTextColor(Color.parseColor("#ffffff"));
            sat.setBackgroundResource(R.drawable.button_day_clicked);
            today();
        }
        else if (day == 1){
            sun.setText("Today");
            sun.setTextColor(Color.parseColor("#ffffff"));
            sun.setBackgroundResource(R.drawable.button_day_clicked);
            today();
        }
        else if (day == 2){
            mon.setText("Today");
            mon.setTextColor(Color.parseColor("#ffffff"));
            mon.setBackgroundResource(R.drawable.button_day_clicked);
            today();
        }
        else if (day == 3){
            tue.setText("Today");
            tue.setTextColor(Color.parseColor("#ffffff"));
            tue.setBackgroundResource(R.drawable.button_day_clicked);
            today();
        }
        else if (day == 4){
            wed.setText("Today");
            wed.setTextColor(Color.parseColor("#ffffff"));
            wed.setBackgroundResource(R.drawable.button_day_clicked);
            today();
        }
        else if (day == 5){
            thu.setText("Today");
            thu.setTextColor(Color.parseColor("#ffffff"));
            thu.setBackgroundResource(R.drawable.button_day_clicked);
            today();
        }
        else {
            fri.setText("Today");
            fri.setTextColor(Color.parseColor("#ffffff"));
            fri.setBackgroundResource(R.drawable.button_day_clicked);
            today();
        }
    }

    public void DayButton(View v){

        Button b = (Button)v;
        String id = b.getResources().getResourceEntryName(v.getId());

        if (id.equals("seven")){

            colorDayButtonsToDefault();
            colorDayButtonsTextToDefault();
            sat.setBackgroundResource(R.drawable.button_day_clicked);
            sat.setTextColor(Color.parseColor("#ffffff"));

            if (day == 7){
                today();
            }
            else {
                past();
                Log.d("3", "past okay");
            }

        }
        else if (id.equals("one")){

            colorDayButtonsToDefault();
            colorDayButtonsTextToDefault();
            sun.setBackgroundResource(R.drawable.button_day_clicked);
            sun.setTextColor(Color.parseColor("#ffffff"));

            if (day == 7){
                future();
            }
            else if (day == 1){
                today();
            }
            else {
                past();
            }
        }
        else if (id.equals("two")){

            colorDayButtonsToDefault();
            colorDayButtonsTextToDefault();
            mon.setBackgroundResource(R.drawable.button_day_clicked);
            mon.setTextColor(Color.parseColor("#ffffff"));

            if (day == 7 || day == 1){
                future();
            }
            else if (day == 2){
                today();
            }
            else {
                past();
            }

        }
        else if (id.equals("three")){

            colorDayButtonsToDefault();
            colorDayButtonsTextToDefault();
            tue.setBackgroundResource(R.drawable.button_day_clicked);
            tue.setTextColor(Color.parseColor("#ffffff"));

            if (day == 7 || day == 1 || day == 2){
                future();
            }
            else if (day == 3){
                today();
            }
            else {
                past();
            }

        }
        else if (id.equals("four")){

            colorDayButtonsToDefault();
            colorDayButtonsTextToDefault();
            wed.setBackgroundResource(R.drawable.button_day_clicked);
            wed.setTextColor(Color.parseColor("#ffffff"));

            if (day == 5 || day == 6){
                past();
            }
            else if (day == 4){
                today();
            }
            else {
                future();
            }

        }
        else if (id.equals("five")){

            colorDayButtonsToDefault();
            colorDayButtonsTextToDefault();
            thu.setBackgroundResource(R.drawable.button_day_clicked);
            thu.setTextColor(Color.parseColor("#ffffff"));

            if (day == 6){
                past();
            }
            else if (day == 5){
                today();
            }
            else {
                future();
            }

        }
        else {

            colorDayButtonsToDefault();
            colorDayButtonsTextToDefault();
            fri.setBackgroundResource(R.drawable.button_day_clicked);
            fri.setTextColor(Color.parseColor("#ffffff"));

            if (day == 6){
                today();
            }
            else {
                future();
            }

        }
    }
}

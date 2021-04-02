package com.happiness.todaysout.src;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.happiness.todaysout.R;

public class GuideActivity extends BaseActivity {

    TabLayout info;
    ImageView btn_x;
    ImageView natural;
    ImageView society;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        info = findViewById(R.id.infoTablayout);
        btn_x = findViewById(R.id.btn_x);
        btn_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        natural = findViewById(R.id.natural);
        society = findViewById(R.id.society);

        info.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //탭이 선택되었을 때

                int pos = tab.getPosition();
                if (pos == 0) {
                   //자연재난 선택
                    natural.setVisibility(View.VISIBLE);
                    society.setVisibility(View.GONE);

                } else if (pos == 1) {
                    //사회재난 선택
                    natural.setVisibility(View.GONE);
                    society.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //탭이 선택되지 않았을 때
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //탭이 다시 선택되었을 때
            }
        });



    }
}
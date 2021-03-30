package com.softsquared.todaysout.src.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.softsquared.todaysout.R;
import com.softsquared.todaysout.src.BaseActivity;
import com.softsquared.todaysout.src.main.MainActivity;

public class SplashFirstActivity extends BaseActivity {
    TextView btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_first);

        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(getApplication(), LoginActivity.class);
                startActivity(next);
                //SplashActivity.this.finish();
            }
        });
    }
}
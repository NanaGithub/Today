package com.example.jnn.today.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.jnn.today.R;

/**
 * 主题设置
 */
public class ThemeActivity extends AppCompatActivity {

    private int theme = R.style.ThemeActivityLight;
    private final String THEME_KEY = "currentTheme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            theme = savedInstanceState.getInt(THEME_KEY);
            setTheme(theme);
        }
        setContentView(R.layout.activity_theme);
        ImageView ivTheme = findViewById(R.id.iv_theme);
        if (theme == R.style.ThemeActivityLight) {
            ivTheme.setImageResource(R.drawable.ic_theme_weixiao_light);
        } else {
            ivTheme.setImageResource(R.drawable.ic_theme_weixiao_night);
        }
        ivTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThemeActivity.this, BActivity.class));
            }
        });
    }


    public void switchTheme(View view) {
        theme = (theme == R.style.ThemeActivityLight) ? R.style.ThemeActivityNight : R.style.ThemeActivityLight;
        this.recreate();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(THEME_KEY, theme);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt(THEME_KEY);
    }
}

package com.example.l.wanshaapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.l.wanshaapp.R;

public class SearchApp extends AppCompatActivity{
    private ImageView searchimageview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.searchapplayout);
        searchimageview=(ImageView)findViewById(R.id.searchimageview);
        searchimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchDealActivity.class);
                startActivity(intent);
            }
        });

    }
}

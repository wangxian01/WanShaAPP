package com.example.l.wanshaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class shezhi_activity extends   AppCompatActivity {


       private TextView guanyuwomen;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shezhi);



        guanyuwomen=(TextView)findViewById(R.id.guanyuwomen) ;



        guanyuwomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(shezhi_activity.this,guanyuwomen.class);
                startActivity(intent);

            }
        });



    }


   }
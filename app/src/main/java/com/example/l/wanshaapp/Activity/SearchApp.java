package com.example.l.wanshaapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.l.wanshaapp.R;

public class SearchApp extends AppCompatActivity{
    private ImageView searchimageview;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.searchapplayout);
        searchimageview=(ImageView)findViewById(R.id.searchimageview);
        editText=(EditText)findViewById(R.id.searchname) ;


        searchimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String searchkeyword=editText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), SearchDealActivity.class);
                intent.putExtra("searchkeyword",searchkeyword);
                startActivity(intent);
            }
        });

    }
}

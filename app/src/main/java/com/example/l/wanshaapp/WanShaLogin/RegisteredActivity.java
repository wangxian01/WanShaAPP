package com.example.l.wanshaapp.WanShaLogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.l.wanshaapp.R;


public class RegisteredActivity extends AppCompatActivity {
    private ImageView mRegisteredReturn;
    private EditText mRegisteredUsername;
    private EditText mRegisteredPhone;
    private EditText mRegisteredPassword;
    private EditText mRegisteredConfirm;
    private Button mRegisteredButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        mRegisteredReturn = (ImageView) findViewById(R.id.registered_return);
        mRegisteredUsername = (EditText) findViewById(R.id.registered_username);
        mRegisteredPhone = (EditText) findViewById(R.id.registered_phone);
        mRegisteredPassword = (EditText) findViewById(R.id.registered_password);
        mRegisteredConfirm = (EditText) findViewById(R.id.registered_confirm);
        mRegisteredButton = (Button) findViewById(R.id.registered_button);

        mRegisteredReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

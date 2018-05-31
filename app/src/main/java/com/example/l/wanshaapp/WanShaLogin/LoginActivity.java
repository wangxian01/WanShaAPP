package com.example.l.wanshaapp.WanShaLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.HomeBannerActivity;
import com.example.l.wanshaapp.R;


public class LoginActivity extends AppCompatActivity {
    private Button mLoginButton;
    private ImageView mLoginReturn;
    private LoginCircularImageView mLoginImg;
    private EditText mUserName;
    private EditText mUserPassword;
    private CheckBox mRememberPassword;
    private TextView mLoginRegistered;
    private TextView mLoginForget;
    private ImageView mImgQqlogin;
    private ImageView mImgWxlogin;
    private ImageView mImgWblogin;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginReturn = (ImageView) findViewById(R.id.login_return);
        mLoginImg = (LoginCircularImageView) findViewById(R.id.login_img);
        mUserName = (EditText) findViewById(R.id.user_name);
        mUserPassword = (EditText) findViewById(R.id.user_password);
        mRememberPassword = (CheckBox) findViewById(R.id.remember_password);
        mLoginRegistered = (TextView) findViewById(R.id.login_registered);
        mLoginForget = (TextView) findViewById(R.id.login_forget);
        mImgQqlogin = (ImageView) findViewById(R.id.img_qqlogin);
        mImgWxlogin = (ImageView) findViewById(R.id.img_wxlogin);
        mImgWblogin = (ImageView) findViewById(R.id.img_wblogin);
        mLoginButton = (Button) findViewById(R.id.login_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomeBannerActivity.class);
                startActivity(intent);
            }
        });

        mLoginRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisteredActivity.class);
                startActivity(intent);
            }
        });


    }
}

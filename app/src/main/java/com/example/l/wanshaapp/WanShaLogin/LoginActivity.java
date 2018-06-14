package com.example.l.wanshaapp.WanShaLogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.HomeBannerActivity;
import com.example.l.wanshaapp.R;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;


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

    private String username, password;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {      //接收其他子线程的消息
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    new AlertDialog.Builder(LoginActivity.this).setMessage("用户登录失败，账号或密码错误").create().show();
                    break;

                case 1:
                    new AlertDialog.Builder(LoginActivity.this).setMessage( "欢迎  "+ msg.obj.toString() ).create().show();
                    Intent intent = new Intent(LoginActivity.this, HomeBannerActivity.class);
                    intent.putExtra("username",msg.obj.toString());
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
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
                username = mUserName.getText().toString();
                password = mUserPassword.getText().toString();

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpUtils
                                .get()
                                .url("http://"+getApplicationContext().getString(R.string.netip)+":8080/AndroidServers/WanShaLoginServlet")
                                .addParams("username", username)
                                .addParams("password", password)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Request request, Exception e) {
                                        Log.e(TAG, "网络错误");
                                    }

                                    @Override
                                    public void onResponse(String response) {

                                        if (response.toString() != null) {

                                            try {
                                                JSONObject jsonObject = new JSONObject(response.toString());
                                                String name = jsonObject.getString("username");
                                                Message msg = new Message();
                                                msg.what = 1;
                                                msg.obj = name;
                                                mHandler.sendMessage(msg);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            Message msg = new Message();
                                            msg.what = 0;
                                            mHandler.sendMessage(msg);
                                        }
                                    }
                                });

                    }
                });
                thread.start();
            }
        });

        mLoginRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisteredActivity.class);
                startActivity(intent);
            }
        });


    }
}

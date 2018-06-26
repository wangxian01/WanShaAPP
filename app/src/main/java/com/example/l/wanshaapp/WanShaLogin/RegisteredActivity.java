package com.example.l.wanshaapp.WanShaLogin;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.l.wanshaapp.DynamicCommentDetails.ReplayBean;
import com.example.l.wanshaapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


public class RegisteredActivity extends AppCompatActivity {
    private ImageView mRegisteredReturn;
    private EditText mRegisteredUsername;
    private EditText mRegisteredPassword;
    private EditText mRegisteredConfirm;
    private Button mRegisteredButton;
    private OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        mRegisteredReturn = (ImageView) findViewById(R.id.registered_return);
        mRegisteredUsername = (EditText) findViewById(R.id.registered_username);
        mRegisteredPassword = (EditText) findViewById(R.id.registered_password);
        mRegisteredConfirm = (EditText) findViewById(R.id.registered_confirm);
        mRegisteredButton = (Button) findViewById(R.id.registered_button);

        mRegisteredReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRegisteredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            String restult = post("http://"+getString(R.string.netip)+":8080/AndroidServers/UserInfoServlet","");
                            Gson gson = new Gson();
//                            ArrayList<UserBean> userBeans = gson.fromJson(restult,new TypeToken<ArrayList<UserBean>>() {
//                            }.getType());
                            Log.e("测试user：", String.valueOf(restult));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                final String username = mRegisteredUsername.getText().toString();
                final String password = mRegisteredPassword.getText().toString();
//                Log.e("测试name：", String.valueOf(mRegisteredUsername.getText()));
//                Log.e("测试id：",  String.valueOf(mRegisteredId.getText()));
//                Log.e("测试password：",  String.valueOf(mRegisteredPassword.getText()));
                if(TextUtils.isEmpty(mRegisteredUsername.getText())){
                    new AlertDialog.Builder(RegisteredActivity.this)
                            .setTitle("用户名不能为空！")
                            .setMessage("请输入用户名！")
                            .setPositiveButton("确定", null)
                            .show();
                }else {
                    if(TextUtils.isEmpty(mRegisteredPassword.getText())){
                        new AlertDialog.Builder(RegisteredActivity.this)
                                .setTitle("密码不能为空！")
                                .setMessage("请输入密码！")
                                .setPositiveButton("确定", null)
                                .show();
                    }else {

                        if(String.valueOf(mRegisteredPassword.getText()).equals(String.valueOf(mRegisteredConfirm.getText()))){
                            Thread threads = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        OkHttpUtils
                                                .get()
                                                .url("http://"+getApplicationContext().getString(R.string.netip)+":8080/AndroidServers/RegisterServlet")
                                                .addParams("username", username)
                                                .addParams("password", password)
                                                .build().execute();

                                        new AlertDialog.Builder(RegisteredActivity.this)
                                                .setTitle("注册成功！")
                                                .setPositiveButton("确定", null)
                                                .show();
                                        mRegisteredUsername.setText("");
                                        mRegisteredConfirm.setText("");
                                        mRegisteredPassword.setText("");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                            thread.start();

                        }else{
                            new AlertDialog.Builder(RegisteredActivity.this)
                                    .setTitle("两次密码输入不一致！")
                                    .setMessage("请重新输入！")
                                    .setPositiveButton("确定", null)
                                    .show();
                        }
                    }

                }

            }
        });
    }

    /**
     * post请求*/
    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}

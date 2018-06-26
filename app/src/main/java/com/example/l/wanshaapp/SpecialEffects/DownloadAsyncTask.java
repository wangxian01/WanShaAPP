package com.example.l.wanshaapp.SpecialEffects;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.l.wanshaapp.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;


/*created by 谭林
*
* 2018.6.26
*
* */
@SuppressWarnings("deprecation")
public class DownloadAsyncTask extends AsyncTask<String,Integer,Integer> {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    private NotificationCompat.Builder notificationBuilder;
    public DownloadAsyncTask(Context context){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }
        this.context = context;
       // notificationManager = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
        //builder = new NotificationCompat.Builder(context);
/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager on_manmanger =  (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channelbody = new NotificationChannel("1","消息推送",NotificationManager.IMPORTANCE_DEFAULT);
            on_manmanger.createNotificationChannel(channelbody);
            Notification.Builder notifi = new Notification.Builder(context);
            android.app.Notification notifi_true = notifi.build();
            on_manmanger.notify(1,notifi_true);
        }
*/

        String name = "my_package_channel";//渠道名字
        String id = "my_package_channel_1"; // 渠道ID
        String description = "my_package_first_channel"; // 渠道解释说明
        PendingIntent pendingIntent;//非紧急意图，可设置可不设置

        if (notificationManager == null) {
            notificationManager =  (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        //判断是否是8.0上设备
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = null;
            mChannel = new NotificationChannel(id, name, importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true); //是否在桌面icon右上角展示小红点
            notificationManager.createNotificationChannel(mChannel);
            notificationBuilder = new NotificationCompat.Builder(context);
      /*      intent = new Intent(this, DownloadService.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);*/

/*         notificationBuilder.
                    setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("你说8.0以上")
                    .setContentText("正在下载......")
                    //.setContentIntent(pendingIntent)
                    .setChannelId(id)
                    .setAutoCancel(true);*/
        }else{
            notificationBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.mianlogo)
                    .setContentTitle("8.0以下")
                    .setContentText("8.0以下正在下载......")
                    .setAutoCancel(true);

        }
       // notificationManager.notify(0, notificationBuilder.build());

    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentInfo("下载中...")
                .setChannelId("my_package_channel_1")
                .setAutoCancel(true)
                .setContentTitle("正在下载...");
        notificationManager.notify(0x3, notificationBuilder.build());
    }

    @Override
    protected Integer doInBackground(String... params) {
        Log.e(TAG, "doInBackground: "+params[0] );
        InputStream is = null;
        OutputStream os = null;
        HttpURLConnection connection = null;
        int total_length = 0;
        try {
            URL url1 = new URL(params[0]);
            connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(50000);
            connection.connect();

            if(connection.getResponseCode() == 200){
                is = connection.getInputStream();
                os = new FileOutputStream("/sdcard/zongzhi.apk");
                byte [] buf = new byte[1024];
                int len;
                int pro1=0;
                int pro2=0;
                // 获取文件流大小，用于更新进度
                long file_length = connection.getContentLength();
                while((len = is.read(buf))!=-1){
                    total_length += len;
                    if(file_length>0) {
                        pro1 = (int) ((total_length / (float) file_length) * 100);//传递进度（注意顺序）
                    }
                    if(pro1!=pro2) {
                        // 调用update函数，更新进度
                        publishProgress(pro2=pro1);
                    }
                    os.write(buf, 0, len);
                }
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return total_length;
    }

    @Override
    protected void onCancelled(Integer integer) {
        super.onCancelled(integer);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        notificationBuilder.setProgress(100,values[0],false);
        notificationManager.notify(0x3,notificationBuilder.build());
        //下载进度提示
        notificationBuilder.setContentText("下载"+values[0]+"%");
        if(values[0]==100) {    //下载完成后点击安装
            Intent it = new Intent(Intent.ACTION_VIEW);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            it.setDataAndType(Uri.parse("file:///sdcard/zongzhi.apk"), "application/vnd.android.package-archive");
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, it, PendingIntent.FLAG_UPDATE_CURRENT);
            notificationBuilder.setContentTitle("下载完成")
                    .setContentText("点击安装")
                    .setContentInfo("下载完成")
                    .setContentIntent(pendingIntent);
                notificationManager.notify(0x3, notificationBuilder.build());
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if(integer == 100) {
            Toast.makeText(context, "下载完成", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.siemens.troubleshoot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;



public class LaunchActivity extends AppCompatActivity {
    SharedPreferences userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);
        Log.d("-->login","Have Launch Activity");
        final Intent activityIntent;
        final Handler handler = new Handler();


        userInfo = getSharedPreferences("login",MODE_PRIVATE);

        if (userInfo.getBoolean("logged",false)) {

            activityIntent = new Intent(this, OtherActivity.class);
            Log.d("-->login","Go to other Activity"+userInfo.getBoolean("logged",false));
        } else {

            activityIntent = new Intent(this, LoginActivity.class);
            Log.d("-->login","Go to login Activity"+userInfo.getBoolean("logged",false));
        }


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(activityIntent);
                finish();
            }
        },2000);  //2s


        Log.d("-->login","Finish Launch");
    }
}

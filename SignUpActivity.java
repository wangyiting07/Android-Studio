package com.siemens.troubleshoot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    public static final String UserInfo = "login";
    public static final String PREF_USERNAME = "UserName";
    public static final String PREF_PASSWORD = "Password";
    private String UserName;
    private String Password;
    EditText email;
    EditText pass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        Log.d("-->login","In Sign up Activity");
        email = findViewById(R.id.edit_email);
        pass = findViewById(R.id.edit_pass);
        Button finish_signup = findViewById(R.id.button_finishSignup);
        finish_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserName = email.getText().toString();
                Password = pass.getText().toString();
                getSharedPreferences(UserInfo,MODE_PRIVATE)
                        .edit()
                        .putString(PREF_PASSWORD,Password)
                        .putString(PREF_USERNAME,UserName).commit();
                Log.d("-->login","username: "+UserName.getClass().getName());
                Log.d("-->login","password: "+Password.getClass().getName());
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.siemens.troubleshoot;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.siemens.troubleshoot.SignUpActivity.UserInfo;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences userInfo;
    EditText userName ;
    EditText password ;
    private String knownUser;
    private String knownPassword;
    private boolean canLogin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Log.d("-->login","In Login Activity");

        userName = findViewById(R.id.edit_username);
        password = findViewById(R.id.edit_password);
        Button login = findViewById(R.id.button_login);
        Button signUp = findViewById(R.id.button_signUp);

        //userInfo = getSharedPreferences("login",MODE_PRIVATE);
        Log.d("-->login","Initial variables In Login Activity");

        userInfo = getSharedPreferences(UserInfo,MODE_PRIVATE);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        password.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {
                                // the user is done typing.
                                knownUser = userInfo.getString("UserName", null);
                                knownPassword = userInfo.getString("Password", null);
                                String name = userName.getText().toString();
                                String word = password.getText().toString();
                                if ((name.equals(knownUser)) && (word.equals(knownPassword))) {
                                    userInfo.edit().putBoolean("logged", true).commit();
                                    canLogin = true;
                                }
                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(canLogin == true) {
                    Intent intent = new Intent(LoginActivity.this, SelectFaultsActivity.class);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder detail_dialog = new AlertDialog.Builder(LoginActivity.this);
                    detail_dialog.setTitle("Warning");
                    detail_dialog.setMessage("Wrong Username or Password");
                    detail_dialog.setCancelable(false);
                    detail_dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    detail_dialog.show();
                }
            }
        });
    }


}

package net.deschulz.login1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by schulz on 2/28/17.
 */

public class CreateLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LoginActivity.TAG, "CreateLoginActivity: OnCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createlogin);

        Intent intent = getIntent();

    }
}
package net.deschulz.login1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by schulz on 2/12/17.
 */

public class LoggedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LoginActivity.TAG,"LoggedInActivity: OnCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        Intent intent = getIntent();
        String msg = intent.getStringExtra(LoginActivity.STARTUP_MESSAGE);
        Log.i(LoginActivity.TAG,"LoggedInActivity Intent Message " + msg);
    }
}

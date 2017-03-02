package net.deschulz.login1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by schulz on 2/28/17.
 */

public class CreateLoginActivity extends AppCompatActivity {

    // todo add a button and after successful database integration return success.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LoginActivity.TAG, "CreateLoginActivity: OnCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createlogin);

        // this activity does not need to know how it was started, so it doesn't need
        // to know the requestCode.  If it did, you'd have to add it to the intent.
        Intent intent = getIntent();
        String ival = intent.getStringExtra(LoginActivity.EMAILID);
        Log.i(LoginActivity.TAG,"Got Intent: " + ival);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setResult(RESULT_OK,intent);
        finish();

    }
}
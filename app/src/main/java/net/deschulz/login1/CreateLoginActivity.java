package net.deschulz.login1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by schulz on 2/28/17.
 */

public class CreateLoginActivity extends AppCompatActivity {

    private DBManager dbm = new DBManager(this);
    private String mUserName;
    private EditText mPassword;
    private EditText mConfirmText;
    private EditText mHintText;

    // todo add a button and after successful database integration return success.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LoginActivity.TAG, "CreateLoginActivity: OnCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createlogin);

        // this activity does not need to know how it was started, so it doesn't need
        // to know the requestCode.  If it did, you'd have to add it to the intent.

        Intent intent = getIntent();
        // get the email-id from the main activity
        mUserName = intent.getStringExtra(LoginActivity.EMAILID);
        Log.i(LoginActivity.TAG,"Got Intent: " + mUserName);

        TextView userinfo = (TextView) findViewById(R.id.newUID);
        userinfo.setText(getResources().getString(R.string.uidgreet,mUserName));

        mPassword = (EditText) findViewById(R.id.newpassword);
        mConfirmText  = (EditText) findViewById(R.id.confirmpassword);
        mHintText = (EditText) findViewById(R.id.passwordhint);

        mHintText.setVisibility(View.GONE);
        mConfirmText.setVisibility(View.GONE);

        // catch enter and do something with it.
        mPassword.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    // here is where we should display the confirmation screen ...
                    Toast.makeText(CreateLoginActivity.this, mPassword.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        /*
        setResult(RESULT_OK,intent);
        finish();
        */

    }

    /**
     * This is where we create a database entry for this user if possible.
     * What if it fails?
     * @param v
     */
    public void submitLogin(View v) {

        mHintText.setVisibility(View.VISIBLE);

        String password = mPassword.getText().toString();
        Log.i(LoginActivity.TAG,"Button Clicked pwd= " + password);
        dbm.open();
        // check if this user already exists
        if (dbm.checkEntry(mUserName)){
            dbm.close();
            Log.i(LoginActivity.TAG,"This user already exists " + mUserName);
            // need to do something better here

            return;
        }
        // prompt for password
        // prompt for hint
        long retval = dbm.newEntry(mUserName,password);
        Log.i(LoginActivity.TAG,"newEntry returns: " + retval);
    }
}
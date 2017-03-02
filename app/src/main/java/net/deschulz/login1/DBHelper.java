package net.deschulz.login1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by schulz on 3/2/17.
 */

class DBHelper extends SQLiteOpenHelper {

    private static final String DBHTAG = "DBHelper";
    private static final String DB_NAME = "LoginDatabase.db";
    private static final int DB_VERSION = 1;

    static final String TABLE_PASSWORD = "Password";
    static final String COLUMN_ID = "_ID";
    static final String COLUMN_NAME = "NAME";
    static final String COLUMN_PASSWORD = "PASSWORD";
    static final String COLUMN_HINT = "HINT";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_PASSWORD + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT UNIQUE, " +
                    COLUMN_PASSWORD + " TEXT," +
                    COLUMN_HINT + " TEXT" +  ")";

    DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /* When we open the database (DesDBManager.open()) onCreate is called if the database
        does not exist.  If it does, then this isn't created.  What happens if there are several
        tables and some exist and others don't?
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(DBHTAG, "DBHelper: OnCreate()");
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(DBHTAG, "DBHelper: OnUpgrade()");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSWORD);
        onCreate(db);
    }
}
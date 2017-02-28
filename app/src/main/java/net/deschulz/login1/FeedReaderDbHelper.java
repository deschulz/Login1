package net.deschulz.login1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by schulz on 2/12/17.
 */

public class FeedReaderDbHelper extends SQLiteOpenHelper {

    public final class FeedReaderContract {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private FeedReaderContract() {
            Log.i(LoginActivity.TAG,"Construct FeedReaderContract");
        }

        /* Inner class that defines the table contents */
        public class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "Logins";
            public static final String COLUMN_NAME = "name";
            public static final String COLUMN_PASSWORD = "passwd";
        }

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        FeedEntry.COLUMN_NAME + " TEXT," +
                        FeedEntry.COLUMN_PASSWORD + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LoginDatabase.db";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(LoginActivity.TAG,"Construct FeedReaderDbHelper");
    }
    public void onCreate(SQLiteDatabase db) {
        Log.i(LoginActivity.TAG,"Database onCreate()");
        db.execSQL(FeedReaderContract.SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(FeedReaderContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}

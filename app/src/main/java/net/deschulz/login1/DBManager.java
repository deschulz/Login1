package net.deschulz.login1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by schulz on 3/2/17.
 */

public class DBManager {

    private static final String TAG = LoginActivity.TAG;

    private SQLiteDatabase db;
    private SQLiteOpenHelper desDBHelper;
    private boolean isOpen;


    /*  These agree with the columns in DesDBHelper.  Is there a way to import them directly?
        This seems error prone.
     */
    private static final String[] COLUMNS = {
            DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NAME,
            DBHelper.COLUMN_PASSWORD
    };

    /* The constructor creates the database. */
    public DBManager(Context context) {
        Log.i(TAG,"DBManager: Constructor");
        this.desDBHelper = new DBHelper(context);
        isOpen = false;
    }

    public void open() {
        db = desDBHelper.getWritableDatabase();
        isOpen = true;
        Log.i(TAG, "desDBHelper opened");
    }

    public void close() {
        if (desDBHelper != null) {
            isOpen = false;
            desDBHelper.close();
            Log.i(TAG, "desDBbHelper closed");
        }

    }

    public boolean checkEntry(String name) {
        Cursor cursor = null;
        String selection = DBHelper.COLUMN_NAME + " = ?";        //WHERE Clause
        String[] selArgs = { name };
        try {
            cursor = db.query(DBHelper.TABLE_PASSWORD, null, selection, selArgs, null, null, null);
            boolean ret =  (cursor.getCount() == 0);
            cursor.close();
            return ret;
        } catch (Exception e){
            Log.i(TAG, "Exception raised with a value of " + e);
        } finally{
            if (cursor != null) {
                cursor.close();
            }
        }
        return false;

    }

    public long newEntry(String name, String password) {
        if (!isOpen) {
            Log.i(TAG, "trying to insert into closed database");
            return -1;
        }
        String sql = "INSERT INTO " + DBHelper.TABLE_PASSWORD
                + "(" + DBHelper.COLUMN_NAME + "," + DBHelper.COLUMN_PASSWORD + ")"
                + " VALUES (?, ?)";
        Log.i(TAG, "newEntry: sql = " + sql);
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, name);
        stmt.bindString(2, password);

        /* execute() and executeInsert() do not return cursors. */
        try {
            return stmt.executeInsert();
        } catch (SQLiteConstraintException e) {
            // this will trigger if a constraint is violated, such as a duplicate recorddav
            return -1;
        }
    }

    public void delete(String name) {
        String whereClause = DBHelper.COLUMN_NAME + "=" + name;
    }

    public ArrayList<DBRecord> getAll() {
        ArrayList<DBRecord> dbRecs = new ArrayList<DBRecord>();
        Cursor cursor = null;
        try {
            cursor = db.query(DBHelper.TABLE_PASSWORD, COLUMNS, null, null, null, null, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    DBRecord card = new DBRecord();
                    card.setId(cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_ID)));
                    card.setName(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME)));
                    card.setPassword(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PASSWORD)));
                    dbRecs.add(card);
                }
            }
            Log.i(TAG, "Total rows = " + cursor.getCount());
        } catch (Exception e){
            Log.i(TAG, "Exception raised with a value of " + e);
        } finally{
            if (cursor != null) {
                cursor.close();
            }
        }
        return dbRecs;
    }

    /* use parameter substitution to prevent sql injection attacks */
    public DBRecord getOneRec(String name) {
        String table = DBHelper.TABLE_PASSWORD;
        String[] columns = {DBHelper.COLUMN_NAME, DBHelper.TABLE_PASSWORD};
        String selection = DBHelper.COLUMN_NAME + " = ?";        //WHERE Clause
        String[] selArgs = { name };
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = null;
        try {
            cursor = db.query(table,columns,selection,selArgs,groupBy,having,orderBy);
            if (cursor.getCount() > 0) {
                if (cursor.moveToNext()) {
                    DBRecord retRec = null;
                    retRec =  new DBRecord();
                    retRec.setName(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME)));
                    retRec.setPassword(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PASSWORD)));
                    cursor.close();
                    return retRec;
                }
            }
            Log.i(TAG, "Total rows = " + cursor.getCount());
        } catch (Exception e){
            Log.i(TAG, "Exception raised with a value of " + e);
        } finally{
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }
}
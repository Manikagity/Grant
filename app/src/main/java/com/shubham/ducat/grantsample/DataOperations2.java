package com.shubham.ducat.grantsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by user on 16-07-2018.
 */

public class DataOperations2 extends SQLiteOpenHelper {



    public static final int database_version = 1;
    public String create_query = "create table " + Table2.TableInfo.table_name + "(" + Table2.TableInfo.user_id + " text,"+
            Table2.TableInfo.user_follow + " text);";

    public DataOperations2(Context context) {
        super(context, Table2.TableInfo.database_name, null, database_version);
        Log.d("Database OPerations2", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(create_query);
        Log.d("Database Operations2", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void updateInformation2(DataOperations2 dop,String id,String follow)
    {
        SQLiteDatabase sq=dop.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Table2.TableInfo.user_id,id);
        cv.put(Table2.TableInfo.user_follow,follow);
        sq.update(Table2.TableInfo.table_name,cv, Table2.TableInfo.user_id+"=?",new String[]{follow});
    }
    public void deleteRecord(DataOperations2 dob,String id) {
        SQLiteDatabase sq = getWritableDatabase();
        sq.delete(Table2.TableInfo.table_name, Table2.TableInfo.user_id + "=?", new String[]{id});
        sq.close();
    }

    public void putInformation2(DataOperations2 dop2, String userid,String follow) {
        SQLiteDatabase SQ = dop2.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Table2.TableInfo.user_id,userid);
        cv.put(Table2.TableInfo.user_follow,follow);
        //cv.put(Table2.TableInfo.user_password,Password);
        long k = SQ.insert(Table2.TableInfo.table_name, null, cv);
        Log.d("Database insertion", "one row inserted");

    }

    public Cursor getInformation2(DataOperations2 dob) {
        SQLiteDatabase SQ = dob.getReadableDatabase();
        String columns[] = {Table2.TableInfo.user_id, Table2.TableInfo.user_follow};  //columns daal do
        Cursor cr1 = SQ.query(Table2.TableInfo.table_name, columns, null, null, null, null, null);
        return cr1;
    }

}

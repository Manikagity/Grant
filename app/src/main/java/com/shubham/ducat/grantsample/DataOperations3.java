package com.shubham.ducat.grantsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by user on 04-08-2018.
 */

public class DataOperations3 extends SQLiteOpenHelper {

    public static final int database_version = 1;
    public String create_query = "create table " + Table3.TableInfo.table_name + "(" + Table3.TableInfo.user_id + " text,"+
            Table3.TableInfo.user_rate + " text);";

    public DataOperations3(Context context) {
        super(context, Table3.TableInfo.database_name, null, database_version);
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
    public void updateInformation2(DataOperations3 dop,String id,String follow)
    {
        SQLiteDatabase sq=dop.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Table3.TableInfo.user_id,id);
        cv.put(Table3.TableInfo.user_rate,follow);
        sq.update(Table3.TableInfo.table_name,cv, Table3.TableInfo.user_id+"=?",new String[]{follow});
    }
    public void deleteRecord(DataOperations3 dob,String id) {
        SQLiteDatabase sq = getWritableDatabase();
        sq.delete(Table3.TableInfo.table_name, Table3.TableInfo.user_id + "=?", new String[]{id});
        sq.close();
    }

    public void putInformation2(DataOperations3 dop2, String userid,String follow) {
        SQLiteDatabase SQ = dop2.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Table3.TableInfo.user_id,userid);
        cv.put(Table3.TableInfo.user_rate,follow);
        //cv.put(Table2.TableInfo.user_password,Password);
        long k = SQ.insert(Table3.TableInfo.table_name, null, cv);
        Log.d("Database insertion", "one row inserted");

    }

    public Cursor getInformation2(DataOperations3 dob) {
        SQLiteDatabase SQ = dob.getReadableDatabase();
        String columns[] = {Table3.TableInfo.user_id, Table3.TableInfo.user_rate};  //columns daal do
        Cursor cr1 = SQ.query(Table3.TableInfo.table_name, columns, null, null, null, null, null);
        return cr1;
    }

}


package com.shubham.ducat.grantsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by user on 09-07-2018.
 */

public class DatabaseOperation extends SQLiteOpenHelper

{

    public static final int database_version = 1;
    public String create_query = "create table " + Table.TableInfo.table_name + "(" + Table.TableInfo.user_location + " text);";

    public DatabaseOperation(Context context) {
        super(context, Table.TableInfo.database_name, null, database_version);
        Log.d("Database OPerations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(create_query);
        Log.d("Database Operations", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putInformation(DatabaseOperation dop, String location) {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Table.TableInfo.user_location, location);
        long k = SQ.insert(Table.TableInfo.table_name, null, cv);
        Log.d("Database insertion", "one row inserted");

    }

    public Cursor getInformation(DatabaseOperation dob) {
        SQLiteDatabase SQ = dob.getReadableDatabase();
        String columns[] = {Table.TableInfo.user_location};  //columns daal do
        Cursor cr1 = SQ.query(Table.TableInfo.table_name, columns, null, null, null, null, null);
        return cr1;
    }

    public void deleteAll() {
        SQLiteDatabase SQ = this.getWritableDatabase();
        //SQ.execSQL("delete from "+ Table.TableInfo.table_name);
        SQ.delete(Table.TableInfo.table_name, null, null);
    }

    public boolean isEmptyTable()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        int no= (int) DatabaseUtils.queryNumEntries(db, Table.TableInfo.table_name);
        if(no==0)
        {
            return true;
        }
        else {
            return false;
        }
    }
}

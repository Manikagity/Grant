package com.shubham.ducat.grantsample;

import android.provider.BaseColumns;

/**
 * Created by user on 09-07-2018.
 */

public class Table {


    public Table()
    {

    }
    public static abstract class TableInfo implements BaseColumns
    {
        public static final String user_location="location_key";
        public static final String database_name="user_info";
        public static final String table_name="location_info";
    }
}

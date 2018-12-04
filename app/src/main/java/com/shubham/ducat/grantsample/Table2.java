package com.shubham.ducat.grantsample;

import android.provider.BaseColumns;

/**
 * Created by user on 16-07-2018.
 */

public class Table2 {

    public Table2()
    {

    }
    public static abstract class TableInfo implements BaseColumns
    {
        public static final String user_id="userid_key";
        public static final String user_follow="userfollow_key";
    //    public static final String user_password="userpassword_key";
        public static final String database_name="userlogin_info";
        public static final String table_name="login_info";
    }
}

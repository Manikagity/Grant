package com.shubham.ducat.grantsample;

import android.provider.BaseColumns;

/**
 * Created by user on 04-08-2018.
 */

public class Table3 {
    public Table3()
    {

    }
    public static abstract class TableInfo implements BaseColumns
    {
        public static final String user_id="userid_key";
        public static final String user_rate="userfollow_key";
        //    public static final String user_password="userpassword_key";
        public static final String database_name="userlogin_info";
        public static final String table_name="login_info";
    }

}

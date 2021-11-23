package com.os.humanhelpss.data;

import android.provider.BaseColumns;

public class UserContract {
    public static final String TABLE_NAME = "user";

    public static final class UserEntry implements BaseColumns {
        //EmployeeEntry class for deifning column names of Employee table

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_USER_NAME = "nom";
        public static final String COLUMN_USER_PASS = "pass";
        public static final String COLUMN_USER_EMAIL = "email";
        public static final String COLUMN_USER_PHONE = "telephone";


    }
}

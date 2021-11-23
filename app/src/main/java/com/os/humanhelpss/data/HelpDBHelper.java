package com.os.humanhelpss.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class HelpDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "employees_management.db";
    private static final int DATABASE_VERSION = 1;

    public HelpDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the user table
        String SQL_CREATE_USER_TABLE = "CREATE TABLE " + UserContract.TABLE_NAME + "("
                + UserContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserContract.UserEntry.COLUMN_USER_NAME + " VARCHAR(255) NOT NULL, "
                + UserContract.UserEntry.COLUMN_USER_EMAIL + "  VARCHAR(255) ,"
                + UserContract.UserEntry.COLUMN_USER_PASS + "  VARCHAR(20) ,"
                + UserContract.UserEntry.COLUMN_USER_PHONE + "  VARCHAR(20) "
                + ");";

        // Create a String that contains the SQL statement to create the user table
        String SQL_CREATE_SUJET_TABLE = "CREATE TABLE " + SujetContrat.TABLE_NAME + "("
                + SujetContrat.SujetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SujetContrat.SujetEntry.COLUMN_SUJET_NAME + " VARCHAR(255) NOT NULL, "
                + SujetContrat.SujetEntry.COLUMN_SUJET_PRENOM + "  VARCHAR(255) ,"
                + SujetContrat.SujetEntry.COLUMN_SUJET_AGE + "  INTEGER ,"
                + SujetContrat.SujetEntry.COLUMN_SUJET_TITRE + "  VARCHAR(250) ,"
                + SujetContrat.SujetEntry.COLUMN_SUJET_DESCRIPTION + "  VARCHAR(250) ,"
                + SujetContrat.SujetEntry.COLUMN_SUJET_IMAGE + "  BLOB "
                + ");";

   // Create a String that contains the SQL statement to create the user table
        String SQL_CREATE_HELP_TABLE = "CREATE TABLE " + HelpContrat.TABLE_NAME + "("
                + HelpContrat.HelpEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HelpContrat.HelpEntry.COLUMN_LECON_TITRE + " VARCHAR(255) NOT NULL, "
                + HelpContrat.HelpEntry.COLUMN_LECON_DESCRIPTION + "  VARCHAR(255) ,"
                + HelpContrat.HelpEntry.COLUMN_LECON_MONTANT + "  VARCHAR(20) "
                + ");";




        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(SQL_CREATE_HELP_TABLE);
        db.execSQL(SQL_CREATE_SUJET_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public boolean addUser(String user_name, String user_email, String user_pass, String user_phone) {
        //adds an user entry to user table

        SQLiteDatabase db = this.getWritableDatabase(); //gets writeable instance of database
        ContentValues cv = new ContentValues(); //used for inserting an entry


        if (user_name != null && !user_name.isEmpty() && !user_name.trim().isEmpty()) //checks if field is provided if not it is not added in the query
            cv.put(UserContract.UserEntry.COLUMN_USER_NAME, user_name);
        if (user_email != null && !user_email.isEmpty() && !user_email.trim().isEmpty()) // to be edited
            cv.put(UserContract.UserEntry.COLUMN_USER_EMAIL, user_email);
        if (user_pass != null && !user_pass.isEmpty() && !user_pass.trim().isEmpty())
            cv.put(UserContract.UserEntry.COLUMN_USER_PASS, user_pass);

        // no need to check for null as it is required to be provided
        cv.put(UserContract.UserEntry.COLUMN_USER_PHONE, user_phone);
        long flag = db.insert(UserContract.TABLE_NAME, null, cv); //returns a flag to indicate succes of insertion

        return flag != -1; //-1 if insert fails

    }

    public boolean addSujet(String name, String prenom, int age, String titre, String description, byte[] image) {
        //adds an user entry to user table

        SQLiteDatabase db = this.getWritableDatabase(); //gets writeable instance of database
        ContentValues cv = new ContentValues(); //used for inserting an entry


        if (name != null && !name.isEmpty() && !name.trim().isEmpty()) //checks if field is provided if not it is not added in the query
            cv.put(SujetContrat.SujetEntry.COLUMN_SUJET_NAME, name);
        if (prenom != null && !prenom.isEmpty() && !prenom.trim().isEmpty()) // to be edited
            cv.put(SujetContrat.SujetEntry.COLUMN_SUJET_PRENOM, prenom);
        if (description != null && !description.isEmpty() && !description.trim().isEmpty())
            cv.put(SujetContrat.SujetEntry.COLUMN_SUJET_TITRE, titre);
        cv.put(SujetContrat.SujetEntry.COLUMN_SUJET_DESCRIPTION, description);

        // no need to check for null as it is required to be provided
        cv.put(SujetContrat.SujetEntry.COLUMN_SUJET_AGE, age);
        cv.put(SujetContrat.SujetEntry.COLUMN_SUJET_IMAGE, image);
        long flag = db.insert(SujetContrat.TABLE_NAME, null, cv); //returns a flag to indicate succes of insertion

        return flag != -1; //-1 if insert fails

    }

    public boolean checkUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                UserContract.UserEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = UserContract.UserEntry.COLUMN_USER_EMAIL + " = ?" + " AND " + UserContract.UserEntry.COLUMN_USER_PASS + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions

        Cursor cursor = db.query(UserContract.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String email) {
        // array of columns to fetch
        String[] columns = {
                UserContract.UserEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = UserContract.UserEntry.COLUMN_USER_EMAIL + " = ?";
        // selection argument
        String[] selectionArgs = {email};
        // query user table with condition

        Cursor cursor = db.query(UserContract.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    public Cursor getAllSujets() {
        //gets all departments
        SQLiteDatabase db = this.getReadableDatabase(); //get readable instance of the db

        //specify the columns to be read
        String[] columns = {
                SujetContrat.SujetEntry._ID,
                SujetContrat.SujetEntry.COLUMN_SUJET_NAME,
                SujetContrat.SujetEntry.COLUMN_SUJET_PRENOM,
                SujetContrat.SujetEntry.COLUMN_SUJET_AGE,
                SujetContrat.SujetEntry.COLUMN_SUJET_TITRE,
                SujetContrat.SujetEntry.COLUMN_SUJET_DESCRIPTION,
                SujetContrat.SujetEntry.COLUMN_SUJET_IMAGE
        };

        String orderBy = SujetContrat.SujetEntry.COLUMN_SUJET_NAME + " ASC "; //order by statement

        //cursor is a table containing the rows returned form the query
        return db.query(SujetContrat.TABLE_NAME, columns, null, null, null, null, orderBy); //don't forget to close the cursor after usage

    }
}

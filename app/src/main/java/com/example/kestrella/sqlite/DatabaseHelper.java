package com.example.kestrella.sqlite;

/**
 * Created by kestrella on 1/15/16.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbKen";
    public  static final int DATABASE_VERSION = 3; // https://www.sqlite.org/
    public static final String TABLE_NAME = "tblAshley";
    public static final String UID = "Id";
    public static final String NAME = "Name";
    public static final String AGE = "Age";

    /*
    CONSTRUCTOR
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    super(context, name, factory, version);
    }
    */

    public DatabaseHelper(Context context) {
        // Call the constructor of the super class
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        The reason of passing null is we want the standard SQLiteCursor behaviour
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL Statement: CREATE TABLE tblAshley (_id INTEGER PRIMARY KEY AUTOINCREMENT,
        //               name TEXT, age TEXT)
        final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME
                + " VARCHAR(50), " + AGE
                + " INT(100));";
        // Execute the SQL statement
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table, when you update your database version
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE);

        // Recreate the tables
        onCreate(db);
    }
}

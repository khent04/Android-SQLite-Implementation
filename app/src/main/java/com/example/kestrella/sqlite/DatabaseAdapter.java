package com.example.kestrella.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kestrella on 1/15/16.
 */
public class DatabaseAdapter {
    public static SQLiteDatabase database;
    public static DatabaseHelper dbHelper;

    // Constructor
    public DatabaseAdapter(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Method used to open your SQLite database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Method used to close the connection
    public void close() {
        dbHelper.close();
    }

    // Add account
    public void addAccount(Data data) {
        // Content Values are used to insert and update data
        ContentValues values = new ContentValues();
        //         Column Name                  Value
        values.put(DatabaseHelper.NAME, data.getName());
        database.insert(DatabaseHelper.TABLE_NAME, null, values);
    }

    // Get the last id number, why? http://developer.android.com/reference/android/database/Cursor.html
    public int getLastId() {
        int last = 0;

        String query = "SELECT  * FROM " + DatabaseHelper.TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.getCount() != 0) {
            cursor.moveToLast();
            last = cursor.getInt(0);
        }

        cursor.close();

        return last;
    }


    // Get all accounts
    public List<Data> getAllData() {
        List<Data> accountLists = new ArrayList<Data>();

        // Select all rows
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_NAME;

        // A Cursor represents the result of a query and basically points to one row of the query result
        Cursor cursor = database.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Data account = new Data
                        (cursor.getInt(0), cursor.getString(1), cursor.getInt(2));

                // Adding data to list
                accountLists.add(account);
            } while (cursor.moveToNext());
        }

        // Close the cursor
        cursor.close();

        return accountLists;
    }

    // Delete account
    public void deleteAccount(int id) {
        // Parameters of the Delete method of SQLiteDatabase
        // 1 - Table Name
        // 2 - Where clause
        // 3 - Values to be replaced in the where clause (?)
        database.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.UID + "=?",
                new String[]{Integer.toString(id)});
    }

    // Update account
    public void updateAccount(Data data) {
        // Content Values are used to insert and update data
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.NAME, data.getName());
        //         Column Name                  Value
        // Parameters of the Update method of SQLiteDatabase
        // 1 - Table Name
        // 2 - Values to be updated
        // 3 - Where clause
        // 4 - Values to be replaced in the where clause (?)
        database.update(DatabaseHelper.TABLE_NAME, values,
                DatabaseHelper.UID + " = ?",
                new String[] { Integer.toString(data.getId()) });
    }


}

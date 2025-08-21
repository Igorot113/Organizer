package com.igor.organizer.DataUser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.igor.organizer.Data.DatabaseHelper;
import com.igor.organizer.Modelo.User;
public class UserDAO {
    private SQLiteDatabase db;
    public UserDAO(Context context){
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }
    public long inserir(User user){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_USERNAME,user.getUsername());
        values.put(DatabaseHelper.COL_PASSWORD,user.getPassword());
        return db.insert(DatabaseHelper.TABELA_USER,null,values);
    }
    public User getUserByCredentials(String username, String password) {
        SQLiteDatabase db = this.db;
        Cursor cursor = null;
        User user = null;
        try {
            String query = "SELECT " +
                    DatabaseHelper.COL_USER_ID + ", " +
                    DatabaseHelper.COL_USERNAME + ", " +
                    DatabaseHelper.COL_PASSWORD +
                    " FROM " + DatabaseHelper.TABELA_USER +
                    " WHERE " + DatabaseHelper.COL_USERNAME + " = ? AND " +
                    DatabaseHelper.COL_PASSWORD + " = ?";
            String[] selectionArgs = {username, password};
            cursor = db.rawQuery(query, selectionArgs);
            if (cursor.moveToFirst()) {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_ID));
                String dbUsername = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USERNAME));
                String dbPassword = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_PASSWORD));
                user = new User(id, dbUsername, dbPassword);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return user;
    }
}
package com.example.sadafx.tasky_proj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "tasky_db";
    private static final int DB_VERSION = 1;

    public DBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS tasks");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys = ON;");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TASK_TABLE);
        db.execSQL(CREATE_TOKEN_TABLE);

        db.execSQL("INSERT INTO utilities (last_token) \n" + "VALUES (?)", new String[]{""});
    }

    private static final String CREATE_TOKEN_TABLE = "CREATE TABLE utilities ( " +
            "last_token TEXT)";

    private static final String CREATE_USER_TABLE = "CREATE TABLE users ( " +
            "token TEXT, \n" +
            "first_name TEXT, \n" +
            "last_name TEXT, \n" +
            "username TEXT, \n" +
            "password TEXT,\n" +
            "email TEXT PRIMARY KEY)";

    private static final String CREATE_TASK_TABLE = "CREATE TABLE tasks ( " +
            "task_id TEXT PRIMARY KEY,\n" +
            "title TEXT,\n" +
            "context TEXT,\n" +
            "time TEXT, \n" +
            "alarm TEXT,\n" +
            "done TEXT,\n" +
            "day TEXT, \n" +
            "email TEXT, \n" +
            "FOREIGN KEY (email) " +
            "REFERENCES users (email) ON DELETE CASCADE ON UPDATE CASCADE )";

    public String getLastToken(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM utilities", new String[]{});
        cursor.moveToFirst();
        String token = cursor.getString((cursor.getColumnIndex("last_token")));
        db.close();
        cursor.close();
        return token;
    }

    public void updateLastToken(String token){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE utilities SET last_token = ? ", new String[]{token});
        db.close();
    }

    public  ArrayList<String> getUser(String token){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users\n" +
                "WHERE token = ?", new String[]{token});
        ArrayList<String> user = new ArrayList<>();
        if ( cursor.moveToFirst() ){
            user.add(cursor.getString(cursor.getColumnIndex("email")));
            user.add(cursor.getString(cursor.getColumnIndex("first_name")));
            user.add(cursor.getString(cursor.getColumnIndex("last_name")));
            user.add(cursor.getString(cursor.getColumnIndex("username")));
            user.add(cursor.getString(cursor.getColumnIndex("last_name")));
        }
        else{
            user = null;
        }
        db.close();
        cursor.close();
        return user;
    }


    public void onSignupInsert(String token, String first_name, String last_name, String username, String email, String password){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO users (token,first_name,last_name,username,password,email) \n" +
                "VALUES (?,?,?,?,?,?)", new String[]{token,first_name,last_name,username,password,email});
        db.close();
    }

    public void deleteTask(String email, String task_id){
        SQLiteDatabase db = getReadableDatabase();
        db.delete("tasks","email=? AND task_id=?",new String[]{email,task_id});
    }

    public void doneTask(String email, String task_id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE tasks SET done = 1 WHERE email = ?", new String[]{email});
        db.close();
    }

    public int maxTaskID(String email){
        int i;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(task_id) FROM tasks",null);
        cursor.moveToFirst();
        i = cursor.getInt(0);
        db.close();
        cursor.close();
        return i;
    }

    public void onEditPassword(String email, String password){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE users SET password = ?\n" +
                "WHERE email = ?", new String[]{password, email});
        db.close();
    }

    public void onEditEmail(String email, String new_email){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE users SET email = ?\n" +
                "WHERE email = ?", new String[]{new_email, email});
        db.close();
    }

    public void onNewTask(String taskid, String title, String context, String time,
                          String alarm, String done, String day, String email){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO tasks (task_id,title,context,time,alarm,done,day,email)\n" +
                "VALUES (?,?,?,?,?,?,?,?)", new String[]{taskid,title,context,time,alarm,done,day,email});
        db.close();
    }

    public ArrayList<Task> getDayTasks_todo(String email, String day){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tasks\n" +
                "WHERE email = ? AND day = ? AND done = ?", new String[]{email,day,"0"});
        ArrayList<Task> tasks = new ArrayList<>();
        if ( cursor.moveToFirst() ){
            do {
                tasks.add(new Task(cursor.getString(cursor.getColumnIndex("task_id")),
                        cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getString(cursor.getColumnIndex("context")),
                        cursor.getString(cursor.getColumnIndex("time")),
                        cursor.getString(cursor.getColumnIndex("alarm"))));
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return tasks;
    }

    public ArrayList<Done> getDayTasks_done(String email, String day){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tasks\n" +
                "WHERE email = ? AND day = ? AND done = ?", new String[]{email,day,"1"});
        ArrayList<Done> tasks = new ArrayList<>();
        if ( cursor.moveToFirst() ){
            do {
                tasks.add(new Done(cursor.getString(cursor.getColumnIndex("title"))));
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return tasks;
    }

    public ArrayList<Overdue> getDayTasks_overdue(String email, String day){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tasks\n" +
                "WHERE email = ? AND day = ? AND done = ?", new String[]{email,day,"0"});
        ArrayList<Overdue> tasks = new ArrayList<>();
        if ( cursor.moveToFirst() ){
            do {
                tasks.add(new Overdue(cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getString(cursor.getColumnIndex("context"))));
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return tasks;
    }

}
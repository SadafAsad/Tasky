package com.example.sadafx.tasky_proj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
        db.execSQL(CREAT_USER_TABLE);
        db.execSQL(CREAT_TASK_TABLE);
    }

    private static final String CREAT_USER_TABLE = "CREATE TABLE users ( " +
            "name TEXT, \n" +
            "password TEXT,\n" +
            "email TEXT PRIMARY KEY)";

    private static final String CREAT_TASK_TABLE = "CREATE TABLE tasks ( " +
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

    public boolean findUser(String email, String password){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users\n" +
                "WHERE email = ? AND password = ?", new String[]{email,password});
        if ( cursor.getCount() == 1 ){
            cursor.close();
            db.close();
            return true;
        }
        else {
            cursor.close();
            db.close();
            return false;
        }
    }


    public void onSignupInsert(String name, String email, String password){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO users (name,password,email) \n" +
                "VALUES (?,?,?)", new String[]{name,password,email});
        db.close();
    }

    public void deleteTask(String task_id){
        SQLiteDatabase db = getReadableDatabase();
        db.delete("tasks","task_id=?",new String[]{task_id});
    }

    public int maxTaskID(String email){
        int i;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(task_id) FROM tasks\n" +
                "WHERE email = ?", new String[]{email});
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
                "VALUES (?,?,?,?,?,?)", new String[]{taskid,title,context,time,alarm,done,day,email});
        db.close();
    }

    public List<String> getDayTasks_todo_overdue(String email, String day){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tasks\n" +
                "WHERE email = ? AND day = ? AND done = ?", new String[]{email,day,"0"});
        List<String> tasks = new ArrayList<>();
        if ( cursor.moveToFirst() ){
            do {
                String[] task = {"","","","","","","",""};
                task[0]=cursor.getString(cursor.getColumnIndex("task_id"));
                task[1]=cursor.getString(cursor.getColumnIndex("title"));
                task[2]=cursor.getString(cursor.getColumnIndex("context"));
                task[3]=cursor.getString(cursor.getColumnIndex("time"));
                task[4]=cursor.getString(cursor.getColumnIndex("alarm"));
                task[5]=cursor.getString(cursor.getColumnIndex("done"));
                task[6]=cursor.getString(cursor.getColumnIndex("day"));
                task[7]=cursor.getString(cursor.getColumnIndex("email"));
                tasks.add(task.toString());
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return tasks;
    }

    public List<String> getDayTasks_done(String email, String day){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tasks\n" +
                "WHERE email = ? AND day = ? AND done = ?", new String[]{email,day,"1"});
        List<String> tasks = new ArrayList<>();
        if ( cursor.moveToFirst() ){
            do {
                String[] task = {"","","","","","","",""};
                task[0]=cursor.getString(cursor.getColumnIndex("task_id"));
                task[1]=cursor.getString(cursor.getColumnIndex("title"));
                task[2]=cursor.getString(cursor.getColumnIndex("context"));
                task[3]=cursor.getString(cursor.getColumnIndex("time"));
                task[4]=cursor.getString(cursor.getColumnIndex("alarm"));
                task[5]=cursor.getString(cursor.getColumnIndex("done"));
                task[6]=cursor.getString(cursor.getColumnIndex("day"));
                task[7]=cursor.getString(cursor.getColumnIndex("email"));
                tasks.add(task.toString());
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return tasks;
    }

}
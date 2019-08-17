package com.larten.sep_project;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DataHelper extends SQLiteOpenHelper {

    private static final String TAG = DataHelper.class.getSimpleName();

    private Resources mResources;
    private static final String DATABASE_NAME = "SEP-DATA-ver2";
    private  static final int DATABASE_VERSION = 1;
    Context context;
    SQLiteDatabase db;


    public DataHelper(classActivity classActivity, String s, @Nullable Context context, int i) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mResources = context.getResources();
        db = this.getWritableDatabase();
    }

    public DataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_BUGS_TABLE = "CREATE TABLE "+ DbContract.Entry.TABLE_NAME + "(" +
                DbContract.Entry._ID + " INTEGER PRIMARY KEY, " +
                DbContract.Entry.COLUMN_NAME + " TEXT NOT NULL, " +
                DbContract.Entry.COLUMN_STUDENTS + " INTEGER NOT NULL, " +
                DbContract.Entry.COLUMN_DAY + " TEXT NOT NULL, " +
                DbContract.Entry.COLUMN_TIME + " TEXT NOT NULL, " +
                DbContract.Entry.COLUMN_ROOM + " TEXT NOT NULL " + " );";


        db.execSQL(SQL_CREATE_BUGS_TABLE);
        Log.d(TAG, "Database created successfully !!! ");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void ReadDatatoDb(SQLiteDatabase db) throws IOException, JSONException{

        final String mm_id = "id";
        final String mm_Name = "name";
        final String mm_Students = "students";
        final String mm_Day = "day";
        final String mm_Time = "time";
        final String mm_Room = "room";

        try {
            String jsonDataString = docNoiDung_Tu_URL("http://cntttest.vanlanguni.edu.vn:18080/Cap21T4/AttendanceManagement/JSON/GetCourses?email=phamngocduy%40vanlanguni.edu.vn&hk=182&fbclid=IwAR24BCAdHeLNuut8s0yddRBT7NWtvnZ83ey_4RGB7xsnvS2cTSG6HDelutY");
            JSONArray menuItemsJsonArray = new JSONArray(jsonDataString);
            for (int i = 0; i < menuItemsJsonArray.length(); ++i){
                String id;
                 String name;
                 String students;
                 String day;
                 String time;
                 String room;

                JSONObject menuItemOject = menuItemsJsonArray.getJSONObject(i);
                id = menuItemOject.getString(mm_id);
                name = menuItemOject.getString(mm_Name);
                students = menuItemOject.getString(mm_Students);
                day = menuItemOject.getString(mm_Day);
                time = menuItemOject.getString(mm_Time);
                room = menuItemOject.getString(mm_Room);


                ContentValues menuvalues = new ContentValues();

                menuvalues.put(DbContract.Entry.COLUMN_ID, id);
                menuvalues.put(DbContract.Entry.COLUMN_NAME, name);
                menuvalues.put(DbContract.Entry.COLUMN_STUDENTS, students);
                menuvalues.put(DbContract.Entry.COLUMN_DAY, day);
                menuvalues.put(DbContract.Entry.COLUMN_TIME, time);
                menuvalues.put(DbContract.Entry.COLUMN_ROOM, room);

                db.insert(DbContract.Entry.TABLE_NAME, null, menuvalues);


                Log.d(TAG, "Inserted Successfully "+menuvalues );

            }
        } catch (JSONException e){
            Log.d(TAG, e.getMessage(), e);
            e.printStackTrace();
        }


    }


    // doc noi dung tu web

    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
    // truy van 1
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //truy van 2
    public Cursor Getdata(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


}

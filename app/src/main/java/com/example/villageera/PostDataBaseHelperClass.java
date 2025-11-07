package com.example.villageera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class PostDataBaseHelperClass extends SQLiteOpenHelper {
    //Table columns
    public static final String ID = "post_id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String POST_TYPE = "post_type";
    public static final String POST_DESC = "post_desc";
    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "VillageEra";
    //Database Table name
    private static final String TABLE_NAME = "post";
    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL," + PHONE + " TEXT NOT NULL," + POST_TYPE + " TEXT NOT NULL ," + POST_DESC + " TEXT NOT NULL)";
    private SQLiteDatabase sqLiteDatabase;

    //Constructor0
    public PostDataBaseHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add Employee Data
    public void addPost(PostModalClass postModalClass) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PostDataBaseHelperClass.NAME, postModalClass.getName());
        contentValues.put(PostDataBaseHelperClass.PHONE, postModalClass.getPhone());
        contentValues.put(PostDataBaseHelperClass.POST_TYPE, postModalClass.getPost_type());
        contentValues.put(PostDataBaseHelperClass.POST_DESC, postModalClass.getPost_desc());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(PostDataBaseHelperClass.TABLE_NAME, null, contentValues);
    }

    public ArrayList<PostModalClass> getPosts() {
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        ArrayList<PostModalClass> readPost = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int post_id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String post_type = cursor.getString(3);
                String post_desc = cursor.getString(4);
                readPost.add(new PostModalClass(post_id, name, phone, post_type, post_desc));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return readPost;
    }
}

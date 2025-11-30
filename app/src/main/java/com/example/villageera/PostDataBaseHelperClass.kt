package com.example.villageera

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PostDataBaseHelperClass(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Insert new post
    fun addPost(post: PostModalClass) {
        val db = writableDatabase

        val cv = ContentValues().apply {
            put(NAME, post.name)
            put(PHONE, post.phone)
            put(POST_TYPE, post.postType)
            put(POST_DESC, post.postDesc)
        }

        db.insert(TABLE_NAME, null, cv)
        db.close()
    }

    // Fetch all posts
    fun getPosts(): ArrayList<PostModalClass> {
        val list = ArrayList<PostModalClass>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"

        db.rawQuery(query, null).use { cursor ->
            if (cursor.moveToFirst()) {
                do {
                    val postId = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(NAME))
                    val phone = cursor.getString(cursor.getColumnIndexOrThrow(PHONE))
                    val postType = cursor.getString(cursor.getColumnIndexOrThrow(POST_TYPE))
                    val postDesc = cursor.getString(cursor.getColumnIndexOrThrow(POST_DESC))

                    list.add(
                        PostModalClass(
                            postId = postId,
                            name = name,
                            phone = phone,
                            postType = postType,
                            postDesc = postDesc
                        )
                    )
                } while (cursor.moveToNext())
            }
        }

        return list
    }

    companion object {
        const val ID = "post_id"
        const val NAME = "name"
        const val PHONE = "phone"
        const val POST_TYPE = "post_type"
        const val POST_DESC = "post_desc"

        private const val DATABASE_NAME = "VillageEra"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "post"

        private const val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME (" + "$ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "$NAME TEXT NOT NULL, " + "$PHONE TEXT NOT NULL, " + "$POST_TYPE TEXT NOT NULL, " + "$POST_DESC TEXT NOT NULL)"
    }
}

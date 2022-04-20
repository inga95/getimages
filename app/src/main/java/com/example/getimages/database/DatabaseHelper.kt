package com.example.getimages.database
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.graphics.BitmapFactory
import android.graphics.Bitmap


class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {

        // creating table
        db.execSQL(CREATE_TABLE_IMAGE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS $DB_TABLE")

        // create new table
        onCreate(db)
    }

    companion object {
        // Database Version
        private const val DATABASE_VERSION = 1

        // Database Name
        private const val DATABASE_NAME = "images_database"

        // Table Names
        private const val DB_TABLE = "table_image"

        // column names
        private const val KEY_NAME = "image_name"
        private const val KEY_IMAGE = "image_data"

        // Table create statement
        private const val CREATE_TABLE_IMAGE = "CREATE TABLE " + DB_TABLE + "(" +
                KEY_NAME + " TEXT," +
                KEY_IMAGE + " BLOB);"
    }

    @Throws(SQLiteException::class)
    fun addEntry(name: String?, image: ByteArray?) {
        val database = this.writableDatabase
        val cv = ContentValues()
        cv.put(KEY_NAME, name)
        cv.put(KEY_IMAGE, image)
        database.insert(DB_TABLE, null, cv)
    }

}
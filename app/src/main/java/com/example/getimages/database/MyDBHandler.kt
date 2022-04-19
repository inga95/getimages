package com.example.getimages.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import java.io.ByteArrayOutputStream

class MyDBHandler (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object {
        private const val DATABASE_NAME = "Images database"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE images (id INTEGER PRIMARY KEY AUTOINCREMENT, image BLOB NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS images")
        onCreate(db)
    }

    /*fun addImage(bitmap: Bitmap): Boolean {
        val cv = ContentValues()
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 30, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        cv.put("image", byteArray)
        return writableDatabase.insert("images", null, cv) > 0
    }*/

    //Boris sin kode
    /*fun bitmapTobyteArray(image: Bitmap): ByteArrayOutputStream {
        val outputStream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream
    }*/

    /*fun getAllImages(): ArrayList<Image>{
        val arr = arrayListOf<Image>()
        val cursor = readableDatabase.rawQuery("SELECT * FROM images", null)
        if (cursor.count > 0){
            cursor.moveToFirst()
            while (!cursor.isAfterLast){
                val id = cursor.getInt(0)
                val byteArray = cursor.getBlob(1)
                val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                val image = Image(id,bitmap)
                arr.add(image)
                cursor.moveToNext()
            }
        }
        cursor.close()
        return arr
    }*/

}
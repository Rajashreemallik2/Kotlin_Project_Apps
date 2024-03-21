package com.example.happyplaces.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.happyplaces.models.HappyPlaceModel
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

        companion object{
            private const val DATABASE_VERSION = 1 // Database Version
            private const val DATABASE_NAME = "HappyPlacesDatabase" // Database Name
            private const val TABLE_HAPPY_PLACE = "HappyPlacesTable" // Database Table

            // All the columns names

            private const val KEY_ID = "_id"
            private const val KEY_TITLE = "title"
            private const val KEY_IMAGE = "image"
            private const val KEY_DESCRIPTION = "description"
            private const val KEY_DATE = "date"
            private const val KEY_LOCATION = "location"
            private const val KEY_LATITUDE = "latitude"
            private const val KEY_LONGITUDE = "longitude"


        }

    override fun onCreate(db: SQLiteDatabase?) {
        // Creating Table with Field
        val CREATE_HAPPY_PLACE_TABLE = ("CREATE TABLE" + TABLE_HAPPY_PLACE + "("
                + KEY_ID + "INTEGER PRIMARY KEY,"
                + KEY_TITLE + "TEXT,"
                + KEY_IMAGE + "TEXT,"
                + KEY_DESCRIPTION + "TEXT,"
                + KEY_DATE + "TEXT,"
                + KEY_LOCATION + "TEXT,"
                + KEY_LATITUDE + "TEXT,"
                + KEY_LONGITUDE + "TEXT")
        db?.execSQL(CREATE_HAPPY_PLACE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_HAPPY_PLACE")
        onCreate(db)
    }
     /**
     * Function to insert a Happy Place details to SQLite database
     */

    fun addHappyPlace(happyPlace: HappyPlaceModel): Long {
        val db = this.writableDatabase

         val contentValues = ContentValues()
         contentValues.put("KEY_TITLE", happyPlace.title)
         contentValues.put("KEY_IMAGE", happyPlace.image)
         contentValues.put("KEY_DESCRIPTION", happyPlace.description)
         contentValues.put("KEY_DATE", happyPlace.date)
         contentValues.put("KEY_LOCATION", happyPlace.location)
         contentValues.put("KEY_LATITUDE", happyPlace.latitude)
         contentValues.put("KEY_LONGITUDE", happyPlace.longitude)

         //inserting row

         val result = db.insert(TABLE_HAPPY_PLACE,null, contentValues)
         db.close() // closing database connection

         return result
    }
}
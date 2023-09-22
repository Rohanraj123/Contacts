package com.example.contacts.DATABASE

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    //This is a functin that takes the data and stores inside the database
    fun addNewContact(contactName : String, contactNumber : String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("name", contactName)
        values.put("number", contactNumber)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    fun readValues() : ArrayList<Model> {
        val db = readableDatabase
        val cursorContacts = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val contactModelArrayList = ArrayList<Model>()

        if (cursorContacts.moveToFirst()) {
            do {
                contactModelArrayList.add(
                    Model(
                        cursorContacts.getString(1),
                        cursorContacts.getString(2),
                        cursorContacts.getString(3),
                        cursorContacts.getString(4)
                    )
                )
            } while (cursorContacts.moveToNext())
        }
        cursorContacts.close()
        return contactModelArrayList
    }


    companion object{
        private final const val DATABASE_NAME = "contacts.db"
        private final const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Contact"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_NUMBER = "number"
        private const val COLUMN_ID = "id"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_NUMBER TEXT NOT NULL
            )
        """.trimIndent()
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(query)
        onCreate(db)
    }
}
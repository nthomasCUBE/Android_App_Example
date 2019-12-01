package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "PersonDatabase"
        private val TABLE_CONTACTS = "PersonTable"
        private val KEY_ID = "id"
        private val KEY_PRENAME = "prename"
        private val KEY_LASTNAME = "lastname"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PRENAME + " TEXT,"
                + KEY_LASTNAME + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }

    fun addPerson(person: PersonModelClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, person.userId)
        contentValues.put(KEY_PRENAME, person.prename)
        contentValues.put(KEY_LASTNAME,person.lastname )
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return success
    }

    fun viewPerson():List<PersonModelClass>{
        val personList:ArrayList<PersonModelClass> = ArrayList<PersonModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userId: Int
        var prename: String
        var lastname: String
        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex("id"))
                prename = cursor.getString(cursor.getColumnIndex("prename"))
                lastname = cursor.getString(cursor.getColumnIndex("lastname"))
                val person= PersonModelClass(userId = userId, prename = prename, lastname = lastname)
                personList.add(person)
            } while (cursor.moveToNext())
        }
        return personList
    }

    fun updatePerson(person: PersonModelClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, person.userId)
        contentValues.put(KEY_PRENAME, person.prename)
        contentValues.put(KEY_LASTNAME, person.lastname )
        val success = db.update(TABLE_CONTACTS, contentValues,"id="+person.userId,null)
        db.close()
        return success
    }

    fun deletePerson(person: PersonModelClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, person.userId)
        val success = db.delete(TABLE_CONTACTS,"id="+person.userId,null)
        db.close()
        return success
    }
}
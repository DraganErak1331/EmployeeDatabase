package com.example.employeedatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

/**
 * Creating the database logic, extending the SQliteOpenHelper base class.
 */
class DatabaseHandler (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    //database constants
    companion object {
        private val DATABASE_VERSION = 1;
        private val DATABASE_NAME = "Employee Database"

        private val TABLE_CONTACTS = "EmployeeTable"

        private val KEY_ID = "_id"
        private val KEY_NAME = "name"
        private val KEY_EMAIL = "email"
    }

    /**
     * Initial creation of the database.
     */
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_EMAIL + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    /**
     * On schema update.
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { //if lets say another column is added
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    /**
     * Reads records from the database in the form of an ArrayList.
     */
    fun viewEmployee(): ArrayList<EmployeeModel> {
        val empList: ArrayList<EmployeeModel> = ArrayList<EmployeeModel>()

        //Query to select all the records from the table
        val selectQuery = "SELECT * FROM $TABLE_CONTACTS"

        val db = this.readableDatabase
        //cursor is used to read the record one by one
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var email: String

        //adds each row of the database table to empList
        if(cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString((cursor.getColumnIndex(KEY_NAME)))
                email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))

                val emp = EmployeeModel(id = id, name = name, email = email)
                empList.add(emp)
            } while (cursor.moveToNext())
        }

        return empList
    }

    /**
     * Inserts an EmployeeModel into the database.
     */
    fun addEmployee(emp: EmployeeModel) : Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, emp.name)
        contentValues.put(KEY_EMAIL, emp.email)

        val success = db.insert(TABLE_CONTACTS, null, contentValues)

        db.close()
        return success
    }

    /**
     * Updates a row in the database.
     */
    fun updateEmployee(emp: EmployeeModel) : Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, emp.name)
        contentValues.put(KEY_EMAIL, emp.email)

        val success = db.update(TABLE_CONTACTS, contentValues, KEY_ID + "=" + emp.id, null)

        db.close()
        return success
    }

    /**
     * Deletes a row in the database.
     */
    fun deleteEmployee(emp: EmployeeModel) : Int {
        val db = this.writableDatabase

        val success = db.delete(TABLE_CONTACTS, KEY_ID + "=" + emp.id, null)

        db.close()
        return success
    }

}
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button;
import android.widget.Toast;
import android.widget.AutoCompleteTextView;

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

import android.provider.BaseColumns

class MainActivity : AppCompatActivity() {

    object DBContract {

        /* Inner class that defines the table contents */
        class UserEntry : BaseColumns {
            companion object {
                val TABLE_NAME = "users"
                val COLUMN_USER_ID = "userid"
                val COLUMN_PRENAME = "prename"
                val COLUMN_LASTNAME = "lastname"
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var goToChildButton = findViewById<Button>(R.id.go_to_child_btn);
        goToChildButton.setOnClickListener{

            var prename = findViewById<AutoCompleteTextView>(R.id.tx1);
            var lastname = findViewById<AutoCompleteTextView>(R.id.tx2);
            Toast.makeText(this,prename.editableText.toString().plus(" ").plus(lastname.editableText.toString()),Toast.LENGTH_SHORT).show()

            val databaseHandler: DatabaseHandler= DatabaseHandler(this)
            val emp:List<PersonModelClass> = databaseHandler.viewPerson()
            for(e in emp) {
                Toast.makeText(this,"saved:".plus(e.prename.plus(" ").plus(e.lastname)),Toast.LENGTH_SHORT).show();
            }
            databaseHandler.addPerson(PersonModelClass(emp.size+1,prename.editableText.toString(),lastname.editableText.toString()));
            databaseHandler.close();

        }
    }
}

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
import android.widget.EditText

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

        var goToChildButton2 = findViewById<Button>(R.id.go_to_child_btn2);
        goToChildButton2.setOnClickListener {

            var editText = findViewById<EditText>(R.id.editText);
3
            var searchText = findViewById<AutoCompleteTextView>(R.id.tx3);
            val databaseHandler: DatabaseHandler= DatabaseHandler(this)
            val emp:List<PersonModelClass> = databaseHandler.viewPerson()

            val x=StringBuilder()
            for(e in emp){
                if(e.lastname.toString().equals(searchText.editableText.toString()) or e.prename.toString().equals(searchText.editableText.toString())){
                    x.append(e.prename.toString().plus(" ").plus(e.lastname.toString()))
                }
            }
            editText.setText(x.toString());
            databaseHandler.close()
        }

        var goToChildButton = findViewById<Button>(R.id.go_to_child_btn);
        goToChildButton.setOnClickListener{

            var prename = findViewById<AutoCompleteTextView>(R.id.tx1);
            var lastname = findViewById<AutoCompleteTextView>(R.id.tx2);
            var editText = findViewById<EditText>(R.id.editText);

            val databaseHandler: DatabaseHandler= DatabaseHandler(this)
            val emp:List<PersonModelClass> = databaseHandler.viewPerson()
            val x=StringBuilder()

            for(e in emp) {
                x.append(e.prename.toString().plus(" ").plus(e.lastname.toString()))
                x.append("\n")
            }

            editText.setText(x.toString());
            databaseHandler.addPerson(PersonModelClass(emp.size+1,prename.editableText.toString(),lastname.editableText.toString()));
            databaseHandler.close();
        }
    }
}

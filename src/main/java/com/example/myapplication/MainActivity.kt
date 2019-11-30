package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button;
import android.widget.Toast;
import android.widget.AutoCompleteTextView;

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var goToChildButton = findViewById<Button>(R.id.go_to_child_btn);
        goToChildButton.setOnClickListener{

            var prename = findViewById<AutoCompleteTextView>(R.id.tx1);
            var lastname = findViewById<AutoCompleteTextView>(R.id.tx2);

            Toast.makeText(this,prename.editableText.toString().plus(" ").plus(lastname.editableText.toString()),Toast.LENGTH_SHORT).show()
        }
    }
}

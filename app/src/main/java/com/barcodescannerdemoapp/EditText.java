package com.barcodescannerdemoapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class EditText extends AppCompatActivity {
    private android.widget.EditText noteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_text);
        noteEditText = findViewById(R.id.editText);
    }
}

package com.cs.ultron.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.cs.ultron.R;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText = (EditText) findViewById(R.id.editText);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if (noteId != -1) {

            editText.setText(Notepad.notes.get(noteId));
        }

        else {

            Notepad.notes.add("");
            noteId = Notepad.notes.size() -1;
            Notepad.arrayAdapter.notifyDataSetChanged();
        }


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Notepad.notes.set(noteId, String.valueOf(s));

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("notepad", Context.MODE_PRIVATE);

                HashSet<String> set = new HashSet<>(Notepad.notes);

                sharedPreferences.edit().putStringSet("notes", set).apply();
                Notepad.arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

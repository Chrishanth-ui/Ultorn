package com.cs.ultron.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.cs.ultron.R;

import java.util.ArrayList;
import java.util.HashSet;

public class Notepad extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    static ArrayList<String> notes = new ArrayList<>();
    static TodoAdapter arrayAdapter;
    ListView listView;





    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.add_note_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(this::onMenuItemClick);
        popup.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this,"OnMenu Selected",Toast.LENGTH_LONG).show();
        switch (item.getItemId()) {
            case R.id.add_note:
                Intent intent1 =new Intent(this,NoteEditorActivity.class);
                startActivity(intent1);
                return true;
            default:
                return false;

        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);

        listView = (ListView) findViewById(R.id.listView);



        getData();





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),NoteEditorActivity.class);
                intent.putExtra("noteId", position);
                startActivity(intent);


            }
        });



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int itemToDel = position;

                new AlertDialog.Builder(Notepad.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are You Sure?")
                        .setMessage("DO YOU WANNA DELETE THIS NOTE?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                notes.remove(itemToDel);


                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("notepad", Context.MODE_PRIVATE);

                                HashSet<String> set = new HashSet<String>(Notepad.notes);
                                sharedPreferences.edit().putStringSet("notes", set).apply();

                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("no", null)
                        .show();

                return true;
            }
        });




    }

    public class TodoAdapter extends BaseAdapter{
        Context context;
        ArrayList<String> notes;

        public TodoAdapter(Context context, ArrayList<String> notes) {
            this.context = context;
            this.notes = notes;
        }

        @Override
        public int getCount() {
            return notes.size();
        }

        @Override
        public Object getItem(int position) {
            return notes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.notepad_list_adapter,parent,false);
            String note = notes.get(position);
            TextView title = view.findViewById(R.id.list_name);
            title.setText(note);
            return view;
        }
    }


    private void getData(){

        ArrayList<String> sharedData;
        notes.clear();

        try{

            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("notepad", Context.MODE_PRIVATE);

            HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);




            if (set != null) {
                sharedData = new ArrayList<>(set);
                notes = sharedData;


            } else  {
                notes.add("SAMPLE NOTE");
            }

        }catch (Exception ex){
            Log.e("Notepad",ex.toString());
        }


        arrayAdapter = new TodoAdapter(this, notes);
        listView.setAdapter(arrayAdapter);

    }

    @Override
    protected void onResume() {

        super.onResume();

        getData();
    }
}
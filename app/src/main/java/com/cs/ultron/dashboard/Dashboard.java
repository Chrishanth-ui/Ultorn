package com.cs.ultron.dashboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cs.ultron.Calculator;
import com.cs.ultron.ViewDialog;
import com.cs.ultron.alarm.Alarm;
import com.cs.ultron.notepad.Notepad;
import com.cs.ultron.R;
import com.cs.ultron.Model.Card;
import com.cs.ultron.todo.ToDo;

import java.util.ArrayList;
import android.app.AlertDialog.Builder;
import android.widget.EditText;

public class Dashboard extends AppCompatActivity {
    private final ArrayList<Card> cardsList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        cardsList.add(new Card(1, "Notepad",R.drawable.notepad));
        cardsList.add(new Card(2,"Calculater",R.drawable.calculater));
        cardsList.add(new Card(3,"To-Do",R.drawable.tdo));
        cardsList.add(new Card(4,"Clock",R.drawable.clock));
        cardsList.add(new Card(5,"PDF-Scanner",R.drawable.pdf));


        CardAdapter cardAdapter = new CardAdapter(this, cardsList,selectedCallBack);
        RecyclerView recyclerView = findViewById(R.id.dashboard_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(cardAdapter);

    }

    private final SelectedCallBack selectedCallBack = new SelectedCallBack() {
        @Override
        public void itemSelected(int position) {
            switch (position ) {
                case 1:
                    Intent intent1 = new Intent(getApplicationContext(), Notepad.class);
                    startActivity(intent1);
                    break;
                case 2:
                    Intent intent2 = new Intent(getApplicationContext(), Calculator.class);
                    startActivity(intent2);

                    break;
                case 3:
                    Intent intent3 = new Intent(getApplicationContext(), ToDo.class);
                    startActivity(intent3);
                    break;
                case 4:
                    Intent intent4 = new Intent(getApplicationContext(), Alarm.class);
                    startActivity(intent4);
                    break;
                case 5:
                    Dialog dialog = new Dialog(Dashboard.this);
                    dialog.setContentView(R.layout.pdf_dialog);

                    Button dialogButton = (Button) dialog.findViewById(R.id.btnDone);
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();



                default:
                    Log.i("Position else", String.valueOf(position));

            }

        }
    };




    public void onBackPressed(){
        finishAffinity();
    }
}
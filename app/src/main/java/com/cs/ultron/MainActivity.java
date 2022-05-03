package com.cs.ultron;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.cs.ultron.dashboard.PopUp_Fragment;

public class MainActivity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                PopUp_Fragment fragment = PopUp_Fragment.newInstance() ;

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.popup_Container,fragment,"popup");
                transaction.commit();
            }
        }, secondsDelayed * 2000);

    }


}
package com.cs.ultron.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cs.ultron.R;

import java.util.Calendar;
import java.util.Date;

public class Alarm extends AppCompatActivity {

    TimePicker timePicker;
    TextView textView;
    AlarmBroadcastReceiver receiver = new AlarmBroadcastReceiver();

    int mHour, mMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        timePicker = (TimePicker) findViewById(R.id.timepicker);
        textView = (TextView) findViewById(R.id.textView);

        Button buttonCancelAlarm = findViewById(R.id.button_cancel);
        buttonCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });

        Button buttonStop = findViewById(R.id.button_stop);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Utility.ringtoneHelper != null) {
                    Utility.ringtoneHelper.stopRingtone();

                }


            }
        });


        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMin = minute;
                textView.setText("Choose your time");
            }
        });
    }


    public void setTimer(View view) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Date date = new Date();
        Calendar calendar_alarm = Calendar.getInstance();
        Calendar calendar_now = Calendar.getInstance();

        calendar_now.setTime(date);
        calendar_alarm.setTime(date);


        calendar_alarm.set(Calendar.HOUR_OF_DAY, mHour);
        calendar_alarm.set(Calendar.MINUTE, mMin);
        calendar_alarm.set(Calendar.SECOND, 0);

        if (calendar_alarm.before(calendar_now)) {
            calendar_alarm.add(Calendar.DATE, 1);
        }

        Intent intent = new Intent(Alarm.this, AlarmBroadcastReceiver.class);
        PendingIntent pendingIntent = android.app.PendingIntent.getBroadcast(Alarm.this, 0524, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar_alarm.getTimeInMillis(), pendingIntent);
        textView.setText("Alarm set");
    }


    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(Alarm.this, AlarmBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Alarm.this, 0524, intent, 0);
        alarmManager.cancel(pendingIntent);
        textView.setText("Alarm Canceled");


    }




}
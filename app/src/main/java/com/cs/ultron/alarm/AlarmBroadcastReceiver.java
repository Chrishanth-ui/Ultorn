package com.cs.ultron.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.cs.ultron.R;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        Notification notification = new Notification.Builder(context)
                .setContentTitle("Alarm is ON")
                .setContentText("You Have Set The Alarm")
                .setSmallIcon(R.mipmap.ic_launcher).build();

        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(0,notification);


        Uri notification_sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone r  = RingtoneManager.getRingtone(context,notification_sound);
        r.play();


        Utility.ringtoneHelper = new RingtoneHelper() {
            @Override
            public void stopRingtone() {
                if (r.isPlaying()){
                    r.stop();
                }

            }

        };

    }




}

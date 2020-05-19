package mo.ed.aad.alarmmanager.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver {
    private String PARTIALWAKE_STR="PartialWake";

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm=(PowerManager)context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,PARTIALWAKE_STR);

        wl.acquire();

        Toast.makeText(context,"Alarm !!!!", Toast.LENGTH_LONG).show();

        wl.release();
    }

    public void setAlarm(Context context){
        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(context, Alarm.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,0,intent,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000*60*10,pendingIntent);
    }

    public void cancelAlarm(Context context){
        Intent intent=new Intent(context, Alarm.class);
        PendingIntent sender=PendingIntent.getBroadcast(context,0,intent,0);
        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
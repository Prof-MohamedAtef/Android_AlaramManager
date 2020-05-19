package mo.ed.aad.alarmmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import mo.ed.aad.alarmmanager.receiver.Alarm;
import mo.ed.aad.alarmmanager.service.MyService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(getApplicationContext(), MyService.class);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getApplicationContext().startForegroundService(i);
        } else {
            getApplicationContext().startService(i);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Alarm alarm=new Alarm();
        alarm.cancelAlarm(getApplicationContext());
    }
}
package viquar.com.medreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText medicine,dosage;
    Button set;
    TimePicker timePicker;
    String med,dose;
    int hour,minute;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        medicine= (EditText) findViewById(R.id.medicine_input);
        dosage=(EditText)findViewById(R.id.dosage_input);
        set=(Button)findViewById(R.id.button_set);
        timePicker= (TimePicker) findViewById(R.id.timePicker);
        set.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        med=medicine.getText().toString();
        dose=dosage.getText().toString();
        hour=timePicker.getHour();
        minute=timePicker.getMinute();

        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("medicine",med);
        intent.putExtra("dosage",dose);
        alarmIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

// Set the alarm to start at time taken from timepicker.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

// setRepeating() lets you specify a precise custom interval--in this case,
// 2 minutes.
        //alarmMgr.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),alarmIntent);
       alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),1000*60*2, alarmIntent);



    }



}

package viquar.com.medreminder;

import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Locale;

public class AlarmReceiver extends AppCompatActivity {
    TextToSpeech textToSpeech;
    String say;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_receiver);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("CST"));
        int currenthour=calendar.get(Calendar.HOUR_OF_DAY);
        int currentminute=calendar.get(Calendar.MINUTE);
        Intent intent=getIntent();
        String medicineGet=intent.getStringExtra("medicine");
        String dosageGet=intent.getStringExtra("dosage");
        String time_speak="The time is "+currenthour+" "+currentminute;
        String med_speak="Please take the medicine called "+medicineGet;
        String dose_speak="The dosage is "+dosageGet;
        say=time_speak+" "+med_speak+" "+dose_speak;
        //generating the text to speech
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=textToSpeech.ERROR)
                {
                    int result=textToSpeech.setLanguage(Locale.ENGLISH);
                    if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(AlarmReceiver.this, "Language not Supported", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        speak(say);
                    }
                }

            }
        });

    }


    public void speak(String s){
        textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
    }
}

package in.ac.utm.smartreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class AlarmHandler extends AppCompatActivity {

    TimePicker tp;
    DatePicker dp;
    Button tDone;
    Button alarmOff;
    static EditText des;
    Context context;
    AlarmManager  alarm_manager;
    PendingIntent pending_intent;
    static String getDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_handler);

        tp = (TimePicker) findViewById(R.id.timePicker);
        dp = (DatePicker) findViewById(R.id.datePicker);
        tDone=(Button)findViewById(R.id.timeSet);
        des=(EditText)findViewById(R.id.timeDesc);
        alarmOff=(Button)findViewById(R.id.canc_btn);



       // Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        this.context=this;

        dp.setVisibility(View.GONE);

        final Intent myintent=new Intent(this.context,Alarm_Ringer.class);

        alarm_manager=(AlarmManager)getSystemService(ALARM_SERVICE);

        final Calendar calendar=Calendar.getInstance();
        tDone.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                   calendar.set(Calendar.HOUR_OF_DAY , tp.getHour());
                   int hours=tp.getHour();
               }
               else {calendar.set(Calendar.HOUR_OF_DAY,tp.getCurrentHour()); int hours=tp.getCurrentHour();}

               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                   calendar.set(Calendar.MINUTE,tp.getMinute());
                   int mins=tp.getHour();
               }
                else{ calendar.set(Calendar.MINUTE,tp.getCurrentMinute());  int mins=tp.getCurrentMinute();}

               //put in extra string in myintent until the specified time
               myintent.putExtra("extra","alarm on");


               pending_intent=PendingIntent.getBroadcast(AlarmHandler.this,0,myintent,PendingIntent.FLAG_UPDATE_CURRENT);

               alarm_manager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pending_intent);


               putMeBack();
           }
        });

        alarmOff.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //cancel the alarm
                alarm_manager.cancel(pending_intent);

                //put in extra string in myintent until the specified time
                myintent.putExtra("extra","alarm off");

                //stop the ringtone
                sendBroadcast(myintent);

                putMeBack();
            }
        });




    }
          static String mylogg(){
               getDes = des.getText().toString();
               return getDes;
           }
    public  static String alDesc(){
        return mylogg();
    }

    public void putMeBack(){
       // Intent i=new Intent(AlarmHandler.class,Chat.class);
       // startActivity(i);
        Intent i=new Intent(this,Chat.class);
        startActivity(i);
    }
}

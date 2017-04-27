package in.ac.utm.smartreminder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlarmStop extends AppCompatActivity {

    Button alarmOffing;
    TextView myview;
   // AlarmManager alarm_manager;
    Context context;
   // MediaPlayer media_songs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_stop);

        this.context=this;



       // alarm_manager=(AlarmManager)getSystemService(ALARM_SERVICE);
        alarmOffing=(Button) (findViewById(R.id.button2));
        myview=(TextView)(findViewById(R.id.viewDes));
       myview.setText(AlarmHandler.alDesc());
       // myview.setText("milton kumar your text here");
        alarmOffing.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                RingtonePlayingService.doWork();
            }
        });
    }
    public void onDestroy(){
        super.onDestroy();

    }

}

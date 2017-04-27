package in.ac.utm.smartreminder;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by milton on 4/1/2017.
 */

public class RingtonePlayingService extends Service {

   static MediaPlayer media_songs;
    int startId;
    boolean isRunning;
    private static final int uniqueId=40101;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.i("LocalService","Received start id"+startId+":"+intent);

        NotificationCompat.Builder notification;

        //set notification
       /* NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent1=new Intent(this.getApplicationContext(),AlarmHandler.class);

        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent1,0);

        Notification notification=new Notification.Builder(this).setContentTitle("ALARM").setContentText("click me")
                .setContentIntent(pendingIntent).setAutoCancel(true).build();


        notificationManager.notify(0,notification);*/

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        notification.setSmallIcon(R.mipmap.logo);
        notification.setTicker(" Smart Reminder");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("alarm going on");
        notification.setContentText("Click here to stop alarm...");

        Intent inte=new Intent(this,AlarmStop.class);

        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,inte,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueId,notification.build());





        //fetch extra string values
        String state=intent.getExtras().getString("extra");
        assert state !=null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        // if(!this.isRunning && startId==1){

        // }
         if(this.isRunning && startId==0){
             media_songs.stop();
             media_songs.reset();

             this.isRunning=false;
             this.startId=0;
         }
        else if(!this.isRunning && startId==0){
               //  this.isRunning=false;
            // this.startId=0;
         }

        else {

         }


        media_songs=MediaPlayer.create(this,R.raw.loveu);
        media_songs.start();
       // Intent i=new Intent(this.getApplicationContext(),StopAlarm.class);
       // startActivity(i);

        return START_NOT_STICKY;
    }

    public void onDestroy(){
          super.onDestroy();
        this.isRunning=false;
    }

    public static void doWork(){
        media_songs.stop();
        media_songs.reset();


    }

}

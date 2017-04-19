package in.ac.utm.smartreminder;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by milton on 4/1/2017.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer media_songs;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.i("LocalService","Received start id"+startId+":"+intent);
        media_songs=MediaPlayer.create(this,R.raw.azhar);
        media_songs.start();

        return START_NOT_STICKY;
    }
    public void onDestroy(){

    }

}

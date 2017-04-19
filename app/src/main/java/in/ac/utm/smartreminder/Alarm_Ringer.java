package in.ac.utm.smartreminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by milton on 4/1/2017.
 */

public class Alarm_Ringer extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent service_intent=new Intent(context,RingtonePlayingService.class);
        context.startService(service_intent);
    }
}

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

        //fetch extra string from intent
        String get_string=intent.getExtras().getString("extra");

        Intent service_intent=new Intent(context,RingtonePlayingService.class);

        //pass the extra string to the ringtone playing service
        service_intent.putExtra("extra",get_string);

        context.startService(service_intent);
    }
}

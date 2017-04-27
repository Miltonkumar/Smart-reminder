package in.ac.utm.smartreminder;

/**
 * Created by milton on 2/22/2017.
 */

import android.support.v7.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserDetails extends AppCompatActivity{
    static String username = "";
    static String password = "assistant";
    static String chatWith = "admin";

    public static void getUserName(String em) {
        username = em.split("@")[0];
    }

    public static String assistant(String mymess) {
        mymess=mymess.trim();
        String result = "";
            if(mymess.equalsIgnoreCase("hello"))
                return "hi...how can i help you";
            if(mymess.equalsIgnoreCase("hi"))
                return "hello...how can i help you";
            if(mymess.equalsIgnoreCase("morning")||mymess.equalsIgnoreCase("good morning"))
                return "Morning!!! have a nice day";
            if(mymess.equalsIgnoreCase("evening")||mymess.equalsIgnoreCase("good evening"))
                return "Evening!!!enjoy your evening";
            if(mymess.equalsIgnoreCase("good night")||mymess.equalsIgnoreCase("night"))
                return "good night!!! sweet dreams";

        // sending notification to friends -- keyword user
        Pattern p1=Pattern.compile("user");
        Matcher m1=p1.matcher(mymess);
        while (m1.find()){
            return "two";
        }

        // setting up alarm
        Pattern p=Pattern.compile(".*[rR]eminder|[wW]ake|[rR]emainder|[rR]emind|[aA]larm.*");
        Matcher m=p.matcher(mymess);
        while (m.find()){
            return "one";
        }

       /* Pattern p2=Pattern.compile("remind");
        Matcher m2=p2.matcher(mymess);
        while (m2.find()){
            return "one";
        }*/


            return "";
        }

    }


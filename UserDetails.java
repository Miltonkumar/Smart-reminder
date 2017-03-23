package in.ac.utm.smartreminder;

/**
 * Created by milto on 2/22/2017.
 */
import java.lang.String;

public class UserDetails {
    static String username = "";
    static String password = "assistant";
    static String chatWith = "admin";

    public static void getUserName(String em) {
        username = em.split("@")[0];
    }

    public static String assistant(String mymess) {
        mymess=mymess.trim();
        int l = mymess.length();
        int b = 0;
        String result = "";
        for (int i = 0; i < l - 1; i++) {
            char p = mymess.charAt(i);
            char q = mymess.charAt(i + 1);
        }
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


            return "i am unable to understand you...my admin will reply you soon.Thanks!!!";
        }

    }


package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static String getDateByAddingDays(String format, int diff){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, diff);
        return sdf.format(cal.getTime());
    }

    public static void printMap(Map<String,Integer> map){
        for (Map.Entry<String, Integer> m:
                map.entrySet()) {
            System.out.println(m.getKey() + " => " + m.getValue());

        }
    }
}

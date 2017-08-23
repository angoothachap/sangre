package fi.sangre.assignment.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by rsubramanian on 8/17/2017.
 */
public class Util {
    private static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static String getTodaysDate()  {
        SimpleDateFormat sm = new SimpleDateFormat(YYYY_MM_DD);
        return sm.format(new Date());
    }

    public static String getRandomObjectFromList(List<String> list, int listSize)  {
        Random randomizer = new Random();
        return list.get(randomizer.nextInt(listSize));
    }
}

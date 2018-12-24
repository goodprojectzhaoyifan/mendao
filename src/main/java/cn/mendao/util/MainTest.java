package cn.mendao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by warden on 18/5/24.
 */
public class MainTest {


    public static void main(String[] args) throws Exception {


        String aaa = "1/693[39]";
        String ragex = "\\[(.*?)\\]";
        String bbb = aaa.replaceAll(ragex, "[**]");

        Pattern p = Pattern.compile("\\[(.*?)\\]");
        Matcher m = p.matcher(aaa);
        while(m.find()) {
            System.out.println(m.group(1));
        }

        System.out.println(getRemainSecondsOneDay(new Date()));
        System.out.println(getTimeForMidnight());
    }


    public static Long getRemainSecondsOneDay(Date currentDate) {
        Calendar midnight=Calendar.getInstance();
        midnight.setTime(currentDate);
        midnight.add(midnight.DAY_OF_MONTH, 1);
        midnight.set(midnight.HOUR_OF_DAY,0);
        midnight.set(midnight.MINUTE, 0);
        midnight.set(midnight.SECOND,0);
        midnight.set(midnight.MILLISECOND, 0);
        Long seconds=(long)((midnight.getTime().getTime()-currentDate.getTime())/1000);
        return seconds;
    }


    public static long getTimeForMidnight() throws ParseException {
        //获取当前时间
        Date nowDate = new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(nowDate);
        dateString = dateString + " 23:59:59";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date midNightDate=sdf.parse(dateString);
        //获取当前时间距离凌晨12点的毫秒数
        int l = (int) (midNightDate.getTime() - nowDate.getTime());
        return l/1000;
    }
}

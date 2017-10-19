import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wpliu on 16/4/7.
 */
public class TestClass {

    public static void  main(String[] args){
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date= format.parse("20160518130242826");
            Date date1=format.parse("20160518125902094");

            long days = (date.getTime()-date1.getTime()) / (1000 * 60 * 60 );
            System.out.println((date.getTime()-date1.getTime())/1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(format.format(new Date()));
        System.out.println(format2.format(new Date()));
    }
}

package cn.thinkjoy.ctd.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxilin on 2016/12/15.
 */
public class PresentDataUtil {
    /**序列号*/
    private static long seq = 1;
    /**流量包赠送交易码 [固定值] LLSL */
    public static final String PRESENT_OPT_CODE = "LLSL";
    /**流量包赠送结果查询交易码 [固定值] LLQY */
    public static final String QUERY_OPT_CODE = "LLQY";
    /**外围渠道 [固定值] QTJY */
    public static final String BIZ_CODE = "QTJY";
    /**批量赠送的数量 */
    public static final String SUCCESS_CODE = "0000000";
    /**批量赠送的数量 */
    public static final int DEFAULT_ROWS = 100;
    /**路径分隔符 */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * 获取序列号
     */
    public static synchronized String nextSerialNo() {
        String seqStr = String.valueOf(seq);
        while (seqStr.length() < 8) {
            seqStr = "0" + seqStr;
        }
        seq++;
        if (seq > 99999999) {
            seq = 1;
        }
        return seqStr;
    }

    /**
     * 交易日期字符串
     */
    public static String getTradeDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * 清算日期字符串
     */
    public static String getAccountDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(currentDate.getTime());
    }

    public static Map<String,String> getOfferIDMap(){
        Map<String,String> offerIdMap = new HashMap<>();

        offerIdMap.put("省内接口通用流量3元10MB产品","600000287759");
        offerIdMap.put("省内接口通用流量5元30MB产品","600000287766");
        offerIdMap.put("省内接口通用流量10元70MB产品","600000287768");
        offerIdMap.put("省内接口通用流量20元150MB产品","600000287770");
        offerIdMap.put("省内接口通用流量30元500MB产品","600000287772");
        offerIdMap.put("省内接口通用流量50元1GB产品","600000287774");
        offerIdMap.put("省内接口通用流量70元2GB产品","600000287776");
        offerIdMap.put("省内接口通用流量100元3GB产品","600000287778");
        offerIdMap.put("省内接口通用流量130元4GB产品","600000287781");
        offerIdMap.put("省内接口通用流量180元6GB产品", "600000287785");
        offerIdMap.put("省内接口通用流量280元11GB产品", "600000287787");

        return offerIdMap;
    }

    public static Map<String,String> getOfferNameMap(){
        Map<String,String> offerNameMap = new HashMap<>();

        offerNameMap.put("10M流量","省内接口通用流量3元10MB产品");
        offerNameMap.put("30M流量","省内接口通用流量5元30MB产品");
        offerNameMap.put("70M流量","省内接口通用流量10元70MB产品");
        offerNameMap.put("150M流量","省内接口通用流量20元150MB产品");
        offerNameMap.put("500M流量","省内接口通用流量30元500MB产品");
        offerNameMap.put("1G流量","省内接口通用流量50元1GB产品");
        offerNameMap.put("2G流量","省内接口通用流量70元2GB产品");
        offerNameMap.put("3G流量","省内接口通用流量100元3GB产品");
        offerNameMap.put("4G流量","省内接口通用流量130元4GB产品");
        offerNameMap.put("6G流量","省内接口通用流量180元6GB产品");
        offerNameMap.put("11G流量","省内接口通用流量280元11GB产品");

        return offerNameMap;
    }

    public static int totalPage(int total){
        //计算分页
        int pageTem = total / DEFAULT_ROWS;
        int totalPage = total % DEFAULT_ROWS == 0 ? pageTem : pageTem + 1;//总页数
        return totalPage;
    }

    public static void main(String[] argus){
        System.out.println("文件分隔符：" + System.getProperty("file.separator")); // 文件分隔符
        System.out.println("路径分隔符：" + System.getProperty("path.separator")); // 路径分隔符
        System.out.println("直线分隔符：" + System.getProperty("line.separator")); // 直线分隔符
        System.out.println("操作系统用户名：" + System.getProperty("user.name")); // 用户名
        System.out.println(System.getProperty("user.dir"));

        File file = new File("D:\\Program Files\\apache-tomcat-7.0.72\\webapps\\ROOT\\excel\\1.txt");
        if (!file.exists()){
            file.mkdirs();
        }

    }
}

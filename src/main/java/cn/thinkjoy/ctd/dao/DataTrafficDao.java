package cn.thinkjoy.ctd.dao;


import cn.thinkjoy.ctd.bean.PresentDataInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangxilin on 16/12/16.
 */
public interface DataTrafficDao {

    int findPresentDataTotal();

    List<PresentDataInfo> findPresentData(@Param("start") int start, @Param("end") int end);

    void insertPresentData(@Param("list") List<PresentDataInfo> list);

    void updatePresentData(@Param("list") List<PresentDataInfo> list);

    List<PresentDataInfo> findBatchPresent(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("billId") String billId, @Param("presentStatus") String presentStatus, @Param("startRow") Integer startRow, @Param("endRow") Integer endRow);

    int findBatchPresentTotal(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("billId") String billId, @Param("presentStatus") String presentStatus);
}

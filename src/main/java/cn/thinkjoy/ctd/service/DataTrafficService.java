package cn.thinkjoy.ctd.service;

import cn.thinkjoy.ctd.bean.PresentDataInfo;

import java.util.List;

/**
 * Created by zhangxilin on 16/12/16.
 */
public interface DataTrafficService {

    int findPresentDataTotal();

    List<PresentDataInfo> findPresentData(int start, int end);

    void insertPresentData(List<PresentDataInfo> list);

    void updatePresentData(List<PresentDataInfo> list);

    List<PresentDataInfo> findBatchPresent(String startTime, String endTime, String billId, String presentStatus, Integer startRow, Integer endRow);

    int findBatchPresentTotal(String startTime, String endTime, String billId, String presentStatus);
}
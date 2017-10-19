package cn.thinkjoy.ctd.service.impl;

import cn.thinkjoy.ctd.bean.PresentDataInfo;
import cn.thinkjoy.ctd.dao.DataTrafficDao;
import cn.thinkjoy.ctd.service.DataTrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangxilin on 16/12/16.
 */
@Service("dataTrafficService")
public class DataTrafficServiceImpl implements DataTrafficService {

    @Autowired
    private DataTrafficDao dataTrafficDao;

    @Override
    public int findPresentDataTotal() {
        return dataTrafficDao.findPresentDataTotal();
    }

    @Override
    public List<PresentDataInfo> findPresentData(int start, int end) {
        return dataTrafficDao.findPresentData(start, end);
    }

    @Override
    public void insertPresentData(List<PresentDataInfo> list) {
        dataTrafficDao.insertPresentData(list);
    }

    @Override
    public void updatePresentData(List<PresentDataInfo> list) {
        dataTrafficDao.updatePresentData(list);
    }

    @Override
    public List<PresentDataInfo> findBatchPresent(String startTime, String endTime, String billId, String presentStatus, Integer startRow, Integer endRow) {
        return dataTrafficDao.findBatchPresent(startTime, endTime, billId, presentStatus, startRow, endRow);
    }

    @Override
    public int findBatchPresentTotal(String startTime, String endTime, String billId, String presentStatus) {
        return dataTrafficDao.findBatchPresentTotal(startTime, endTime, billId, presentStatus);
    }
}

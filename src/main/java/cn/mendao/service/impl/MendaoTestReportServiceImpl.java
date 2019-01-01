package cn.mendao.service.impl;

import cn.mendao.bean.MendaoTestReport;
import cn.mendao.dao.MendaoTestReportDao;
import cn.mendao.service.MendaoTestReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 18/1/25.
 */
@Service("mendaoTestReportService")
public class MendaoTestReportServiceImpl implements MendaoTestReportService{

    @Autowired
    private MendaoTestReportDao testReportDao;

    @Override
    public MendaoTestReport findOne(Long id) {
        return testReportDao.findOne(id);
    }

    @Override
    public List<MendaoTestReport> getListByTestId(long testId) {
        return testReportDao.getListByTestId(testId);
    }

    @Override
    public void insertDate(MendaoTestReport testReport) {
        testReportDao.insertDate(testReport);
    }

    @Override
    public void updateDate(MendaoTestReport testReport) {
        testReportDao.updateDate(testReport);
    }

}

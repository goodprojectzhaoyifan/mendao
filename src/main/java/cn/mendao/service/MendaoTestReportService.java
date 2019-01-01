package cn.mendao.service;

import cn.mendao.bean.MendaoTestReport;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
public interface MendaoTestReportService {


    MendaoTestReport findOne(Long id);

    List<MendaoTestReport> getListByTestId(long testId);

    void insertDate(MendaoTestReport testReport);

    void updateDate(MendaoTestReport testReport);
}

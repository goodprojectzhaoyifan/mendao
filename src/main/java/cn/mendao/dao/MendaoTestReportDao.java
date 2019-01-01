package cn.mendao.dao;

import cn.mendao.bean.MendaoTestReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
public interface MendaoTestReportDao {

    MendaoTestReport findOne(@Param("id") Long id);

    List<MendaoTestReport> getListByTestId(@Param("testId")long testId);

    int insertDate(@Param("testReport") MendaoTestReport testReport);

    void updateDate(@Param("testReport") MendaoTestReport testReport);
}

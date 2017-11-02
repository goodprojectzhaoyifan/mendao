package cn.mendao.service;

import cn.mendao.bean.School;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
public interface SchoolService {
    List<School> getListByParam(int type, String major);
}

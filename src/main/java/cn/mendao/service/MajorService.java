package cn.mendao.service;

import cn.mendao.bean.Major;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
public interface MajorService {
    List<Major> getListByParam(int type, String code, String majorName);
}

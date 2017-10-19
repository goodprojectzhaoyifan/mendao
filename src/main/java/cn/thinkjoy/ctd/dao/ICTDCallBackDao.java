package cn.thinkjoy.ctd.dao;


import org.apache.ibatis.annotations.Param;

/**
 * Created by wpliu on 16/3/14.
 */
public interface ICTDCallBackDao  {


    void callBack(@Param("sessionid")String sessionid, @Param("callerId")String callerId, @Param("calledId")String calledId, @Param("duration")long duration, @Param("date") String date ,@Param("state")String state);

    void updateCallInfo(@Param("sessionid")String sessionid,  @Param("duration")long duration, @Param("date") String date,@Param("state")String state);

    void updateCallSubRel(@Param("sessionid")String sessionid,@Param("duration")long duration);


}

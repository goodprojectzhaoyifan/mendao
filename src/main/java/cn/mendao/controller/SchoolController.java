package cn.mendao.controller;

import cn.mendao.bean.Major;
import cn.mendao.bean.School;
import cn.mendao.resp.*;
import cn.mendao.service.MajorService;
import cn.mendao.service.SchoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Scope("prototype")
@Controller("schoolController")
@RequestMapping(value = "/mendao")
public class SchoolController {

    private static final Logger logger = LoggerFactory.getLogger(SchoolController.class);

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private MajorService majorService;

    @ResponseBody
    @RequestMapping(value = "/getSchool")
    public Object clickView(HttpServletRequest request){
        BaseRespList resp = new BaseRespList();
        try{
            String typeString =  request.getParameter("type");
            String major = request.getParameter("major");
            int type = 1;
            if(typeString != null && !"".equals(typeString)){
                type = Integer.valueOf(typeString);
            }
            List<School> list = schoolService.getListByParam(type, major);
            List<SchoolResp> respList = new ArrayList<>();
            for(School school:list){
                SchoolResp schoolResp = new SchoolResp();
                schoolResp.setSort(school.getSort());
                schoolResp.setCode(school.getCode());
                schoolResp.setSchoolName(school.getSchoolName());
                schoolResp.setSortNum(school.getSortNum());
                schoolResp.setMsg2015(school.getToudang2015() + "|" + school.getMinscore2015() + "|<strong>" + school.getLowest2015()+"</strong>");
                schoolResp.setMsg2016(school.getToudang2016() + "|" + school.getMinscore2016() + "|<strong>" + school.getLowest2016()+"</strong>");
                schoolResp.setMsg2017(school.getToudang2017() + "|" + school.getMinscore2017() + "|<strong>" + school.getLowest2017()+"</strong>");
                schoolResp.setManager(school.getManager());
                UtilResp province = new UtilResp();
                province.setName(school.getProvince());
                schoolResp.setProvince(province);
                schoolResp.setCity(school.getCity());
                UtilResp level = new UtilResp();
                level.setName(school.getLevel());
                schoolResp.setLevel(level);
                schoolResp.setPlan2018(school.getPlan2018());
                //对比招生计划
                if(school.getPlan2018()-school.getPlan2017()>0){
                    schoolResp.setPlanStatus2018(1);
                }else{
                    schoolResp.setPlanStatus2018(0);
                }
                schoolResp.setColor(school.getColor());
                schoolResp.setDescribe(school.getSchoolDesc());
                UtilResp property = new UtilResp();
                property.setName(school.getProperty());
                schoolResp.setProperty(property);
                schoolResp.setMajorPlan(school.getMajorPlan());

                if(school.getType() == 1){
                    schoolResp.setType("理工类一本");
                }else if(school.getType() == 2){
                    schoolResp.setType("文史类一本");
                }else if(school.getType() == 3){
                    schoolResp.setType("理工类二本");
                }else if(school.getType() == 4){
                    schoolResp.setType("文史类二本");
                }else if(school.getType() == 5){
                    schoolResp.setType("理工类三本");
                }else if(school.getType() == 6){
                    schoolResp.setType("文史类三本");
                }


                respList.add(schoolResp);
            }
            resp.setCode(1);
            resp.setMsg("请求成功");
            resp.setList(respList);
            return resp;
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("请求失败");
            return resp;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getMajor")
    public Object getMajor(HttpServletRequest request){
        BaseRespList resp = new BaseRespList();
        try{
            String schoolCode =  request.getParameter("schoolCode");
            String typeString = request.getParameter("type");
            String majorName = request.getParameter("majorName");
            int type = 1;
            if(typeString != null && !"".equals(typeString)) {
                type = Integer.valueOf(typeString);
            }
            List<Major> list = majorService.getListByParam(type, schoolCode, majorName);
            List<MajorResp> respList =  new ArrayList<>();
            for(Major major:list){
                MajorResp majorResp = new MajorResp();
                majorResp.setCode(major.getCode());
                majorResp.setMajorName(major.getMajorName());
                majorResp.setMsg2015(major.getMsg2015());
                majorResp.setMsg2016(major.getMsg2016());
                majorResp.setMsg2017(major.getMsg2017());
                majorResp.setLevel(major.getLevel());
                majorResp.setMajorLimit(major.getMajorLimit());
                majorResp.setMajorDesc(major.getMajorDesc());

                majorResp.setPlan2018(major.getPlan2018());
                //对比招生计划
                if(major.getPlan2018()-major.getPlan2017()>0){
                    majorResp.setPlanStatus2018(1);
                }else{
                    majorResp.setPlanStatus2018(0);
                }

                respList.add(majorResp);
            }

            resp.setCode(1);
            resp.setMsg("请求成功");
            resp.setList(respList);
            return resp;
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("请求失败");
            return resp;
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getSchoolV2")
    public Object getSchoolV2(HttpServletRequest request){
        BaseRespList resp = new BaseRespList();
        try{
            String typeString =  request.getParameter("type");
            String majorString = request.getParameter("major");
            int type = 1;
            if(typeString != null && !"".equals(typeString)){
                type = Integer.valueOf(typeString);
            }
            List<School> list = schoolService.getListByParam(type, majorString);
            List<SchoolRespV2> respList = new ArrayList<>();
            for(School school:list){
                SchoolRespV2 schoolResp = new SchoolRespV2();
                schoolResp.setSort(school.getSort());
                schoolResp.setCode(school.getCode());
                schoolResp.setSchoolName(school.getSchoolName());
                schoolResp.setSortNum(school.getSortNum());
                schoolResp.setMsg2015(school.getToudang2015() + "|" + school.getMinscore2015() + "|<strong>" + school.getLowest2015()+"</strong>");
                schoolResp.setMsg2016(school.getToudang2016() + "|" + school.getMinscore2016() + "|<strong>" + school.getLowest2016()+"</strong>");
                schoolResp.setMsg2017(school.getToudang2017() + "|" + school.getMinscore2017() + "|<strong>" + school.getLowest2017()+"</strong>");
                schoolResp.setManager(school.getManager());
                UtilResp province = new UtilResp();
                province.setName(school.getProvince());
                schoolResp.setProvince(province);
                schoolResp.setProvince(province);
                UtilResp city = new UtilResp();
                city.setName(school.getCity());
                schoolResp.setCity(city);
                UtilResp level = new UtilResp();
                level.setName(school.getLevel());
                schoolResp.setLevel(level);
                schoolResp.setPlan2018(school.getPlan2018());
                //对比招生计划
                if(school.getPlan2018()-school.getPlan2017()>0){
                    schoolResp.setPlanStatus2018(1);
                }else{
                    schoolResp.setPlanStatus2018(0);
                }
                schoolResp.setColor(school.getColor());
                schoolResp.setDescribe(school.getSchoolDesc());
                UtilResp property = new UtilResp();
                property.setName(school.getProperty());
                schoolResp.setProperty(property);
                schoolResp.setMajorPlan(school.getMajorPlan());

                if(school.getType() == 1){
                    schoolResp.setType("理工类一本");
                }else if(school.getType() == 2){
                    schoolResp.setType("文史类一本");
                }else if(school.getType() == 3){
                    schoolResp.setType("理工类二本");
                }else if(school.getType() == 4){
                    schoolResp.setType("文史类二本");
                }else if(school.getType() == 5){
                    schoolResp.setType("理工类三本");
                }else if(school.getType() == 6){
                    schoolResp.setType("文史类三本");
                }

                List<MajorResp> majorRespList = new ArrayList<>();
                List<Major> majorList = majorService.getListByParam(type, school.getCode(), null);
                for(Major major:majorList){
                    MajorResp majorResp = new MajorResp();
                    majorResp.setCode(major.getCode());
                    majorResp.setMajorName(major.getMajorName());
                    majorResp.setMsg2015(major.getMsg2015());
                    majorResp.setMsg2016(major.getMsg2016());
                    majorResp.setMsg2017(major.getMsg2017());
                    majorResp.setLevel(major.getLevel());
                    majorResp.setMajorLimit(major.getMajorLimit());
                    majorResp.setMajorDesc(major.getMajorDesc());

                    majorResp.setPlan2018(major.getPlan2018());
                    //对比招生计划
                    if(major.getPlan2018()-major.getPlan2017()>0){
                        majorResp.setPlanStatus2018(1);
                    }else{
                        majorResp.setPlanStatus2018(0);
                    }

                    majorRespList.add(majorResp);
                }

                schoolResp.setMajorList(majorRespList);
                respList.add(schoolResp);
            }
            resp.setCode(1);
            resp.setMsg("请求成功");
            resp.setList(respList);
            return resp;
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("请求失败");
            return resp;
        }
    }


}

package cn.mendao.controller;

import cn.mendao.bean.School;
import cn.mendao.resp.BaseRespList;
import cn.mendao.resp.SchoolResp;
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

    @ResponseBody
    @RequestMapping(value = "/schoolList")
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
                schoolResp.setMsg2015(school.getToudang2015() + " " + school.getMinscore2015() + " " + school.getLowest2015());
                schoolResp.setMsg2016(school.getToudang2016() + " " + school.getMinscore2016() + " " + school.getLowest2016());
                schoolResp.setMsg2017(school.getToudang2017() + " " + school.getMinscore2017() + " " + school.getLowest2017());
                schoolResp.setManager(school.getManager());
                schoolResp.setProvince(school.getProvince());
                schoolResp.setCity(school.getCity());
                schoolResp.setLevel(school.getLevel());
                schoolResp.setPlan2018(school.getPlan2018());
                //对比招生计划
                if(school.getPlan2018()-school.getPlan2017()>0){
                    schoolResp.setPlanStatus2018(1);
                }else{
                    schoolResp.setPlanStatus2018(0);
                }
                schoolResp.setColor(school.getColor());
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
            resp.setCode(0);
            resp.setMsg("请求失败");
            return resp;
        }
    }




}

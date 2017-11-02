package cn.mendao.controller;

import cn.mendao.bean.School;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Scope("prototype")
@Controller("testController")
@RequestMapping(value = "/")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Object clickView(HttpServletRequest request){
        School school = new School();
        school.setSort(1);
        school.setCode("1001");
        school.setSchoolName("清华大学");
        return school;
    }




}

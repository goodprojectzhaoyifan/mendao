package cn.mendao.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Scope("prototype")
@Controller("IndexController")
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "/")
    public void clickView(HttpServletRequest request,HttpServletResponse response){
        String contextPath = request.getContextPath();
        try {
            response.sendRedirect(contextPath + "/index.html#/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

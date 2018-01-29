package cn.mendao.controller;

import cn.mendao.bean.User;
import cn.mendao.resp.BaseRespData;
import cn.mendao.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Scope("prototype")
@Controller("loginController")
@RequestMapping(value = "/mendao")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object clickView(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        BaseRespData resp = new BaseRespData();
        try{
            if(username == null || password == null){
                resp.setCode(0);
                resp.setMsg("用户名或者密码不可为空");
                return resp;
            }
            List<User> list = userService.getUser(username,password);
            if(list != null && list.size()>0){
                resp.setCode(1);
                resp.setMsg("登录成功");
                resp.setData(list.get(0));
                return resp;
            }else{
                resp.setCode(0);
                resp.setMsg("用户名或者密码输入错误");
                return resp;
            }
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("请求失败");
            return resp;
        }
    }



}

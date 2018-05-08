package cn.mendao.controller;

import cn.mendao.bean.User;
import cn.mendao.resp.BaseResp;
import cn.mendao.resp.BaseRespList;
import cn.mendao.resp.UserResp;
import cn.mendao.service.UserService;
import cn.mendao.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Scope("prototype")
@Controller("userController")
@RequestMapping(value = "/mendao")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    public Object getUserList(HttpServletRequest request){
        BaseRespList resp = new BaseRespList();
        try{
            List<User> list = userService.getUserListByParam(null, null, 0);
            List<UserResp> respList = new ArrayList<>();
            for(User user:list){
                UserResp userResp = new UserResp();
                userResp.setId(user.getId());
                userResp.setUsername(user.getUsername());
                userResp.setName(user.getName());
                userResp.setPassword(user.getPassword());
                userResp.setUserType(user.getUserType());
                userResp.setCreatedTime(user.getCreatedTime());
                respList.add(userResp);
            }
            resp.setList(respList);
            resp.setCode(1);
            resp.setMsg("请求成功");
            return resp;
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("请求失败");
            return resp;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/editUser",method = RequestMethod.POST)
    public Object editUser(HttpServletRequest request){
        BaseResp resp = new BaseResp();
        try{
            String data = request.getParameter("data");
            System.out.println("editUser---->"+data);
            if(data == null){
                resp.setCode(0);
                resp.setMsg("参数为空");
            }

            User user = JsonUtil.tranjsonStrToObject(data, User.class);
            if(user != null && user.getId() > 0){
                User oldUser = userService.findOne(user.getId());
                oldUser.setUsername(user.getUsername());
                oldUser.setName(user.getName());
                oldUser.setArea(user.getArea());
                oldUser.setPhone(user.getPhone());
                if(user.getPassword() != null && !user.getPassword().equals("")){
                    oldUser.setPassword(user.getPassword());
                }
                if(user.getStatus()>0){
                    oldUser.setStatus(user.getStatus());
                }
                userService.updateDate(oldUser);
            }else{
                if(user == null){
                    resp.setCode(0);
                    resp.setMsg("参数错误");
                    return resp;
                }
                user.setUserType(2);
                userService.insertDate(user);
            }
            resp.setCode(1);
            resp.setMsg("请求成功");
            return resp;
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("请求失败");
            return resp;
        }
    }


}

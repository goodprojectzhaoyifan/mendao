package cn.mendao.controller;


import cn.mendao.bean.UploadBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



@Scope("prototype")
@Controller("uploadController")
@RequestMapping(value = "/file")
public class UploadController {



    String upAddress="http://118.31.37.164:8080/upload/";

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Object upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {

        System.out.println("开始");
        UploadBean uploadBean = new UploadBean();
        //保存
        try {
            String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            path = path+df.format(new Date());

            String fileName = file.getOriginalFilename();
    //        String fileName = new Date().getTime()+".jpg";
            int l=fileName.lastIndexOf(".");
            String fileTail=fileName.substring(l);
            String newFileName=System.currentTimeMillis()+fileTail;
            System.out.println(path);
            File targetFile = new File(path, newFileName);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }

            file.transferTo(targetFile);

            uploadBean.setImageUrl(upAddress+df.format(new Date())+"/"+newFileName);
            uploadBean.setState(1);

        } catch (Exception e) {
            e.printStackTrace();
            uploadBean.setImageUrl("");
            uploadBean.setState(0);
        }
        return uploadBean;
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request) {
        Map<String,String> aaa = new HashMap<>();
        aaa.put("aaa","哈哈哈");
        return new ModelAndView("upload",aaa);
    }
}

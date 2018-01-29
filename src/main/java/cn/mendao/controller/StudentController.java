package cn.mendao.controller;

import cn.mendao.bean.StuScheme;
import cn.mendao.bean.Student;
import cn.mendao.bean.User;
import cn.mendao.req.Schemes;
import cn.mendao.req.StuSchemeList;
import cn.mendao.resp.*;
import cn.mendao.service.StuSchemeService;
import cn.mendao.service.StudentService;
import cn.mendao.service.UserService;
import cn.mendao.util.JsonUtil;
import cn.mendao.util.RedisUtil;
import com.alibaba.fastjson.JSONObject;
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
import java.util.Calendar;
import java.util.List;

@Scope("prototype")
@Controller("studentController")
@RequestMapping(value = "/mendao")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private StuSchemeService stuSchemeService;

    @ResponseBody
    @RequestMapping(value = "/editStudent",method = RequestMethod.POST)
    public Object editStudent(HttpServletRequest request){

        BaseResp resp = new BaseResp();
        try{
            String data = request.getParameter("data");
            if(data == null){
                resp.setCode(0);
                resp.setMsg("参数为空");
            }

            Student student = JsonUtil.tranjsonStrToObject(data, Student.class);
            if(student.getId() > 0){
                Student oldStudent = studentService.findOne(student.getId());
                oldStudent.setName(student.getName());
                oldStudent.setExamNo(student.getExamNo());
                oldStudent.setCardNo(student.getCardNo());
                oldStudent.setMajor(student.getMajor());
                oldStudent.setSex(student.getSex());
                oldStudent.setSchool(student.getSchool());
                oldStudent.setRank(student.getRank());
                oldStudent.setStuNum(student.getStuNum());
                oldStudent.setStuType(student.getStuType());
                oldStudent.setScore(student.getScore());
                oldStudent.setPrecedence(student.getPrecedence());
                oldStudent.setPolicy(student.getPolicy());
                oldStudent.setLimitContent(student.getLimitContent());
                oldStudent.setOther(student.getOther());
                oldStudent.setChinese(student.getChinese());
                oldStudent.setEnglish(student.getEnglish());
                oldStudent.setManth(student.getManth());
                oldStudent.setPhysics(student.getPhysics());
                oldStudent.setChemistry(student.getChemistry());
                oldStudent.setBiology(student.getBiology());
                oldStudent.setComprehensive(student.getComprehensive());

                studentService.updateData(oldStudent);
            }else{
                //保存学生数据
                student.setStuNo(createStuNo());
                studentService.insertDate(student);
            }
            resp.setCode(1);
            resp.setMsg("请求成功");
            return resp;
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("发生异常");
            return resp;
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getStudent",method = RequestMethod.POST)
    public Object getStudent(HttpServletRequest request){
        String userId = request.getParameter("userId");
        BaseRespList resp = new BaseRespList();
        try{
            List<Student> list = new ArrayList<>();
            User user = userService.findOne(Long.valueOf(userId));
            if(user == null){
                resp.setCode(0);
                resp.setMsg("参数错误");
                return resp;
            }else{
                //如果是管理员获取所有学生
                if(user.getUserType() == 1){
                    list = studentService.getList(0l);
                }else{
                    list = studentService.getList(Long.valueOf(userId));
                }
            }

            List<StudentResp> respList = new ArrayList<>();
            if(list != null && list.size()>0){
                for (Student student:list){
                    StudentResp studentResp = new StudentResp();
                    studentResp.setId(student.getId());
                    studentResp.setName(student.getName());
                    studentResp.setStuNo(student.getStuNo());
                    studentResp.setSex(student.getSex());
                    studentResp.setMajor(student.getMajor());
                    studentResp.setSchool(student.getSchool());
                    studentResp.setScore(student.getScore());
                    studentResp.setPrecedence(student.getPrecedence());
                    if(student.getStuNum()>0 && student.getRank()>0){
                        studentResp.setRanking(student.getRank()+"/"+student.getStuNum());
                    }
                    respList.add(studentResp);
                }
            }
            resp.setList(respList);
            resp.setCode(1);
            resp.setMsg("请求成功");
            return resp;
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("发生异常");
            return resp;
        }

    }


    @ResponseBody
    @RequestMapping(value = "/saveStuScheme",method = RequestMethod.POST)
    public Object saveStuScheme(HttpServletRequest request){

        BaseResp resp = new BaseResp();
        try{
            String data = request.getParameter("data");
            if(data == null){
                resp.setCode(0);
                resp.setMsg("参数为空");
            }

            StuSchemeList stuSchemeList = JsonUtil.tranjsonStrToObject(data, StuSchemeList.class);
            //先删除这个学生以前的志愿方案
            stuSchemeService.deleteDataByStuNo(stuSchemeList.getStuNo());
            //新增志愿
            if(stuSchemeList.getSchemes() != null && stuSchemeList.getSchemes().size()>0){
                for(Schemes scheme:stuSchemeList.getSchemes()){
                    StuScheme stuScheme = new StuScheme();
                    stuScheme.setStuNo(stuSchemeList.getStuNo());
                    stuScheme.setSchemeName(scheme.getSchemeName());
                    stuScheme.setSchemeNum(scheme.getSchemeNum());
                    stuScheme.setSchoolName(scheme.getSchoolName());
                    stuScheme.setSchoolCode(scheme.getSchoolCode());
                    stuScheme.setMajorList(scheme.getMajorList());
                    stuScheme.setSchemeChange(scheme.getSchemeChange());

                    stuSchemeService.insertData(stuScheme);
                }
            }

            resp.setCode(1);
            resp.setMsg("请求成功");
            return resp;
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("发生异常");
            return resp;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getStuScheme",method = RequestMethod.POST)
    public Object getStuScheme(HttpServletRequest request){
        String stuNo = request.getParameter("stuNo");
        BaseRespList resp = new BaseRespList();
        List<StuSchemeResp> respList = new ArrayList<>();
        try{
            List<StuScheme> list = stuSchemeService.getListByStuNo(stuNo);

            if(list != null && list.size()>0){
                for(StuScheme stuScheme:list){
                    StuSchemeResp stuSchemeResp = new StuSchemeResp();
                    stuSchemeResp.setId(stuScheme.getId());
                    stuSchemeResp.setStuNo(stuScheme.getStuNo());
                    stuSchemeResp.setSchemeName(stuScheme.getSchemeName());
                    stuSchemeResp.setSchemeNum(stuScheme.getSchemeNum());
                    stuSchemeResp.setSchoolName(stuScheme.getSchoolName());
                    stuSchemeResp.setSchoolCode(stuScheme.getSchoolCode());
                    stuSchemeResp.setSchemeChange(stuScheme.getSchemeChange());
                    if(stuScheme.getMajorList() != null && !"".equals(stuScheme.getMajorList())){
                        List<StuSchemeMajorResp> majorRespList = JSONObject.parseArray(stuScheme.getMajorList(), StuSchemeMajorResp.class);
                        stuSchemeResp.setMajorList(majorRespList);
                    }
                    respList.add(stuSchemeResp);
                }
            }

            resp.setList(respList);
            resp.setCode(1);
            resp.setMsg("请求成功");
            return resp;
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("发生异常");
            return resp;
        }

    }

    /**
     * 学生编号生成
     * @return
     */
    public String createStuNo(){
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = Calendar.DAY_OF_MONTH;

        StringBuffer no = new StringBuffer();
        no.append(year-2000);
        if(month<10){
            no.append("0"+month);
        }else{
            no.append(month);
        }
        if(day<10){
            no.append("0"+day);
        }else{
            no.append(day);
        }

        String key = no.toString()+"_no";
        RedisUtil redisUtil = new RedisUtil();
        Object num = redisUtil.get(key);
        if(num == null){
            no.append("0001");
            redisUtil.set(key,2,7200l);
        }else{
            no.append(String.format("%04d", (int) num));
            redisUtil.set(key,(int)num + 1,7200l);
        }
        return no.toString();
    }
}

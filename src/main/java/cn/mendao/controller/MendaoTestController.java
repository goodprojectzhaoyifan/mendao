package cn.mendao.controller;

import cn.mendao.bean.MendaoTest;
import cn.mendao.bean.MendaoTestReport;
import cn.mendao.bean.MendaoTopicLibrary;
import cn.mendao.req.MendaoTestReq;
import cn.mendao.resp.*;
import cn.mendao.service.MendaoTestReportService;
import cn.mendao.service.MendaoTestService;
import cn.mendao.service.MendaoTopicLibraryService;
import cn.mendao.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Scope("prototype")
@Controller("mendaoTestController")
@RequestMapping(value = "/mendao")
public class MendaoTestController {

    private static final Logger logger = LoggerFactory.getLogger(MendaoTestController.class);

    @Autowired
    private MendaoTestService mendaoTestService;

    @Autowired
    private MendaoTopicLibraryService topicLibraryService;

    @Autowired
    private MendaoTestReportService testReportService;

    @ResponseBody
    @RequestMapping(value = "/addTest")
    public Object addTest(HttpServletRequest request){
        MendaoAddTestResp resp = new MendaoAddTestResp();
        try{
            String data = request.getParameter("data");
            System.out.println("addTest---->"+data);
            if(data == null){
                resp.setCode(0);
                resp.setMsg("参数为空");
            }

            MendaoTestReq mendaoTestReq = JsonUtil.tranjsonStrToObject(data, MendaoTestReq.class);
            if(mendaoTestReq != null && mendaoTestReq.getTestId()>0){
                MendaoTest oldMendaoTest = mendaoTestService.findOne(mendaoTestReq.getTestId());
                oldMendaoTest.setName(mendaoTestReq.getTestName());
                oldMendaoTest.setImage(mendaoTestReq.getTestImage());
                oldMendaoTest.setIntroduction(mendaoTestReq.getTestIntroduction());
                oldMendaoTest.setTestDesc(mendaoTestReq.getTestDesc());
                oldMendaoTest.setTopicGroup(mendaoTestReq.getTopicGroup());

                if(mendaoTestReq.getTopicGroup() != null && !mendaoTestReq.getTopicGroup().equals("")){
                    List<MendaoTopicGroupResp> topicGroupList = JsonUtil.jsonToObjectList(mendaoTestReq.getTopicGroup(),MendaoTopicGroupResp.class);
                    List<MendaoTopicGroupResp> saveList = new ArrayList<>();
                    int i = 1;
                    for(MendaoTopicGroupResp topicGroup:topicGroupList){
                        topicGroup.setTopicGroupId(mendaoTestReq.getTestId()+"_"+String.format("%04d", i));
                        saveList.add(topicGroup);
                        i++;
                    }
                    oldMendaoTest.setTopicGroup(JsonUtil.tranObjectToJsonStr(saveList));
                }

                mendaoTestService.updateDate(oldMendaoTest);
                resp.setTestId(mendaoTestReq.getTestId());
            }else{
                if(mendaoTestReq == null){
                    resp.setCode(0);
                    resp.setMsg("参数错误");
                    return resp;
                }

                MendaoTest mendaoTest = new MendaoTest();
                mendaoTest.setImage(mendaoTestReq.getTestImage());
                mendaoTest.setName(mendaoTestReq.getTestName());
                mendaoTest.setTestDesc(mendaoTestReq.getTestDesc());
                mendaoTest.setIntroduction(mendaoTestReq.getTestIntroduction());
                mendaoTest.setStatus(1);

                mendaoTestService.insertDate(mendaoTest);
                System.out.println(mendaoTest.getId());
                if(mendaoTestReq.getTopicGroup() != null && !mendaoTestReq.getTopicGroup().equals("")){
                    List<MendaoTopicGroupResp> topicGroupList = JsonUtil.jsonToObjectList(mendaoTestReq.getTopicGroup(),MendaoTopicGroupResp.class);
                    List<MendaoTopicGroupResp> saveList = new ArrayList<>();
                    int i = 1;
                    for(MendaoTopicGroupResp topicGroup:topicGroupList){
                        topicGroup.setTopicGroupId(mendaoTest.getId()+"_"+String.format("%04d", i));
                        saveList.add(topicGroup);
                        i++;
                    }
                    mendaoTest.setTopicGroup(JsonUtil.tranObjectToJsonStr(saveList));
                    mendaoTestService.updateDate(mendaoTest);
                }
                resp.setTestId(mendaoTest.getId());
            }


            resp.setCode(1);
            resp.setMsg("请求成功");

        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("发生异常");
        }
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "/getTest")
    public Object getTest(HttpServletRequest request) {
        BaseRespList resp = new BaseRespList();
        try{

            String data = request.getParameter("data");
            System.out.println("getTest---->"+data);
            if(data == null){
                resp.setCode(0);
                resp.setMsg("参数为空");
            }

            MendaoTestReq mendaoTestReq = JsonUtil.tranjsonStrToObject(data, MendaoTestReq.class);
            List<MendaoTestResp> respList = new ArrayList<MendaoTestResp>();
            //type=1后台获取全部type=2前台获取列表
            List<MendaoTest> list = mendaoTestService.getList(mendaoTestReq.getType());
            if(list != null && list.size()>0){
                for(MendaoTest test:list){
                    MendaoTestResp mendaoTestResp = new MendaoTestResp();
                    mendaoTestResp.setTestId(test.getId());
                    mendaoTestResp.setTestName(test.getName());
                    mendaoTestResp.setTestImage(test.getImage());
                    mendaoTestResp.setTestIntroduction(test.getIntroduction());
                    mendaoTestResp.setTestDesc(test.getTestDesc());
                    mendaoTestResp.setStatus(test.getStatus());
                    respList.add(mendaoTestResp);
                }
            }

            resp.setList(respList);
            resp.setCode(1);
            resp.setMsg("请求成功");
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("发生异常");
        }
        return resp;

    }

    @ResponseBody
    @RequestMapping(value = "/optionTestByType")
    public Object editTest(HttpServletRequest request){
        BaseResp resp = new BaseResp();
        try{
            String data = request.getParameter("data");
            System.out.println("optionTestByType---->"+data);
            if(data == null){
                resp.setCode(0);
                resp.setMsg("参数为空");
            }

            MendaoTestReq mendaoTestReq = JsonUtil.tranjsonStrToObject(data, MendaoTestReq.class);

            MendaoTest oldMendaoTest = mendaoTestService.findOne(mendaoTestReq.getTestId());
            if(mendaoTestReq.getType() == 1){
                oldMendaoTest.setStatus(2);
                mendaoTestService.updateDate(oldMendaoTest);
            }else if(mendaoTestReq.getType() == 2){
                oldMendaoTest.setStatus(-1);
                mendaoTestService.updateDate(oldMendaoTest);
            }

            resp.setCode(1);
            resp.setMsg("请求成功");

        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("发生异常");
        }
        return resp;

    }

    @ResponseBody
    @RequestMapping(value = "/getTestById")
    public Object getTestById(HttpServletRequest request) {
        BaseRespData resp = new BaseRespData();
        try{

            String data = request.getParameter("data");
            System.out.println("getTestById---->"+data);
            if(data == null){
                resp.setCode(0);
                resp.setMsg("参数为空");
            }

            MendaoTestReq mendaoTestReq = JsonUtil.tranjsonStrToObject(data, MendaoTestReq.class);
            MendaoTest mendaoTest = mendaoTestService.findOne(mendaoTestReq.getTestId());

            MendaoTestResp testResp = new MendaoTestResp();
            testResp.setTestId(mendaoTest.getId());
            testResp.setTestName(mendaoTest.getName());
            testResp.setTestImage(mendaoTest.getImage());
            testResp.setTestDesc(mendaoTest.getTestDesc());
            testResp.setTestIntroduction(mendaoTest.getIntroduction());
            testResp.setStatus(mendaoTest.getStatus());

            resp.setData(testResp);
            resp.setCode(1);
            resp.setMsg("请求成功");
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("发生异常");
        }
        return resp;

    }

    @ResponseBody
    @RequestMapping(value = "/getTopicByTestId")
    public Object getTopicByTestId(HttpServletRequest request) {
        BaseRespList resp = new BaseRespList();
        try{
            String data = request.getParameter("data");
            System.out.println("getTopicByTestId---->"+data);
            if(data == null){
                resp.setCode(0);
                resp.setMsg("参数为空");
            }

            MendaoTestReq mendaoTestReq = JsonUtil.tranjsonStrToObject(data, MendaoTestReq.class);

            List<MendaoTopicGroupResp> respList = new ArrayList<MendaoTopicGroupResp>();
            MendaoTest mendaoTest = mendaoTestService.findOne(mendaoTestReq.getTestId());
            if(mendaoTest.getTopicGroup() != null && !"".equals(mendaoTest.getTopicGroup())){
                respList = JsonUtil.jsonToObjectList(mendaoTest.getTopicGroup(), MendaoTopicGroupResp.class);
            }

            resp.setList(respList);
            resp.setCode(1);
            resp.setMsg("请求成功");
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("发生异常");
        }
        return resp;

    }


    @ResponseBody
    @RequestMapping(value = "/addTopicLibrary")
    public Object addTopicLibrary(HttpServletRequest request){
        BaseResp resp = new BaseResp();
        try{
            String data = request.getParameter("data");
            System.out.println("addTopicLibrary---->"+data);
            if(data == null){
                resp.setCode(0);
                resp.setMsg("参数为空");
            }

            List<MendaoTopicLibrary> topicLibraryList = JsonUtil.jsonToObjectList(data, MendaoTopicLibrary.class);
            if(topicLibraryList != null && topicLibraryList.size()>0){
                MendaoTopicLibrary mendaoTopicLibrary = topicLibraryList.get(0);
                topicLibraryService.deleteByTestId(mendaoTopicLibrary.getTestId());
                for(MendaoTopicLibrary topicLibrary:topicLibraryList){
                    if(topicLibrary != null && topicLibrary.getId()>0){
                        MendaoTopicLibrary oldTopicLibrary = topicLibraryService.findOne(topicLibrary.getId());
                        oldTopicLibrary.setTopicLibraryName(topicLibrary.getTopicLibraryName());
                        oldTopicLibrary.setTopicGroupId(topicLibrary.getTopicGroupId());
                        oldTopicLibrary.setOptions(topicLibrary.getOptions());
                        topicLibraryService.updateDate(oldTopicLibrary);
                    }else{
                        if(topicLibrary == null){
                            resp.setCode(0);
                            resp.setMsg("参数错误");
                            return resp;
                        }
                        topicLibrary.setCreateTime(new Date());
                        topicLibraryService.insertDate(topicLibrary);
                    }
                }
            }


            resp.setCode(1);
            resp.setMsg("请求成功");

        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("发生异常");
        }
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "/getTopicLibrary")
    public Object getTopicLibrary(HttpServletRequest request) {
        BaseRespList resp = new BaseRespList();
        try{
            String data = request.getParameter("data");
            System.out.println("getTopicLibrary---->"+data);
            if(data == null){
                resp.setCode(0);
                resp.setMsg("参数为空");
            }

            MendaoTestReq mendaoTestReq = JsonUtil.tranjsonStrToObject(data, MendaoTestReq.class);

            List<MendaoTopicLibraryResp> respList = new ArrayList<MendaoTopicLibraryResp>();

            List<MendaoTopicLibrary> list = topicLibraryService.getListByTestId(mendaoTestReq.getTestId());
            if(list != null && list.size()>0){
                for(MendaoTopicLibrary test:list){
                    MendaoTopicLibraryResp topicLibraryResp = new MendaoTopicLibraryResp();
                    topicLibraryResp.setTopicLibraryName(test.getTopicLibraryName());
                    topicLibraryResp.setTopicGroupId(test.getTopicGroupId());
                    topicLibraryResp.setOptions(test.getOptions());
                    respList.add(topicLibraryResp);
                }
            }

            resp.setList(respList);
            resp.setCode(1);
            resp.setMsg("请求成功");
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("发生异常");
        }
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "/addTestReport")
    public Object addTestReport(HttpServletRequest request){
        BaseResp resp = new BaseResp();
        try{
            String data = request.getParameter("data");
            System.out.println("addTestReport---->"+data);
            if(data == null){
                resp.setCode(0);
                resp.setMsg("参数为空");
            }

            MendaoTestReport testReport = JsonUtil.tranjsonStrToObject(data, MendaoTestReport.class);
            if(testReport != null && testReport.getId()>0){
                MendaoTestReport oldTestReport = testReportService.findOne(testReport.getId());
                oldTestReport.setReportDesc(testReport.getReportDesc());
                oldTestReport.setIsShowReportDesc(testReport.getIsShowReportDesc());
                oldTestReport.setTableSet(testReport.getTableSet());
                oldTestReport.setIsShowTableSet(testReport.getIsShowTableSet());
                oldTestReport.setTableResult(testReport.getTableResult());
                oldTestReport.setTopicReport(testReport.getTopicReport());
                oldTestReport.setIsShowTopicReportList(testReport.getIsShowTopicReportList());
                oldTestReport.setMoreSuggest(testReport.getMoreSuggest());
                oldTestReport.setIsShowMoreSuggest(testReport.getIsShowMoreSuggest());
                oldTestReport.setReportResult(testReport.getReportResult());
                oldTestReport.setIsShowReportResult(testReport.getIsShowReportResult());
                oldTestReport.setLastImage(testReport.getLastImage());
                oldTestReport.setIsShowLastImage(testReport.getIsShowLastImage());

                testReportService.updateDate(oldTestReport);
            }else{
                if(testReport == null){
                    resp.setCode(0);
                    resp.setMsg("参数错误");
                    return resp;
                }
                testReport.setCreateTime(new Date());
                testReportService.insertDate(testReport);
            }

            resp.setCode(1);
            resp.setMsg("请求成功");

        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("发生异常");
        }
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "/getTestReport")
    public Object getTestReport(HttpServletRequest request) {
        BaseRespData resp = new BaseRespData();
        try{
            String data = request.getParameter("data");
            System.out.println("getTestReport---->"+data);
            if(data == null){
                resp.setCode(0);
                resp.setMsg("参数为空");
            }

            MendaoTestReq mendaoTestReq = JsonUtil.tranjsonStrToObject(data, MendaoTestReq.class);

            List<MendaoTopicLibraryResp> respList = new ArrayList<MendaoTopicLibraryResp>();

            List<MendaoTestReport> list = testReportService.getListByTestId(mendaoTestReq.getTestId());
            if(list != null && list.size()>0){
                MendaoTestReport report = list.get(0);
                resp.setData(report);
            }

            resp.setCode(1);
            resp.setMsg("请求成功");
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(0);
            resp.setMsg("发生异常");
        }
        return resp;
    }

}

<%--
  Created by IntelliJ IDEA.
  User: zhangxilin
  Date: 2016/12/14
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>流量包赠送测试</title>
    <script type="text/javascript" src="/js/jquery-2.2.2.min.js"></script>
    <script type="text/javascript">
      var currentDate = new Date();
      var currentTime = formatDate(currentDate,"yyyyMMddhhmmss");
      currentDate.setDate(currentDate.getDate()+1);
      var accountDate = formatDate(currentDate,"yyyyMMdd");

      var params1 = {
        "optCode": "LLSL",
        "tradeSerialNo": "LLSL"+currentTime+"QTJY10000239",
        "tradeDate": currentTime,
        "bizCode": "QTJY",
        "billId": "13738184934",
        "accountDate": accountDate,
        "offerId": "600000287772",
        "clientIp": "",
        "macAddress": ""
      };
      var params2 = {
        "optCode":"LLQY",
        "tradeSerialNo": "LLQY"+currentTime+"QTJY10000361",
        "bizCode": "QTJY",
        "billId": "13732274384",
        "orderOrgTradeNo": "LLSL20161215112034QTJY10000239",
        "clientIp": "",
        "macAddress": ""
      };
      function testPresent(){
        $.ajax({
          type:'POST',
          url:'/dataTraffic/present',
          contentType:"application/json",
          data: JSON.stringify(params1),
          dataType:"json",
          success:function(result){
            alert(result.description);
          }
        })
      }
      function testQuery(){
        $.ajax({
          type:'POST',
          url:'/dataTraffic/query',
          contentType:"application/json",
          data: JSON.stringify(params2),
          dataType:"json",
          success:function(result){
            alert(result.description);
          }
        })
      }

      //格式化CST日期的字串

      function formatCSTDate(strDate,format){

        return formatDate(new Date(strDate),format);

      }



      //格式化日期,

      function formatDate(date,format){

        var paddNum = function(num){

          num += "";

          return num.replace(/^(\d)$/,"0$1");

        };

        //指定格式字符

        var cfg = {

          yyyy : date.getFullYear(), //年 : 4位

          yy : date.getFullYear().toString().substring(2),//年 : 2位

          M  : date.getMonth() + 1,  //月 : 如果1位的时候不补0

          MM : paddNum(date.getMonth() + 1), //月 : 如果1位的时候补0

          d  : date.getDate(),   //日 : 如果1位的时候不补0

          dd : paddNum(date.getDate()),//日 : 如果1位的时候补0

          hh : date.getHours(),  //时

          mm : date.getMinutes(), //分

          ss : date.getSeconds() //秒

        };

        format || (format = "yyyy-MM-dd hh:mm:ss");

        return format.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});

      }
    </script>
</head>
<body>
  <input type="button" id="present" name="present" value="测试赠送" onclick="testPresent()">
  <br/><br/>
  <input type="button" id="query" name="query" value="测试查询" onclick="testQuery()">
</body>
</html>

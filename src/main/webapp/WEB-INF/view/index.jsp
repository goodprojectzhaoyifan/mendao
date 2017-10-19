<%--
  Created by IntelliJ IDEA.
  User: wpliu
  Date: 16/4/11
  Time: 上午11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <script type="text/javascript" src="/js/jquery-2.2.2.min.js"></script>
</head>

<body>
  <table id="tb">
    <tr>

      <td>主叫号码:</td>
      <td><input   type="text" id="callerNum" name="callerNum" value=""></td>
    </tr>
    <tr>
      <td>
        被叫号码:
      </td>
      <td>  <input type="text" id="calleeNum" name="calleeNum" value=""> </td>

    </tr>

    <tr>
      <td colspan="2">
        <input align="center" type="button" id="confirm" name="confirm" value="确认" onclick="confirm(this)">
      </td>

    </tr>

  </table>
<script type="text/javascript">
 function confirm(){
    var callerNum=$("#callerNum").val();
    var calleeNum=$("#calleeNum").val();
    callerNum="+86"+callerNum;
    calleeNum="+86"+calleeNum;
    var data = {
      callerNbr:callerNum,
      calleeNbr:calleeNum
    };
   $.ajax({
     type:'POST',
     url:'/click/click2Call',
     contentType:"application/json",
     data: JSON.stringify(data),
     success:function(result){
       alert("拨号中，请等待!");
     },
     dataType:"json"
   })
  }


</script>
</body>
</html>

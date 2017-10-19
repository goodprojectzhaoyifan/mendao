<%--
  Created by IntelliJ IDEA.
  User: zhangxilin
  Date: 2016/12/19
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>批量赠送及结果查询</title>
    <script src="/js/jquery-2.2.2.min.js" type="text/javascript"></script>
    <script src="/js/jquery-easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="/js/jquery-easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <link href="/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="/js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css"/>
    <style>
        #formTable {
            border-right: 1px solid #95B8E7;
            border-bottom: 1px solid #95B8E7;
        }

        #formTable td {
            border-left: 1px solid #95B8E7;
            border-top: 1px solid #95B8E7;
            height: 40px;
        }

        table {
            table-layout:fixed;
        }

        table tr td{
            overflow:hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }

        /* 设置数据行高 */
        .datagrid-row {
            height: 40px;
        }

        /* 设置数据字体 */
        .datagrid-cell {
            font-size: medium;
        }

        /* 设置列名称字体 */
        .datagrid-header .datagrid-cell span {
            font-size: medium;
            line-height: 40px;
        }

        /* 设置列名称行高 */
        .datagrid-header .datagrid-cell {
            height: 40px;
        }

        /* 设置下拉框字体 */
        .textbox-text .combobox-item {
            font-size: medium;
            line-height: 40px;
        }
    </style>
</head>
<body style="margin: 0px;">
    <div style="margin: 0 auto;width: 100%; height: 100%; background-color: #F7F7F7;overflow-y: scroll;">
        <div id="main" style="margin: 0 auto;width: 90%">
            <br/>

            <form id="queryForm" method="post">
                <table id="formTable" cellspacing="0" cellpadding="5px" width="100%">
                    <tr>
                        <td align="right" style="width: 10%;">
                            开始日期:
                        </td>
                        <td style="width: 15%;">
                            <input type="text" id="startTime" name="startTime" class="easyui-datebox"
                                   data-options="required:true, editable:false"/>
                        </td>
                        <td align="right" style="width: 10%;">
                            结束日期:
                        </td>
                        <td style="width: 15%;">
                            <input type="text" id="endTime" name="endTime" class="easyui-datebox"
                                   data-options="required:true, editable:false"/>
                        </td>
                        <td align="right" style="width: 10%;">
                            赠送状态:
                        </td>
                        <td style="width: 15%;">
                            <input id="presentStatus" name="presentStatus" >
                        </td>
                        <td align="right" style="width: 10%;">
                            手机号码:
                        </td>
                        <td style="width: 15%;">
                            <input type="text" id="billId" name="billId" maxlength="11"
                                   class="easyui-validatebox textbox" data-options="validType:'phoneRex'"
                                   style="width:100px;height:22px;">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="8" align="center">
                            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()"
                               style="width:100px">赠送结果查询</a>&nbsp;
                            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="openDialog()"
                               style="width:100px">Excel导入</a>&nbsp;
                            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="presentBatch()"
                               style="width:100px;">批量赠送</a>
                        </td>
                    </tr>
                </table>
            </form>
            <br/>
            <table id="resultData" class="resultData" width="100%" cellspacing="0" cellpadding="5px"></table>
        </div>

        <div id="uploadWindow" class="easyui-dialog" title="Excel导入窗口" style="width:500px;height:180px; background-color: #EAEAEA" data-options="draggable:true,closed:true,modal:true">
            <form id="uploadForm" action="/dataTraffic/upload" method="post" enctype="multipart/form-data">
                <table border="0" style="border:1px solid #EAEAEA; margin:0 auto;" cellpadding="0"cellspacing="0">
                    <tr style="height:30px;padding:10px;">
                        <td align="center">
                            <span>流量包赠送信息批量导入</span>
                        </td>
                    </tr>
                    <tr style="height:40px;padding:10px;">
                        <td>
                            <input id="uploadExcel" name="uploadExcel" class="easyui-filebox" style="width:400px; height:25px;" data-options="prompt:'请选择Excel文件...',buttonText:'选择文件'"/>
                        </td>
                    </tr>
                    <tr style="height:40px;padding:10px;">
                        <td align="center">
                            <a id="excelUploadLink" href="javascript:void(0)" class="easyui-linkbutton" style="width:120px; height:25px;" onclick="uploadExcel()" >导入Excel</a>&nbsp;
                            <a id="excelDownloadLink" href="javascript:void(0)" class="easyui-linkbutton" style="width:120px; height:25px;" onclick="downloadExcel()" >下载导入失败信息</a>
                        </td>
                    </tr>
                </table>　  　　     　　　　　
            </form>
            <iframe id="loadFileHide" style="display:none;"></iframe>
        </div>

    </div>
    <script>
        $('#presentStatus').combobox({
            width: '100px',
            panelHeight: '70px',
            editable: false,
            valueField: 'value',
            textField: 'label',
            data: [{label: '所有状态',value: ''},{label: '赠送成功', value: '0'},{label: '赠送失败', value: '1'}]
        });
        $(".textbox-text").css("font-size", "14px");
        $(".combobox-item").css("font-size", "14px");
        $('#excelDownloadLink').linkbutton({disabled:true});
        var params = {};
        function submitForm() {
            if (!$('#queryForm').form('validate')) {
                return false;
            }
            var billId = $("#billId").val();
            var presentStatus = $("#presentStatus").combobox("getValue");
            var startTime = $("#startTime").datebox("getValue");
            var endTime = $("#endTime").datebox("getValue");
            params.billId = billId;
            params.presentStatus = presentStatus;
            params.startTime = startTime;
            params.endTime = endTime;
            showResult();
        }
        function clearForm() {
            $('#queryForm').form('clear');
        }

        function showResult() {
            $("#resultData").datagrid({
                url: '/dataTraffic/batchQuery', //请求的数据源
                method: 'post',
                queryParams: params, //查询参数
                pagination: true, //显示分页
                pagePosition: 'both',
                pageSize: 10, //页大小
                pageList: [10, 20, 40, 80], //页大小下拉选项此项各value是pageSize的倍数
                loadMsg: 0,
                fitColumn: false, //列自适应宽度
                striped: true, //行背景交换
                nowap: false, //列内容多时自动折至第二行
                border: true,
                singleSelect: true,
                //rownumbers: true,
                columns: [[//显示的列
                    {
                        field: 'city', title: '地市', align: 'center', width: 80
                    },
                    {
                        field: 'district', title: '县区', align: 'center', width: 80
                    },
                    {
                        field: 'schoolId', title: '学校ID', align: 'center', width:100
                    },
                    {
                        field: 'presentUser', title: '代理商', align: 'center', width:100
                    },
                    {
                        field: 'type', title: '用户类型', align: 'center', width:90,
                        formatter: function (value) {
                            if (value == 1) {
                                return '教师';
                            } else if (value == 2) {
                                return '学生';
                            } else if (value == 3) {
                                return '家长';
                            } else if (!value){
                                return '';
                            } else {
                                return '未知类型';
                            }
                        }
                    },
                    {
                        field: 'objectId', title: '用户ID', align: 'center', width:110
                    },
                    {
                        field: 'billId', title: '手机号码', align: 'center', width:130
                    },
                    {
                        field: 'offerName', title: '流量包名称', align: 'center', width:250
                    },
                    {
                        field: 'tradeDate', title: '交易时间', align: 'center', width:150
                    },
                    {
                        field: 'presentStatus', title: '赠送状态', align: 'center', width:100,
                        formatter: function (value) {
                            if (value == 0) {
                                return '赠送成功';
                            } else if (value == 1) {
                                return '赠送失败';
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'retMsg', title: '赠送结果描述', align: 'center', width:330
                    },

                    {
                        field: 'remark', title: '备注', align: 'center', width:187
                    }
                ]],
                onLoadSuccess: function (data) {
                    if (data.total == 0) {
                        $.messager.alert('提示', data.description, 'info');
                        $(this).datagrid('appendRow', {city: '<div style="text-align:center;color:#000">暂无数据！</div>'}).datagrid('mergeCells', {
                            index: 0,
                            field: 'city',
                            colspan: 12
                        });
                    }
                },
                onLoadError:function(jqXHR) {
                    var msg = '查询失败: ' + jqXHR.status + '  ' + jqXHR.statusText;
                    $.messager.alert('提示', msg, 'error');
                }

            })
        }

        //打开excel导入窗口
        function openDialog(){
            $("#uploadWindow").dialog('open');
        }

        //批量赠送
        function presentBatch(){
            $.ajax({
                type:'POST',
                url:'/dataTraffic/presentBatch',
                contentType:"application/json",
                dataType:"json",
                success:function(result){
                    $.messager.alert('提示', result.description, 'info');
                },
                error: function (result) {
                    $.messager.alert('提示', '批量导入执行出错！', 'error');
                }
            })
        }

        function uploadExcel() {
            //得到上传文件的全路径
            var fileName = $('#uploadExcel').filebox('getValue');

            //进行基本校验
            if (fileName == "") {
                $.messager.alert('提示', '请先选择要导入的Excel文件！', 'info');
            } else {
                //对文件格式进行校验
                var fileType = /\.[^\.]+$/.exec(fileName);
                if (fileType == ".xls" || fileType == ".xlsx") {
                    var formData = new FormData($("#uploadForm")[0]);
                    $.ajax({
                        url: '/dataTraffic/excelUpload',
                        type: 'POST',
                        data: formData,
                        async: false,
                        cache: false,
                        contentType: false,
                        processData: false,
                        dataType: "json",
                        success: function (data) {
                            var code = data.code;
                            var msg = data.msg;
                            $.messager.alert('提示', msg, 'info');
                            if(code == 0){
                                var wrongSize = data.wrongSize;
                                if(wrongSize > 0){
                                    $('#excelDownloadLink').linkbutton({disabled:false});
                                }else{
                                    $('#excelDownloadLink').linkbutton({disabled:true});
                                }
                            }
                            $('#uploadExcel').filebox({value:''});
                        },
                        error: function (data) {
                            $('#excelDownloadLink').linkbutton({disabled:true});
                            $.messager.alert('提示', '导入失败！', 'error');
                            $('#uploadExcel').filebox({value:''});
                        }
                    });
                } else {
                    $.messager.alert('提示', '请选择xls或xlsx格式的文件！', 'info');
                }
            }
        }

        function downloadExcel() {
            document.getElementById("loadFileHide").src = '/dataTraffic/excelDownload';
        }

        $(document).ajaxStart(function(){
            $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
            $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
        });

        $(document).ajaxStop(function(){
            $(".datagrid-mask").remove();
            $(".datagrid-mask-msg").remove();
        });

        //自定义验证
        $.extend($.fn.validatebox.defaults.rules, {
            phoneRex: {
                validator: function (value) {
                    var rex = /^1[3-8]+\d{9}$/;
                    if (rex.test(value)) {
                        return true;
                    } else {
                        return false;
                    }
                },
                message: '请输入正确的手机号码'
            }
        });
    </script>
</body>
</html>
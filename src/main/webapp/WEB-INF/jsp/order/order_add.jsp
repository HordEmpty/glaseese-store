<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui.admin/css/style.css"/>
    <title>订单添加</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 订单<span class="c-gray en">&gt;</span> 订单添加 <a
        class="btn btn-success radius r"
        style="line-height:1.6em;margin-top:3px"
        href="javascript:location.replace(location.href);"
        title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
    <a href="?_type=list" class="btn btn-primary radius"><i
            class="icon-plus"></i> 订单列表</a></span>
    </div>
    <table class="table table-border table-bordered table-hover table-bg table-sort">
        <thead>
        <tr class="text-c">
            <th width="25"></th>
            <th width="80">订单编号</th>
            <th width="60">客户</th>
            <th width="100">产品名称</th>
            <th width="90">订单价格</th>
            <th width="100">工厂</th>
            <th width="50">订单状态</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody>
        <form action="<c:url value="/order?_type=save"/>" method="post">
            <tr class="text-c">
                <td><input type="hidden" id="wtf" name="orderId"><br></td>
                <td>自动生成</td>
                <td><select id="customId" name="customId" style="width: 100%;height: 100%;" required>
                    <c:forEach items="${customList}" var="custom">
                        <option value="${custom.custom_id}">${custom.custom_name}</option>
                    </c:forEach>
                </select><br></td>
                <td><select id="productName" name="productName" style="width: 100%;height: 100%;" required>
                    <c:forEach items="${productList}" var="product">
                        <option value="${product.product_name}">${product.product_name}</option>
                    </c:forEach>
                </select><br></td>
                <td><input type="text" id="orderPrice" name="orderPrice" style="width: 100%;height: 100%;" required><br></td>
                <td><input type="text" id="factory" name="factory" style="width: 100%;height: 100%;" required><br></td>
                <td><select id="orderStatus" name="orderStatus" style="width: 100%;height: 100%;" required>
                    <option value="未完成">未完成</option>
                    <option value="已完成">已完成</option>
                </select><br></td>
                <td><input type="submit" value="添加" class="btn btn-primary radius"></td>
            </tr>
        </form>
        </tbody>
    </table>
    <div id="pageNav" class="pageNav"></div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/datatables/1.10.15/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
    window.onload = (function () {
        // optional set
        pageNav.pre = "&lt;上一页";
        pageNav.next = "下一页&gt;";
        // p,当前页码,pn,总页面
        pageNav.fn = function (p, pn) {
            $("#pageinfo").text("当前页:" + p + " 总页: " + pn);
        };
        //重写分页状态,跳到第三页,总页33页
        pageNav.go(1, 13);
    });
    $('.table-sort').dataTable({
        "lengthMenu": false,//显示数量选择
        "bFilter": false,//过滤功能
        "bPaginate": false,//翻页信息
        "bInfo": false,//数量信息
        "aaSorting": [[1, "desc"]],//默认第几个排序
        "bStateSave": true,//状态保存
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            {"orderable": false, "aTargets": [0, 8, 9]}// 制定列不参与排序
        ]
    });
</script>
</body>
</html>

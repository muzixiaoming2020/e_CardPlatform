<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 获得请求的绝对地址
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <!-- 设置网页的基链接地址 -->
    <base href="<%=basePath%>">
    <link href="css/base.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="foot">
        <div class="foot_area">
            <a href="javascript:;">关于</a> |
            <a href="javascript:;">网站地图</a> |
            <a href="javascript:;">友情链接</a> |
            <a href="javascript:;">网站统计</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <br /> 免费服务热线：400-658-1022 &nbsp; &nbsp;
                免责声明：本网站内容的解释权归本站所有
        </div>
    </div>
</body>
</html>

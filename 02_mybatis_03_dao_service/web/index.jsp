<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 06/01/2021
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--绝对路径->
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>$Title$</title>
  </head>
  <body>
  $END$
  </body>
</html>

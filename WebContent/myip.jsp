<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>获取IP</title>	
</head>
  
<body>

<h1>你的IP：${requestScope.userIP}</h1>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
<!DOCTYPE html>
<html lang="en">
<head>
 <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>上传图片</title>
</head>
<body>
<div>
    <p>请选择上传需要上传的文件(*.jpg;*.png)</p>
    <form action="UpLoadServlet" method="post" enctype="multipart/form-data">
    	<input name="names" type="text">
        <input type="file" name="file"/><br />
        <button type="submit" >提交</button>
    </form>
 

</div>
</body>
</html>
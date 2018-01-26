<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>APP Loading...</title>	
</head>  
<body>
    <c:set var="androidurl" value="http://a.app.qq.com/o/simple.jsp?pkgname=com.tencent.android.qqdownloader"></c:set>
  <c:choose>
    <c:when test="${fn:contains(header['user-agent'], 'Android')}">
      <c:redirect url="${androidurl}"></c:redirect>
    </c:when>
     <c:when test="${fn:contains(header['user-agent'], 'iPhone')}">
     <jsp:forward page=""></jsp:forward>
    </c:when>
    <c:otherwise>
       <jsp:forward page=""></jsp:forward>
    </c:otherwise>
</c:choose>       
</body>
</html>

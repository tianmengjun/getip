<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"
%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>jstl打印</title>	
</head>
  
<body>
<h5>---------------打印cookie--------</h5>
 <h5>name:${cookie.lastAccessTime.name}</h5>
 <h5>value:${cookie.lastAccessTime.value}</h5>
 <h5>name:${cookie.state.name}</h5>
 <h5>value:${cookie.state.value}</h5>


<h5>-------------打印header---------------</h5>

<h5>浏览器支持的语言和区域(accept-language):${header['accept-language']}</h5>
<h5>cookie:${header['cookie']}</h5>
<h5>host:${header['host']}</h5>
<h5>缓存机制（cache-control）：${header['cache-control']}</h5>
<h5>connection:${header['connection']}</h5>
<h5>upgrade-insecure-requests:${header['upgrade-insecure-requests']}</h5>
<h5>请求编码类型（accept-encoding）：${header['accept-encoding']}</h5>
<h5>用户代理（user-agent）：${header['user-agent']}</h5>
<h5>请求数据类型（accept）:${header['accept']}</h5>
<h5></h5>

  <c:set value="${fn:split(header,',')}" var="headerList"/>
    <c:forEach var="head" items="${headerList }">
        ${head}<br/>
        </c:forEach>
        
        <c:choose>
    <c:when test="${fn:contains(header['user-agent'], 'Chrome')}">
        你使用的是 Chrome浏览器
    </c:when>
    <c:otherwise>
        You are using some browser other than IE.
    </c:otherwise>
</c:choose>
        
<h5>--------------打印list----------------</h5>
<% List<String> list = new ArrayList<>();
   list.add(0, "zhangsan");
   list.add(1, "lisi");
request.setAttribute("list", list);
%>
<c:forEach var="l" items="${list}">
${l}<br>
</c:forEach>

<h5>--------------打印Map----------------</h5>
<% Map<String,String> map = new HashMap<>();
  map.put("name1", "zhangsan");
  map.put("name2", "lisi");
request.setAttribute("map", map);
%>
<c:forEach var="m" items="${map}">
${m}<br>
</c:forEach>
<h5>-------------格式化数字--------</h5>
<c:set var="num" value="1000009"></c:set>
<h5>格式化前：${num}</h5>
<h5>格式化后：<fmt:formatNumber value="${num}" pattern="#00.00#" /></h5>

<h5>-------- 格式化日期输出(日期为当前时间) --------</h5>
<c:set var="date" value="<%=new Date() %>"></c:set>
<fmt:formatDate value="${date}" type="date" dateStyle="full"/>
<br/>
<fmt:formatDate value="${date}" type="both" pattern="yyyy-MM-dd HH:mm"/>

</body>
</html>

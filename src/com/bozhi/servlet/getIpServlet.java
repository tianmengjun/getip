package com.bozhi.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("getip")
public class getIpServlet extends HttpServlet{
	
	
	
	/**
	 * 获取IP
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String IP=request.getHeader("x-forwarded-for");
	
	if(IP==null||IP.length()==0||"unknown".equalsIgnoreCase(IP)) {
		IP=request.getHeader("Proxy-Client-IP");	
	}
	
	
	if(IP==null||IP.length()==0||"unknown".equalsIgnoreCase(IP)) {
		IP=request.getHeader("WL-Proxy-Client-IP");	
	}
	
	
	if(IP==null||IP.length()==0||"unknown".equalsIgnoreCase(IP)) {
		IP=request.getHeader("HTTP_CLIENT_IP");	
	}
	if(IP==null||IP.length()==0||"unknown".equalsIgnoreCase(IP)) {
		IP=request.getHeader("HTTP_X_FORWARDED_FOR");	
	}
	if(IP==null||IP.length()==0||"unknown".equalsIgnoreCase(IP)) {
		IP=request.getRemoteAddr();	
	}
	
	
	
	request.setAttribute("userIP", IP);
	response.setDateHeader("Expires", System.currentTimeMillis()+30*60*1000);//设置缓存时间为30分钟
	
	Cookie[] cookies=request.getCookies();
	if (cookies!=null) {
		            for ( Cookie cookie:cookies) {
	                    //Cookie cookie = cookies[i];
	                   
	                    System.out.println("key:"+cookie.getName()+"-->"+"value:"+cookie.getValue());
		             /*    if (cookie.getName().equals("lastAccessTime")) {
		                     Long lastAccessTime =Long.parseLong(cookie.getValue());
	                    Date date = new Date(lastAccessTime);
	                  
	                }*/
	            }
		         }else {
		            System.out.println("第一次访问");
	        }
	
	Cookie cookie=new Cookie("lastAccessTime",System.currentTimeMillis()+"");//创建一个新的cookie设置客户端最后访问时间
	Cookie cookie2=new Cookie("state","succss");
	cookie.setMaxAge(24*60*60);//设置有效期为一天。
	cookie2.setMaxAge(24*60*60);
	response.addCookie(cookie);
	response.addCookie(cookie2);
	System.out.println("客户端ip:"+IP);
	request.getRequestDispatcher("myip.jsp").forward(request, response);
	
	
	}	

}

package com.bozhi.agent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/getOSInfo")
public class GetOSInfo extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String userAgent=request.getHeader("user-agent");
		
		if(userAgent.toUpperCase().contains("ANDROID")) {
			
			response.getWriter().print("INFO:"+userAgent+"安卓手机");
		}
		if(userAgent.toUpperCase().contains("IPONE")) {
			response.getWriter().print("INFO:"+userAgent+"ipone");
		}
		
		
	}

}

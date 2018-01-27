package com.bozhi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**   
* @version 1.0   
* @author TianMengJun
* @since JDK 1.8.0_20
* Create at:   2018年1月26日 下午6:14:07   
* Description:  
*
*@param     
*/

@WebServlet("/getip")
public class GetIpServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取客户端IP
		String IP = request.getRemoteAddr();
		request.setAttribute("userIP", IP);
		// 设置缓存时间为30秒
		Long expires = System.currentTimeMillis() + 30 * 1000;
		response.setDateHeader("Expires", expires);
		// 创建一个新的cookie设置客户端最后访问时间
		Cookie cookie = new Cookie("lastTime", System.currentTimeMillis() + "");
		// 设置有效期为一天
		cookie.setMaxAge(24 * 60 * 60);
		response.addCookie(cookie);

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			request.getRequestDispatcher("myip.jsp").forward(request, response);
		} else {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("第一次访问！ 你的IP:" + IP);
		}

	}

}

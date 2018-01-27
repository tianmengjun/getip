package com.bozhi.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**   
* @version 1.0   
* @author TianMengJun
* @since JDK 1.8.0_20
* Create at:   2018年1月26日 下午6:14:07   
* Description:  
*
*@param     
*/

@WebServlet("/UpLoadServlet")
@MultipartConfig
public class UpLoadServlet extends HttpServlet {

	/*
	 * @see servlet 3.0 上传文件
	 */

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// 获取文件上传组件
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Part part = null;
		Part part1 = null;
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			part = request.getPart("file");
			System.out.println(request.getParameter("names"));
			part1=request.getPart("names");
			System.out.println(part1.);
			//Collection<Part> parts =request.getParts();
		
			// 获取头信息的request playload 内容
			String playload = part.getHeader("content-disposition");
			// 获取文件名称包含扩展名
			String filedisposition = playload.substring(playload.indexOf("filename=") + 10, playload.length() - 1);

			String name = getRealName(filedisposition);

			// 获取扩展名
			String ex = getExtension(filedisposition);

			String filePath = this.getServletContext().getRealPath("/upload");
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}

			// 可通过扩展名限制上传文件类型
			if ("JPG".equals(ex.toUpperCase()) || "PNG".equals(ex.toUpperCase())) {				
				inputStream = part.getInputStream();
				outputStream = new FileOutputStream(new File(file, name));					
				int len = -1;
				// OIUtil
				byte[] bytes = new byte[1024];
				while ((len = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, len);
				}

				// 删除临时文件
				part.delete();
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print("文件" + name + "上传成功！");
				
			} else {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print("文件" + name + "上传失败！");			
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}


     //fastdsf学习
	public static String getRealName(String path) {
		int index = path.lastIndexOf("\\");
		if (index == -1) {
			index = path.lastIndexOf("/");
		}
		return path.substring(index + 1);
	}

	public static String getExtension(String name) {
		int index = name.lastIndexOf(".");
		return name.substring(index + 1);
	}

}

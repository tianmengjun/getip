package com.bozhi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class FileLoadServlet extends HttpServlet{
	
private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		 //img upload
		request.setCharacterEncoding("utf-8");
		SmartUpload su = new SmartUpload();	
		su.initialize(this.getServletConfig(), request, response);
		su.setAllowedFilesList("gif,png,jpg");
		//su.setDeniedFilesList("exe");
		String fileName=null;
			try {
				su.upload();			
				Files fs=su.getFiles();
				File file = fs.getFile(0);
			fileName=file.getFileName();
			System.out.println("fileName="+fileName);
			su.save("file/test", File.SAVEAS_VIRTUAL);
			
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}		
		String pimg= "file/test"+fileName;
		
	 System.out.println("文件路径："+pimg);
					
		request.getRequestDispatcher("").forward(request, response);
	
		
		
	}

}

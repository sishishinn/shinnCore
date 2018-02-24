package com.sishishinn.core.web.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.sishishinn.core.util.JsonResult;
import com.sishishinn.core.web.CrudControllerSupport;

@Controller
@RequestMapping(value = "/core/upload")
public class UploadController extends CrudControllerSupport{
	
	@RequestMapping(value = "uploadImg",method = RequestMethod.POST)
	public void uploadImg(MultipartFile file,HttpServletResponse response)throws Exception{
		JsonResult jsonResult = new JsonResult();
		 if(!file.isEmpty()){  
        	String filename = System.currentTimeMillis()+ file.getOriginalFilename();
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File("S:\\temp\\file\\",filename)); 
            jsonResult.putData("filename", filename);
            writeJson(response, jsonResult.successASJson(""));
        }  
	}
	
	@RequestMapping(value = "showImg",method = RequestMethod.GET)  
    public void showImg(HttpServletRequest request,HttpServletResponse response) {  
		String filename = request.getParameter("filename");
        FileInputStream fis = null;  
        OutputStream os = null;  
        try {  
            fis = new FileInputStream("s:/temp/file/"+filename);  
            os = response.getOutputStream();  
            int count = 0;  
            byte[] buffer = new byte[1024 * 8];  
            while ((count = fis.read(buffer)) != -1) {  
                os.write(buffer, 0, count);  
                os.flush();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        try {  
            fis.close();  
            os.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
	
	@RequestMapping(value = "uploadImgs",method = RequestMethod.POST)
	public void uploadImgs(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try {
			JsonResult jsonResult = new JsonResult();
			List<String> filenameList = new ArrayList<String>();
			
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			//判断 request 是否有文件上传,即多部分请求  
			if(multipartResolver.isMultipart(request)){  
				//转换成多部分request    
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
				//取得request中的所有文件名  
				Iterator<String> iter = multiRequest.getFileNames();  
				while(iter.hasNext()){  
					//取得上传文件  
					MultipartFile file = multiRequest.getFile(iter.next());  
					if(!file.isEmpty()){  
						String filename = System.currentTimeMillis()+ file.getOriginalFilename();
						FileUtils.copyInputStreamToFile(file.getInputStream(), new File("S:\\temp\\file\\",filename)); 
						filenameList.add(filename);
					}  
				}  
			}
			jsonResult.putData("filenameList", filenameList);
			writeJson(response, jsonResult.successASJson(""));
		} catch (Exception e) {
			writeJson(response, JsonResult.failureToJson(""));
		}
	}
		
	
}

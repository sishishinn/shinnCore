package com.sishishinn.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class TemplateUtil {

	private static final String TEMPLATE_PATH = "/common/template";
	public static String TEMP_PATH = "/temp.ftl";
	
	public static Template configTemplate(HttpServletRequest request,String source){
		try {
			Configuration config = new Configuration();
			ServletContext servletContext = request.getSession().getServletContext();
			config.setDirectoryForTemplateLoading(new File(servletContext.getRealPath(TEMPLATE_PATH)));
			config.setObjectWrapper(new DefaultObjectWrapper());
			Template template = config.getTemplate(source, "UTF-8");
			return template;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean toPreview(HttpServletRequest request,String source,Map<?, ?> root) throws Exception{
		boolean flag = false;
		Writer out = null;
		try {
			String tempPath = request.getSession().getServletContext().getRealPath("common/template"+TEMP_PATH);
			Template template = configTemplate(request, source);
			FileOutputStream fos = new FileOutputStream(tempPath);
			out = new OutputStreamWriter(fos, "UTF-8");
			template.process(root, out);
			out.flush();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		return flag;
	}
}

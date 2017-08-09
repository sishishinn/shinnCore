package com.sishishinn.core.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;

import com.sishishinn.core.util.EmptyUtil;
import com.sishishinn.core.util.JsonDataGrid;

public class CrudControllerSupport {
	/**
	 * 向页面输出json数据
	 */
	public void writeJson(HttpServletResponse response,JSON json) throws Exception{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());
	}
	
	/**
	 * 封装分页对象
	 */
	protected static Map<String, String> setPage(HttpServletRequest request,JsonDataGrid json)throws Exception{
		Map<String, String> pageData = new HashMap<String, String>();
		
		String backFlag = (String) request.getSession().getAttribute("backFlag");
		String pageno = request.getParameter("page");
		String rows = request.getParameter("rows");
		
		//当前页码
		int pageNo = 1;
		if (EmptyUtil.isEmpty(backFlag)) {
			if (EmptyUtil.isEmpty(pageno)) {
				json.setPageIndex(pageNo);
			}else{
				pageNo = Integer.parseInt(pageno);
				json.setPageIndex(pageNo);
			}
			request.getSession().setAttribute("pageNo", pageNo);
		}else{
			if (!EmptyUtil.isEmpty(request.getSession().getAttribute("pageNo"))) {
				pageNo = (Integer) request.getSession().getAttribute("pageNo");
				json.setPageIndex(pageNo);
			}else {
				json.setPageIndex(pageNo);
			}
			request.getSession().removeAttribute("backFlag");
		}
		
		//每页记录数
		int pagesize =10;
		if (!EmptyUtil.isEmpty(rows)) {
			pagesize = Integer.parseInt(rows);
		}
		
		pageData.put("pageNo", String.valueOf(pageNo));
		pageData.put("pagesize", String.valueOf(pagesize));
		
		return pageData;
	}
}

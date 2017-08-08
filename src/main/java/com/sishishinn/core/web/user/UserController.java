package com.sishishinn.core.web.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sishishinn.core.dto.user.UserView;
import com.sishishinn.core.entity.role.Role;
import com.sishishinn.core.entity.user.User;
import com.sishishinn.core.service.role.RoleManager;
import com.sishishinn.core.service.user.UserManager;
import com.sishishinn.core.util.EmptyUtil;
import com.sishishinn.core.util.JsonDataGrid;
import com.sishishinn.core.util.JsonResult;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserManager userManager;
	@Autowired
	private RoleManager roleManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletRequest request){
		return "core/user/userList";
	}
	
	@RequestMapping(value = "loadData")
	public void loadData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		JsonDataGrid json = new JsonDataGrid();
		Map<String, String> pageData = setPage(request, json);
		int pageNo = Integer.parseInt(pageData.get("pageNo"));
		int pagesize = Integer.parseInt(pageData.get("pagesize"));
		String sort = request.getParameter("sidx");
		String order = request.getParameter("sord");
		
		Map<String, Object> searchParams = new HashMap<String, Object>();
		String keyword = request.getParameter("keyword");
		if (!EmptyUtil.isEmpty(keyword)) {
			searchParams.put("LIKE_name", keyword);
		}
		Page<User> page = userManager.getPage(searchParams,pageNo,pagesize,sort,order);
		json.setPageCount(page.getTotalPages());
		json.setTotal((int) page.getTotalElements());
		for (User user : page.getContent()) {
			UserView view = new UserView(user);
			json.addItem(view);
		}
		writeJson(response, json.successASJson("ok"));
	}
	
	/**
	 * 向页面输出json数据
	 */
	public void writeJson(HttpServletResponse response,JSON json) throws Exception{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());
	}
	
	
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
	
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String input(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		User entity = new User();
		if (!EmptyUtil.isEmpty(id)) {
			entity = userManager.get(id);
		}
		request.setAttribute("entity", entity);
		List<Role> roleList = roleManager.getAll();
		request.setAttribute("roleList", roleList);
		return "core/user/userForm";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void add(User user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JsonResult jsonResult = new JsonResult();
		userManager.save(user);
		writeJson(response, jsonResult.successASJson(""));
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String ids = request.getParameter("ids");
		try {
			for (String id : ids.split(",")) {
				if (!EmptyUtil.isEmpty(userManager.get(id))) {
					userManager.delete(id);
				}
			}
			writeJson(response,JsonResult.successToJson(""));
		} catch (Exception e) {
			e.printStackTrace();
			writeJson(response,JsonResult.failureToJson(""));
		}
	}
	
}


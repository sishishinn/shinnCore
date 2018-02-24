package com.sishishinn.core.web.user;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sishishinn.core.dto.user.UserView;
import com.sishishinn.core.entity.role.Role;
import com.sishishinn.core.entity.user.User;
import com.sishishinn.core.service.role.RoleManager;
import com.sishishinn.core.service.user.UserManager;
import com.sishishinn.core.util.EmptyUtil;
import com.sishishinn.core.util.JsonDataGrid;
import com.sishishinn.core.util.JsonResult;
import com.sishishinn.core.web.CrudControllerSupport;

@Controller
@RequestMapping(value = "/core/user")
public class UserController extends CrudControllerSupport{
	
	@Autowired
	private UserManager userManager;
	@Autowired
	private RoleManager roleManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletRequest request){
		String backFlag = request.getParameter("backFlag");
		request.getSession().setAttribute("backFlag", backFlag);
		return "core/user/userList";
	}
	
	@RequestMapping(value = "loadData",method = RequestMethod.POST)
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
	
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String input(HttpServletRequest request) throws Exception{
		List<Role> roleList = roleManager.getAll();
		request.setAttribute("roleList", roleList);
		return "core/user/userForm";
	}
	
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public void add(User user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JsonResult jsonResult = new JsonResult();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();  
   	 	Validator validator = factory.getValidator();  
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        if (constraintViolations.size()==0) {

        	String roleId = request.getParameter("roleId");
        	if (!EmptyUtil.isEmpty(roleId)) {
        		Role role = roleManager.get(roleId);
        		user.setRole(role);
        		user.setRolename(role.getName());
        	}else{
        		user.setRole(null);
        		user.setRolename(null);
        	}
        	
        	userManager.save(user);
        	writeJson(response, jsonResult.successASJson(""));
        	
        }else{
        	
        	String msg = "验证不满足数:"+constraintViolations.size()+";";
			Iterator<ConstraintViolation<User>> iterator = constraintViolations.iterator();
			while (iterator.hasNext()) {
				ConstraintViolation<User> message = iterator.next();
				msg += message.getPropertyPath() + ":" + message.getMessage()+";";
			}
			writeJson(response, jsonResult.failureASJson(msg));
			
        }
	}
	
	@RequestMapping(value = "user", method = RequestMethod.DELETE)
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
	
	/**
	 * 每个方法执行前被执行
	 */
	@ModelAttribute
	public void prepareModel(@RequestParam(value = "id",required=false) String id, Map<String, Object> map) throws Exception{
		if (!EmptyUtil.isEmpty(id)) {
			map.put("user", userManager.get(id));
		}else{
			map.put("user", new User());
		}
	}
	
}


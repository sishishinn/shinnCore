package com.sishishinn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sishishinn.core.entity.user.User;
import com.sishishinn.core.service.user.UserManager;

@Controller
public class HelloWorld {
	
	@Autowired
	private UserManager userManager;
	
	/**测试提交
     * 1. 使用RequestMapping注解来映射请求的URL
     * 2. 返回值会通过视图解析器解析为实际的物理视图, 对于InternalResourceViewResolver视图解析器，会做如下解析
     * 通过prefix+returnVal+suffix 这样的方式得到实际的物理视图，然后会转发操作
     * "/WEB-INF/views/success.jsp"
     * @return
     */
    @RequestMapping("/helloworld")
    public String hello(){
        System.out.println("hello world");
        return "success";
    }
    
    
    @RequestMapping("/adduser")
    public String addUser(){
    	
    	User user = new User();
    	user.setName("11222");
    	user.setPassword("11111111");
    	userManager.save(user);
    	
    	return "success";
    }
    
    
}

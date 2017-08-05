package com.sishishinn.core.web.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
    public String login(HttpServletRequest request) {
		
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("message");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		try {
            subject.login(token);
		} catch (UnknownAccountException unknownaccount) {  
            System.out.println("用户名不存在: " + unknownaccount);
            request.getSession().setAttribute("message", "账号不存在");
            return "redirect:login.jsp";  
        } catch (IncorrectCredentialsException incorrectcredentials) {  
            System.out.println("用户名存在,但密码和用户名不匹配: " + incorrectcredentials);
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("message", "密码错误");
            return "redirect:login.jsp";  
        } catch (Exception others) {  
            System.out.println("其他异常: " + others);  
            others.printStackTrace();
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("message", "登陆异常");
            return "redirect:login.jsp";  
        }
        return "success";
		
    }
	
}

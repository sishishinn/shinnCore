package com.sishishinn.core.framework.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.sishishinn.core.entity.user.User;
import com.sishishinn.core.service.user.UserManager;
import com.sishishinn.core.util.EmptyUtil;

public class ShiroDbRealm extends AuthorizingRealm implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private UserManager userManager;
	
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken logintoken = (UsernamePasswordToken) token;
		String username = logintoken.getUsername();
		String password = new String(logintoken.getPassword());
		
		User user = userManager.findByName(username);
		
		if (EmptyUtil.isEmpty(user)) {
			throw new UnknownAccountException("没有这个账号");
		}else{
			if (!user.getPassword().equals(password)) {
				throw new IncorrectCredentialsException("密码错误");
			}
		}
		ShiroUser shiroUser = new ShiroUser();
		shiroUser.setId(user.getId());
		shiroUser.setUsername(username);
		if("admin".equals(username)){  
			shiroUser.getRoles().add("admin");  
			shiroUser.getRoles().add("user");  
        }else{  
        	shiroUser.getRoles().add("user");  
        } 
		return new SimpleAuthenticationInfo(shiroUser,password,getName());
		
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("授权");
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		return new SimpleAuthorizationInfo(shiroUser.getRoles());
	}

	//新建一个类定义用户角色和权限  
    class ShiroUser implements Serializable{  
        private static final long serialVersionUID = 1L;
        private String id;
        private String username;  
        private Set<String>roles=new HashSet<String>();  
        
        public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUsername() {  
            return username;  
        }  
        public void setUsername(String username) {  
            this.username = username;  
        }  
        public Set<String> getRoles() {  
            return roles;  
        }  
        public void setRoles(Set<String> roles) {  
            this.roles = roles;  
        }  
    }

}

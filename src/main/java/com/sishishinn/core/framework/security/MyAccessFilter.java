package com.sishishinn.core.framework.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

public class MyAccessFilter extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request,ServletResponse response, Object mappedValue) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("in isAccessAllowed");
		
		
		return true;
	}

	/**
	 * isAccessAllowed返回false时才走onAccessDenied
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request,ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("in onAccessDenied");
		
		return true;
	}

	

}

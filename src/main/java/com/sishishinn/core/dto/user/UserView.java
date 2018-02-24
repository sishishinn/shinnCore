package com.sishishinn.core.dto.user;

import java.text.SimpleDateFormat;

import com.sishishinn.core.entity.user.User;
import com.sishishinn.core.util.EmptyUtil;


public class UserView {

	private String id;
	private String name;
	private String rolename;
	private String createtime;
	
	public UserView(){
	}

	public UserView(User user){
		this.id = user.getId();
		this.name = user.getName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!EmptyUtil.isEmpty(user.getCreatetime())) {
			this.createtime = sdf.format(user.getCreatetime());
		}
		if (!EmptyUtil.isEmpty(user.getRole())) {
			this.rolename = user.getRole().getName();
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}

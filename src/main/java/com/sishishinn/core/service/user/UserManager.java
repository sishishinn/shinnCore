package com.sishishinn.core.service.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sishishinn.core.dao.user.UserDao;
import com.sishishinn.core.entity.user.User;
import com.sishishinn.core.service.ServiceManager;
import com.sishishinn.core.util.EmptyUtil;


@Transactional(rollbackFor=Exception.class)
@Service
public class UserManager extends ServiceManager<User>{
	
	@Autowired
	private UserDao userdao;
	
	@Override
	public void save(User user) {
		if (EmptyUtil.isEmpty(user.getId())) {
			user.setCreatetime(new Date());
		}
		userdao.save(user);
    }  
	
	@Override
	public void delete(String id) throws Exception {
		userdao.delete(id);
	}

	@Override
	public User get(String id) throws Exception {
		return userdao.findOne(id);
	}

	@Override
	public List<User> getAll() throws Exception {
		return userdao.findAll();
	}
	
	@Override
	public Page<User> getPage(Map<String, Object> searchParams, int pageNumber, int pageSize,String mysort,String myorder) {
    	Specification<User> spec = buildSpecification(searchParams,User.class);
    	PageRequest pageRequest = buildPageRequest(pageNumber, pageSize,mysort,myorder);
		return userdao.findAll(spec, pageRequest);
	}
	
	public User findByName(String loginname) {
		return userdao.findByName(loginname);
	}

	public List<User> findByRoleId(String roleid) {
		return userdao.findByRole_Id(roleid);
	}
    
}

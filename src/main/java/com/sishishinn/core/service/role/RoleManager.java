package com.sishishinn.core.service.role;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sishishinn.core.dao.role.RoleDao;
import com.sishishinn.core.entity.role.Role;
import com.sishishinn.core.service.ServiceManager;
import com.sishishinn.core.util.EmptyUtil;


@Transactional(rollbackFor=Exception.class)
@Service
public class RoleManager extends ServiceManager<Role>{
	
	@Autowired
	private RoleDao roledao;
	
	@Override
	public void save(Role role) {
		if (EmptyUtil.isEmpty(role.getId())) {
			role.setCreatetime(new Date());
		}
		roledao.save(role);  
    }  
	
	@Override
	public void delete(String id) throws Exception {
		roledao.delete(id);
	}

	@Override
	public Role get(String id) throws Exception {
		return roledao.findOne(id);
	}

	@Override
	public List<Role> getAll() throws Exception {
		return roledao.findAll();
	}
	
	@Override
	public Page<Role> getPage(Map<String, Object> searchParams, int pageNumber, int pageSize,String mysort,String myorder) {
    	Specification<Role> spec = buildSpecification(searchParams,Role.class);
    	PageRequest pageRequest = buildPageRequest(pageNumber, pageSize,mysort,myorder);
		return roledao.findAll(spec, pageRequest);
	}
}

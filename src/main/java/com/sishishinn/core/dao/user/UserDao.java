package com.sishishinn.core.dao.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sishishinn.core.entity.user.User;


public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

	User findByName(String name);
	
	List<User> findByRole_Id(String roleid);
	
	Page<User> findAll(Specification<User> spec, Pageable pageable);
	
//	@Query("select user from User user where role.id=?1")
//	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true") })
//	List<User> test(String roleid);
	
}

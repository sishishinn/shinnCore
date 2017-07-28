package com.sishishinn.core.dao.user;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sishishinn.core.entity.user.User;


public interface UserDao extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {

	@Query("select user from User user where id=?1")
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true") })
	User get(String id);
	
	@Query("select user from User user ORDER BY user.createtime DESC")
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true") })
	List<User> getAll();

	@Query("select user from User user where name=?1")
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true") })
	User fingUserByLoginName(String loginname);
	
	//@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true") })
	Page<User> findAll(Specification<User> spec, Pageable pageable);
}

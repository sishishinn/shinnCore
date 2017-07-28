package com.sishishinn.core.dao.role;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sishishinn.core.entity.role.Role;


public interface RoleDao extends PagingAndSortingRepository<Role, String>, JpaSpecificationExecutor<Role> {

	@Query("select role from Role role where id=?1")
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true") })
	Role get(String id);
	
	@Query("select role from Role role ORDER BY role.createtime DESC")
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true") })
	List<Role> getAll();

	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true") })
	Page<Role> findAll(Specification<Role> spec, Pageable pageable);
}

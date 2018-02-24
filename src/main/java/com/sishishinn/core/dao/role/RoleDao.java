package com.sishishinn.core.dao.role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sishishinn.core.entity.role.Role;


public interface RoleDao extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

	Page<Role> findAll(Specification<Role> spec, Pageable pageable);
	
}

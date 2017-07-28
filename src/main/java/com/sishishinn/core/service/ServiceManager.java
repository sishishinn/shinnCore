package com.sishishinn.core.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.sishishinn.core.util.EmptyUtil;


public abstract class ServiceManager<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public abstract void save(T entity)throws Exception;
	
	public abstract void delete(String id) throws Exception;
	
	public abstract T get(String id)throws Exception;
	
	public abstract List<T> getAll() throws Exception;
	
	public abstract Page<T> getPage(Map<String, Object> searchParams,int pageNumber, int pageSize, String mysort, String myorder) throws Exception;

	/**
     * 创建动态查询条件组合.                        
     */
	public Specification<T> buildSpecification(Map<String, Object> searchParams,Class<T> class1) {
    	Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    	Specification<T> spec = DynamicSpecifications.bySearchFilter(filters.values(), class1);
    	return spec;
    }
	
	/**
	 * 创建分页请求.
	 */
	public PageRequest buildPageRequest(int pageNumber, int pagzSize,String mysort,String myorder) {
		Sort sort = null;
		if (EmptyUtil.isEmpty(mysort)) {
			sort = new Sort(Direction.DESC, "createtime");
		}else{
			if ("asc".equals(myorder)) {
				sort = new Sort(Direction.ASC, mysort);
			}else{
				sort = new Sort(Direction.DESC, mysort);
			}
		}
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

}

package com.sishishinn.core.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class IdEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	@Id
	@GeneratedValue(generator = "uuid")   //指定生成器名称  
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		
}

package com.sishishinn.core.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.sishishinn.core.util.EmptyUtil;


@MappedSuperclass
public class ObjectEntity extends IdEntity implements Cloneable {

	private static final long serialVersionUID = 1L;

	private Date createtime;

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public void initDate(){
		if (EmptyUtil.isEmpty(getId())) {
			setCreatetime(new Date());
		}
	}
	
}

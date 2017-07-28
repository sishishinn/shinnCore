package com.sishishinn.core.entity.role;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

import com.sishishinn.core.entity.ObjectEntity;
import com.sishishinn.core.entity.user.User;


@Entity
@Table(name = "my_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends ObjectEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	private List<User> userList;

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn(name="role_id")
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.DEFAULT_STYLE);
	}

}

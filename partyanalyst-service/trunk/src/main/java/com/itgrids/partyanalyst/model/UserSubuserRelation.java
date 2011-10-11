package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * user_subuser_relation entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name = "user_subuser_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserSubuserRelation implements Serializable{

	private static final long serialVersionUID = 7637873471291781621L;
	private Long userSubuserRelationId;
	private Registration user;
	private Registration subuser;
	private String isChildAccess;
	
	public UserSubuserRelation()
	{}
	
	public UserSubuserRelation(Registration user,Registration subuser,String isChildAccess)
	{
		this.user = user;
		this.subuser = subuser;
		this.isChildAccess = isChildAccess;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_subuser_relation_id", unique=true, nullable=false)
	public Long getUserSubuserRelationId() {
		return userSubuserRelationId;
	}

	public void setUserSubuserRelationId(Long userSubuserRelationId) {
		this.userSubuserRelationId = userSubuserRelationId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Registration getUser() {
		return user;
	}

	public void setUser(Registration user) {
		this.user = user;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "subuser_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Registration getSubuser() {
		return subuser;
	}

	public void setSubuser(Registration subuser) {
		this.subuser = subuser;
	}

	@Column(name = "is_child_access" , length = 10)
	public String getIsChildAccess() {
		return isChildAccess;
	}

	public void setIsChildAccess(String isChildAccess) {
		this.isChildAccess = isChildAccess;
	}
	
	
}

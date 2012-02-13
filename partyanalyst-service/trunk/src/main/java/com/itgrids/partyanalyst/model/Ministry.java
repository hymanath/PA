package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

/**
 * ministry entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="ministry")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ministry extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = 7588040581430248197L;
	private Long ministryId;
	private String name;
	private Integer orderNo;
	private Set<MinistryScope> ministryScopes = new HashSet<MinistryScope>(0);
	
	public Ministry()
	{}
	
	public Ministry(String name,Integer orderNo)
	{
		this.name = name;
		this.orderNo = orderNo;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ministry_id", unique=true, nullable=false)
	public Long getMinistryId() {
		return ministryId;
	}

	public void setMinistryId(Long ministryId) {
		this.ministryId = ministryId;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "order_no")
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ministry")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<MinistryScope> getMinistryScopes() {
		return ministryScopes;
	}

	public void setMinistryScopes(Set<MinistryScope> ministryScopes) {
		this.ministryScopes = ministryScopes;
	}

}

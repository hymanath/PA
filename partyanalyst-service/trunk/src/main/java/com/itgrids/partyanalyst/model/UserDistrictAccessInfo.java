package com.itgrids.partyanalyst.model;

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

@Entity
@Table(name = "user_district_access_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserDistrictAccessInfo extends BaseModel{

	private static final long serialVersionUID = 1L;
	private Long userDistrictAccessInfoId;
	private UserGroups userGroup;
	private District district ;
	
	public UserDistrictAccessInfo(){
		
	}
	
	public UserDistrictAccessInfo(Long userDistrictAccessInfoId,
			UserGroups userGroup, District district) {
		super();
		this.userDistrictAccessInfoId = userDistrictAccessInfoId;
		this.userGroup = userGroup;
		this.district = district;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_district_access_info_id", unique = true, nullable = false)
	public Long getUserDistrictAccessInfoId() {
		return userDistrictAccessInfoId;
	}

	public void setUserDistrictAccessInfoId(Long userDistrictAccessInfoId) {
		this.userDistrictAccessInfoId = userDistrictAccessInfoId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_group_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserGroups getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroups userGroup) {
		this.userGroup = userGroup;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	

}

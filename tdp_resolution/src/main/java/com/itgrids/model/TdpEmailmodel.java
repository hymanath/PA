package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tdp_email")
public class TdpEmailmodel extends BaseEntity {
	

	private static final long serialVersionUID = -2853930539938433902L;
	
	@Id
	@Column(name="tdp_email_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long tdpEmailId;

	@Column(name="tdp_email")
	private String email;
	
	@Column(name="tdp_cadre_id")
	private Long tdpCadreId;
	
	@Column(name="is_active")
	private boolean isActive;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getTdpCadreId() {
		return tdpCadreId;
	}

	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getTdpEmailId() {
		return tdpEmailId;
	}

	public void setTdpEmailId(Long tdpEmailId) {
		this.tdpEmailId = tdpEmailId;
	}

}

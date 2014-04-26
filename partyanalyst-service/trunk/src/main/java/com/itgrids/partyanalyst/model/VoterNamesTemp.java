package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "voter_names_temp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterNamesTemp extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 5326933174498930228L;
	private Long voterNamesTempId;
	private Constituency constituency;
	private String firstName;
	private String lastName;
	private String relativeFirstName;
	private String relativeLastName;
	private String voterIdCardNo;
	
	public VoterNamesTemp()
	{
		
	}
	
	public VoterNamesTemp(Long voterNamesTempId,Constituency constituency,String firstName,
			String lastName,String relativeFirstName,String voterIdCardNo, String relativeLastName)
	{
		
		this.voterNamesTempId = voterNamesTempId;
		this.constituency = constituency;
		this.firstName = firstName;
		this.lastName = lastName;
		this.relativeFirstName = relativeFirstName;
		this.relativeLastName = relativeLastName;
		this.voterIdCardNo = voterIdCardNo;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_names_temp_id", unique = true, nullable = false)
	public Long getVoterNamesTempId() {
		return voterNamesTempId;
	}
	public void setVoterNamesTempId(Long voterNamesTempId) {
		this.voterNamesTempId = voterNamesTempId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AC_ID")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	@Column(name = "FM_NAME_UNIC")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name = "LASTNAME_UNIC")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name = "RLN_FM_NM_UNIC")
	public String getRelativeFirstName() {
		return relativeFirstName;
	}
	public void setRelativeFirstName(String relativeFirstName) {
		this.relativeFirstName = relativeFirstName;
	}
	@Column(name = "RLN_L_NM_UNIC")
	public String getRelativeLastName() {
		return relativeLastName;
	}
	public void setRelativeLastName(String relativeLastName) {
		this.relativeLastName = relativeLastName;
	}
	@Column(name = "IDCARD_NO")
	public String getVoterIdCardNo() {
		return voterIdCardNo;
	}
	public void setVoterIdCardNo(String voterIdCardNo) {
		this.voterIdCardNo = voterIdCardNo;
	}
	
	
	

}

/**
 * @author Sravanth
 * Apr 28, 2016
 * RegistrationQueriesVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

/**
 * @author Sravanth
 * @date Apr 28, 2016
 */
public class RegistrationQueriesVO {

	private Long id;
	private String name;
	private String mobileNo;
	private String email;
	private String description;
	
	
	public RegistrationQueriesVO(String name,String mobileNo,String email ,String description){
		this.name=name;
		this.mobileNo = mobileNo;
		this.email=email;
		this.description = description;
	}	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

package com.itgrids.partyanalyst.dto;

public class VoterWebServiceDataVO  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2463037785793965855L;
	String voterCardNo;
	String name;
	String relativeName;
	String relation;
	String age;
	String doorNo;
	Long constituencyId;
	Integer pageNo;
	
	public String getVoterCardNo() {
		return voterCardNo;
	}
	
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRelativeName() {
		return relativeName;
	}
	
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	
	public String getRelation() {
		return relation;
	}
	
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getDoorNo() {
		return doorNo;
	}
	
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	public Integer getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	
}

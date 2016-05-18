/**
 * @author Sravanth
 * May 18, 2016
 * ActivityResponseVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

/**
 * @author Sravanth
 * @date May 18, 2016
 */
public class ActivityResponseVO {

	private Long id;
	private String name;
	
	private Long questionId;
	private Long totalCount;
	private Long APCount;
	private Long TSCount;
	private Long desTotalCount;
	private Long desAPCount;
	private Long desTSCount;
	
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
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getAPCount() {
		return APCount;
	}
	public void setAPCount(Long aPCount) {
		APCount = aPCount;
	}
	public Long getTSCount() {
		return TSCount;
	}
	public void setTSCount(Long tSCount) {
		TSCount = tSCount;
	}
	public Long getDesTotalCount() {
		return desTotalCount;
	}
	public void setDesTotalCount(Long desTotalCount) {
		this.desTotalCount = desTotalCount;
	}
	public Long getDesAPCount() {
		return desAPCount;
	}
	public void setDesAPCount(Long desAPCount) {
		this.desAPCount = desAPCount;
	}
	public Long getDesTSCount() {
		return desTSCount;
	}
	public void setDesTSCount(Long desTSCount) {
		this.desTSCount = desTSCount;
	}
}

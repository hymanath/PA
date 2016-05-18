/**
 * @author Sravanth
 * May 18, 2016
 * ActivityResponseVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sravanth
 * @date May 18, 2016
 */
public class ActivityResponseVO {

	private Long id;
	private String name;
	
	private Long questionId;
	private Long totalCount = 0l;
	private Long APCount = 0l;
	private Long TSCount = 0l;
	private Long desTotalCount = 0l;
	private Long desAPCount = 0l;
	private Long desTSCount = 0l;
	
	private Long called=0L;
	private Long pending=0L;
	private Long sumcount=0L;
	private List<ActivityResponseVO> sublist1 = new ArrayList<ActivityResponseVO>(0);
	private List<ActivityResponseVO> sublist2 = new ArrayList<ActivityResponseVO>(0);
	
	
	public Long getCalled() {
		return called;
	}
	public void setCalled(Long called) {
		this.called = called;
	}
	public Long getPending() {
		return pending;
	}
	public void setPending(Long pending) {
		this.pending = pending;
	}
	public Long getSumcount() {
		return sumcount;
	}
	public void setSumcount(Long sumcount) {
		this.sumcount = sumcount;
	}
	public List<ActivityResponseVO> getSublist1() {
		return sublist1;
	}
	public void setSublist1(List<ActivityResponseVO> sublist1) {
		this.sublist1 = sublist1;
	}
	public List<ActivityResponseVO> getSublist2() {
		return sublist2;
	}
	public void setSublist2(List<ActivityResponseVO> sublist2) {
		this.sublist2 = sublist2;
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

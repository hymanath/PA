/**
 * @author SRAVANTH
 * MAY 29, 2017
 * MeekosamDynamicVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SRAVANTH
 * @date MAY 29, 2017
 */
public class MeekosamDynamicVO {

	private Long id;
	private String name;
	
	private Long issueTypeId;
	private String issueType;
	private Long issueFieldId;
	private String issueField;
	private Long issueFieldTypeId;
	private String issueFielsType;
	
	private List<MeekosamDynamicVO> subList = new ArrayList<MeekosamDynamicVO>();
	private Long issueRelationId;
	private Long issueRelationDataId;
	
	
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
	public Long getIssueTypeId() {
		return issueTypeId;
	}
	public void setIssueTypeId(Long issueTypeId) {
		this.issueTypeId = issueTypeId;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public Long getIssueFieldId() {
		return issueFieldId;
	}
	public void setIssueFieldId(Long issueFieldId) {
		this.issueFieldId = issueFieldId;
	}
	public String getIssueField() {
		return issueField;
	}
	public void setIssueField(String issueField) {
		this.issueField = issueField;
	}
	public Long getIssueFieldTypeId() {
		return issueFieldTypeId;
	}
	public void setIssueFieldTypeId(Long issueFieldTypeId) {
		this.issueFieldTypeId = issueFieldTypeId;
	}
	public String getIssueFielsType() {
		return issueFielsType;
	}
	public void setIssueFielsType(String issueFielsType) {
		this.issueFielsType = issueFielsType;
	}
	public List<MeekosamDynamicVO> getSubList() {
		return subList;
	}
	public void setSubList(List<MeekosamDynamicVO> subList) {
		this.subList = subList;
	}
	public Long getIssueRelationId() {
		return issueRelationId;
	}
	public void setIssueRelationId(Long issueRelationId) {
		this.issueRelationId = issueRelationId;
	}
	public Long getIssueRelationDataId() {
		return issueRelationDataId;
	}
	public void setIssueRelationDataId(Long issueRelationDataId) {
		this.issueRelationDataId = issueRelationDataId;
	}
}

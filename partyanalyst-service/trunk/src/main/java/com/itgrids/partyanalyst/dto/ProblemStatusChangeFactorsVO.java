/* 
 * Copyright (c) 2011 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February, 2011
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sai Krishna
 *
 */
public class ProblemStatusChangeFactorsVO extends ResultStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean isProblemScope;
	private Boolean isDepartments;
	private Boolean isCadre;
	private Boolean isProblemType;

	private List<SelectOptionVO> problemScopes;
	private List<SelectOptionVO> departments;
	private List<SelectOptionVO> problemTypes;
	
	
	public Boolean getIsProblemScope() {
		return isProblemScope;
	}
	public void setIsProblemScope(Boolean isProblemScope) {
		this.isProblemScope = isProblemScope;
	}
	public Boolean getIsDepartments() {
		return isDepartments;
	}
	public void setIsDepartments(Boolean isDepartments) {
		this.isDepartments = isDepartments;
	}
	public Boolean getIsCadre() {
		return isCadre;
	}
	public void setIsCadre(Boolean isCadre) {
		this.isCadre = isCadre;
	}
	public Boolean getIsProblemType() {
		return isProblemType;
	}
	public void setIsProblemType(Boolean isProblemType) {
		this.isProblemType = isProblemType;
	}
	public List<SelectOptionVO> getProblemScopes() {
		return problemScopes;
	}
	public void setProblemScopes(List<SelectOptionVO> problemScopes) {
		this.problemScopes = problemScopes;
	}
	public List<SelectOptionVO> getDepartments() {
		return departments;
	}
	public void setDepartments(List<SelectOptionVO> departments) {
		this.departments = departments;
	}
	public List<SelectOptionVO> getProblemTypes() {
		return problemTypes;
	}
	public void setProblemTypes(List<SelectOptionVO> problemTypes) {
		this.problemTypes = problemTypes;
	}
	
	
}

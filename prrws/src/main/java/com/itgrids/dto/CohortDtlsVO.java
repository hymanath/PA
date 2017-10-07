package com.itgrids.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CohortDtlsVO {

    private String cohort;
    private String innovator_name;
    private String company_name;
    private String permanent_jobs;
    private String temporary_jobs;
    private String interns;
    private String innovation;
    
	public String getCohort() {
		return cohort;
	}
	public void setCohort(String cohort) {
		this.cohort = cohort;
	}
	public String getInnovator_name() {
		return innovator_name;
	}
	public void setInnovator_name(String innovator_name) {
		this.innovator_name = innovator_name;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getPermanent_jobs() {
		return permanent_jobs;
	}
	public void setPermanent_jobs(String permanent_jobs) {
		this.permanent_jobs = permanent_jobs;
	}
	public String getTemporary_jobs() {
		return temporary_jobs;
	}
	public void setTemporary_jobs(String temporary_jobs) {
		this.temporary_jobs = temporary_jobs;
	}
	public String getInterns() {
		return interns;
	}
	public void setInterns(String interns) {
		this.interns = interns;
	}
	public String getInnovation() {
		return innovation;
	}
	public void setInnovation(String innovation) {
		this.innovation = innovation;
	}
    
}

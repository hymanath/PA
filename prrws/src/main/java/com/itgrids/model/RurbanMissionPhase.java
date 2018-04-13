package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rurban_mission_phase")
public class RurbanMissionPhase {
	private Long rurbanMissionPhaseId;
	private String phaseName;
	private Long fromYear;
	private Long toYear;
	private Long estimatedFund;
	private Long cgf;
	private Long convergenceExpenditure;
	private Long worksSanctioned;
	private Long worksGrounded;
	
	@Id
	@Column(name="rurban_mission_phase_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getRurbanMissionPhaseId() {
		return rurbanMissionPhaseId;
	}
	public void setRurbanMissionPhaseId(Long rurbanMissionPhaseId) {
		this.rurbanMissionPhaseId = rurbanMissionPhaseId;
	}
	@Column(name="phase_name")
	public String getPhaseName() {
		return phaseName;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
	@Column(name="from_year")
	public Long getFromYear() {
		return fromYear;
	}
	public void setFromYear(Long fromYear) {
		this.fromYear = fromYear;
	}
	@Column(name="to_year")
	public Long getToYear() {
		return toYear;
	}
	public void setToYear(Long toYear) {
		this.toYear = toYear;
	}
	@Column(name="estimated_fund")
	public Long getEstimatedFund() {
		return estimatedFund;
	}
	public void setEstimatedFund(Long estimatedFund) {
		this.estimatedFund = estimatedFund;
	}
	@Column(name="cgf")
	public Long getCgf() {
		return cgf;
	}
	public void setCgf(Long cgf) {
		this.cgf = cgf;
	}
	@Column(name="convergence_expenditure")
	public Long getConvergenceExpenditure() {
		return convergenceExpenditure;
	}
	public void setConvergenceExpenditure(Long convergenceExpenditure) {
		this.convergenceExpenditure = convergenceExpenditure;
	}
	@Column(name="works_sanctioned")
	public Long getWorksSanctioned() {
		return worksSanctioned;
	}
	public void setWorksSanctioned(Long worksSanctioned) {
		this.worksSanctioned = worksSanctioned;
	}
	@Column(name="works_grounded")
	public Long getWorksGrounded() {
		return worksGrounded;
	}
	public void setWorksGrounded(Long worksGrounded) {
		this.worksGrounded = worksGrounded;
	}
	
}

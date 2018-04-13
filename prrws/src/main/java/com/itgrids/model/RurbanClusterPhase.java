package com.itgrids.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rurban_cluster_phase")
public class RurbanClusterPhase {
	private Long rurbanClusterPhase;
	private Long rurbanClusterId;
	private Long rurbanMissionPhaseId;
	private Long totalCost;
	private Long convergenceTarget;
	private Long cgf;
	private Long cgfReleased;
	private Long adminSanction;
	private Long estimatedWorks;
	private Long sanctioned;
	private Long grounded;
	private String idDeleted;
	
	private RurbanCluster rurbanCluster;
	private RurbanMissionPhase rurbanMissionPhase;
	
	@Id
	@Column(name="rurban_cluster_phase_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getRurbanClusterPhase() {
		return rurbanClusterPhase;
	}
	public void setRurbanClusterPhase(Long rurbanClusterPhase) {
		this.rurbanClusterPhase = rurbanClusterPhase;
	}
	@Column(name="rurban_cluster_id")
	public Long getRurbanClusterId() {
		return rurbanClusterId;
	}
	public void setRurbanClusterId(Long rurbanClusterId) {
		this.rurbanClusterId = rurbanClusterId;
	}
	@Column(name="rurban_mission_phase_id")
	public Long getRurbanMissionPhaseId() {
		return rurbanMissionPhaseId;
	}
	public void setRurbanMissionPhaseId(Long rurbanMissionPhaseId) {
		this.rurbanMissionPhaseId = rurbanMissionPhaseId;
	}
	@Column(name="total_cost")
	public Long getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Long totalCost) {
		this.totalCost = totalCost;
	}
	@Column(name="convergence_target")
	public Long getConvergenceTarget() {
		return convergenceTarget;
	}
	public void setConvergenceTarget(Long convergenceTarget) {
		this.convergenceTarget = convergenceTarget;
	}
	@Column(name="cgf")
	public Long getCgf() {
		return cgf;
	}
	public void setCgf(Long cgf) {
		this.cgf = cgf;
	}
	@Column(name="cgf_released")
	public Long getCgfReleased() {
		return cgfReleased;
	}
	public void setCgfReleased(Long cgfReleased) {
		this.cgfReleased = cgfReleased;
	}
	@Column(name="admin_sanction")
	public Long getAdminSanction() {
		return adminSanction;
	}
	public void setAdminSanction(Long adminSanction) {
		this.adminSanction = adminSanction;
	}
	@Column(name="estimated_works")
	public Long getEstimatedWorks() {
		return estimatedWorks;
	}
	public void setEstimatedWorks(Long estimatedWorks) {
		this.estimatedWorks = estimatedWorks;
	}
	@Column(name="sanctioned")
	public Long getSanctioned() {
		return sanctioned;
	}
	public void setSanctioned(Long sanctioned) {
		this.sanctioned = sanctioned;
	}
	@Column(name="grounded")
	public Long getGrounded() {
		return grounded;
	}
	public void setGrounded(Long grounded) {
		this.grounded = grounded;
	}
	@Column(name="id_deleted")
	public String getIdDeleted() {
		return idDeleted;
	}
	public void setIdDeleted(String idDeleted) {
		this.idDeleted = idDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rurban_cluster_id", insertable = false, updatable = false)
	public RurbanCluster getRurbanCluster() {
		return rurbanCluster;
	}
	public void setRurbanCluster(RurbanCluster rurbanCluster) {
		this.rurbanCluster = rurbanCluster;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rurban_mission_phase_id", insertable = false, updatable = false)
	public RurbanMissionPhase getRurbanMissionPhase() {
		return rurbanMissionPhase;
	}
	public void setRurbanMissionPhase(RurbanMissionPhase rurbanMissionPhase) {
		this.rurbanMissionPhase = rurbanMissionPhase;
	}
	
	
}

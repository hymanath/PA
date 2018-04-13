package com.itgrids.model;

import java.util.Date;

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
@Table(name = "rurban_work")
public class RurbanWork {
	private Long rurbanWorkId;
	private String workName;
	private Long rurbanWorkStatusId;
	private Long rurbanClusterId;
	private Long totalCost;
	private Long convergenceCost;
	private Long cgfCost;
	private Date startDate;
	private Date completedDate;
	private Date updatedTime;
	private String isDeletd;
	
	private RurbanWorkStatus rurbanWorkStatus;
	private RurbanCluster rurbanCluster;
	@Id
	@Column(name="rurban_work_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getRurbanWorkId() {
		return rurbanWorkId;
	}
	public void setRurbanWorkId(Long rurbanWorkId) {
		this.rurbanWorkId = rurbanWorkId;
	}
	@Column(name="work_name")
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	@Column(name="rurban_work_status_id")
	public Long getRurbanWorkStatusId() {
		return rurbanWorkStatusId;
	}
	public void setRurbanWorkStatusId(Long rurbanWorkStatusId) {
		this.rurbanWorkStatusId = rurbanWorkStatusId;
	}
	@Column(name="rurban_cluster_id")
	public Long getRurbanClusterId() {
		return rurbanClusterId;
	}
	public void setRurbanClusterId(Long rurbanClusterId) {
		this.rurbanClusterId = rurbanClusterId;
	}
	@Column(name="total_cost")
	public Long getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Long totalCost) {
		this.totalCost = totalCost;
	}
	@Column(name="convergence_cost")
	public Long getConvergenceCost() {
		return convergenceCost;
	}
	public void setConvergenceCost(Long convergenceCost) {
		this.convergenceCost = convergenceCost;
	}
	@Column(name="cgf_cost")
	public Long getCgfCost() {
		return cgfCost;
	}
	public void setCgfCost(Long cgfCost) {
		this.cgfCost = cgfCost;
	}
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name="completed_date")
	public Date getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name="is_deletd")
	public String getIsDeletd() {
		return isDeletd;
	}
	public void setIsDeletd(String isDeletd) {
		this.isDeletd = isDeletd;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rurban_work_status_id", insertable = false, updatable = false)
	public RurbanWorkStatus getRurbanWorkStatus() {
		return rurbanWorkStatus;
	}
	public void setRurbanWorkStatus(RurbanWorkStatus rurbanWorkStatus) {
		this.rurbanWorkStatus = rurbanWorkStatus;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rurban_cluster_id", insertable = false, updatable = false)
	public RurbanCluster getRurbanCluster() {
		return rurbanCluster;
	}
	public void setRurbanCluster(RurbanCluster rurbanCluster) {
		this.rurbanCluster = rurbanCluster;
	}
}

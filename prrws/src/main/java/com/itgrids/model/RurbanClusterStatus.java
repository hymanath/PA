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
@Table(name = "rurban_cluster_status")
public class RurbanClusterStatus {
	private Long rurbanClusterStatus;
	private Long rurbanClusterId;
	private Long clusterStatusId;
	private Date updatedTime;
	private String isDeleted;
	
	private RurbanCluster rurbanCluster;
	private ClusterStatus clusterStatus;
	@Id
	@Column(name="rurban_cluster_status_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getRurbanClusterStatus() {
		return rurbanClusterStatus;
	}
	public void setRurbanClusterStatus(Long rurbanClusterStatus) {
		this.rurbanClusterStatus = rurbanClusterStatus;
	}
	@Column(name="rurban_cluster_id")
	public Long getRurbanClusterId() {
		return rurbanClusterId;
	}
	public void setRurbanClusterId(Long rurbanClusterId) {
		this.rurbanClusterId = rurbanClusterId;
	}
	@Column(name="cluster_status_id")
	public Long getClusterStatusId() {
		return clusterStatusId;
	}
	public void setClusterStatusId(Long clusterStatusId) {
		this.clusterStatusId = clusterStatusId;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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
	@JoinColumn(name = "cluster_status_id", insertable = false, updatable = false)
	public ClusterStatus getClusterStatus() {
		return clusterStatus;
	}
	public void setClusterStatus(ClusterStatus clusterStatus) {
		this.clusterStatus = clusterStatus;
	}
}

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
@Table(name = "rurban_cluster")
public class RurbanCluster {
	private Long rurbanClusterId;
	private String clustorName;
	private Long clusterStatusId;
	private Long clusterAreaCategoryId;
	private Date insertedTime;
	private Date updatedTime;
	
	private ClusterStatus clusterStatus;
	private ClusterAreaCategory clusterAreaCategory;
	@Id
	@Column(name="rurban_cluster_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getRurbanClusterId() {
		return rurbanClusterId;
	}
	public void setRurbanClusterId(Long rurbanClusterId) {
		this.rurbanClusterId = rurbanClusterId;
	}
	@Column(name="clustor_name")
	public String getClustorName() {
		return clustorName;
	}
	public void setClustorName(String clustorName) {
		this.clustorName = clustorName;
	}
	@Column(name="cluster_status_id")
	public Long getClusterStatusId() {
		return clusterStatusId;
	}
	public void setClusterStatusId(Long clusterStatusId) {
		this.clusterStatusId = clusterStatusId;
	}
	@Column(name="cluster_area_category_id")
	public Long getClusterAreaCategoryId() {
		return clusterAreaCategoryId;
	}
	public void setClusterAreaCategoryId(Long clusterAreaCategoryId) {
		this.clusterAreaCategoryId = clusterAreaCategoryId;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cluster_status_id", insertable = false, updatable = false)
	public ClusterStatus getClusterStatus() {
		return clusterStatus;
	}
	public void setClusterStatus(ClusterStatus clusterStatus) {
		this.clusterStatus = clusterStatus;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cluster_area_category_id", insertable = false, updatable = false)
	public ClusterAreaCategory getClusterAreaCategory() {
		return clusterAreaCategory;
	}
	public void setClusterAreaCategory(ClusterAreaCategory clusterAreaCategory) {
		this.clusterAreaCategory = clusterAreaCategory;
	}
	
}

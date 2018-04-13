package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cluster_status")
public class ClusterStatus {
	private Long clusterStatusId;
	private String statusName;
	private Long orderNo;
	@Id
	@Column(name="cluster_status_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getClusterStatusId() {
		return clusterStatusId;
	}
	public void setClusterStatusId(Long clusterStatusId) {
		this.clusterStatusId = clusterStatusId;
	}
	@Column(name="status_name")
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
}

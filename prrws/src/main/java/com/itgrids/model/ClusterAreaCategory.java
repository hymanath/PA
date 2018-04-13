package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cluster_area_category")
public class ClusterAreaCategory {
	private Long clusterAreaCategoryId;
	private String category;
	@Id
	@Column(name="cluster_area_category_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getClusterAreaCategoryId() {
		return clusterAreaCategoryId;
	}
	public void setClusterAreaCategoryId(Long clusterAreaCategoryId) {
		this.clusterAreaCategoryId = clusterAreaCategoryId;
	}
	@Column(name="category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}

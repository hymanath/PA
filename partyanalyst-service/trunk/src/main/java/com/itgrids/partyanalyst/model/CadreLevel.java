package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
 

@Entity
@Table(name = "cadre_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreLevel extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long cadreLevelID;
	private String level;
	private Long orderNo;

	
	public CadreLevel(){
		super();
	}
	
	public CadreLevel(Long cadreLevelID, String level){
		super();
		this.cadreLevelID = cadreLevelID;
		this.level = level;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_level_id", unique = true, nullable = false)
	public Long getCadreLevelID() {
		return cadreLevelID;
	}
	
	public void setCadreLevelID(Long cadreLevelID) {
		this.cadreLevelID = cadreLevelID;
	}
	
	@Column(name="level", length = 40)
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	@Column(name="order_no", length = 40)
	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

}

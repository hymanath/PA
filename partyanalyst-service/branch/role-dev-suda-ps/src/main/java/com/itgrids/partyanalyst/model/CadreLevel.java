package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
 

@Entity
@Table(name = "cadre_level")
public class CadreLevel extends BaseModel{
	
	private Long cadreLevelID;
	private String level;

	
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

}

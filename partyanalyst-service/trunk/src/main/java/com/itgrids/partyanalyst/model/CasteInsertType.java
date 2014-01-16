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
@Table(name = "caste_insert_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CasteInsertType extends BaseModel implements Serializable{
	
private Long casteInsertTypeId;
private String type;
private String description;

public CasteInsertType()
{
	
}
public CasteInsertType( Long casteInsertTypeId, String type,String description)
{
	this.casteInsertTypeId =casteInsertTypeId;
	this.type =type;
	this.description = description;
}
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="caste_insert_type_id", unique=true, nullable=false)
public Long getCasteInsertTypeId() {
	return casteInsertTypeId;
}
public void setCasteInsertTypeId(Long casteInsertTypeId) {
	this.casteInsertTypeId = casteInsertTypeId;
}
@Column(name = "type", length = 45)
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
@Column(name = "description", length = 45)
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}


}

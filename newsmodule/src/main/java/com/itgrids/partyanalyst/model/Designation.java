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
@Table(name="designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Designation extends BaseModel implements Serializable{
	

   private static final long serialVersionUID = 2550780368276207008L;
   private Long designationId;
   private String designation;
  
   public Designation(){}
  
    public Designation(String designation)
    {
	  this.designation = designation;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "designation_id", unique = true, nullable = false)
	public Long getDesignationId() {
		return designationId;
	}
	
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	
	@Column(name = "designation", length = 40)
	public String getDesignation() {
		return designation;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
  
  

}

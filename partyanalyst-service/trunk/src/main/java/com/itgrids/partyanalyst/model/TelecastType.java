package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "telecast_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TelecastType extends BaseModel implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 2770484105252236590L;

	//telecast_type_id,name,is_deleted
	
	private Long telecastTypeId;
	private String name;
	private String isDeleted;
	private Set<Debate> debate = new HashSet<Debate>(0);


	//default constructor.
	
     public TelecastType() {}

   //setters and getters.
     
     
     @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "telecast_type_id", unique = true, nullable = false)
	public Long getTelecastTypeId() {
		return telecastTypeId;
	}

	public void setTelecastTypeId(Long telecastTypeId) {
		this.telecastTypeId = telecastTypeId;
	}
	
	@Column(name = "name", length = 50)
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name ="is_deleted",length = 1,nullable=true,updatable=true)
    public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	} 
     
   
	   
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "telecastType")
		public Set<Debate> getDebate() {
			return debate;
		}

		public void setDebate(Set<Debate> debate) {
			this.debate = debate;
		}
					
	
	
	
     
     
     
     
     
     
     
     
     
     
     
     
     
     
	
}

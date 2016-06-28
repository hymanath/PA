package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "debate_roles")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DebateRoles extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = 2770484105252236590L;

	//debate_roles_id,name,is_deleted
	
	private Long debateRolesId;
	private String name;
	private String isDeleted;

	//default constructor.
	
     public DebateRoles() {}

     
   //setters and getters.
     
     
     @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "debate_roles_id", unique = true, nullable = false)
	public Long getDebateRolesId() {
		return debateRolesId;
	}

	public void setDebateRolesId(Long debateRolesId) {
		this.debateRolesId = debateRolesId;
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

	
	
	
	
	
	
	
	
}

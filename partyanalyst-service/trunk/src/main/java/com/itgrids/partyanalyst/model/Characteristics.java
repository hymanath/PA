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
@Table(name = "characteristics")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Characteristics extends BaseModel implements java.io.Serializable{


	private Long characteristicsId;
	private String name;
	private String isDeleted;
	private Long maxScale;

	//default constructor.
	
     public Characteristics() {}
 
     
     @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "characteristics_id", unique = true, nullable = false)
	public Long getCharacteristicsId() {
		return characteristicsId;
	}

	public void setCharacteristicsId(Long characteristicsId) {
		this.characteristicsId = characteristicsId;
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

	@Column(name = "max_scale")
	public Long getMaxScale() {
		return maxScale;
	}
	public void setMaxScale(Long maxScale) {
		this.maxScale = maxScale;
	}

	
	
}

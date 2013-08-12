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
@Table(name = "suggestive_range")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SuggestiveRange extends BaseModel implements Serializable{

	private static final long serialVersionUID = -1153751405063419593L;
    private Long suggestiveRangeId;
    private String name;
    private String range;
    private Double minValue;
    private Double maxValue;
    private String color;
    
    public SuggestiveRange(){}
    
    public SuggestiveRange(String name,String range,Double minValue,Double maxValue,String color){
    
    	this.name = name;
    	this.range = range;
    	this.minValue = minValue;
    	this.maxValue = maxValue;
    	this.color = color;
   
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "suggestive_range_id",unique = true, nullable = false)
	public Long getSuggestiveRangeId() {
		return suggestiveRangeId;
	}

	public void setSuggestiveRangeId(Long suggestiveRangeId) {
		this.suggestiveRangeId = suggestiveRangeId;
	}
    
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	@Column(name = "range")
	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}
  
	@Column(name = "min_value")
	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
    
	@Column(name = "max_value")
	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	
	@Column(name = "color")	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
    

    
	
}

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
@Table(name="special_page_description")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpecialPageDescription implements Serializable{
	
	private Long specialPageDescriptionId; 
	private Long specialPageId; 
	private String description;
	private Long orderNo;

	/* default constructor*/
	
	public SpecialPageDescription(){
	}
	public SpecialPageDescription(Long specialPageId,String description,Long orderNo){
		
		this.description = description;
		this.specialPageId = specialPageId;
		this.orderNo = orderNo;
	
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="special_page_description_id ", unique=true, nullable=false)
	public Long getSpecialPageDescriptionId() {
		return specialPageDescriptionId;
	}
	public void setSpecialPageDescriptionId(Long specialPageDescriptionId) {
		this.specialPageDescriptionId = specialPageDescriptionId;
	}
	
	@Column(name="special_page_id")
	public Long getSpecialPageId() {
		return specialPageId;
	}
	public void setSpecialPageId(Long specialPageId) {
		this.specialPageId = specialPageId;
	}
	
	@Column(name="description",length=1000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
}

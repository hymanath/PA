package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="special_page_description")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpecialPageDescription implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long specialPageDescriptionId; 
	private SpecialPage specialPage; 
	private String description;
	private Long orderNo;

	/* default constructor*/
	
	public SpecialPageDescription(){
	}
	public SpecialPageDescription(SpecialPage specialPage,String description,Long orderNo){
		
		this.description = description;
		this.specialPage = specialPage;
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
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "special_page_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public SpecialPage getSpecialPage() {
		return specialPage;
	}
	public void setSpecialPage(SpecialPage specialPage) {
		this.specialPage = specialPage;
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

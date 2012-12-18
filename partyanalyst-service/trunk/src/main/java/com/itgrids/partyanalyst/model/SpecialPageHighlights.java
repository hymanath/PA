package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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


@Entity
@Table(name = "special_page_highlights")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpecialPageHighlights implements Serializable{
	
	private Long specialPageHighlightsId;
	
	private SpecialPage specialPage;
	
	private String description;
	
	private String orderNo;
	
	
	//Default Constructor
	public SpecialPageHighlights()
	{
		
	}
	
	//Full Constructor
	
	public SpecialPageHighlights(Long specialPageHighlightsId,SpecialPage specialPage,String description,String orderNo)
	{
		this.specialPageHighlightsId =specialPageHighlightsId;
		this.specialPage=specialPage;
		this.description = description;
		this.orderNo =orderNo;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "special_page_highlights_id", unique = true, nullable = false)
	public Long getSpecialPageHighlightsId() {
		return specialPageHighlightsId;
	}

	public void setSpecialPageHighlightsId(Long specialPageHighlightsId) {
		this.specialPageHighlightsId = specialPageHighlightsId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "special_page_id")
	public SpecialPage getSpecialPage() {
		return specialPage;
	}

	public void setSpecialPage(SpecialPage specialPage) {
		this.specialPage = specialPage;
	}
	@Column(name="description",length =2000 )
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="order_no")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	

}

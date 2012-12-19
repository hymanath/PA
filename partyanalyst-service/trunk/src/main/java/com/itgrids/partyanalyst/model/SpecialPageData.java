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
@Table(name="special_page_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpecialPageData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long specialPageDataId;
	private String specialPageData;
	private SpecialPage specialPage;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "special_page_data_id", unique = true, nullable = false)
	public Long getSpecialPageDataId() {
		return specialPageDataId;
	}
	public void setSpecialPageDataId(Long specialPageDataId) {
		this.specialPageDataId = specialPageDataId;
	}
	
	@Column(name="special_page_data" , length = 10000)
	public String getSpecialPageData() {
		return specialPageData;
	}
	public void setSpecialPageData(String specialPageData) {
		this.specialPageData = specialPageData;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "special_page_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)		
	public SpecialPage getSpecialPage() {
		return specialPage;
	}
	public void setSpecialPage(SpecialPage specialPage) {
		this.specialPage = specialPage;
	}
	
	

}

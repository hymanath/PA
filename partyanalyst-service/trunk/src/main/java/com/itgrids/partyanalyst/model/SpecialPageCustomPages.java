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
/**
 * special_page_custom_pages entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name = "special_page_custom_pages")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpecialPageCustomPages implements Serializable{

	private static final long serialVersionUID = 7156545203385267265L;
	
	private Long specialPageCustomPagesId;
	private SpecialPage specialPage;
	private CustomPage customPage;
	
	public SpecialPageCustomPages()
	{}
	
	public SpecialPageCustomPages(SpecialPage specialPage,CustomPage customPage)
	{
		this.specialPage = specialPage;
		this.customPage = customPage;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "special_page_custom_pages_id", unique = true, nullable = false)
	public Long getSpecialPageCustomPagesId() {
		return specialPageCustomPagesId;
	}

	public void setSpecialPageCustomPagesId(Long specialPageCustomPagesId) {
		this.specialPageCustomPagesId = specialPageCustomPagesId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="special_page_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SpecialPage getSpecialPage() {
		return specialPage;
	}

	public void setSpecialPage(SpecialPage specialPage) {
		this.specialPage = specialPage;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="custom_page_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CustomPage getCustomPage() {
		return customPage;
	}

	public void setCustomPage(CustomPage customPage) {
		this.customPage = customPage;
	}
	
}

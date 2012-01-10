package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
/**
 * custom_page entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name = "custom_page")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomPage implements Serializable{

	private static final long serialVersionUID = 6640138368852264918L;
	
	private Long customPageId;
	private String name;
	private CustomPageType customPageType;
	private Set<SpecialPageCustomPages> specialPageCustomPages = new HashSet<SpecialPageCustomPages>(0);
	
	public CustomPage()
	{}
	
	public CustomPage(String name,CustomPageType customPageType)
	{
		this.name = name;
		this.customPageType = customPageType;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "custom_page_id", unique = true, nullable = false)
	public Long getCustomPageId() {
		return customPageId;
	}

	public void setCustomPageId(Long customPageId) {
		this.customPageId = customPageId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="custom_page_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CustomPageType getCustomPageType() {
		return customPageType;
	}

	public void setCustomPageType(CustomPageType customPageType) {
		this.customPageType = customPageType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customPage")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<SpecialPageCustomPages> getSpecialPageCustomPages() {
		return specialPageCustomPages;
	}

	public void setSpecialPageCustomPages(
			Set<SpecialPageCustomPages> specialPageCustomPages) {
		this.specialPageCustomPages = specialPageCustomPages;
	}
	
	

}

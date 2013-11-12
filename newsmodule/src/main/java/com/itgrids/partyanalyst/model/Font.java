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
@Table(name = "font")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Font  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8426774599804412578L;
	private Integer fontId;
	private String name;
	private String path;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "font_id", unique = true, nullable = false)
	public Integer getFontId() {
		return fontId;
	}
	
	public void setFontId(Integer fontId) {
		this.fontId = fontId;
	}
	
	@Column(name = "name", length = 45)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "path", length = 100)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
}

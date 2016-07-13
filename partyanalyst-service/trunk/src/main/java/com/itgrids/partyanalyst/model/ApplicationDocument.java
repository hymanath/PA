package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="application_document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApplicationDocument extends BaseModel implements Serializable{
	
	private Long applicationDocumentId;
	private String tdpCadreId;
	private String filePath;
	
	private TdpCadre tdpCadre;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "application_document_id", unique = true, nullable = false)
	public Long getApplicationDocumentId() {
		return applicationDocumentId;
	}

	public void setApplicationDocumentId(Long applicationDocumentId) {
		this.applicationDocumentId = applicationDocumentId;
	}
	@Column(name = "tdp_cadre_id")
	public String getTdpCadreId() {
		return tdpCadreId;
	}

	public void setTdpCadreId(String tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name = "file_path")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}

	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	
	
}

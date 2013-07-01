package com.itgrids.electoralconnect.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name="file_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileType extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2795726017371613156L;
	private Long fileTypeId;
	private String type;
	
	public FileType(String type){
		this.type=type;
	}
	public FileType(){}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="file_type_id", unique=true, nullable=false)
	public Long getFileTypeId() {
		return fileTypeId;
	}
	public void setFileTypeId(Long fileTypeId) {
		this.fileTypeId = fileTypeId;
	}
	
	@Column(name="name")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}

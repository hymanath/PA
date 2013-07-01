package com.itgrids.electoralconnect.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

import com.itgrids.partyanalyst.model.Announcement;


@Entity
@Table(name="announcement_files")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnnouncementType extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2352065893110547737L;
	private Long announcementTypeId;
	private String name;
	
	public AnnouncementType(String name){
		this.name=name;
	}
	public AnnouncementType(){}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="announcement_type_id", unique=true, nullable=false)
	public Long getAnnouncementTypeId() {
		return announcementTypeId;
	}
	public void setAnnouncementTypeId(Long announcementTypeId) {
		this.announcementTypeId = announcementTypeId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

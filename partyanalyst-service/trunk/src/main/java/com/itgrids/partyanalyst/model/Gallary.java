package com.itgrids.partyanalyst.model;

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
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


/**
 * gallary entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="gallary")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Gallary implements Serializable{

	private static final long serialVersionUID = -5272608781710793322L;
	private Long gallaryId;
	private String name;
	private String description;
	private Date createdDate;
	private Date updateddate;
	private ContentType contentType;
	private String isDelete;
	private String isPrivate;
	private Candidate candidate;
	private Set<FileGallary> fileGallary = new HashSet<FileGallary>(0);
	private Set<PartyGallery> partyGallery = new HashSet<PartyGallery>(0);
	/* default constructor*/
	
	public Gallary(){
	}
	
	/* full constructor */
	
	public Gallary(String name,String description,Date createdDate,
			Date updateddate,ContentType contentType,String isDelete,String isPrivate){
		this.name = name;
		this.description = description;
		this.createdDate = createdDate;
		this.updateddate = updateddate;
		this.contentType = contentType;
		this.isDelete = isDelete;
		this.isPrivate = isPrivate;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="gallary_id", unique=true, nullable=false)
	public Long getGallaryId() {
		return gallaryId;
	}

	public void setGallaryId(Long gallaryId) {
		this.gallaryId = gallaryId;
	}

	@Column(name="name",length=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="description",length=300)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "created_date", length = 10)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updated_date", length = 10)
	public Date getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

	@Column(name = "is_delete", length = 10)
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "is_private", length = 10)
	public String getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gallary")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<FileGallary> getFileGallary() {
		return fileGallary;
	}

	public void setFileGallary(Set<FileGallary> fileGallary) {
		this.fileGallary = fileGallary;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="content_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gallery")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartyGallery> getPartyGallery() {
		return partyGallery;
	}

	public void setPartyGallery(Set<PartyGallery> partyGallery) {
		this.partyGallery = partyGallery;
	}
	

}

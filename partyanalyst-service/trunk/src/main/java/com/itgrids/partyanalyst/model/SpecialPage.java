package com.itgrids.partyanalyst.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "special_page")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpecialPage extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = -7068771289339646068L;
	
	private Long specialPageId;
	private String name;
	private String title;
	private String heading;
	private String profileImgPath;
	private Date createdDate;
	private Date updateddate;
	private String isDelete;
	private Set<SpecialPageDescription> specialPageDescriptions = new HashSet<SpecialPageDescription>(0);
	private Set<SpecialPageGallery> specialPageGalleries = new HashSet<SpecialPageGallery>(0);
	private Set<SpecialPageUpdatesEmail> specialPageUpdatesEmails = new HashSet<SpecialPageUpdatesEmail>(0);
	private Set<SpecialPageCustomPages> specialPageCustomPages = new HashSet<SpecialPageCustomPages>(0);
	private Set<SpecialPageMetaInfo> specialPageMetaInfo = new HashSet<SpecialPageMetaInfo>(0);
	
	public SpecialPage()
	{}
	
	public SpecialPage(String title,String heading,String profileImgPath,
			Date createdDate,Date updateddate,String isDelete)
	{
		this.title = title;
		this.heading = heading;
		this.profileImgPath = profileImgPath;
		this.createdDate = createdDate;
		this.updateddate = updateddate;
		this.isDelete = isDelete;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="special_page_id", unique=true, nullable=false)
	public Long getSpecialPageId() {
		return specialPageId;
	}

	public void setSpecialPageId(Long specialPageId) {
		this.specialPageId = specialPageId;
	}

	@Column(name="title",length=500)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="heading",length=300)
	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	@Column(name="profile_img_path",length=200)
	public String getProfileImgPath() {
		return profileImgPath;
	}

	public void setProfileImgPath(String profileImgPath) {
		this.profileImgPath = profileImgPath;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specialPage")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<SpecialPageDescription> getSpecialPageDescriptions() {
		return specialPageDescriptions;
	}

	public void setSpecialPageDescriptions(
			Set<SpecialPageDescription> specialPageDescriptions) {
		this.specialPageDescriptions = specialPageDescriptions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specialPage")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<SpecialPageGallery> getSpecialPageGalleries() {
		return specialPageGalleries;
	}

	public void setSpecialPageGalleries(Set<SpecialPageGallery> specialPageGalleries) {
		this.specialPageGalleries = specialPageGalleries;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specialPage")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<SpecialPageUpdatesEmail> getSpecialPageUpdatesEmails() {
		return specialPageUpdatesEmails;
	}

	public void setSpecialPageUpdatesEmails(
			Set<SpecialPageUpdatesEmail> specialPageUpdatesEmails) {
		this.specialPageUpdatesEmails = specialPageUpdatesEmails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specialPage")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<SpecialPageCustomPages> getSpecialPageCustomPages() {
		return specialPageCustomPages;
	}

	public void setSpecialPageCustomPages(
			Set<SpecialPageCustomPages> specialPageCustomPages) {
		this.specialPageCustomPages = specialPageCustomPages;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specialPage")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<SpecialPageMetaInfo> getSpecialPageMetaInfo() {
		return specialPageMetaInfo;
	}

	public void setSpecialPageMetaInfo(Set<SpecialPageMetaInfo> specialPageMetaInfo) {
		this.specialPageMetaInfo = specialPageMetaInfo;
	}
	
}

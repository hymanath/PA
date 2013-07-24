package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "constituency_hierarchy_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ConstituencyHierarchyInfo extends BaseModel{
	
	private Long constituencyHierarchyInfoId;
	private Long constituencyId;
	private Long reportLevelId;
	private Long reportLevelValue;
	private Long mandals;
	private Long municipalities;
	private Long panchayats;
    private Long booths;
    private Long hamlets;
    private Long wards;
    private Long publicationDateId;
    private Long userId;
    
    public ConstituencyHierarchyInfo(){}
    
    public ConstituencyHierarchyInfo(Long constituencyId,Long reportLevelId,Long reportLevelValue,Long mandals,Long municipalities
    		,Long panchayats,Long booths,Long hamlets,Long wards,Long publicationDateId,Long userId){
    	
    	this.constituencyId = constituencyId;
    	this.publicationDateId = publicationDateId;
    	this.reportLevelId = reportLevelId;
    	this.reportLevelValue = reportLevelValue;
    	this.mandals = mandals;
    	this.panchayats = panchayats;
    	this.booths = booths;
    	this.hamlets = hamlets;
    	this.wards = wards;
    	this.userId = userId;
    	
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "constituency_hierarchy_info_id",unique = true,nullable = false)
	public Long getConstituencyHierarchyInfoId() {
		return constituencyHierarchyInfoId;
	}
	public void setConstituencyHierarchyInfoId(Long constituencyHierarchyInfoId) {
		this.constituencyHierarchyInfoId = constituencyHierarchyInfoId;
	}
	
	@Column(name ="constituency_id",length=11)
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	@Column(name = "report_level_id", length = 11)
	public Long getReportLevelId() {
		return reportLevelId;
	}
	public void setReportLevelId(Long reportLevelId) {
		this.reportLevelId = reportLevelId;
	}
	
	@Column(name = "report_level_value",length = 11)
	public Long getReportLevelValue() {
		return reportLevelValue;
	}
	public void setReportLevelValue(Long reportLevelValue) {
		this.reportLevelValue = reportLevelValue;
	}
	
	@Column(name = "mandals", length = 11)
	public Long getMandals() {
		return mandals;
	}
	public void setMandals(Long mandals) {
		this.mandals = mandals;
	}
	
	@Column(name = "municipalities", length = 11)
	public Long getMunicipalities() {
		return municipalities;
	}
	public void setMunicipalities(Long municipalities) {
		this.municipalities = municipalities;
	}
	
	@Column(name = "panchayats", length = 11)
	public Long getPanchayats() {
		return panchayats;
	}
	public void setPanchayats(Long panchayats) {
		this.panchayats = panchayats;
	}
	@Column(name = "booths" ,length = 11) 
	public Long getBooths() {
		return booths;
	}
	public void setBooths(Long booths) {
		this.booths = booths;
	}
	
	@Column(name = "hamlets", length = 11)
	public Long getHamlets() {
		return hamlets;
	}
	public void setHamlets(Long hamlets) {
		this.hamlets = hamlets;
	}
	
	@Column(name = "wards", length = 11)
	public Long getWards() {
		return wards;
	}
	public void setWards(Long wards) {
		this.wards = wards;
	}
	
	@Column(name = "publication_date_id", length = 11)
  	public Long getPublicationDateId() {
		return publicationDateId;
	}

	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}

	@Column(name = "user_id",length = 11)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
    
	
}

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
@Table(name = "booth_village")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothVillage extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5280341898078700621L;

	// Fields
	
	private Long boothVillageId;
	private String stCode;
	private Long acNo;
	private String acName;
	private Long partNo;
	private Long sectionNo;
	private Long startslNo;
	private String endslNo;
	private String areaIde;
	private String localityIde;
	
	
	//Default constructr
	public BoothVillage(){
		
	}
	
	//primary key constructr
	public BoothVillage(Long boothVillageId){
		this.boothVillageId = boothVillageId;
	}
	
	//Full constructr
	public BoothVillage(String stCode,Long acNo,String acName,Long partNo,Long sectionNo,Long startslNo,String endslNo,String areaIde,String localityIde){
		this.stCode = stCode;
		this.acNo = acNo;
		this.acName = acName;
		this.partNo = partNo;
		this.sectionNo = sectionNo;
		this.startslNo = startslNo;
		this.endslNo = endslNo;
		this.areaIde = areaIde;
		this.localityIde = localityIde;		
	}

	
	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booth_village_id", unique = true, nullable = false)
	public Long getBoothVillageId() {
		return boothVillageId;
	}

	public void setBoothVillageId(Long boothVillageId) {
		this.boothVillageId = boothVillageId;
	}

	@Column(name = "st_code", length = 50)
	public String getStCode() {
		return stCode;
	}

	public void setStCode(String stCode) {
		this.stCode = stCode;
	}

	
	
	@Column(name = "ac_no", length = 50)
	public Long getAcNo() {
		return acNo;
	}

	public void setAcNo(Long acNo) {
		this.acNo = acNo;
	}
	
	

	@Column(name = "ac_name", length = 300)
	public String getAcName() {
		return acName;
	}

	public void setAcName(String acName) {
		this.acName = acName;
	}

	
	@Column(name = "part_no", length = 50)
	public Long getPartNo() {
		return partNo;
	}

	public void setPartNo(Long partNo) {
		this.partNo = partNo;
	}

	
	@Column(name = "section_no", length = 50)
	public Long getSectionNo() {
		return sectionNo;
	}

	public void setSectionNo(Long sectionNo) {
		this.sectionNo = sectionNo;
	}

	
	@Column(name = "startsl_no", length = 50)
	public Long getStartslNo() {
		return startslNo;
	}

	public void setStartslNo(Long startslNo) {
		this.startslNo = startslNo;
	}

	
	@Column(name = "endsl_no", length = 300)
	public String getEndslNo() {
		return endslNo;
	}

	public void setEndslNo(String endslNo) {
		this.endslNo = endslNo;
	}

	
	@Column(name = "area_ide", length = 300)
	public String getAreaIde() {
		return areaIde;
	}

	public void setAreaIde(String areaIde) {
		this.areaIde = areaIde;
	}

	
	@Column(name = "localityide", length = 300)
	public String getLocalityIde() {
		return localityIde;
	}

	public void setLocalityIde(String localityIde) {
		this.localityIde = localityIde;
	}
	
}

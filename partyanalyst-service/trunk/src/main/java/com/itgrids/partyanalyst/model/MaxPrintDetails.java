package com.itgrids.partyanalyst.model;

import java.util.Date;

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
@Table(name = "max_print_details")//    max_print_details20
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MaxPrintDetails extends BaseModel implements java.io.Serializable {

	private Long maxPrintDetailsId;
	private Long tdpCadreCardPrintId;
	private Long tdpCadreId;
	private String memberShipId;
	private String cadreName;
	private String relativeName;
	private String imagePath;
	private String mobileNo;
	
	private String locationType;
	private Long  districtId;
	private String districtName;
	private Long constituencyId;
	private String constituencyName;
	private Long mandalId;	
	private String mandalName;
	private Long panchayatId;
	private String panchayatName;
	private Long muncipalityId;
	private String muncipalityName;
	private Long wardId;
	private String wardName;
	private Long boothId;
	private String boothName;
	private Long serialNoInBooth;
	private String areaCovered;
	private String houseNo;
	
	private Date pushTime;
	private String constituencyType;
	private Date printTime;
	private String serialNumber;
	private String printStatus;
	private String printCode;
	private String printDesc;
	private String printerSerialNumber;
	private String boxNo;
	private String outerBoxNo;
	private String pcNo;
	
	private District district;
	private Constituency constituency;
	private TdpCadreCardPrint tdpCadreCardPrint;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "max_print_details_id", unique = true, nullable = false)
	public Long getMaxPrintDetailsId() {
		return maxPrintDetailsId;
	}
	public void setMaxPrintDetailsId(Long maxPrintDetailsId) {
		this.maxPrintDetailsId = maxPrintDetailsId;
	}
	
	@Column(name = "tdp_cadre_card_print_id")
	public Long getTdpCadreCardPrintId() {
		return tdpCadreCardPrintId;
	}
	public void setTdpCadreCardPrintId(Long tdpCadreCardPrintId) {
		this.tdpCadreCardPrintId = tdpCadreCardPrintId;
	}
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name="membership_id")
	public String getMemberShipId() {
		return memberShipId;
	}
	public void setMemberShipId(String memberShipId) {
		this.memberShipId = memberShipId;
	}
	@Column(name="cadre_name")
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	
	@Column(name="relative_name")
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	
	@Column(name="image_path")
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name="location_type")
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
	@Column(name="district_id")
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	@Column(name="district_name")
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	@Column(name="constituency_name")
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	@Column(name="mandal_id")
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	
	@Column(name="mandal_name")
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	
	@Column(name="panchayat_id")
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	
	@Column(name="panchayat_name")
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	
	@Column(name="muncipality_id")
	public Long getMuncipalityId() {
		return muncipalityId;
	}
	public void setMuncipalityId(Long muncipalityId) {
		this.muncipalityId = muncipalityId;
	}
	
	@Column(name="muncipality_name")
	public String getMuncipalityName() {
		return muncipalityName;
	}
	public void setMuncipalityName(String muncipalityName) {
		this.muncipalityName = muncipalityName;
	}
	
	@Column(name="ward_id")
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	
	@Column(name="ward_name")
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	
	@Column(name="booth_id")
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	
	@Column(name="booth_name")
	public String getBoothName() {
		return boothName;
	}
	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}
	
	@Column(name="serial_no_in_booth")
	public Long getSerialNoInBooth() {
		return serialNoInBooth;
	}
	public void setSerialNoInBooth(Long serialNoInBooth) {
		this.serialNoInBooth = serialNoInBooth;
	}
	
	@Column(name="area_covered")
	public String getAreaCovered() {
		return areaCovered;
	}
	public void setAreaCovered(String areaCovered) {
		this.areaCovered = areaCovered;
	}
	
	@Column(name="house_no")
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	
	@Column(name="push_time")
	public Date getPushTime() {
		return pushTime;
	}
	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
	
	
	@Column(name="constituency_type")
	public String getConstituencyType() {
		return constituencyType;
	}
	public void setConstituencyType(String constituencyType) {
		this.constituencyType = constituencyType;
	}
	
	@Column(name="print_time")
	public Date getPrintTime() {
		return printTime;
	}
	public void setPrintTime(Date printTime) {
		this.printTime = printTime;
	}
	
	@Column(name="serial_number")
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@Column(name="print_status")
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	
	@Column(name="print_code")
	public String getPrintCode() {
		return printCode;
	}
	public void setPrintCode(String printCode) {
		this.printCode = printCode;
	}
	
	@Column(name="print_desc")
	public String getPrintDesc() {
		return printDesc;
	}
	public void setPrintDesc(String printDesc) {
		this.printDesc = printDesc;
	}
	
	@Column(name="printer_serial_number")
	public String getPrinterSerialNumber() {
		return printerSerialNumber;
	}
	public void setPrinterSerialNumber(String printerSerialNumber) {
		this.printerSerialNumber = printerSerialNumber;
	}
	
	@Column(name="box_no")
	public String getBoxNo() {
		return boxNo;
	}
	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}
	@Column(name="pc_no")
	public String getPcNo() {
		return pcNo;
	}
	public void setPcNo(String pcNo) {
		this.pcNo = pcNo;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "constituency_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "district_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_card_print_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadreCardPrint getTdpCadreCardPrint() {
		return tdpCadreCardPrint;
	}
	public void setTdpCadreCardPrint(TdpCadreCardPrint tdpCadreCardPrint) {
		this.tdpCadreCardPrint = tdpCadreCardPrint;
	}
	
	@Column(name="outer_box_no")
	public String getOuterBoxNo() {
		return outerBoxNo;
	}
	public void setOuterBoxNo(String outerBoxNo) {
		this.outerBoxNo = outerBoxNo;
	}
}

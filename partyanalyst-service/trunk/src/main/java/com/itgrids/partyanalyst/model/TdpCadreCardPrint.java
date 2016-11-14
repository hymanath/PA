package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
@Table(name = "tdp_cadre_card_print")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreCardPrint extends BaseModel implements Serializable{
	
	private Long tdpCadreCardPrintId;
	private Long tdpCadreId;
	private String memberShipId;
	private String cadreName;
	private String imagePath;
	
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
	
	private String areaCovered;
	private String houseNo;
	
	private Date pushTime;
	private Long cardPrintVendorId;
	private String constituencyType;
	private Date printTime;
	private String serialNumber;
	private String printStatus;
	private String printCode;
	private String printDesc;
	private String printerSerialNumber;
	private String boxNo;
	private String pcNo;
	
	
	private TdpCadre tdpCadre;
	private CardPrintVendor cardPrintVendor;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_card_print_id", unique = true, nullable = false)
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
	
	@Column(name="image_path")
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	
	@Column(name="card_print_vendor_id")
	public Long getCardPrintVendorId() {
		return cardPrintVendorId;
	}
	public void setCardPrintVendorId(Long cardPrintVendorId) {
		this.cardPrintVendorId = cardPrintVendorId;
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
	@JoinColumn(name = "tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "card_print_vendor_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CardPrintVendor getCardPrintVendor() {
		return cardPrintVendor;
	}
	public void setCardPrintVendor(CardPrintVendor cardPrintVendor) {
		this.cardPrintVendor = cardPrintVendor;
	}
	
	
}

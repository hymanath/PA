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
@Table(name = "zebra_print_online_ship")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZebraPrintOnlineShip extends BaseModel implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5991972740188764985L;
	private Long zebraPrintDetailsId;
	private String voterName;
	private String panchayatName;
	private String mandalName;
	private String muncipalityName;
	private String constituencyName;
	private String districtName;
	private TdpCadre tdpCadre;
	private Long tdpCadreId;
	private String memberShipMember;
	private String voterIdCardNumber;
	private String image;
	private String constituencyType;
	private String refNumber;
	private String userName;
	private String serialNumber;
	private Date insertedTime;
	private Date updateTime;
	private String printStatus;
	private String printerSerialNumber;
	private String reserveStatus;
	private String pcNo;
	private String printerIndex;	
	private Date downloadTime;	
	private String updateStatus;
	private String errorStatus;
	private String photoType;
	private String voterImagePath;
	private String mobileNo;
	private String jobCode;
	private String partNo;
	private String areaCovered;
	private String houseNo;
	private Long deliveryMode;
	private String shipAddress;
	private String permanentAddress;
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "zebra_print_details_id", nullable = false, unique = true)
	public Long getZebraPrintDetailsId() {
		return zebraPrintDetailsId;
	}
	public void setZebraPrintDetailsId(Long zebraPrintDetailsId) {
		this.zebraPrintDetailsId = zebraPrintDetailsId;
	}
	
	@Column(name="voter_name")
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	
	@Column(name="panchayat_name")
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	
	@Column(name="mandal_name")
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	
	
	@Column(name="muncipality_name")
	public String getMuncipalityName() {
		return muncipalityName;
	}
	public void setMuncipalityName(String muncipalityName) {
		this.muncipalityName = muncipalityName;
	}
	
	
	@Column(name="constituency_name")
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	@Column(name="district_name")
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name="member_ship_member")
	public String getMemberShipMember() {
		return memberShipMember;
	}
	public void setMemberShipMember(String memberShipMember) {
		this.memberShipMember = memberShipMember;
	}
	
	@Column(name="voter_id_card_number")
	public String getVoterIdCardNumber() {
		return voterIdCardNumber;
	}
	public void setVoterIdCardNumber(String voterIdCardNumber) {
		this.voterIdCardNumber = voterIdCardNumber;
	}
	
	@Column(name="image")
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@Column(name="constituency_type")
	public String getConstituencyType() {
		return constituencyType;
	}
	public void setConstituencyType(String constituencyType) {
		this.constituencyType = constituencyType;
	}
	
	@Column(name="ref_number")
	public String getRefNumber() {
		return refNumber;
	}
	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}
	
	
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	@Column(name="serial_number")
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="update_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	@Column(name="print_status")
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	
	@Column(name="printer_serial_number")
	public String getPrinterSerialNumber() {
		return printerSerialNumber;
	}
	public void setPrinterSerialNumber(String printerSerialNumber) {
		this.printerSerialNumber = printerSerialNumber;
	}
	
	
	@Column(name="reserve_status")
	public String getReserveStatus() {
		return reserveStatus;
	}
	public void setReserveStatus(String reserveStatus) {
		this.reserveStatus = reserveStatus;
	}
	
	
	@Column(name="pc_no")
	public String getPcNo() {
		return pcNo;
	}
	public void setPcNo(String pcNo) {
		this.pcNo = pcNo;
	}
	
	@Column(name="printer_index")
	public String getPrinterIndex() {
		return printerIndex;
	}
	public void setPrinterIndex(String printerIndex) {
		this.printerIndex = printerIndex;
	}
	
	
	@Column(name="download_time")
	public Date getDownloadTime() {
		return downloadTime;
	}
	public void setDownloadTime(Date downloadTime) {
		this.downloadTime = downloadTime;
	}
	
	
	@Column(name="update_status")
	public String getUpdateStatus() {
		return updateStatus;
	}
	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}
	
	
	@Column(name="error_Status")
	public String getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
	
	@Column(name="photo_type")
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	
	
	@Column(name="voter_image_path")
	public String getVoterImagePath() {
		return voterImagePath;
	}
	public void setVoterImagePath(String voterImagePath) {
		this.voterImagePath = voterImagePath;
	}
	
	
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	@Column(name="job_code")
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	
	@Column(name="part_no")
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
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
	
	@Column(name="delivery_mode")
	public Long getDeliveryMode() {
		return deliveryMode;
	}
	public void setDeliveryMode(Long deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	
	@Column(name="ship_address")
	public String getShipAddress() {
		return shipAddress;
	}
	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}
	
	
	@Column(name="permanent_address")
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	
	
	
}

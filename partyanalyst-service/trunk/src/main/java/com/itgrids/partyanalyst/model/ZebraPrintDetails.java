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

/**
 * 
 * @author Srishailam Pittala
 *
 */

@Entity
@Table(name = "zebra_print_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZebraPrintDetails extends BaseModel implements java.io.Serializable {

	private Long zebraPrintDetailsId;
	private String voterName;
	private String panchayatName;
	private String mandalName;
	private String muncipalityName;
	private String consiteuncyName;
	private String districtName;
	private TdpCadre tdpCadre;
	private Long tdpCadreId;
	private String memberShipNumber;
	private String voterCardNo;
	private String image;
	private String constituencyType;
	private String refNo;
	private String userName;
	private String serialNo;
	private Date insertedTime;
	private Date updatedTime;
	private String printStatus;
	private String printSerialNo;
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
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "zebra_print_details_id", unique = true, nullable = false)
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
	public String getConsiteuncyName() {
		return consiteuncyName;
	}
	public void setConsiteuncyName(String consiteuncyName) {
		this.consiteuncyName = consiteuncyName;
	}
	
	@Column(name="district_name")
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
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
	public String getMemberShipNumber() {
		return memberShipNumber;
	}
	public void setMemberShipNumber(String memberShipNumber) {
		this.memberShipNumber = memberShipNumber;
	}
	
	@Column(name="voter_id_card_number")
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
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
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="serial_number")
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="update_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name="print_status")
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	
	@Column(name="printer_serial_number")
	public String getPrintSerialNo() {
		return printSerialNo;
	}
	public void setPrintSerialNo(String printSerialNo) {
		this.printSerialNo = printSerialNo;
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
	
	
}

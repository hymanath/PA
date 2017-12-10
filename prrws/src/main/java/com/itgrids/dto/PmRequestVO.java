package com.itgrids.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author sanjeev
 *
 */
public class PmRequestVO {
	
	private Long representationId;
	private String name;
	private String mobileNO;
	private String email;
	private String voterCardNo;
	private String adharCardNo;
	private Long AadharCardNo;
	private Long tdpCadreId;
	private Long designationId;
	private String representationType;
	private String representationdate;
	private Long representeeDesignationId;
	private AddressVO addressVO;
	private List<PetitionsWorksVO> worksList = new ArrayList<PetitionsWorksVO>(0);
	private List<PmRequestVO> referList= new ArrayList<PmRequestVO>(0);
	private List<MultipartFile> fileList = new ArrayList<MultipartFile>(0);
	
	private Long refCandidateId;
	private String startDate;
	private String endDate;
	private Long userId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<PetitionsWorksVO> getWorksList() {
		return worksList;
	}
	public void setWorksList(List<PetitionsWorksVO> worksList) {
		this.worksList = worksList;
	}
	public Long getRepresentationId() {
		return representationId;
	}
	public void setRepresentationId(Long representationId) {
		this.representationId = representationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNO() {
		return mobileNO;
	}
	public void setMobileNO(String mobileNO) {
		this.mobileNO = mobileNO;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	
	public Long getAadharCardNo() {
		return AadharCardNo;
	}
	public void setAadharCardNo(Long aadharCardNo) {
		AadharCardNo = aadharCardNo;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public String getRepresentationType() {
		return representationType;
	}
	public void setRepresentationType(String representationType) {
		this.representationType = representationType;
	}
	public String getRepresentationdate() {
		return representationdate;
	}
	public void setRepresentationdate(String representationdate) {
		this.representationdate = representationdate;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public List<PmRequestVO> getReferList() {
		return referList;
	}
	public void setReferList(List<PmRequestVO> referList) {
		this.referList = referList;
	}
	public List<MultipartFile> getFileList() {
		return fileList;
	}
	public void setFileList(List<MultipartFile> fileList) {
		this.fileList = fileList;
	}
	public String getAdharCardNo() {
		return adharCardNo;
	}
	public void setAdharCardNo(String adharCardNo) {
		this.adharCardNo = adharCardNo;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public Long getRepresenteeDesignationId() {
		return representeeDesignationId;
	}
	public void setRepresenteeDesignationId(Long representeeDesignationId) {
		this.representeeDesignationId = representeeDesignationId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Long getRefCandidateId() {
		return refCandidateId;
	}
	public void setRefCandidateId(Long refCandidateId) {
		this.refCandidateId = refCandidateId;
	}
}

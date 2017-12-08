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
	private Long AadharNo;
	private Long designationId;
	private String representationType;
	private String representationdate;
	private AddressVO address;
	private List<PmRequestVO> referList= new ArrayList<PmRequestVO>();
	private List<MultipartFile> fileList = new ArrayList<MultipartFile>();
	
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
	public Long getAadharNo() {
		return AadharNo;
	}
	public void setAadharNo(Long aadharNo) {
		AadharNo = aadharNo;
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
	public AddressVO getAddress() {
		return address;
	}
	public void setAddress(AddressVO address) {
		this.address = address;
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

	

}

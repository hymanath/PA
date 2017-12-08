package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

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
	private List<File> fileList = new ArrayList<File>();
	
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
	public List<File> getFileList() {
		return fileList;
	}
	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}

	

}

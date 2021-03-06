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
	
	private Long existingPetitionId;
	private Long petitionTypeId;
	private Long id;
	private Long representeeId;
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
	private AddressVO addressVO = new AddressVO();
	private AddressVO candidateAddressVO = new AddressVO();
	private AddressVO candidateNativeAddressVO  = new AddressVO();
	private List<AddressVO> addressVOList = new ArrayList<AddressVO>(0);
	private List<PetitionsWorksVO> worksList = new ArrayList<PetitionsWorksVO>(0);
	private List<PmRequestVO> referList= new ArrayList<PmRequestVO>(0);
	private List<PmRequestVO> selfReferList= new ArrayList<PmRequestVO>(0);
	private List<MultipartFile> fileList = new ArrayList<MultipartFile>(0);
	private List<KeyValueVO> fileNamesList = new ArrayList<KeyValueVO>();
	private List<String> base64ImageList = new ArrayList<String>(0);
	private Long refCandidateId;
	private String startDate;
	private String endDate;
	private Long userId;
	
	private Long partyId;
	private String partyName;
	private String candidatePath;
	private String designation;
	private String endorsmentNo;
	private String endorsmentDate;
	private String repImagePath; 
	private String remarks;
	private String saveType;
	
	public List<AddressVO> getAddressVOList() {
		return addressVOList;
	}
	public void setAddressVOList(List<AddressVO> addressVOList) {
		this.addressVOList = addressVOList;
	}
	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<KeyValueVO> getFileNamesList() {
		return fileNamesList;
	}
	public void setFileNamesList(List<KeyValueVO> fileNamesList) {
		this.fileNamesList = fileNamesList;
	}
	public String getEndorsmentDate() {
		return endorsmentDate;
	}
	public void setEndorsmentDate(String endorsmentDate) {
		this.endorsmentDate = endorsmentDate;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
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
	
	public Long getRepresenteeId() {
		return representeeId;
	}
	public void setRepresenteeId(Long representeeId) {
		this.representeeId = representeeId;
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
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getCandidatePath() {
		return candidatePath;
	}
	public void setCandidatePath(String candidatePath) {
		this.candidatePath = candidatePath;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEndorsmentNo() {
		return endorsmentNo;
	}
	public void setEndorsmentNo(String endorsmentNo) {
		this.endorsmentNo = endorsmentNo;
	}
	public String getRepImagePath() {
		return repImagePath;
	}
	public void setRepImagePath(String repImagePath) {
		this.repImagePath = repImagePath;
	}
	public AddressVO getCandidateAddressVO() {
		return candidateAddressVO;
	}
	public void setCandidateAddressVO(AddressVO candidateAddressVO) {
		this.candidateAddressVO = candidateAddressVO;
	}
	public Long getExistingPetitionId() {
		return existingPetitionId;
	}
	public void setExistingPetitionId(Long existingPetitionId) {
		this.existingPetitionId = existingPetitionId;
	}
	public AddressVO getCandidateNativeAddressVO() {
		return candidateNativeAddressVO;
	}
	public void setCandidateNativeAddressVO(AddressVO candidateNativeAddressVO) {
		this.candidateNativeAddressVO = candidateNativeAddressVO;
	}
	public List<PmRequestVO> getSelfReferList() {
		return selfReferList;
	}
	public void setSelfReferList(List<PmRequestVO> selfReferList) {
		this.selfReferList = selfReferList;
	}
	public List<String> getBase64ImageList() {
		return base64ImageList;
	}
	public void setBase64ImageList(List<String> base64ImageList) {
		this.base64ImageList = base64ImageList;
	}
	public Long getPetitionTypeId() {
		return petitionTypeId;
	}
	public void setPetitionTypeId(Long petitionTypeId) {
		this.petitionTypeId = petitionTypeId;
	}
}

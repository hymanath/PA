package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BenefitVO {
	
	private Long benefitId;
	private String benefitName="";
	private String status="N";
	private Long amount=0l;
	private Long userWisetotalAmount=0l;
	private String name;
	private Long cadreId;
	private String cadreName=""; 
	private List<BenefitVO> cadreDetailsVO = new ArrayList<BenefitVO>(0);
	private List<BenefitVO> familyDetailsVO = new ArrayList<BenefitVO>(0);
	private Long schemeWiseTotalCount = 0l;
	private Long allSchemesAmount = 0l;
	private Long constituencyId;
	private String constituencyName;
	private Long locationId;
	private String locationName;
	private String locationtype;
	private Long sumOfAll=0l;
	private List<Long> locationValues;
	private String designationName;
	private Long designationId;
	
	
	public Long getBenefitId() {
		return benefitId;
	}
	public void setBenefitId(Long benefitId) {
		this.benefitId = benefitId;
	}
	public String getBenefitName() {
		return benefitName;
	}
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<BenefitVO> getCadreDetailsVO() {
		return cadreDetailsVO;
	}
	public void setCadreDetailsVO(List<BenefitVO> cadreDetailsVO) {
		this.cadreDetailsVO = cadreDetailsVO;
	}
	public List<BenefitVO> getFamilyDetailsVO() {
		return familyDetailsVO;
	}
	public void setFamilyDetailsVO(List<BenefitVO> familyDetailsVO) {
		this.familyDetailsVO = familyDetailsVO;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCadreId() {
		return cadreId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	public Long getUserWisetotalAmount() {
		return userWisetotalAmount;
	}
	public void setUserWisetotalAmount(Long userWisetotalAmount) {
		this.userWisetotalAmount = userWisetotalAmount;
	}
	public Long getSchemeWiseTotalCount() {
		return schemeWiseTotalCount;
	}
	public void setSchemeWiseTotalCount(Long schemeWiseTotalCount) {
		this.schemeWiseTotalCount = schemeWiseTotalCount;
	}
	public Long getAllSchemesAmount() {
		return allSchemesAmount;
	}
	public void setAllSchemesAmount(Long allSchemesAmount) {
		this.allSchemesAmount = allSchemesAmount;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationtype() {
		return locationtype;
	}
	public void setLocationtype(String locationtype) {
		this.locationtype = locationtype;
	}
	public Long getSumOfAll() {
		return sumOfAll;
	}
	public void setSumOfAll(Long sumOfAll) {
		this.sumOfAll = sumOfAll;
	}
	public List<Long> getLocationValues() {
		return locationValues;
	}
	public void setLocationValues(List<Long> locationValues) {
		this.locationValues = locationValues;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	  
}

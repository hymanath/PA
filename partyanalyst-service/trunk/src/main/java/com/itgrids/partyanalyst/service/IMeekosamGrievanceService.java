package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.MeekosamDynamicVO;
import com.itgrids.partyanalyst.dto.MeekosamGrievanceVO;
import com.itgrids.partyanalyst.dto.PetitionerDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IMeekosamGrievanceService {
	public String saveMeekosamPetitionerDetails(final PetitionerDetailsVO inputvo);
	public List<IdNameVO> getAllMandalsByDistrictID(Long districtId);
	public List<IdNameVO> getAllHamletByPanchayatID(Long panchayatId);
	public List<IdAndNameVO> getMeekosamOccupationList();
	public List<IdAndNameVO> getMeekosamCasteCategoryList();
	public List<IdAndNameVO> getMeekosamArgeeCategoryList();
	public List<IdAndNameVO> getMeekosamAnnualIncomeList();
	public List<KeyValueVO> getMeekosamIssueTypeListByDept(Long departmentId);
	public List<KeyValueVO> getMeekosamSubIssueTypeListForParentIssueType(Long parentIssueTypeId);
	public List<MeekosamDynamicVO> getAllDynamicFieldsAndDataForIsueType(Long issueTypeId);
	public List<PetitionerDetailsVO> searchPetitionerDetailsByVoterNoAadharNoMobileNo(String cardNo, String type);
	public ResultStatus saveMeekosamGrievance(final MeekosamGrievanceVO inputvo,final Long userId);
}

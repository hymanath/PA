package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.PetitionerDetailsVO;

public interface IMeekosamGrievanceService {
	public String saveMeekosamPetitionerDetails(final PetitionerDetailsVO inputvo);
	public List<IdNameVO> getAllMandalsByDistrictID(Long districtId);
	public List<IdNameVO> getAllHamletByPanchayatID(Long panchayatId);
	public List<IdAndNameVO> getMeekosamOccupationList();
	public List<IdAndNameVO> getMeekosamCasteCategoryList();
	public List<IdAndNameVO> getMeekosamArgeeCategoryList();
	public List<IdAndNameVO> getMeekosamAnnualIncomeList();
	public List<PetitionerDetailsVO> searchPetitionerDetailsByVoterNoAadharNoMobileNo(String cardNo, String type);
}

package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;
import com.itgrids.partyanalyst.dto.VoterInfoVO;

public interface ICadreRegistrationForOtherStatesService {
	public void tdpCadreSavingLogic(final AddressVO addressVO,final CadreRegistrationVO cadreRegistrationVO,final SurveyCadreResponceVO surveyCadreResponceVO,final String insertType, final boolean statusVar, String registrationType);
	public List<GenericVO> getTehsilByConstiteuncy(Long constituencyId);
	public List<GenericVO> getBoothsDetailsByTehsil(Long tehsilId);
	public List<VoterInfoVO> getSearchDetailsCadreRegistration(Long stateId,Long constituencyId, String candidateName, String voterCardId,
			String houseNo,Long tehsilId,Long boothId,Integer startIndex,Integer maxIndex);
	public List<VoterInfoVO> getCandidateInfoBySearchCriteria(String voterType, Long candidateId,Long constituencyId);
	public String checkVoterAlreadyRegisteredOrNot(Long voterId);
	public List<SelectOptionVO> getAllMandalsInADistrict(Long district);
	public Long getDistrictIdByConstituencyId(Long constituencyId);
	public List<SelectOptionVO> getAllDistrictsByStateId(Long stateId);
	public Long getStateByConstituencyId(Long constituencyId);
	
	public List<GenericVO> getBoothsByConstiteuncy(Long constituencyId);
	public List<SelectOptionVO> getCasteDetailsByCasteCategoryId(Long casteCategoryGroupId,Long stateId);
	public void tdpTempararyCadreSavingLogic(final AddressVO addressVO,final CadreRegistrationVO cadreRegistrationVO,final SurveyCadreResponceVO surveyCadreResponceVO,final String insertType, final boolean statusVar,final String registrationType);
	public List<IdNameVO> getConstituencyByState(Long stateId);
}

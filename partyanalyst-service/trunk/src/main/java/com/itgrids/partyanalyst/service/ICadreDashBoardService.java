package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.CadreRegisterInfo;

public interface ICadreDashBoardService {
	
	public List<CadreRegisterInfo> getWorkStartedConstituencyCount();

	public List<CadreRegisterInfo> getDashBoardBasicInfo();
	
	public  List<CadreRegisterInfo> getRecentlyRegisteredCadresInfo();
	
	public List<CadreRegisterInfo> getAssemblyWiseCompletedPercentage(Long assemblyId,Long stateId);
	
	public List<CadreRegisterInfo> getDistrictWiseCompletedPercentage(Long districtId,Long stateId);
	
	public CadreRegisterInfo getWorkingMembersInfo();
	
	public List<CadreRegisterInfo> getCandidateDataCollectionInfo(Date fromDate,Date toDate);
	
	public Map<Long,List<CadreRegisterInfo>> getConstituencyWiseAgeRangeCount(Long constituencyId);
	
	public Map<Long,List<CadreRegisterInfo>> getDistrictWiseAgeRangeCount(Long districtId);
	
	public Map<Long,List<CadreRegisterInfo>> getConstituencyWiseGenderCadreCount(Long constituencyId);
	
	public Map<Long,List<CadreRegisterInfo>> getDistrictWiseGenderCadreCount(Long districtId);
	
	public Map<Long,List<CadreRegisterInfo>> getConstituencyWiseCastCadreCount(Long constituencyId);
	
	public Map<Long,List<CadreRegisterInfo>> getDistrictWiseCastCadreCount(Long districtId);
		
	public List<CadreRegisterInfo> getAssemblyConstituencies(String type);
	
	public List<CadreRegisterInfo> getPanchayatsInConstituencies(Long constituencyId);
	
	public List<CadreRegisterInfo> getBoothsInConstituencies(Long constituencyId);
	
	public List<CadreRegisterInfo> getStateWiseRegistrationInfo(List<Long> stateIds);
	
	public List<CadreRegisterInfo> getLocationWiseRegistrationInfo(List<Long> ids,String type);
}

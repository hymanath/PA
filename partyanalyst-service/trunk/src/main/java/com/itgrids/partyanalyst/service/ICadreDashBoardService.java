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
		
	public List<CadreRegisterInfo> getDetailsForConstituency(Long constituencyId);
	
	public List<CadreRegisterInfo> getDetailsForDistricts(Long districtId);
		
	public List<CadreRegisterInfo> getAssemblyConstituencies(String type);
	
	public List<CadreRegisterInfo> getPanchayatsInConstituencies(Long constituencyId);
	
	public List<CadreRegisterInfo> getBoothsInConstituencies(Long constituencyId);
	
	public List<CadreRegisterInfo> getStateWiseRegistrationInfo(List<Long> stateIds,String fromDate,String toDate);
	
	public List<CadreRegisterInfo> getLocationWiseRegistrationInfo(List<Long> ids,String type,String fromDate,String toDate);
}

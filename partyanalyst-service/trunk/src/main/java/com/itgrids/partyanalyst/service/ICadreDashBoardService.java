package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.CadreRegisterInfo;

public interface ICadreDashBoardService {
	
	public List<CadreRegisterInfo> getWorkStartedConstituencyCount();

	public List<CadreRegisterInfo> getDashBoardBasicInfo();
	
	public  List<CadreRegisterInfo> getRecentlyRegisteredCadresInfo();
	
	public List<CadreRegisterInfo> getAssemblyWiseCompletedPercentage(Long assemblyId,Long stateId);
	
	public List<CadreRegisterInfo> getDistrictWiseCompletedPercentage(Long districtId,Long stateId);
	
	public CadreRegisterInfo getWorkingMembersInfo();
	
	public List<CadreRegisterInfo> getCandidateDataCollectionInfo(Date fromDate,Date toDate);
	
}

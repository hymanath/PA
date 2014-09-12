package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MobileNumbers;

public interface IMobileNumbersDAO extends GenericDao<MobileNumbers, Long>{
	
	public List<Long> getConstituencysByDistictID(Long districtId);
	
	public List<Object[]> getMobileNoforVoter(Long constituencyId);
	
	public List<Object[]> getUservoterDetailsByUserId(Long userId,List<Long> voterIds);
	
	public Integer updateMobileNo(String mobileNo,Long userVoterDetailsId);
	
	public List<Object[]> getMobileNumberDetailsByBoothId(Long boothId,Long constituencyId);
	public Set<String> getVotersMobilenos(Long scopeId,Long location,int maxIndex);
	public Set<String> getIvrMobilenosBasedOnPriority(Long scopeId,Long location,int maxIndex,String priority) ;
}

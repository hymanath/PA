package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MobileNumbers;

public interface IMobileNumbersDAO extends GenericDao<MobileNumbers, Long>{
	
	public List<Long> getConstituencysByDistictID(Long districtId);
	
	public List<Object[]> getMobileNoforVoter(Long constituencyId);
	
	public List<Object[]> getUservoterDetailsByUserId(Long userId,List<Long> voterIds);
	
	public Integer updateMobileNo(String mobileNo,Long userVoterDetailsId);
}

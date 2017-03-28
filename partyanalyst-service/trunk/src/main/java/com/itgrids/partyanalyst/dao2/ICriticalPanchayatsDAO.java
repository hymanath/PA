package com.itgrids.partyanalyst.dao;

import java.util.List;

import com.itgrids.partyanalyst.excel.booth.VoterVO;

public interface ICriticalPanchayatsDAO {
	public List<Object[]> getConstituenciesFromCriticalPanchayats();
	public List<Object[]> getCriticalPanchayatBoothHnos(VoterVO voterVo,Integer startIndex,Integer maxIndex);
}

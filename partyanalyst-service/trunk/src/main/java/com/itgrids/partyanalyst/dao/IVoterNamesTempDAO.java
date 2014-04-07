package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterNamesTemp;

public interface IVoterNamesTempDAO extends GenericDao<VoterNamesTemp, Long>{
	
	public List<VoterNamesTemp> getVotersByACNO(Integer startIndex,Integer maxIndex,Long constituencyId);
	

	public Long getVotersCountACNO(Long constituencyId);
	
	public List<Object[]> getConstituencies();
}

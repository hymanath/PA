package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ConstituencyTehsil;
import com.itgrids.partyanalyst.model.Tehsil;

public interface IConstituencyTehsilDAO extends GenericDao<ConstituencyTehsil, Long>{
	public List<Object[]> getTehsilDtlsStateWise(Long stateId,List<Long> mandalIds);
	public List<Long> getAllStateWiseTehsilIds(Long stateId);
	public List<Tehsil> getTehsilInfoOfConstuencyByTehsilName(Long constituencyId,String tehsilName);
}

package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.VoterModificationTemp;

public interface IVoterModificationTempDAO extends GenericDao<VoterModificationTemp,Long>{

	public List<Object[]> getVoterIDAndStatusFromVoterModificationTempByConstituencyId(Long constituencyId);
	
	public List<Object[]> getConstituenciesToBeMappedForVoterChanges();
}

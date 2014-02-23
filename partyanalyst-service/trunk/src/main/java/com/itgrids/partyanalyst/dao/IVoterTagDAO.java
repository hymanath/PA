package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.VoterTag;

public interface IVoterTagDAO extends GenericDao<VoterTag,Long>{
	
	public VoterTag getVoterTagByVoterIdAndUniqueCode(Long voterId,String uniqueCode);

}

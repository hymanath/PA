package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.VoterBasicInfo;

public interface IVoterBasicInfoDAO extends GenericDao<VoterBasicInfo,Long>{
	public Integer deleteVoterBasicInfoByConstituencyId(Long constituencyId);
	
	public List<VoterBasicInfo> getVoterBasicInfoList(Long constituencyId);
}

package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterModificationAgeInfo;

public interface IVoterModificationAgeInfoDAO extends GenericDao<VoterModificationAgeInfo,Long>
{
	
	public Integer deleteVoterModicationAgeInfoById(List<Long> voterModificationInfoId);
}

package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CustomVoterGroup;

public interface ICustomVoterGroupDAO extends GenericDao<CustomVoterGroup,Long>{
	
	public List<Object[]> getCustomVoterGroupsByLocationTypeAndLocationValue(Long userId ,Long locationTypeId ,Long locationValue);


}

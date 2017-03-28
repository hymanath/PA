package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MemberVoterMappingDetails;

public interface IMemberVoterMappingDetailsDAO extends GenericDao<MemberVoterMappingDetails, Long>{
	public void flushSession();
	
	public List<Long> getConstituencyIdsAlreadyhasData();

}

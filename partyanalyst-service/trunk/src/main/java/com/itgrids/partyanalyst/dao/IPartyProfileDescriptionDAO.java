package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyProfileDescription;

public interface IPartyProfileDescriptionDAO extends GenericDao<PartyProfileDescription, Long> {
	
	public List<Object> getPartyProfileDescription(Long partyId);
	  public List<Object> getMaxOrderNo(Long partyId);
	  public List<Object[]> getPartyProfileInfo(Long partyId);
}
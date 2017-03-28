package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyDesignation;

public interface IPartyDesignationDAO extends GenericDao<PartyDesignation, Long>{
	public List<Object[]> getAllPartyDesignation();
}

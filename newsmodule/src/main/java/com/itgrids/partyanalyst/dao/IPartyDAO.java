/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Party;

/**
 * Interface for PartyDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface IPartyDAO extends GenericDao<Party, Long>{

	public List<Object[]> getPartiesList();	
	
	public Long getPartyShortName(String partyShortName);
	
	public List<Object[]> getPartyNames(String partyIds);
	
	public List<String> getPartyShortNames(List<Long> partyIds);

	public List<Object[]> getPartyNames(Set<Long> partyIds);
	
	public List<Object[]> getPartiesListByStateId(List<Long> statesList);
	
	public List<Object[]> getPartyDetails(List<Long> partyIds);

}
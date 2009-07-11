/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Nomination;

/**
 * Interface for NominationDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface INominationDAO extends GenericDao<Nomination, Long>{

	/**
	 * Find all Nomination entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Nomination property to query
	 * @param value
	 *            the property value to match
	 * @return List<Nomination> found by query
	 */
	public List<Nomination> findByProperty(String propertyName, Object value);

	public List<Nomination> findByNominationStatus(Object nominationStatus);

	public List<Nomination> findByAssets(Object assets);

	public List<Nomination> findByLiabilities(Object liabilities);

	public List<Nomination> findByCriminalCharges(Object criminalCharges);

}
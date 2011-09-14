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

import com.itgrids.partyanalyst.model.Country;

/**
 * Interface for CountryDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface ICountryDAO extends GenericDao<Country, Long>{

	/**
	 * Find all Country entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Country property to query
	 * @param value
	 *            the property value to match
	 * @return List<Country> found by query
	 */
	public List<Country> findByProperty(String propertyName, Object value);

	public List<Country> findByCountryName(Object countryName);

	public List<Country> findByCapital(Object capital);

	public List<Country> findByIsoCode(Object isoCode);
	
	@SuppressWarnings("unchecked")
	public List getCountryIdByCountryName(String name);
	
	public List<Object[]> getAllCountryDetails();

}
/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 11, 2009
 */
package com.itgrids.partyanalyst.dao.jpa;
/** 
 * Above package should be changed moved into 
 * "com.itgrids.partyanalyst.dao" package 
 * once we remove Sample programs.
 * */

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.columns.enums.CountryColumnNames;
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
	public List<Country> findByProperty(CountryColumnNames propertyName, Object value);

	public List<Country> findByCountryName(Object countryName);

	public List<Country> findByCapital(Object capital);

	public List<Country> findByIsoCode(Object isoCode);

}
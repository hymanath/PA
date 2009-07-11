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

import com.itgrids.partyanalyst.model.District;

/**
 * Interface for DistrictDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface IDistrictDAO extends GenericDao<District, Long>{

	/**
	 * Find all District entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the District property to query
	 * @param value
	 *            the property value to match
	 * @return List<District> found by query
	 */
	public List<District> findByProperty(String propertyName, Object value);

	public List<District> findByDistrictName(Object districtName);

	public List<District> findByDistrictCapital(Object districtCapital);

	public List<District> findByArea(Object area);

	public List<District> findByPopulation(Object population);

	public List<District> findByDistrictCode(Object districtCode);

}
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

import com.itgrids.partyanalyst.dao.columns.enums.TownshipColumnNames;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.Township;

/**
 * Interface for TownshipDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface ITownshipDAO extends GenericDao<Township, Long>{

	/**
	 * Find all Township entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Township property to query
	 * @param value
	 *            the property value to match
	 * @return List<Township> found by query
	 */
	public List<Township> findByProperty(TownshipColumnNames propertyName, Object value);

	public List<Township> findByTownshipName(Object townshipName);

	public List<Township> findByTownshipCode(Object townshipCode);

	public List<Township> findByTownshipType(Object townshipType);
	
	public List<Township> findByTehsilID(Long mandalID);
	
	public List<Township> findByTownshipNameAndTehsilId(String townshipName,Long tehsilId);
	
	@SuppressWarnings("unchecked")
	public List findTownshipIdByTownshipNameAndTehsilId(String townshipName,Long tehsilId);

	public List getStateToRevenueVillageByRV(String rvIDs);

	public List<Township> findByTownshipNameTehsilNameDistrictId(Long districtId,
			String mandalName, String revenueVillage);
	
	public List<Township> findTownsByTownNameAndTypeAndDistrict(String townName,String townType,Long districtId);
	
	public List<Township> findTownsByTownNameAndTypeAndDistrict(String townName,String townType,Long districtId,String mandal);
	
	@SuppressWarnings("unchecked")
	public List findTownshipsByTehsilId(Long tehsilId);
	
	public List<Panchayat> getPanchayathies(Long mandalID);
}
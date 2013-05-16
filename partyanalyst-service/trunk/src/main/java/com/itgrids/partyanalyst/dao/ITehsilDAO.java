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

import com.itgrids.partyanalyst.dao.columns.enums.TehsilColumnNames;
import com.itgrids.partyanalyst.model.Tehsil;

/**
 * Interface for TehsilDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface ITehsilDAO extends GenericDao<Tehsil, Long>{

	/**
	 * Find all Tehsil entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Tehsil property to query
	 * @param value
	 *            the property value to match
	 * @return List<Tehsil> found by query
	 */
	public List<Tehsil> findByProperty(TehsilColumnNames propertyName, Object value);

	public List<Tehsil> findByTehsilName(Object tehsilName);

	public List<Tehsil> findByTehsilCode(Object tehsilCode);

	public List<Tehsil> findByDeformDate(Object deformDate);
	
	public List<Tehsil> findByTehsilNameAndDistrict(String name, Long districtId);
	
	@SuppressWarnings("unchecked")
	public List findTehsilIdByTehsilNameAndDistrict(String name, Long districtId);
	
	public List<Tehsil> findByState(Long stateId);
	
	public List<Tehsil> findByDistrict(Long districtId);
	
	public List<Tehsil> findByConstituency(Long constituencyId);
	
	public List findTehsilsByDistrict(Long districtId);

	public List getStateToMandalByTehsil(String tehsilIDs);
	
public List<Object[]> getAllTehsilInfoDetails();
	
	public List<Object[]> getAllTehsilDetails(Long districtId);

	
	public List<Object[]> findTehsilsByConstituencyIdAndPublicationDateId(
			Long constituencyId, Long publicationDateId);
	
	public List<Object[]> findAllTehsilsByConstituencyIdAndPublicationDateId(
			Long constituencyId, Long publicationDateId);
	
	public List<Object[]> findAllLocalElecBodyByConstituencyIdAndPublicationDateId(
			Long constituencyId, Long publicationDateId);
	
	public String getTehsilNameByTehsilId(Long tehsilId);
	
	public List<Object[]> getTehsilNameByTehsilIdsList(List<Long> tehsilIdsList);
	
}
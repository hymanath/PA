/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
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

public interface IDistrictDAO extends GenericDao<District, Long>{/*

	*//**
	 * Find all District entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the District property to query
	 * @param value
	 *            the property value to match
	 * @return List<District> found by query
	 *//*
	public List<District> findByProperty(DistrictColumnNames propertyName, Object value);

	public List<District> findByDistrictName(Object districtName);

	public List<District> findByDistrictCapital(Object districtCapital);

	public List<District> findByArea(Object area);

	public List<District> findByPopulation(Object population);

	public List<District> findByDistrictCode(Object districtCode);
	
	

	public List<District> getAllOrderByName();
	
	
	
	public List getDistrictNameByDistrictId(Long districtID);
	
	public List<District> getDistrictIDByStateIDAndDistrictName(Long stateID, String districtName);
	
	@SuppressWarnings("unchecked")
	public List getDistrictIdByStateIdAndDistrictName(Long stateID, String districtName);
	
	public List getStateToDistrictByDistrict(String districtIDs);
	*/
	@SuppressWarnings("unchecked")
	public List getDistrictIdAndNameByState(Long stateId);
	public List getStateDistrictByDistrictID(Long districtID);
	public List<District> findByStateId(Long stateId);
	/*
	
	
	public Object getDistrictNameById(Long districtId);
	
	public List<Object[]> getAllDistrictDetails(Long stateId);
	
	public List<Object[]> getAllDistrictInfoDetails();
	
	public List<Object[]> findByPartyNominationDetails(Long stateId,Long partyId,List findByPartyNomination);
*/
	public List<Long> getAllDistrictByStateIds(long stateIds);	
	
	public List<Object[]> getDistrictNamesByDistrictIdsList(List<Long> districtIdsList);
	
	public List<Object[]> getDistrictNamesByStateId(Long stateId);
	
	public List<Object[]> getStateByDistrictID(Long districtID);
	
	public List<Object[]> getDistrictIdAndNameByDistrictId(Long districtId);
	
	public List<Object[]> getDistrictNames(List<Long> districtIds);
	
}
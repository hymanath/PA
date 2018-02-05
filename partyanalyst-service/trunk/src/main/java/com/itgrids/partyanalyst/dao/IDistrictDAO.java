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

import com.itgrids.partyanalyst.dao.columns.enums.DistrictColumnNames;
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
	public List<District> findByProperty(DistrictColumnNames propertyName, Object value);

	public List<District> findByDistrictName(Object districtName);

	public List<District> findByDistrictCapital(Object districtCapital);

	public List<District> findByArea(Object area);

	public List<District> findByPopulation(Object population);

	public List<District> findByDistrictCode(Object districtCode);
	
	public List<District> findByStateId(Long stateId);

	public List<District> getAllOrderByName();
	
	public List getStateDistrictByDistrictID(Long districtID);
	
	public List getDistrictNameByDistrictId(Long districtID);
	
	public List<District> getDistrictIDByStateIDAndDistrictName(Long stateID, String districtName);
	
	@SuppressWarnings("unchecked")
	public List getDistrictIdByStateIdAndDistrictName(Long stateID, String districtName);
	
	public List getStateToDistrictByDistrict(String districtIDs);
	
	@SuppressWarnings("unchecked")
	public List getDistrictIdAndNameByState(Long stateId);
	
	public List<Long> getAllDistrictByStateIds(List<Long> stateIds);
	
	public Object getDistrictNameById(Long districtId);
	
	public List<Object[]> getAllDistrictDetails(Long stateId);
	
	public List<Object[]> getAllDistrictInfoDetails();
	
	public List<Object[]> findByPartyNominationDetails(Long stateId,Long partyId,List findByPartyNomination);
	
	public List<Object[]> getDistrictList();
	
	public List<Object[]> getDistrictIdAndNameByConstituency(Long constituencyId);
	
	public List getDistrictIdAndNameByStateForRegion(Long stateId,String region);
	
	public List<Object[]> getDistrictDetailsByDistrictIds(List<Long> districtIds);
	
	public List<Object[]> getDistrictIdAndNameByStateForStateTypeId(Long stateId,Long stateTypeId);
	
	public List<Object[]> getDistrictDetailsById(Long districtId);
	
	public List getDistrictsForState(Long stateId);

	public List<Long> getDistrictsInAState(Long stateId);
	
	public List<Long> getDistrictIdsByConstituency(List<Long> constituencyIds);
	
    public List<Object[]> getAllDistrictDetailsForAState(Long stateId);
    
    public List<Object[]> getNewDistrictForState(Long stateId);
    public List<Object[]> getDistrictNamesIds(List<Long> districtIds);
    public String getDitrictNmaeById(Long districtID);
    
    public List<Object[]> getDistrictsWithNewSplitted(Long stateId);
    public List<Object[]> getStatesForDistricts(List<Long> distIds);
    public Long getDistrictByStateIdAndDistrictName(Long stateId,String districtName);
    public List<Object[]> getDistrictsList();
    public List<Long> getLocalBodiesOfDistrict(List<Long> distrctIds);
    public List<Object[]> getAllDistrictList(Long stateId);
    public List<Object[]> getDistrictNamesByIds(List<Long> districtIds);
    public List<Object[]> getStateWiseDistrict(Long stateId);
    public List<Object[]> getDistrictListBystateId(Long stateId);
    public List<Object[]> getDistrictForState(Long stateId);
    public List<Object[]> getSpecificDistrictIdAndName(List<Long> districtIds);
    public List<Object[]> getDistrictsWithNewDistricts();
    public List<Object[]> getDistrictForGrievanceRequest(Long stateId);
    public List<Object[]> getAllNewDistrictDetailsForAState(Long stateId, List<Long> districtIdsArr);
    public List getDistrictIdsByState(List<Long> stateId);
    public List<Long> getAllDistrictIds();
}
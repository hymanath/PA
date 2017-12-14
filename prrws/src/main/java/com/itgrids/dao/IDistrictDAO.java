package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.District;

public interface IDistrictDAO extends GenericDao<District,Long>{
	public List<Object[]> getDistrictIdName(Long stateId);
	public List<Long> getDistrictIdDetailsByDistrictIds(String districtIds);
	
	public List<Object[]> getDistrictIdAndNameByDistrictIds(List<Long> districtIds);
	public List<Object[]> getAllDistricts();
	public String getAssignedSearchIdByDistrictId(Long searchLevelValue,String fromPage);
	public String getAssignedSearchDistrictId(Long searchLevelValue,String fromPage);
	public List<Object[]> getFMSLocationIdfromRWSLocationId(String searchLevelValue);
	public List<Object[]> getFMSLocationIdfromMGNREGSLocationId(String searchLevelValue);
	public List<Object[]> getFMSLocationIdfromPRISLocationId(String searchLevelValue);
	public List<Object[]> getFMSLocationIdfromENCLocationId(String searchLevelValue);

	public List<Object[]> getRWSLocationIdfromFMSLocationId(String searchLevelValue);
	public List<Object[]> getRWSLocationIdfromMGNREGSLocationId(String searchLevelValue);
	public List<Object[]> getRWSLocationIdfromPRISLocationId(String searchLevelValue);
	public List<Object[]> getRWSLocationIdfromENCLocationId(String searchLevelValue);

	public List<Object[]> getMGNREGSLocationIdfromFMSLocationId(String searchLevelValue);
	public List<Object[]> getMGNREGSLocationIdfromRWSLocationId(String searchLevelValue);
	public List<Object[]> getMGNREGSLocationIdfromPRISLocationId(String searchLevelValue);
	public List<Object[]> getMGNREGSLocationIdfromENCLocationId(String searchLevelValue);

	public List<Object[]> getPRISLocationIdfromFMSLocationId(String searchLevelValue);
	public List<Object[]> getPRISLocationIdfromRWSLocationId(String searchLevelValue);
	public List<Object[]> getPRISLocationIdfromMGNREGSLocationId(String searchLevelValue);
	public List<Object[]> getPRISLocationIdfromENCLocationId(String searchLevelValue);

	public List<Object[]> getENCLocationIdfromFMSLocationId(String searchLevelValue);
	public List<Object[]> getENCLocationIdfromRWSLocationId(String searchLevelValue);
	public List<Object[]> getENCLocationIdfromMGNREGSLocationId(String searchLevelValue);
	public List<Object[]> getENCLocationIdfromPRISLocationId(String searchLevelValue);
	
	public List<Object[]> getFMSLocationIdfromFMSLocationId(String searchLocationValue);
	public List<Object[]> getRWSLocationIdfromRWSLocationId(String searchLocationValue);
	public List<Object[]> getMGNREGSLocationIdfromMGNREGSLocationId(String searchLocationValue);
	public List<Object[]> getPRISLocationIdfromPRISLocationId(String searchLocationValue);
	public List<Object[]> getENSLocationIdfromENSLocationId(String searchLocationValue);
	
	public List<Object[]> getMgnregsDistrictMappingCode(Long districtId);
	public List<Object[]> getReffererCandidatesDistrictsList(Long stateId,String searchType,Long searchId);
	public List<Object[]> getEncDistricts();
}

package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;

public interface ILocationDetailsService {
	
	public List<LocationFundDetailsVO> getAllDistrictsInState(Long stateId,String searchType,Long searchId);
	public List<LocationVO> getConstituencyNamesByDistrictId( Long districtId,String searchType,Long searchId);
	public List<KeyValueVO> getTehsilsAndLocalElectionBodyForConstituencyId(Long constituencyId,String searchType,Long searchId);
	public List<KeyValueVO> getPanchayatsByTehsilId(Long tehsilId);
	public List<KeyValueVO> getPmDepartmentList(String searchType);
	public List<KeyValueVO> getPetitionDesignationList(String searchType);
	public List<KeyValueVO>  getParliamentIdsByConstituencyList();//down modify
	public List<KeyValueVO>  getPmSubjectList(Long deptId);
	public List<KeyValueVO>  getPmSubSubjectList(Long subjectId);
	public List<KeyValueVO>  getPmLeadDetailsList();
	public List<KeyValueVO>  getPmBriefLeadList();
	public List<KeyValueVO>  getPmGrantList();
	public List<KeyValueVO>  getPmStatusList();
	public List<KeyValueVO>  getWorkTypeList();
	
	public List<KeyValueVO> getPmDesignations(String searchType);
	public List<KeyValueVO> getDistrictBySearchType(String serchType);
	public List<KeyValueVO> getConstituenciesBySearchTypeAndDistrictId(String serchType,List<Long> districtIds);
	public List<KeyValueVO> getMandalsBySearchTypeAndConstituencyId(String serchType,List<Long> conIds);
	public List<KeyValueVO> getDesignationsBySearchType(String searchType);
	public List<KeyValueVO> getDepartmentsBySearchType(String searchType);
	
}

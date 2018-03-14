package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.InputVO;
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
	public List<KeyValueVO>  getPmBriefLeadList(Long deptDesignationId);
	public List<KeyValueVO>  getPmGrantList();
	public List<KeyValueVO>  getPmStatusList();
	public List<KeyValueVO>  getWorkTypeList();
	
	public List<KeyValueVO> getPmDesignations(String searchType);
	public List<KeyValueVO> getDistrictBySearchType(InputVO  inputVO,List<Long> deptIds);
	public List<KeyValueVO> getConstituenciesBySearchTypeAndDistrictId(InputVO  inputVO,List<Long> districtIds,List<Long> deptIds,List<Long> pmDesignationIds,String type,List<Long> statIds);
	public List<KeyValueVO> getMandalsBySearchTypeAndConstituencyId(String serchType,List<Long> conIds,List<Long> deptIds,String fromDate,String toDate,List<Long> desigids,String desigType,List<Long> statIds,Long userId);
	public List<KeyValueVO> getDesignationsBySearchType(String searchType,String fromDate,String toDate,List<Long> deptIds,Long desigId,List<Long> statusId,Long userId);
	public List<KeyValueVO> getDepartmentsBySearchType(String searchType,String fromDate,String toDate,List<Long> deptIds,List<Long> statusIds,Long userId);
	public List<KeyValueVO> getSubjectsBySearchType(String searchType,String fromDate,String toDate,List<Long> deptIds,List<Long> statusId,String subjectId,Long userId);
	public List<KeyValueVO> getChildOfficersByParentOfficerId(String searchType,String fromDate,String toDate,List<Long> deptIds,List<Long> statusIds,String pmOfficerId,Long userId,List<Long> pmDeptDesigIds,Long officerDesigId);
}

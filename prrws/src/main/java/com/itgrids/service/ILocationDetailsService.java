package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.PmOfficerVO;

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
	public List<KeyValueVO>  getPmBriefLeadList(Long designationId,List<Long> deptIds,List<Long> statusIds,String FromDate,String toDate);
	public List<KeyValueVO>  getPmGrantList();
	public List<KeyValueVO>  getPmStatusList();
	public List<KeyValueVO>  getWorkTypeList();
	
	public List<KeyValueVO> getPmDesignations(String searchType);
	public List<KeyValueVO> getDistrictBySearchType(InputVO  inputVO,List<Long> deptIds);
	public List<KeyValueVO> getConstituenciesBySearchTypeAndDistrictId(InputVO  inputVO,List<Long> districtIds,List<Long> deptIds,List<Long> pmDesignationIds,String type,List<Long> statIds,List<Long> subjectIds,List<Long> leadidsList);
	public List<KeyValueVO> getMandalsBySearchTypeAndConstituencyId(InputVO inputVO,String serchType,List<Long> conIds,List<Long> deptIds,String fromDate,String toDate,List<Long> desigids,String desigType, List<Long> statIds,Long userId,List<Long> subjectIds,List<Long> leadIdsLst);
	public List<KeyValueVO> getDesignationsBySearchType(String searchType,String fromDate,String toDate,List<Long> deptIds,Long desigId,List<Long> statusId,Long userId);
	public List<KeyValueVO> getDepartmentsBySearchType(String searchType,String fromDate,String toDate,List<Long> deptIds,List<Long> statusIds,Long userId);
	public List<KeyValueVO> getSubjectsBySearchType(String searchType,String fromDate,String toDate,List<Long> deptIds,List<Long> statusId,String subjectId,Long userId);
	public List<KeyValueVO> getChildOfficersByParentOfficerId(String searchType,String fromDate,String toDate,List<Long> deptIds,List<Long> statusIds,List<Long> pmOfficerId,Long userId,List<Long> pmDeptDesigIds,Long officerDesigId);
	public List<PmOfficerVO> getLocationWiseRepresentationsOverviewDetails(InputVO inputVO);
	
	public List<KeyValueVO> getSubSubjectsBySubjectId(String searchType,String fromDate,String toDate,List<Long> subSubjectIdsLst,List<Long> statusIds,List<Long> subjectIdsLst,List<Long> deptIdsLst,String assertType,Long userId);
	public List<KeyValueVO> getDistrictBySearchTypeInsubject(InputVO inputVO);
	public List<KeyValueVO>  getConstituencyBySearchTypeAndDistrictIdInSubSubject(InputVO inputVO);
}

package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.RepresentationRequestVO;

public interface ILocationDetailsService {
	
	public List<LocationFundDetailsVO> getAllDistrictsInState(Long stateId,String searchType,Long searchId);
	public List<LocationVO> getConstituencyNamesByDistrictId( Long districtId,String searchType,Long searchId);
	public List<KeyValueVO> getTehsilsAndLocalElectionBodyForConstituencyId(Long constituencyId,String searchType,Long searchId);
	public List<KeyValueVO> getPanchayatsByTehsilId(Long tehsilId);
	public List<KeyValueVO> getPmDepartmentList(String searchType);
	public List<KeyValueVO> getPetitionDesignationList(String searchType);
	public List<KeyValueVO>  getParliamentIdsByConstituencyList();//down modify
	public List<KeyValueVO>  getPmSubjectList();
	public List<KeyValueVO>  getPmSubSubjectList(Long subjectId);
	public List<KeyValueVO>  getPmLeadDetailsList();
	public List<KeyValueVO>  getPmBriefLeadList();
	public List<KeyValueVO>  getPmGrantList();
	public List<KeyValueVO>  getPmStatusList();
	public List<KeyValueVO>  getWorkTypeList();
	
}

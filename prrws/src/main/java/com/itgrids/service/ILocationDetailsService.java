package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.RepresentationRequestVO;

public interface ILocationDetailsService {
	
	public List<LocationFundDetailsVO> getAllDistrictsInState(Long stateId);
	public List<LocationVO> getConstituencyNamesByDistrictId( Long  districtId);
	public List<KeyValueVO> getTehsilsAndLocalElectionBodyForConstituencyId(Long constituencyId);
	public List<KeyValueVO> getPanchayatsByTehsilId(Long tehsilId);
	public List<KeyValueVO> getPetitionDepartmentList(String searchType);
	public List<KeyValueVO> getPetitionDesignationList(String searchType);
	public List<KeyValueVO>  getParliamentIdsByConstituencyList();
	public List<KeyValueVO>  getPetitionSubjectList();
	public List<KeyValueVO>  getPetitionSubSubjectList(Long subjectId);
	public List<KeyValueVO>  getPetitionLeadDetailsList();
	public List<KeyValueVO>  getPetitionBriefLeadList();
	public List<KeyValueVO>  getPetitionGrantList();
	public List<KeyValueVO>  getPetitionStatusList();
	
}

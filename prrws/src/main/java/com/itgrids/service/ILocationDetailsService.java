package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;

public interface ILocationDetailsService {
	
	public List<LocationFundDetailsVO> getAllDistrictsInState(Long stateId);
	public List<LocationVO> getConstituencyNamesByDistrictId( Long  districtId);
	public List<KeyValueVO> getTehsilsAndLocalElectionBodyForConstituencyId(Long constituencyId);
	public List<KeyValueVO> getPanchayatsByTehsilId(Long tehsilId);
	public List<KeyValueVO> getPetitionDepartmentList();
	public List<KeyValueVO> getPetitionDesignationList();
	
}

package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.RtcUnionVO;
import com.itgrids.partyanalyst.service.impl.VoterAddressVO;

public interface IRtcUnionService {
     
	public List<IdNameVO> getDistrictsForState(Long stateId);
	public List<IdNameVO> getConstituenciesForDistrict(Long districtId);
	public List<LocationWiseBoothDetailsVO> getLocationsOfSublevelConstituencyMandal(Long constituencyId, String mandalStr, Long locationLevelId);
	public List<IdNameVO> getAllRTCZones();
	public List<IdNameVO> getRegionsOfZone(Long zoneId);
	public List<IdNameVO> getDepotsOfRegion(Long regionId);
	public List<IdNameVO> getDesignationsOfUnionType(Long uniontypeId);
	public VoterAddressVO getVoterAddressDetails(Long candidateId,String searchType);
	public RtcUnionVO getRtcUnionBasicDetails();
	public RtcUnionVO getRtcUnionZoneWiseDetails();
	public RtcUnionVO getRtcUnionLocationWiseDetails(String type,Long typeId);
	public RtcUnionVO getRtcUnionAllLocationDetails();
	public List<CadreVo> getAffiliatedCadreDetails(String type,String searchType,Long locationId);
	public RtcUnionVO getTodayTabAndWebUsersCount();
}

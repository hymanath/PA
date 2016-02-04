package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.IdNameVO;

public interface IRtcUnionService {
	public List<IdNameVO> getAllRTCZones();
	public List<IdNameVO> getRegionsOfZone(Long zoneId);
	public List<IdNameVO> getDepotsOfRegion(Long regionId);
	public List<IdNameVO> getDesignationsOfUnionType(Long uniontypeId);
}

package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.IdAndNameVO;

public interface IWebServiceHandlerServiceForCadre {
	
	public List<IdAndNameVO> getStateWiseDistrict(Long stateId) ;
	public List<IdAndNameVO> getDistrictWiseConstituency(Long districtId);
	public List<IdAndNameVO> getConstitencyWiseTehsil(Long consistuencyId);
	public List<IdAndNameVO> getAllPanchayatsForMandal(Long mandalOrLocalElectionBodyId);
	public List<IdAndNameVO> getAllBoothsForPanchayat(Long panchayatId);
}

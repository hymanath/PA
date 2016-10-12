package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VoterSearchVO;
import com.itgrids.partyanalyst.dto.WebServiceCadreVO;

public interface IWebServiceHandlerServiceForCadre {
	
	public List<IdAndNameVO> getStateWiseDistrict(Long stateId) ;
	public List<IdAndNameVO> getDistrictWiseConstituency(Long districtId);
	public List<IdAndNameVO> getConstitencyWiseTehsil(Long consistuencyId);
	public List<IdAndNameVO> getAllPanchayatsForMandal(Long mandalOrLocalElectionBodyId);
	public List<IdAndNameVO> getAllBoothsForPanchayat(Long panchayatId);
	public List<VoterSearchVO> getVotersBySearch(WebServiceCadreVO inputVO);
	public CadreRegistrationVO getRegistrationPersonDetails(WebServiceCadreVO inputVO);
	public List<TdpCadreVO> getTdpCadresBySearch(WebServiceCadreVO inputVO);
}

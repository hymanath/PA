package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CardPrintValidationUserVO;
import com.itgrids.partyanalyst.dto.CardPrintValidationVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.ImageCadreVO;
import com.itgrids.partyanalyst.dto.NewCadreRegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TdpCadrePrintDetailsVO;
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
	public NewCadreRegistrationVO getRegistrationPersonDetails(WebServiceCadreVO inputVO);
	public List<TdpCadreVO> getTdpCadresBySearch(WebServiceCadreVO inputVO);
	
	public void saveCadreImage(ImageCadreVO inputVO);
	
	public CardPrintValidationUserVO validateCardPrintUserLogin(String username,String password);
	public TdpCadrePrintDetailsVO getTdpCadrePrintDetailsByMemberShipId(String memberShipId);
	public ResultStatus updateCardPrintValidStatus(CardPrintValidationVO inputVO);
}

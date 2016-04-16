package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreAddressVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;

public interface ICadreVoterSearchService {
	public List<LocationWiseBoothDetailsVO> getAllDistrictsAndConstis(String type,Long id);	
	public List<TdpCadreVO> getCadreVoterDetailsBySearchCriteria(String searchType,Long stateId, String locationType,Long locationId,Long casteStateId, String nameStr,String isFinal);
	public VoterDetailsVO getVoterDetailsByVoterCardNumber(String voterIDCardNo);
	public String generateOTPForMobileNumber(Long userId,String mobileNo,String refNo);
	public String validateOTP(String mobileNo,String refNo,String otp);
}

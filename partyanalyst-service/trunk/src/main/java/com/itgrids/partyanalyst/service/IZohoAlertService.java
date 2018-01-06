package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.AmsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IZohoAlertService {
	
	public String getMobileNoByMemberShip(String memberShipId);
	public String sendOtp(String phoneNumber,Long cadreId,String membershipId);
	public ResultStatus checkOTPDetails(AmsVO vo);

}

package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.TdpCadreVO;

public interface ICadreDetailsService {
	public TdpCadreVO searchTdpCadreDetailsBySearchCriteriaForCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo,
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategoryId,Long fromAge,Long toAge,String houseNo,String gender);
}

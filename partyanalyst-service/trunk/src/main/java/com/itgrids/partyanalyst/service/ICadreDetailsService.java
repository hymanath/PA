package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.TdpCadreVO;

public interface ICadreDetailsService {
	public TdpCadreVO searchTdpCadreDetailsBySearchCriteriaForCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo, String voterCardNo, String trNumber, String mobileNo,String casteStateId,String casteCategoryId);
}

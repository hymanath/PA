package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.PrExpenditureVO;

public interface IPRExpenditureService {

	public List<PrExpenditureVO> getLocationWisePrExpenditureDtls(InputVO inputVO);
	public PrExpenditureVO getTotalAmountForOverview(InputVO inputVO);
}

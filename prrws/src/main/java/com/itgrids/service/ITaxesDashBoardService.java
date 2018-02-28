package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.TaxesVO;

public interface ITaxesDashBoardService {
	
	public TaxesVO getTaxesAndCategoryWiseOverViewDetails(InputVO inputVO);
	public List<TaxesVO> getTaxesIndicatorDetails(InputVO inputVO);
	public List<TaxesVO> getTaxesDefaultOverviewDetails(InputVO inputVO);
}

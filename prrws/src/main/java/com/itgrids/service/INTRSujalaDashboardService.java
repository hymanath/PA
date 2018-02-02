package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.NTRSujalaMotherPlantVO;
import com.itgrids.dto.NTRSujalaOverviewVO;
import com.itgrids.dto.NTRSujalaRduVO;

public interface INTRSujalaDashboardService {
	
	public  NTRSujalaMotherPlantVO getLast30DaysMotherPlantDetails(InputVO inputVO);
	public NTRSujalaRduVO getLast30DaysRDUDetails(InputVO inputVO);
	public NTRSujalaOverviewVO getNtrSujalaOverview();
}

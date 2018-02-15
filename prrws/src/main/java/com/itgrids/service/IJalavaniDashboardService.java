package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.AlertVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.JalavaniAlertsInputVO;

public interface IJalavaniDashboardService {
	public AlertVO getJalavaniDashBoardOverview(JalavaniAlertsInputVO inputVO);
	public AlertVO getJalavaniCategoryWiseDetailsInfo(JalavaniAlertsInputVO inputVO);
	public  List<AlertVO> getArticlesMonthlyOverviewInfoBySearchType(JalavaniAlertsInputVO inputVO) ;
}

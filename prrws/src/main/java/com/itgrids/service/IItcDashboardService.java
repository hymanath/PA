package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.CmEoDBDtlsVO;
import com.itgrids.dto.EofficeDtlsVO;
import com.itgrids.dto.InnovationSocietyDtlsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.ItInformationDtlsVO;
import com.itgrids.dto.ItecOverviewVO;
import com.itgrids.dto.MeesevaDtlsVO;
import com.itgrids.dto.MeesevaKPIDtlsVO;

public interface IItcDashboardService {

	public ItecOverviewVO getPromotionsOverviewByDepartmentType(InputVO inputVO);
	public List<ItInformationDtlsVO> getPromotionsDetailedDepartmentWise(InputVO inputVO);
	public EofficeDtlsVO getEOfficePendencyDtlsByDepartmentType(InputVO inputVO);
	public List<EofficeDtlsVO> getEOfficePendencyByDepartmentAndDayWise(InputVO inputVO);
	public List<MeesevaDtlsVO> getMeesevaSLAOverviewDtls(InputVO inputVO);
	public List<MeesevaDtlsVO> getMeesevaSLAMonitoringDtlsDepartmentWise(InputVO inputVO);
	public List<MeesevaKPIDtlsVO> getMeesevaKPIIndicatorsProgressDtls(InputVO inputVO);
	public List<MeesevaKPIDtlsVO> getMeesevaKPIIndicatorsPeriodWise(InputVO inputVO);
	public CmEoDBDtlsVO getCMEDOBOverview(InputVO inputVO);
	public List<CmEoDBDtlsVO> getCMEDOBReportStatusWise(InputVO inputVO);
	public InnovationSocietyDtlsVO getAPInnovationSocietyOverview(InputVO inputVO);
	public List<InnovationSocietyDtlsVO> getAPISXLR8APDetailedData(InputVO inputVO);
	public List<InnovationSocietyDtlsVO> getCampaignsDetailedData(InputVO inputVO);
	public List<InnovationSocietyDtlsVO> getCampusInnovationCentersDetailedData(InputVO inputVO);
	public List<InnovationSocietyDtlsVO> getSuoMotoProposalsDetailedData(InputVO inputVO);
	public List<InnovationSocietyDtlsVO> getInnovationAwardsDetailedData(InputVO inputVO);
}

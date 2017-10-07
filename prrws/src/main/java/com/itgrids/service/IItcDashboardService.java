package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.ApInnovationCenterVO;
import com.itgrids.dto.ApInnovationSocietyOverviewVO;
import com.itgrids.dto.CmEoDBDtlsVO;
import com.itgrids.dto.CohortDtlsVO;
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
	public ApInnovationSocietyOverviewVO getAPInnovationSocietyOverview(InputVO inputVO);
	public List<ApInnovationCenterVO> getAPISXLR8APDetailedData(InputVO inputVO);
	public List<ApInnovationCenterVO> getCampaignsDetailedData(InputVO inputVO);
	public List<ApInnovationCenterVO> getCampusInnovationCentersDetails(InputVO inputVO);
	public List<CohortDtlsVO> getCohortDetailsByCohortId(InputVO inputVO);
	public List<InnovationSocietyDtlsVO> getInnovationAwardsDetailedData(InputVO inputVO);
}

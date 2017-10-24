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
import com.itgrids.dto.ItecEOfficeVO;
import com.itgrids.dto.ItecOverviewVO;
import com.itgrids.dto.ItecPromotionDetailsVO;
import com.itgrids.dto.MeesevaDtlsVO;
import com.itgrids.dto.MeesevaKPIDtlsVO;

public interface IItcDashboardService {

	public List<MeesevaDtlsVO> getMeesevaSLAOverviewDtls(InputVO inputVO);
	public List<MeesevaDtlsVO> getMeesevaSLAMonitoringDtlsDepartmentWise(InputVO inputVO);
	public CmEoDBDtlsVO getCMEDOBOverview(InputVO inputVO);
	public List<CmEoDBDtlsVO> getCMEDOBReportStatusWise(InputVO inputVO);
	public ApInnovationSocietyOverviewVO getAPInnovationSocietyOverview(InputVO inputVO);
	public List<ApInnovationCenterVO> getAPISXLR8APDetailedData(InputVO inputVO);
	public List<ApInnovationCenterVO> getCampaignsDetailedData(InputVO inputVO);
	public List<ApInnovationCenterVO> getCampusInnovationCentersDetails(InputVO inputVO);
	public List<CohortDtlsVO> getCohortDetailsByCohortId(InputVO inputVO);
	public List<InnovationSocietyDtlsVO> getInnovationAwardsDetailedData(InputVO inputVO);
	
	public List<ItecPromotionDetailsVO> getITSectorWiseOverviewDetails();
	public List<ItecPromotionDetailsVO> getITSectorCategoryWiseDetails(InputVO inputVO);
	public List<ItecPromotionDetailsVO> getITDistrictWiseDetails(InputVO inputVO);
	public List<ItecPromotionDetailsVO> getITSectorLeadCategoryWiseDetails(InputVO inputVO);
	public List<ItecPromotionDetailsVO> getITSectorSubLeadCategoryWiseDetails(InputVO inputVO);
	
	public List<ItecEOfficeVO> getEOfcDepartWiseOverviewDetails();
	public List<ItecEOfficeVO> getEOfcDeptPendancyStatusWiseDetails();
	public List<ItecEOfficeVO> getEofficeDesignationWiseDetails();
	public List<ItecEOfficeVO> getEofficeDesignationWisePendencyDetails();
}

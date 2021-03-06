package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.ApInnovationCenterVO;
import com.itgrids.dto.ApInnovationSocietyOverviewVO;
import com.itgrids.dto.CmEoDBDtlsVO;
import com.itgrids.dto.CohortDtlsVO;
import com.itgrids.dto.ExampleVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InnovationSocietyDtlsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.ItecCMeoDBDetailsVO;
import com.itgrids.dto.ItecEOfficeVO;
import com.itgrids.dto.ItecPromotionDetailsVO;
import com.itgrids.dto.MeesevaDtlsVO;
import com.itgrids.dto.MeesevaKPIDtlsVO;

public interface IItcDashboardService {

	public List<MeesevaDtlsVO> getMeesevaSLAOverviewDtls(InputVO inputVO);
	public List<MeesevaDtlsVO> getMeesevaSLAMonitoringDtlsDepartmentWise(InputVO inputVO);
	public CmEoDBDtlsVO getCMEDOBOverview(InputVO inputVO);
	public List<ItecCMeoDBDetailsVO> getCMEDOBReportStatusWise(InputVO inputVO);
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
	public List<ItecPromotionDetailsVO> getSectorWiseOverviewCountDetails(InputVO inputVO);
	public List<ItecCMeoDBDetailsVO> getCMeoDBOverviewDetails();
	public List<ItecCMeoDBDetailsVO> getCMeoDBStatusDetails();
	public List<ItecCMeoDBDetailsVO> getCMeoDBStatusCountDetails(InputVO inputVO);
	public List<ItecEOfficeVO> getDepartmentWiseHierarchicalDetails();
	public List<ItecCMeoDBDetailsVO> getCMeoDBStatusDetailsNew();
	public List<ItecEOfficeVO> getEofficeDesginationDetailsByDepartment(InputVO inputVO);
	public CmEoDBDtlsVO getCMeoDBSectorWiseStatusDetais(InputVO inputVO);
	public List<MeesevaDtlsVO> getMeesevaSLADepartmentDetails();
	public MeesevaKPIDtlsVO getMeesevaKPIOverViewDetails(InputVO inputVO);
	public List<MeesevaKPIDtlsVO> getMeesevaKPITargetAchieveDetails();
	public List<MeesevaKPIDtlsVO> getMeesevaKPILocationWiseDetails();
	public MeesevaDtlsVO getMeesevaSLACatWiseAbstarctDetails();
	public List<MeesevaDtlsVO> getMeesevaSLAServiceWiseDetails();
	public List<ItecEOfficeVO> getEOfcDepartOverviewDetails(InputVO inputVO);
	public List<ItecEOfficeVO> getEofficeDesginationWiseDetailsFrDepartment(InputVO inputVO);
	public List<MeesevaKPIDtlsVO> getMeesevaKPIOnlineServiceDetails(InputVO inputVO);//Old Call
	public List<MeesevaKPIDtlsVO> getMeesevaKPIOnlineDeptWiseCuntDetails(InputVO inputVO);
	public List<MeesevaKPIDtlsVO> getMeesevaKPIMobileSevicesDetails(InputVO inputVO);
	public List<MeesevaKPIDtlsVO> getMeesevaKPIMobileDeptSevicesDetails(InputVO inputVO);
	public MeesevaKPIDtlsVO getMeesevaKPIOnlineServiceOverviewCount(InputVO inputVO);
	public MeesevaKPIDtlsVO getMeesevaKPIMobileAppServiceOverviewCount(InputVO inputVO);
	public List<MeesevaKPIDtlsVO> getMeesevaKPIOnlineServiceYearWiseDetails(InputVO inputVO);
	public List<MeesevaKPIDtlsVO> getMeesevaKPIMobileSevicesYearWiseDetails(InputVO inputVO);
	public MeesevaKPIDtlsVO getMeesevaKPINewOnlineServiceOverviewCount(InputVO inputVO);
	public List<MeesevaKPIDtlsVO> getMeesevaKPINewOnlineServiceYearWiseDetails(InputVO inputVO);
	public List<MeesevaKPIDtlsVO> getMeesevaKPINewOnlineDeptWiseCuntDetails(InputVO inputVO);
	public IdNameVO savingEofcDataDetails();
	public List<ItecEOfficeVO> getEOfcDepartOverviewDetailsNew(InputVO inputVO);
	public List<ItecEOfficeVO> getEofficeDesginationWiseDetailsFrDepartmentNew(InputVO inputVO);
	public List<ItecEOfficeVO> getEOfcPrAndRdDepartsOverviewDetails(InputVO inputVO);
	public List<ItecEOfficeVO> getEofficePrAndRdDeptDesginationDetails(InputVO inputVO);
	public IdNameVO saveMeesevaKPIDetails(String districtIdStr);
	public List<MeesevaKPIDtlsVO> getMeesevaKPIOverViewDetailsNew();
	public List<MeesevaKPIDtlsVO> getMeesevaKPILocationWiseDetailsNew(InputVO inputVO);
	public List<MeesevaKPIDtlsVO> getMeesevaCentersForDistrict(InputVO inputVO);
	public IdNameVO saveMeesevaKPITargetAchievementValues();
	public List<ApInnovationSocietyOverviewVO> getApInnovationOverviewDetails();
	public List<ApInnovationSocietyOverviewVO> getApInnovationIncubatorsXLr8APDetails();
	public ApInnovationSocietyOverviewVO getApInnovationIncubatorsOtherBlockDetails(InputVO inputVO);
	public List<ApInnovationSocietyOverviewVO> getApInnovationCohortWiseDetails(InputVO inputVO);
	public ApInnovationSocietyOverviewVO getApInnovationBootCampDetails(InputVO inputVO);
	public List<ApInnovationSocietyOverviewVO> getApInnovationEventDetails();
	public ApInnovationSocietyOverviewVO getApInnovationActivityDetails(InputVO inputVO);
	public List<ApInnovationSocietyOverviewVO> getApInnovationIndicatorDetails();
	
	public List<ApInnovationSocietyOverviewVO> getCompleteOverviewForAPIS(InputVO inputVO);
	public ApInnovationSocietyOverviewVO getStartupsEmploymentFundingPatternAcquisitionsDetails();
	public  List<MeesevaKPIDtlsVO> getMeesevaCentersTargetAchievement(InputVO inputVO);
	
	public String checkeOfficeDataExists();
	public String saveeOfficeWebServiceData(ExampleVO inputvo);
}

package com.itgrids.service.integration.impl;

import java.util.List;
import java.util.Map;

import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LabourBudgetOverViewVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.NregaLocationOverviewVO;
import com.itgrids.dto.NregaPaymentsVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsOverviewVO;
import com.itgrids.dto.NregsProjectsVO;
import com.itgrids.dto.WaterTanksClorinationVO;
import com.itgrids.dto.WebserviceDetailsVO;

public interface INREGSTCSService {

	public List<NregsProjectsVO> getNREGSProjectsOverview(InputVO inputVO);
	public LabourBudgetOverViewVO getLabourBudgetOverview(InputVO inputVO);
	public List<IdNameVO> getLabourBudgetExpenditure(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelwiseOverviewForLabourBudgetData(InputVO inputVO);
	public List<WebserviceDetailsVO> getWebserviceHealthDetails(InputVO inputVO);
	public List<NregsDataVO> getNregsConstCuntDetails(String output,Map<String,NregsDataVO> cntMap,String divType,String locationType,String subLocationType);
	public NregsDataVO getMGNregsDistrWiseConsti(InputVO inputVO);
	public List<NregsDataVO> getDistrictsConstitByType(List<NregsDataVO>  returnList,String type);
	public List<NregsDataVO> getNregsMandalsCuntFrDistrict(String output,Map<String,NregsDataVO> cntMap,String divType,String subLocationType);
	public List<NregsDataVO> getNregsMandalsCuntFrConstituncies(String output,Map<String,NregsDataVO> cntMap,String divType);
	public List<NregsDataVO> getNregaLevelsWiseData(InputVO inputVO);
	public NregsOverviewVO getNregasOverview(InputVO inputVO);
	public List<NregsDataVO> getNregsVillagesCuntFrMandals(String output,Map<String,NregsDataVO> cntMap,String divType);
	public List<NregsDataVO> getNregsVillagesCuntFrConstituncies(String output,Map<String,NregsDataVO> cntMap,String divType);
	public List<NregsDataVO> getNregsVillageCuntFrDistrict(String output,Map<String,NregsDataVO> cntMap,String divType,String subLocationType);
	public List<NregsDataVO> getNregaLevelsWiseDataFrNewCalls(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsWiseDataFrAgriculture(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsWiseDataFrHorticulture(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsWiseDataForCCRoads(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsWiseDataFrAvenue(InputVO inputVO);
	public List<NregsProjectsVO> getNREGSAbstractDataByType(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsWiseDataForTimelyPayments(InputVO inputVO);
	public List<NregsDataVO> getNregaParliamentData(InputVO inputVO);
	public List<NregsProjectsVO> getNREGSProjectsAbstractNew(InputVO inputVO);
	public List<LocationFundDetailsVO> getAllNregaSubLocationDetails(InputVO inputVO);
	public List<NregsDataVO> getNregaPanchatVsExpData(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsWiseDataForFAPerformance(InputVO inputVO);
	public List<NregaPaymentsVO> getNregaLevelsWiseDataForNewFTOPayments(InputVO inputVO);
	public List<NregaPaymentsVO> getNregaParliamentDataFrpayments(InputVO inputVO);
	public NregaPaymentsVO getNregaPaymentsAbsAndOverview(InputVO inputVO);
	public List<NregsProjectsVO> getNREGSProjectsAbstractNewFrConstituency(InputVO inputVO);
	public List<NregsProjectsVO> getNREGSAbstractDataByTypeFrConstituency(InputVO inputVO);
	public List<NregsDataVO> getNregaPanchatVsExpLevelWiseCountsData(InputVO inputVO);
	public NregaLocationOverviewVO getIhhlAbstractData(InputVO inputVO);
	public NregaLocationOverviewVO getIhhlOverviewData(InputVO inputVO);
	public List<NregaLocationOverviewVO> getLocationIhhlData(InputVO inputVO);
	
	public List<NregsProjectsVO> getNregsProjectsIWMPAbstract(InputVO inputVO);
	public List<NregsDataVO> getLocationWiseWaterBudgetDetails(InputVO inputVO);
	public List<NregaPaymentsVO> getNregaPaymentsAbsAndOverviewDtls(InputVO inputVO);
	public List<NregaPaymentsVO> getNregaPaymentsDtlsLocationWise(InputVO inputVO);
	public List<NregsProjectsVO> getNREGSForestAbstact(InputVO inputVO);
	public List<NregsDataVO> getNregaForestLevelData(InputVO inputVO);
	public NregsOverviewVO getNregasForestOverview(InputVO inputVO);
	public WaterTanksClorinationVO getSessionTokenDetails(InputVO inputVO);
	public WaterTanksClorinationVO getWaterBodyCumulativeCounts(InputVO inputVO);
	
	public List<WaterTanksClorinationVO> getDistrictWiseClorinationDetails(InputVO inputVO);
	public List<WaterTanksClorinationVO> getLocationWiseClorinationDetails(InputVO inputVO);
	public List<NregsDataVO> getNregaOtherMCCLevelData(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsWiseDataFrCoffeePlantation(InputVO inputVO);
	public List<NregsDataVO> getNregaMCCNewComponetsLevelData(InputVO inputVO);
	public List<NregsDataVO> getNregaCovergancePROtherLevelData(InputVO inputVO);
	public List<NregsDataVO> getWorkWiseAbstractForMCCOthers(InputVO inputVO);
	public List<NregsDataVO> getNregaOtherMCCAbstarctData(InputVO inputVO);
	public List<NregsDataVO> getManWorkDaysOfNrega(InputVO inputVO);
	public InputVO saveNregaComponentComments(Long componentComentId,Long statusId,String comment,String actionType,String uniqueCode);
	public List<InputVO> getNregaComponentStatus(InputVO vo);
	public List<NregsDataVO> getManWorkDaysOfNregaMonthWise(InputVO inputVO);
}

package com.itgrids.tpi.rws.service;

import java.util.List;
import java.util.Map;

import com.itgrids.dto.AmsVO;
import com.itgrids.dto.BasicVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KPIVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.NregaLocationOverviewVO;
import com.itgrids.dto.RangeVO;
import com.itgrids.dto.RwsClickVO;
import com.itgrids.dto.StatusVO;
import com.itgrids.dto.WaterSourceVO;

public interface IRWSNICService {
	
	public List<LocationVO> getHabitationCoverageByStatusByLocationType(InputVO vo);
	public BasicVO getLabTestDetails(InputVO vo);
	public BasicVO getHabitationSupplyDetails(InputVO vo);
	public List<BasicVO> getSchemesDetails(InputVO VO);
	public List<BasicVO> getSchemeWiseWorkDetails(InputVO VO);
	public List<BasicVO> getAssetsInfo(InputVO vo);
	public List<StatusVO> getAlertDetailsOfCategoryByStatusWise(InputVO vo);
	public List<StatusVO> getAlertFeedbackStatusDetails(InputVO vo);
	public WaterSourceVO getWaterSourceInfo(InputVO vo);
	public List<KPIVO> getKeyPerformanceIndicatorsInfo(InputVO inputVO);
	public List<StatusVO> getPlanofActionForStressedHabitations(InputVO vo);
	public StatusVO getStressedHabitationsInfoByLocationType(InputVO vo);
	public List<LocationVO> getLocationWiseAlertStatusCounts(InputVO vo);
	public List<RangeVO> getLocationBasedOnSelection(InputVO vo);
	public List<StatusVO> getHamletWiseIvrCounts(InputVO vo);
	public List<AmsVO> getAlertsOfCategoryByStatusWise(InputVO vo);
	public List<RwsClickVO> getOnclickWorkDetails(InputVO vo);
	public List<RwsClickVO> getOnclickTargetsAcheievementsDetails(InputVO vo);
	public List<RwsClickVO> getOnclickStressedTargetsAcheievementsDetails(InputVO vo);
	public List<RwsClickVO> getOnclickHabitationsupplyDetails(InputVO vo);
	
	public List<RwsClickVO> getSchemeDetailsByTypeOfAssestName(InputVO vo);
	public List<RwsClickVO> getAssetDetailsByAssetType(InputVO vo);
	public List<RwsClickVO> getHabitationDetailsByStatusByLocationType(InputVO vo);
	public List<RwsClickVO> getWaterSourceDeatilsLocationWise(InputVO vo);
	public List<KeyValueVO> getAllPrrwsDistricts();
	public List<KeyValueVO> getConstituenciesForDistrict(IdNameVO idNameVO);
	public List<KeyValueVO> getTehsilsForConstituency(IdNameVO idNameVO);
	public List<WaterSourceVO> getWaterSourceDeatils2(InputVO vo);
	public List<RwsClickVO> getHamletWiseIvrStatusList(InputVO vo);
	public NregaLocationOverviewVO getIHHLOverviewData(InputVO inputVO);
	public List<NregaLocationOverviewVO> getIHHLlocationLvlWiseData(InputVO inputVO);
	public NregaLocationOverviewVO getSBPaymentsAbstract(InputVO inputVO);
	public List<NregaLocationOverviewVO> getSBPaymentsLevelsWiseData(InputVO inputVO);
	
	public Map<Long,IdNameVO> getAllAdminWorksDetails();
	public IdNameVO getExceededTargetWorksDetails();
}

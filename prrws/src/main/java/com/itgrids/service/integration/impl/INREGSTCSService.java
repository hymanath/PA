package com.itgrids.service.integration.impl;

import java.util.List;
import java.util.Map;

import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LabourBudgetOverViewVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsOverviewVO;
import com.itgrids.dto.NregsProjectsVO;
import com.itgrids.dto.WebserviceDetailsVO;

public interface INREGSTCSService {

	public List<NregsProjectsVO> getNREGSProjectsOverview(InputVO inputVO);
	public LabourBudgetOverViewVO getLabourBudgetOverview(InputVO inputVO);
	public List<IdNameVO> getLabourBudgetExpenditure(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelwiseOverviewForLabourBudgetData(InputVO inputVO);
	public List<WebserviceDetailsVO> getWebserviceHealthDetails(InputVO inputVO);
	public List<NregsDataVO> getNregsConstCuntDetails(String output,Map<String,NregsDataVO> cntMap,String divType);
	public NregsDataVO getMGNregsDistrWiseConsti(InputVO inputVO);
	public List<NregsDataVO> getDistrictsConstitByType(List<NregsDataVO>  returnList,String type);
	public List<NregsDataVO> getNregsMandalsCuntFrDistrict(String output,Map<String,NregsDataVO> cntMap,String divType);
	public List<NregsDataVO> getNregsMandalsCuntFrConstituncies(String output,Map<String,NregsDataVO> cntMap,String divType);
	public List<NregsDataVO> getNregaLevelsWiseData(InputVO inputVO);
	public NregsOverviewVO getNregasOverview(InputVO inputVO);
	public List<NregsDataVO> getNregsVillagesCuntFrMandals(String output,Map<String,NregsDataVO> cntMap,String divType);
	public List<NregsDataVO> getNregsVillagesCuntFrConstituncies(String output,Map<String,NregsDataVO> cntMap,String divType);
	public List<NregsDataVO> getNregsVillageCuntFrDistrict(String output,Map<String,NregsDataVO> cntMap,String divType);
	public List<NregsDataVO> getNregaLevelsWiseDataFrNewCalls(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsWiseDataFrAgriculture(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsWiseDataFrHorticulture(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsWiseDataForCCRoads(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsWiseDataFrAvenue(InputVO inputVO);
	public List<NregsProjectsVO> getNREGSAbstractDataByType(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsWiseDataForTimelyPayments(InputVO inputVO);
	//public List<NregsDataVO> getNregaParliamentData(InputVO inputVO);
	public List<NregsProjectsVO> getNREGSProjectsAbstractNew(InputVO inputVO);
	public List<LocationFundDetailsVO> getAllNregaSubLocationDetails(InputVO inputVO);
	public List<NregsDataVO> getNregaPanchatVsExpData(InputVO inputVO);
}

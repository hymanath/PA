package com.itgrids.service.integration.impl;

import java.util.List;

import com.itgrids.dto.FarmPondOverviewVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LabourBudgetOverViewVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsOverviewVO;
import com.itgrids.dto.NregsProjectsVO;

public interface INREGSTCSService {

	public List<NregsProjectsVO> getNREGSProjectsOverview(InputVO inputVO);
	public LabourBudgetOverViewVO getLabourBudgetOverview(InputVO inputVO);
	public List<IdNameVO> getLabourBudgetExpenditure(InputVO inputVO);
	 public FarmPondOverviewVO getFarmPondOverview(InputVO inputVO);
	 public List<NregsDataVO> getFarmPondData(InputVO inputVO);
	public NregsOverviewVO getNregaIHHLOverview(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsOverviewForIHHL(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelwiseOverviewForLabourBudgetData(InputVO inputVO);
	public NregsOverviewVO getNregsVermiOverview(InputVO inputVO);
	public List<NregsDataVO> getNregsVermiData(InputVO inputVO);
	public NregsOverviewVO getNregsNtrsOverview(InputVO inputVO);
	public List<NregsDataVO> getNregsNtrsData(InputVO inputVO);
	public NregsOverviewVO getNregsAnganwadiOverview(InputVO inputVO);
	public List<NregsDataVO> getNregsAnganwadiData(InputVO inputVO);
	public NregsOverviewVO getNregsMandalBuildingOverview(InputVO inputVO);
	public List<NregsDataVO> getNregsMandalBuildingData(InputVO inputVO);
	public NregsOverviewVO getNregaGPBuilingsOverview(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsOverviewForGPBuilding(InputVO inputVO);
	public FarmPondOverviewVO getCCRoadsOverview(InputVO inputVO);
	public List<NregsDataVO> getCCRoadsData(InputVO inputVO);
	public NregsOverviewVO getNregaProductionOfBricksOverview(InputVO inputVO);
	public NregsOverviewVO getNregaRaisingOfPerinnialFodderOverview(InputVO inputVO);
	public NregsOverviewVO getNregaSilkWormOverview(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsOverviewForProductionOfBricks(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsOverviewForRaisingOfPerinnialFodder(InputVO inputVO);
	public List<NregsDataVO> getNregaLevelsOverviewForSilkWarm(InputVO inputVO);
	public NregsOverviewVO getNregsSericultureOverview(InputVO inputVO);
	public List<NregsDataVO> getNregsSericultureData(InputVO inputVO);
	public NregsOverviewVO getNregsHousingOverview(InputVO inputVO);
	public List<NregsDataVO> getNregsHousingData(InputVO inputVO);
}

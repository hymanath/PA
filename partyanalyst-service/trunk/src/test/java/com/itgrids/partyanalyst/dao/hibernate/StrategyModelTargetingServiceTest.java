package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dto.StrategyVO;
import com.itgrids.partyanalyst.service.IStrategyModelTargetingService;

public class StrategyModelTargetingServiceTest  extends BaseDaoTestCase{
	IStrategyModelTargetingService strategyModelTargetingService;

	public IStrategyModelTargetingService getStrategyModelTargetingService() {
		return strategyModelTargetingService;
	}

	public void setStrategyModelTargetingService(
			IStrategyModelTargetingService strategyModelTargetingService) {
		this.strategyModelTargetingService = strategyModelTargetingService;
	}
	
	public void testGetReport(){
		StrategyVO strategyVO = new StrategyVO();
		strategyVO.setConstituencyId(232l);
		strategyVO.setPartyId(872l);
		List<Long> electionIds = new ArrayList<Long>();
		electionIds.add(38l);
		electionIds.add(3l);
		strategyVO.setElectionIds(electionIds);
		strategyVO.setEffectPartyId(662l);
		strategyVO.setEffectElectionId(38l);
		strategyVO.setPrevTrnzWt(0.2);
		strategyVO.setYoungWt(0.2);
		strategyVO.setPrpWt(0.2);
		strategyVO.setAgedWt(0.2);
		strategyVO.setTotalCastWt(0.2);
		strategyVO.setPublicationId(8l);

		strategyModelTargetingService.getPrioritiesToTarget(strategyVO);
	}
}

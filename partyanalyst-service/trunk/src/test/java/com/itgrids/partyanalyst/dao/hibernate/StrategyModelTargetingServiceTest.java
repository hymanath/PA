package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		/*Map<Long,Float> castePercents = new HashMap<Long,Float>();
		castePercents.put(211l,0.4f);
		castePercents.put(161l,0.6f);
		castePercents.put(285l,0.3f);
		castePercents.put(189l,0.8f);
		castePercents.put(0l,0.4f);
		strategyVO.setCastePercents(castePercents);*/
		strategyModelTargetingService.getPrioritiesToTarget(strategyVO,"");
	}
}

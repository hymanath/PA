package com.itgrids.partyanalyst.service;

import java.util.Map;

public interface IPrpEffectCalculateService {
	public Map<Long,Double> calculatePrpEffect(Map<Long,Map<Long,Double>> prevResultMap,Map<Long,Map<Long,Double>> currResultMap, Map<Long,String> locationNames);
}

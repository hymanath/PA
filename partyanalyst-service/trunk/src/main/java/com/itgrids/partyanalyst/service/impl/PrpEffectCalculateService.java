package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.service.IPrpEffectCalculateService;

public class PrpEffectCalculateService implements IPrpEffectCalculateService {
	private static final Logger LOG = Logger.getLogger(PrpEffectCalculateService.class);
	public Map<Long,Double> calculatePrpEffect(Map<Long,Map<Long,Double>> prevResultMap,Map<Long,Map<Long,Double>> currResultMap, Map<Long,String> locationNames){
		Map<Long,Double> returnVO = new HashMap<Long,Double>();
		try{
		Long prpId = 662l;
		Long incId = 362l;
		Long tdpId = 872l;
		Long bjpId = 163l;
		
		/*Long trsId = 886l;
		Long mimId = 72l;
		Long cpiId = 265l;		
		Long cpmId = 269l;*/
		
		Set<Long> panchaytIds =  prevResultMap.keySet();
		
		List<Long> tdpPrevAlliance = new ArrayList<Long>();
		tdpPrevAlliance.add(163l);
		List<Long> tdpCurrentAlliance = new ArrayList<Long>();
		tdpCurrentAlliance.add(886l);
		tdpCurrentAlliance.add(265l);
		tdpCurrentAlliance.add(269l);
		
		List<Long> incPrevAlliance = new ArrayList<Long>();
		incPrevAlliance.add(886l);
		incPrevAlliance.add(265l);
		incPrevAlliance.add(269l);
		
		
		for(Long panchayatId:panchaytIds){

			
			Map<Long,Double> currentResult = currResultMap.get(panchayatId);
			Map<Long,Double> prevResult = prevResultMap.get(panchayatId);
			Long tdpConsidPrtyId = null;
			if(currentResult == null || prevResult == null){
				continue;
			}
			Double tdpPrevPrec = prevResult.get(tdpId);
			Double tdpCurrentPrec = currentResult.get(tdpId);
			
			if(tdpPrevPrec == null){
				for(Long partyId:tdpPrevAlliance){
					tdpPrevPrec = prevResult.get(partyId);
					if(tdpPrevPrec != null){
						break;
					}
				}
			}
			
			if(tdpCurrentPrec == null){
				for(Long partyId:tdpCurrentAlliance){
					tdpCurrentPrec = currentResult.get(partyId);
					if(tdpCurrentPrec != null){
						tdpConsidPrtyId = partyId;
						break;
					}
				}
			}
			
			if(tdpPrevPrec != null && tdpCurrentPrec != null && (tdpCurrentPrec -tdpPrevPrec < 0 ) && currentResult.get(prpId) != null){
				Double tdpLost = tdpPrevPrec-tdpCurrentPrec;
				Double incDiff = 0d;
				Double otherPartiesTotal = 0d;
				Double prpPerc = currentResult.get(prpId);
				
				Double incPrevPrec = prevResult.get(incId);
				Double incCurrentPrec = currentResult.get(incId);
				
				if(incPrevPrec == null){
					for(Long partyId:incPrevAlliance){
						incPrevPrec = prevResult.get(partyId);
						if(incPrevPrec != null){
							break;
						}
					}
				}
				
				if(incPrevPrec != null && incCurrentPrec != null){
					incDiff =  incCurrentPrec-incPrevPrec;
				}
				
				List<Long> notConsiderPrtis = new ArrayList<Long>();
				notConsiderPrtis.add(tdpId);
				notConsiderPrtis.add(incId);
				notConsiderPrtis.add(bjpId);
				notConsiderPrtis.add(prpId);
				if(tdpConsidPrtyId != null){
					notConsiderPrtis.add(tdpConsidPrtyId);
				}	
				for(Long partyId:currentResult.keySet()){
					if(!notConsiderPrtis.contains(partyId) && currentResult.get(partyId) != null){
						otherPartiesTotal = otherPartiesTotal+currentResult.get(partyId);
					}
				}
				
				Double availPerc = incDiff+otherPartiesTotal;
				if(availPerc > 0){
					Double prpEffect = tdpLost - availPerc;
					if(prpEffect > 0){
						if(prpEffect > prpPerc){
							prpEffect = prpPerc;
						}
						prpEffect = new BigDecimal(prpEffect).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						returnVO.put(panchayatId, prpEffect);
					}
				}
				
			}
		}
		}catch(Exception e){
			LOG.error("Exception rised in PrpEffectCalculateService ",e);
		}
		return returnVO;
	}
}

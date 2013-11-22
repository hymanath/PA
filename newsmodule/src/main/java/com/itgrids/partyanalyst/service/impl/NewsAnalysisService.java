package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.AnalysisVO;
import com.itgrids.partyanalyst.service.INewsAnalysisService;

public class NewsAnalysisService implements INewsAnalysisService {
   
	private static final Logger LOG = Logger.getLogger(NewsAnalysisService.class);
			
	public void analyseNewsWithSelectedParameters(AnalysisVO analysisVO){
		if(LOG.isInfoEnabled()){
			LOG.info("Enter in to analyseNewsWithSelectedParameters method ");
		}
		List<String> slectedFields = new ArrayList<String>();
		boolean sourcePresent = false;
		boolean destinationPresent = false;
		StringBuilder queryCandPart = new StringBuilder();
		StringBuilder queryPart = new StringBuilder();
		StringBuilder query = new StringBuilder();
		queryCandPart.append("select ");
		queryPart.append("select ");
		query.append("select ");
		if(sourcePresent && destinationPresent){
			
		}else if(sourcePresent){
			if(analysisVO.isDestCandPartyWise()){
			  query.append(" ");
			}
		}else if(destinationPresent){
			if(analysisVO.isSourceCandPartyWise())
			query.append(" ");
		}
	}
}

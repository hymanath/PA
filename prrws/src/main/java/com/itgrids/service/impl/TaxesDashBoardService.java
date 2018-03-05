package com.itgrids.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.PanchayatTaxVO;
import com.itgrids.dto.TaxesVO;
import com.itgrids.dto.panchayatTaxInputVO;
import com.itgrids.service.ITaxesDashBoardService;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.IConstants;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Transactional
@Service
public class TaxesDashBoardService implements ITaxesDashBoardService{
	
	private static final Logger LOG = Logger.getLogger(TaxesDashBoardService.class);
	
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	
	/*
	 * Author : Nandhini.k,
	 * Date : 26/02/2018,
	 * Inputs : loactionId,location,fromDate,toDate,
	 * @Description : To get Taxes And Category Wise OverView
	 */
	public TaxesVO getTaxesAndCategoryWiseOverViewDetails(InputVO inputVO){
		TaxesVO finalVO = new TaxesVO();
		try {
			Long  demandArrearAmunt = 0L;
			Long  collectionArrearAmunt = 0L;
			Long balArrearAmunt = 0L;
			Long demandCuurentAmunt = 0L;
			Long collectionCuurentAmunt = 0L;
			Long balCuurentAmunt = 0L;
			Long totalDemandAmunt = 0L;
			Long totalCollectionAmunt = 0L;
			Long totalBalalnceAmunt = 0L;
			Long demandArrearUnits = 0L;
			Long collecionArrearUnits = 0L;
			Long balArrearUnits = 0L;
			Long demandCurrentUnits = 0L;
			Long collectionCurrentUnits = 0L;
			Long balCurrentUnits = 0L;
			Map<Long,TaxesVO> taxTypeMap = new LinkedHashMap<Long,TaxesVO>(0);
			Map<Long,TaxesVO> feesMap = new LinkedHashMap<Long,TaxesVO>(0);
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://pris.ap.gov.in/api/taxes/taxstats.php?taxStatistics=1&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate()+"");
	        ClientResponse response = webResource.get(ClientResponse.class);
				
	        	if(response.getStatus() != 200){
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	      }else{
		 	    	 String output = response.getEntity(String.class);
		 	    	 
		 	    	if(output != null && !output.isEmpty()){
		 	    		JSONArray districtArray = new JSONArray(output);
		 	    		if(districtArray!=null && districtArray.length()>0){
		 	    			for(int i=0;i<districtArray.length();i++){
		 	    				JSONObject districtObj = (JSONObject) districtArray.get(i);
		 	    				JSONArray taxesArray = (JSONArray) districtObj.get("taxes");
		 	    				if(taxesArray!=null && taxesArray.length()>0){
				 	    			for(int j=0;j<taxesArray.length();j++){
				 	    				JSONObject taxesObj = (JSONObject) taxesArray.get(j);
				 	    				demandArrearAmunt = demandArrearAmunt+Long.valueOf(taxesObj.get("arrearDemand").toString());
				 	    				collectionArrearAmunt = collectionArrearAmunt+Long.valueOf(taxesObj.get("arrearCollection").toString());
				 	    				balArrearAmunt = balArrearAmunt+Long.valueOf(taxesObj.get("arrearRemainingTax").toString());
				 	    				demandCuurentAmunt = demandCuurentAmunt+Long.valueOf(taxesObj.get("currentDemand").toString());
				 	    				collectionCuurentAmunt =collectionCuurentAmunt+Long.valueOf(taxesObj.get("currentCollection").toString());
				 	    				balCuurentAmunt = balCuurentAmunt+Long.valueOf(taxesObj.get("currentRemainingTax").toString());
				 	    				totalDemandAmunt = demandArrearAmunt+demandCuurentAmunt;
				 	    				totalCollectionAmunt = collectionArrearAmunt+collectionCuurentAmunt;
				 	    				totalBalalnceAmunt = balArrearAmunt+balCuurentAmunt;
				 	    				demandArrearUnits = demandArrearUnits+taxesObj.getLong("arrearDemandAssmts");
				 	    			    collecionArrearUnits = collecionArrearUnits+taxesObj.getLong("arrearCollectionAssmts");
				 	    				balArrearUnits = balArrearUnits+taxesObj.getLong("arrearRemainingAssmts");
				 	    				demandCurrentUnits = demandCurrentUnits+taxesObj.getLong("currentDemandAssmts");
				 	    				collectionCurrentUnits = collectionCurrentUnits+taxesObj.getLong("currentCollectionAssmts");
				 	    				balCurrentUnits = balCurrentUnits+taxesObj.getLong("currentRemainingAssmts");
				 	    				
				 	    				
				 	    				
				 	    				
				 	    				Long taxType = taxesObj.getLong("taxtype");
				 	    				if(taxType != null && (taxType.longValue() == 1L || taxType.longValue() == 2L || taxType.longValue() == 3L)){
				 	    					TaxesVO taxesvo = taxTypeMap.get(taxType);
				 	    					if(taxesvo == null){
				 	    						taxesvo = new TaxesVO();
					 	    				
				 	    						if(taxType.longValue() == 1L){
					 	    						taxesvo.setId(taxType);
					 	    						taxesvo.setName("House");
					 	    					}else if(taxType.longValue() == 2L){
					 	    						taxesvo.setId(taxType);
					 	    						taxesvo.setName("Kolagaaram");
					 	    					}else if(taxType.longValue() == 3L){
					 	    						taxesvo.setId(taxType);
					 	    						taxesvo.setName("Advertisement");
					 	    					}
					 	    					
						 	    				taxesvo.setDemandArrearAmount(taxesObj.get("arrearDemand").toString());
						 	    				taxesvo.setCollectionArrearAmount(taxesObj.get("arrearCollection").toString());
						 	    				taxesvo.setBalanceArrearAmount(taxesObj.get("arrearRemainingTax").toString());
						 	    				taxesvo.setDemandCurrentAmount(taxesObj.get("currentDemand").toString());
						 	    				taxesvo.setCollectionCurrentAmount(taxesObj.get("currentCollection").toString());
						 	    				taxesvo.setBalanceCurentAmount(taxesObj.get("currentRemainingTax").toString());
						 	    				taxesvo.setDemandArrearUnits(taxesObj.getLong("arrearDemandAssmts"));
						 	    				taxesvo.setCollectionArrearUnits(taxesObj.getLong("arrearCollectionAssmts"));
						 	    				taxesvo.setBalanceArrearUnits(taxesObj.getLong("arrearRemainingAssmts"));
						 	    				taxesvo.setDemandCurrentUnits(taxesObj.getLong("currentDemandAssmts"));
						 	    				taxesvo.setCollectionCurrentUnits(taxesObj.getLong("currentCollectionAssmts"));
						 	    				taxesvo.setBalanceCurrentUnits(taxesObj.getLong("currentRemainingAssmts"));
						 	    				taxesvo.setTotalDemandAmount(String.valueOf(Long.valueOf(taxesObj.get("arrearDemand").toString())+Long.valueOf(taxesObj.get("currentDemand").toString())));//totalDemandAmunt)));
						 	    				taxesvo.setTotalCollectionAmount(String.valueOf(Long.valueOf(taxesObj.get("arrearCollection").toString())+Long.valueOf(taxesObj.get("currentCollection").toString())));//totalCollectionAmunt)));
						 	    				taxesvo.setTotalBalanceAmount(String.valueOf(Long.valueOf(taxesObj.get("arrearRemainingTax").toString())+Long.valueOf(taxesObj.get("currentRemainingTax").toString())));//totalBalalnceAmunt)));
						 	    				taxesvo.setTotalDemandUnits(taxesvo.getDemandArrearUnits()+taxesvo.getDemandCurrentUnits());
						 	    				taxesvo.setTotalCollectionUnts(taxesvo.getCollectionArrearUnits()+taxesvo.getCollectionCurrentUnits());
						 	    				taxesvo.setTotalBalanceUnits(taxesvo.getBalanceArrearUnits()+taxesvo.getBalanceCurrentUnits());
						 	    				
						 	    				
						 	    				if(taxesvo.getTotalDemandUnits() != null && Double.valueOf(taxesvo.getTotalDemandUnits()) > 0 && taxesvo.getTotalCollectionUnts() != null
														&& Double.valueOf(taxesvo.getTotalCollectionUnts()) > 0){
						 	    					taxesvo.setCollectionUnitsPerc(new BigDecimal(Double.valueOf(taxesvo.getTotalCollectionUnts()) * 100.00/ Double.valueOf(taxesvo.getTotalDemandUnits())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
												} else {
													taxesvo.setCollectionUnitsPerc("0.00");
												}
						 	    				if(taxesvo.getTotalDemandUnits() != null && Double.valueOf(taxesvo.getTotalDemandUnits()) > 0 && taxesvo.getTotalBalanceUnits() != null
														&& Double.valueOf(taxesvo.getTotalBalanceUnits()) > 0){
						 	    					taxesvo.setBalUnitsPerc(new BigDecimal(Double.valueOf(taxesvo.getTotalBalanceUnits()) * 100.00/ Double.valueOf(taxesvo.getTotalDemandUnits())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
												} else {
													taxesvo.setBalUnitsPerc("0.00");
												}
						 	    				taxTypeMap.put(taxType, taxesvo);
				 	    					}else{
				 	    						taxesvo.setDemandArrearAmount(String.valueOf(Long.valueOf(taxesvo.getDemandArrearAmount())+Long.valueOf(taxesObj.get("arrearDemand").toString())));//demandArrearAmuntType.toString()));
				 	    						taxesvo.setCollectionArrearAmount(String.valueOf(Long.valueOf(taxesvo.getCollectionArrearAmount())+Long.valueOf(taxesObj.get("arrearCollection").toString())));//collectionArrearAmuntType.toString()));
						 	    				taxesvo.setBalanceArrearAmount(String.valueOf(Long.valueOf(taxesvo.getBalanceArrearAmount().split(" ")[0])+Long.valueOf(taxesObj.get("arrearRemainingTax").toString())));//balArrearAmuntType.toString()));
						 	    				taxesvo.setDemandCurrentAmount(String.valueOf(Long.valueOf(taxesvo.getDemandCurrentAmount().split(" ")[0])+Long.valueOf(taxesObj.get("currentDemand").toString())));//demandCuurentAmuntType.toString()));
						 	    				taxesvo.setCollectionCurrentAmount(String.valueOf(Long.valueOf(taxesvo.getCollectionCurrentAmount().split(" ")[0])+Long.valueOf(taxesObj.get("currentCollection").toString())));//collectionCuurentAmuntType.toString()));
						 	    				taxesvo.setBalanceCurentAmount(String.valueOf(Long.valueOf(taxesvo.getBalanceCurentAmount().split(" ")[0])+Long.valueOf(taxesObj.get("currentRemainingTax").toString())));//balCuurentAmuntType.toString()));
						 	    				
						 	    				taxesvo.setDemandArrearUnits(taxesvo.getDemandArrearUnits()+taxesObj.getLong("arrearDemandAssmts"));
						 	    				taxesvo.setCollectionArrearUnits(taxesvo.getCollectionArrearUnits()+taxesObj.getLong("arrearCollectionAssmts"));
						 	    				taxesvo.setBalanceArrearUnits(taxesvo.getBalanceArrearUnits()+taxesObj.getLong("arrearRemainingAssmts"));
						 	    				taxesvo.setDemandCurrentUnits(taxesvo.getDemandCurrentUnits()+taxesObj.getLong("currentDemandAssmts"));
						 	    				taxesvo.setCollectionCurrentUnits(taxesvo.getCollectionCurrentUnits()+taxesObj.getLong("currentCollectionAssmts"));
						 	    				taxesvo.setBalanceCurrentUnits(taxesvo.getBalanceCurrentUnits()+taxesObj.getLong("currentRemainingAssmts"));
						 	    				taxesvo.setTotalDemandAmount(String.valueOf(Long.valueOf(taxesvo.getDemandArrearAmount())+Long.valueOf(taxesvo.getDemandCurrentAmount())));
						 	    				taxesvo.setTotalCollectionAmount(String.valueOf(Long.valueOf(taxesvo.getCollectionArrearAmount())+Long.valueOf(taxesvo.getCollectionCurrentAmount())));
						 	    				taxesvo.setTotalBalanceAmount(String.valueOf(Long.valueOf(taxesvo.getBalanceArrearAmount())+Long.valueOf(taxesvo.getBalanceCurentAmount())));
						 	    				taxesvo.setTotalDemandUnits(taxesvo.getDemandArrearUnits()+taxesvo.getDemandCurrentUnits());
						 	    				taxesvo.setTotalCollectionUnts(taxesvo.getCollectionArrearUnits()+taxesvo.getCollectionCurrentUnits());
						 	    				taxesvo.setTotalBalanceUnits(taxesvo.getBalanceArrearUnits()+taxesvo.getBalanceCurrentUnits());
						 	    				
						 	    				if(taxesvo.getTotalDemandUnits() != null && Double.valueOf(taxesvo.getTotalDemandUnits()) > 0 && taxesvo.getTotalCollectionUnts() != null
														&& Double.valueOf(taxesvo.getTotalCollectionUnts()) > 0){
						 	    					taxesvo.setCollectionUnitsPerc(new BigDecimal(Double.valueOf(taxesvo.getTotalCollectionUnts()) * 100.00/ Double.valueOf(taxesvo.getTotalDemandUnits())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
												} else {
													taxesvo.setCollectionUnitsPerc("0.00");
												}
						 	    				if(taxesvo.getTotalDemandUnits() != null && Double.valueOf(taxesvo.getTotalDemandUnits()) > 0 && taxesvo.getTotalBalanceUnits() != null
														&& Double.valueOf(taxesvo.getTotalBalanceUnits()) > 0){
						 	    					taxesvo.setBalUnitsPerc(new BigDecimal(Double.valueOf(taxesvo.getTotalBalanceUnits()) * 100.00/ Double.valueOf(taxesvo.getTotalDemandUnits())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
												} else {
													taxesvo.setBalUnitsPerc("0.00");
												}
				 	    					}
				 	    				}else{
				 	    					TaxesVO feesvo = feesMap.get(taxType);
				 	    					if(feesvo == null){
				 	    						feesvo = new TaxesVO();
				 	    					
					 	    				if(taxType.longValue() == 4L){
				 	    						feesvo.setId(taxType);
				 	    						feesvo.setName("Trade License");
				 	    					}else if(taxType.longValue() == 5L){
				 	    						feesvo.setId(taxType);
				 	    						feesvo.setName("Auctions");
				 	    					}else if(taxType.longValue() == 6L){
				 	    						feesvo.setId(taxType);
				 	    						feesvo.setName("Private Tap");
				 	    					}else if(taxType.longValue() == 111L){
				 	    						feesvo.setId(taxType);
				 	    						feesvo.setName("Others");
				 	    					}
					 	    				feesvo.setDemandArrearAmount(taxesObj.get("arrearDemand").toString());
					 	    				feesvo.setCollectionArrearAmount(taxesObj.get("arrearCollection").toString());
					 	    				feesvo.setBalanceArrearAmount(taxesObj.get("arrearRemainingTax").toString());
					 	    				feesvo.setDemandCurrentAmount(taxesObj.get("currentDemand").toString());
					 	    				feesvo.setCollectionCurrentAmount(taxesObj.get("currentCollection").toString());
					 	    				feesvo.setBalanceCurentAmount(taxesObj.get("currentRemainingTax").toString());
					 	    				feesvo.setDemandArrearUnits(taxesObj.getLong("arrearDemandAssmts"));
					 	    				feesvo.setCollectionArrearUnits(taxesObj.getLong("arrearCollectionAssmts"));
					 	    				feesvo.setBalanceArrearUnits(taxesObj.getLong("arrearRemainingAssmts"));
					 	    				feesvo.setDemandCurrentUnits(taxesObj.getLong("currentDemandAssmts"));
					 	    				feesvo.setCollectionCurrentUnits(taxesObj.getLong("currentCollectionAssmts"));
					 	    				feesvo.setBalanceCurrentUnits(taxesObj.getLong("currentRemainingAssmts"));
					 	    				feesvo.setTotalDemandAmount(String.valueOf(Long.valueOf(taxesObj.get("arrearDemand").toString())+Long.valueOf(taxesObj.get("currentDemand").toString())));//totalDemandAmunt)));
					 	    				feesvo.setTotalCollectionAmount(String.valueOf(Long.valueOf(taxesObj.get("arrearCollection").toString())+Long.valueOf(taxesObj.get("currentCollection").toString())));//totalCollectionAmunt)));
					 	    				feesvo.setTotalBalanceAmount(String.valueOf(Long.valueOf(taxesObj.get("arrearRemainingTax").toString())+Long.valueOf(taxesObj.get("currentRemainingTax").toString())));//totalBalalnceAmunt)));
					 	    				feesvo.setTotalDemandUnits(feesvo.getDemandArrearUnits()+feesvo.getDemandCurrentUnits());
					 	    				feesvo.setTotalCollectionUnts(feesvo.getCollectionArrearUnits()+feesvo.getCollectionCurrentUnits());
					 	    				feesvo.setTotalBalanceUnits(feesvo.getBalanceArrearUnits()+feesvo.getBalanceCurrentUnits());
					 	    				
					 	    				if(feesvo.getTotalDemandUnits() != null && Double.valueOf(feesvo.getTotalDemandUnits()) > 0 && feesvo.getTotalCollectionUnts() != null
													&& Double.valueOf(feesvo.getTotalCollectionUnts()) > 0){
					 	    					feesvo.setCollectionUnitsPerc(new BigDecimal(Double.valueOf(feesvo.getTotalCollectionUnts()) * 100.00/ Double.valueOf(feesvo.getTotalDemandUnits())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
											} else {
												feesvo.setCollectionUnitsPerc("0.00");
											}
					 	    				if(feesvo.getTotalDemandUnits() != null && Double.valueOf(feesvo.getTotalDemandUnits()) > 0 && feesvo.getTotalBalanceUnits() != null
													&& Double.valueOf(feesvo.getTotalBalanceUnits()) > 0){
					 	    					feesvo.setBalUnitsPerc(new BigDecimal(Double.valueOf(feesvo.getTotalBalanceUnits()) * 100.00/ Double.valueOf(feesvo.getTotalDemandUnits())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
											} else {
												feesvo.setBalUnitsPerc("0.00");
											}
					 	    				feesMap.put(taxType, feesvo);
			 	    					}else{
			 	    						
					 	    				feesvo.setDemandArrearAmount(String.valueOf(Long.valueOf(feesvo.getDemandArrearAmount())+Long.valueOf(taxesObj.get("arrearDemand").toString())));//demandArrearAmuntType.toString()));
					 	    				feesvo.setCollectionArrearAmount(String.valueOf(Long.valueOf(feesvo.getCollectionArrearAmount())+Long.valueOf(taxesObj.get("arrearCollection").toString())));//collectionArrearAmuntType.toString()));
					 	    				feesvo.setBalanceArrearAmount(String.valueOf(Long.valueOf(feesvo.getBalanceArrearAmount())+Long.valueOf(taxesObj.get("arrearRemainingTax").toString())));//balArrearAmuntType.toString()));
					 	    				feesvo.setDemandCurrentAmount(String.valueOf(Long.valueOf(feesvo.getDemandCurrentAmount())+Long.valueOf(taxesObj.get("currentDemand").toString())));//demandCuurentAmuntType.toString()));
					 	    				feesvo.setCollectionCurrentAmount(String.valueOf(Long.valueOf(feesvo.getCollectionCurrentAmount())+Long.valueOf(taxesObj.get("currentCollection").toString())));//collectionCuurentAmuntType.toString()));
					 	    				feesvo.setBalanceCurentAmount(String.valueOf(Long.valueOf(feesvo.getBalanceCurentAmount())+Long.valueOf(taxesObj.get("currentRemainingTax").toString())));//balCuurentAmuntType.toString()));
					 	    				
					 	    				feesvo.setDemandArrearUnits(feesvo.getDemandArrearUnits()+taxesObj.getLong("arrearDemandAssmts"));
					 	    				feesvo.setCollectionArrearUnits(feesvo.getCollectionArrearUnits()+taxesObj.getLong("arrearCollectionAssmts"));
					 	    				feesvo.setBalanceArrearUnits(feesvo.getBalanceArrearUnits()+taxesObj.getLong("arrearRemainingAssmts"));
					 	    				feesvo.setDemandCurrentUnits(feesvo.getDemandCurrentUnits()+taxesObj.getLong("currentDemandAssmts"));
					 	    				feesvo.setCollectionCurrentUnits(feesvo.getCollectionCurrentUnits()+taxesObj.getLong("currentCollectionAssmts"));
					 	    				feesvo.setBalanceCurrentUnits(feesvo.getBalanceCurrentUnits()+taxesObj.getLong("currentRemainingAssmts"));
					 	    				feesvo.setTotalDemandAmount(String.valueOf(Long.valueOf(feesvo.getDemandArrearAmount())+Long.valueOf(feesvo.getDemandCurrentAmount())));
					 	    				feesvo.setTotalCollectionAmount(String.valueOf(Long.valueOf(feesvo.getCollectionArrearAmount())+Long.valueOf(feesvo.getCollectionCurrentAmount())));
					 	    				feesvo.setTotalBalanceAmount(String.valueOf(Long.valueOf(feesvo.getBalanceArrearAmount())+Long.valueOf(feesvo.getBalanceCurentAmount())));
					 	    				feesvo.setTotalDemandUnits(feesvo.getDemandArrearUnits()+feesvo.getDemandCurrentUnits());
					 	    				feesvo.setTotalCollectionUnts(feesvo.getCollectionArrearUnits()+feesvo.getCollectionCurrentUnits());
					 	    				feesvo.setTotalBalanceUnits(feesvo.getBalanceArrearUnits()+feesvo.getBalanceCurrentUnits());
					 	    				
					 	    				if(feesvo.getTotalDemandUnits() != null && Double.valueOf(feesvo.getTotalDemandUnits()) > 0 && feesvo.getTotalCollectionUnts() != null
													&& Double.valueOf(feesvo.getTotalCollectionUnts()) > 0){
					 	    					feesvo.setCollectionUnitsPerc(new BigDecimal(Double.valueOf(feesvo.getTotalCollectionUnts()) * 100.00/ Double.valueOf(feesvo.getTotalDemandUnits())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
											} else {
												feesvo.setCollectionUnitsPerc("0.00");
											}
					 	    				if(feesvo.getTotalDemandUnits() != null && Double.valueOf(feesvo.getTotalDemandUnits()) > 0 && feesvo.getTotalBalanceUnits() != null
													&& Double.valueOf(feesvo.getTotalBalanceUnits()) > 0){
					 	    					feesvo.setBalUnitsPerc(new BigDecimal(Double.valueOf(feesvo.getTotalBalanceUnits()) * 100.00/ Double.valueOf(feesvo.getTotalDemandUnits())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
											} else {
												feesvo.setBalUnitsPerc("0.00");
											}
			 	    					}
			 	    				}
			 	    				
			 	    			}
			 	    		}
	 	    				
	 	    			}
	 	    		}
	 	    	}	
		 	 }
        	
			finalVO.setDemandArrearAmount(convertRupeesIntocrores(demandArrearAmunt.toString()));
			finalVO.setCollectionArrearAmount(convertRupeesIntocrores(collectionArrearAmunt.toString()));
			finalVO.setBalanceArrearAmount(convertRupeesIntocrores(balArrearAmunt.toString()));
			finalVO.setDemandCurrentAmount(convertRupeesIntocrores(demandCuurentAmunt.toString()));
			finalVO.setCollectionCurrentAmount(convertRupeesIntocrores(collectionCuurentAmunt.toString()));
			finalVO.setBalanceCurentAmount(convertRupeesIntocrores(balCuurentAmunt.toString()));
			finalVO.setTotalDemandAmount(convertRupeesIntocrores(String.valueOf(totalDemandAmunt)));
			finalVO.setTotalCollectionAmount(convertRupeesIntocrores(String.valueOf(totalCollectionAmunt)));
			finalVO.setTotalBalanceAmount(convertRupeesIntocrores(String.valueOf(totalBalalnceAmunt)));
			finalVO.setDemandArrearUnits(demandArrearUnits);
			finalVO.setCollectionArrearUnits(collecionArrearUnits);
			finalVO.setBalanceArrearUnits(balArrearUnits);
			finalVO.setDemandCurrentUnits(demandCurrentUnits);
			finalVO.setCollectionCurrentUnits(collectionCurrentUnits);
			finalVO.setBalanceCurrentUnits(balCurrentUnits);
			finalVO.setTotalDemandUnits(finalVO.getDemandArrearUnits()+finalVO.getDemandCurrentUnits());
			finalVO.setTotalCollectionUnts(finalVO.getCollectionArrearUnits()+finalVO.getCollectionCurrentUnits());
			finalVO.setTotalBalanceUnits(finalVO.getBalanceArrearUnits()+finalVO.getBalanceCurrentUnits());	
				
			if(commonMethodsUtilService.isMapValid(taxTypeMap)){
	     		finalVO.getTaxesList().addAll(taxTypeMap.values());
	     	}
	     	if(commonMethodsUtilService.isMapValid(feesMap)){
	     		finalVO.getFeeList().addAll(feesMap.values());
	     	}
	     	if(finalVO.getTaxesList() != null && !finalVO.getTaxesList().isEmpty()){
	     		for (TaxesVO taxesVO : finalVO.getTaxesList()) {
	     			taxesVO.setDemandArrearAmount(convertRupeesIntocrores(taxesVO.getDemandArrearAmount()));
	     			taxesVO.setCollectionArrearAmount(convertRupeesIntocrores(taxesVO.getCollectionArrearAmount()));
	     			taxesVO.setBalanceArrearAmount(convertRupeesIntocrores(taxesVO.getBalanceArrearAmount()));
	     			taxesVO.setDemandCurrentAmount(convertRupeesIntocrores(taxesVO.getDemandCurrentAmount()));
	     			taxesVO.setCollectionCurrentAmount(convertRupeesIntocrores(taxesVO.getCollectionCurrentAmount()));
	     			taxesVO.setBalanceCurentAmount(convertRupeesIntocrores(taxesVO.getBalanceCurentAmount()));
	     			taxesVO.setTotalDemandAmount(convertRupeesIntocrores(taxesVO.getTotalDemandAmount()));
	     			taxesVO.setTotalCollectionAmount(convertRupeesIntocrores(taxesVO.getTotalCollectionAmount()));
	     			taxesVO.setTotalBalanceAmount(convertRupeesIntocrores(taxesVO.getTotalBalanceAmount()));
	     			
	     			if(taxesVO.getTotalDemandAmount() != null && Double.valueOf(taxesVO.getTotalDemandAmount()) > 0 && taxesVO.getTotalCollectionAmount() != null && Double.valueOf(taxesVO.getTotalCollectionAmount()) > 0){
	     				taxesVO.setCollectionAmuntPerc(new BigDecimal(Double.valueOf(taxesVO.getTotalCollectionAmount()) * 100.00/ Double.valueOf(taxesVO.getTotalDemandAmount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					} else {
						taxesVO.setCollectionAmuntPerc("0.00");
					}
	    				if(taxesVO.getTotalDemandAmount() != null && Double.valueOf(taxesVO.getTotalDemandAmount()) > 0 && taxesVO.getTotalBalanceAmount()  != null && Double.valueOf(taxesVO.getTotalBalanceAmount()) > 0){
	    					taxesVO.setBalAmuntPerc(new BigDecimal(Double.valueOf(taxesVO.getTotalBalanceAmount()) * 100.00/ Double.valueOf(taxesVO.getTotalDemandAmount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					} else {
						taxesVO.setBalAmuntPerc("0.00");
					}
				}
	     	}
	     	if(finalVO.getFeeList() != null && !finalVO.getFeeList().isEmpty()){
	     		for (TaxesVO feeVO : finalVO.getFeeList()) {
	     			feeVO.setDemandArrearAmount(convertRupeesIntocrores(feeVO.getDemandArrearAmount()));
	     			feeVO.setCollectionArrearAmount(convertRupeesIntocrores(feeVO.getCollectionArrearAmount()));
	     			feeVO.setBalanceArrearAmount(convertRupeesIntocrores(feeVO.getBalanceArrearAmount()));
	     			feeVO.setDemandCurrentAmount(convertRupeesIntocrores(feeVO.getDemandCurrentAmount()));
	     			feeVO.setCollectionCurrentAmount(convertRupeesIntocrores(feeVO.getCollectionCurrentAmount()));
	     			feeVO.setBalanceCurentAmount(convertRupeesIntocrores(feeVO.getBalanceCurentAmount()));
	     			feeVO.setTotalDemandAmount(convertRupeesIntocrores(feeVO.getTotalDemandAmount()));
	     			feeVO.setTotalCollectionAmount(convertRupeesIntocrores(feeVO.getTotalCollectionAmount()));
	     			feeVO.setTotalBalanceAmount(convertRupeesIntocrores(feeVO.getTotalBalanceAmount()));
	     			
	     			if(feeVO.getTotalDemandAmount() != null && Double.valueOf(feeVO.getTotalDemandAmount()) > 0 && feeVO.getTotalCollectionAmount()  != null && Double.valueOf(feeVO.getTotalCollectionAmount()) > 0){
	     				feeVO.setCollectionAmuntPerc(new BigDecimal(Double.valueOf(feeVO.getTotalCollectionAmount()) * 100.00/ Double.valueOf(feeVO.getTotalDemandAmount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					} else {
						feeVO.setCollectionAmuntPerc("0.00");
					}
	    				if(feeVO.getTotalDemandAmount() != null && Double.valueOf(feeVO.getTotalDemandAmount()) > 0 && feeVO.getTotalBalanceAmount()  != null && Double.valueOf(feeVO.getTotalBalanceAmount()) > 0){
	    					feeVO.setBalAmuntPerc(new BigDecimal(Double.valueOf(feeVO.getTotalBalanceAmount()) * 100.00/ Double.valueOf(feeVO.getTotalDemandAmount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					} else {
						feeVO.setBalAmuntPerc("0.00");
					}
				}
	     	}
			
		} catch (Exception e) {
			LOG.error("Exception occured at getTaxesAndCategoryWiseOverViewDetails() in  TaxesDashBoardService class",e);
		}
		
		return finalVO;
	}
	
	public String convertRupeesIntocrores(String value){
		String returnVal = null;
		try {
			if(value != null){
				returnVal = new BigDecimal(Double.valueOf(value)/10000000.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				returnVal = returnVal;
			}
		} catch (Exception e) {
			LOG.error("Exception raised at convertRupeesIntocrores in TaxesDashBoardService service", e);
		}
		return returnVal;
	}

	public String convertRupeesIntoLakhes(String value){
		String returnVal = null;
		try {
			if(value != null){
				returnVal = new BigDecimal(Double.valueOf(value)/100000.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				returnVal = returnVal;
			}
		} catch (Exception e) {
			LOG.error("Exception raised at convertRupeesIntoLakhes  in TaxesDashBoardService service", e);
		}
		return returnVal;
	}
	/* Author : Nandhini.k,
	 * Date : 27/02/2018,
	 * Input : locationId,location,fromDate,toDate,
	 * @Description  : To Get Taxes Indicator Details 
	 */
	public List<TaxesVO> getTaxesIndicatorDetails(InputVO inputVO){
		List<TaxesVO> returnList = new ArrayList<TaxesVO>(0);
		try {
			
			Map<String,TaxesVO> indicatorsMap = new LinkedHashMap<String,TaxesVO>(0);
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://pris.ap.gov.in/api/taxes/taxstats.php?indicatorsOverview=1%20&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate()+"");
	        ClientResponse response = webResource.get(ClientResponse.class);
				
        	if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray districtArray = new JSONArray(output);
	 	    		if(districtArray!=null && districtArray.length()>0){
	 	    			for(int i=0;i<districtArray.length();i++){
	 	    				JSONObject districtObj = (JSONObject) districtArray.get(i);
	 	    				JSONArray taxesArray = (JSONArray) districtObj.get("usages");
	 	    				if(taxesArray!=null && taxesArray.length()>0){
			 	    			for(int j=0;j<taxesArray.length();j++){
			 	    				JSONObject taxesObj = (JSONObject) taxesArray.get(j);
			 	    				String usageName = taxesObj.get("usage_name").toString();
			 	    				TaxesVO indicatorVO = indicatorsMap.get(usageName);
			 	    				if(indicatorVO == null){
			 	    					indicatorVO = new TaxesVO();
			 	    					 
		 	    					indicatorVO.setId(Long.valueOf(taxesObj.get("usage_type").toString()));
		 	    					indicatorVO.setName(taxesObj.get("usage_name").toString());
		 	    					indicatorVO.setTotalDemandAmount(String.valueOf(Long.valueOf(taxesObj.get("Demand").toString())));
			 	    				indicatorVO.setTotalCollectionAmount(String.valueOf(Long.valueOf(taxesObj.get("Collection").toString())));
			 	    				indicatorVO.setTotalBalanceAmount(String.valueOf(Long.valueOf(indicatorVO.getTotalDemandAmount())-Long.valueOf(indicatorVO.getTotalCollectionAmount())));
			 	    				indicatorVO.setTotalDemandUnits(Long.valueOf(taxesObj.get("DemandAssmts").toString()));
		 	    					indicatorVO.setTotalCollectionUnts(Long.valueOf(taxesObj.get("CollectionAssmts").toString()));
		 	    					indicatorVO.setTotalBalanceUnits(indicatorVO.getTotalDemandUnits()-indicatorVO.getTotalCollectionUnts());
		 	    					
		 	    					if(indicatorVO.getTotalDemandUnits() != null && indicatorVO.getTotalDemandUnits() > 0 && indicatorVO.getTotalCollectionUnts() != null
											&& indicatorVO.getTotalCollectionUnts() > 0){
			 	    					indicatorVO.setCollectionUnitsPerc(new BigDecimal(Double.valueOf(indicatorVO.getTotalCollectionUnts()) * 100.00/ Double.valueOf(indicatorVO.getTotalDemandUnits())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									} else {
										indicatorVO.setCollectionUnitsPerc("0.00");
									}
			 	    				
			 	    				if(indicatorVO.getTotalDemandUnits() != null && indicatorVO.getTotalDemandUnits() > 0 && indicatorVO.getTotalBalanceUnits() != null
											&& indicatorVO.getTotalBalanceUnits() > 0){
			 	    					indicatorVO.setBalUnitsPerc(new BigDecimal(Double.valueOf(indicatorVO.getTotalBalanceUnits()) * 100.00/ Double.valueOf(indicatorVO.getTotalDemandUnits())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									} else {
										indicatorVO.setBalUnitsPerc("0.00");
									}
			 	    				indicatorsMap.put(usageName, indicatorVO);
			 	    			}else{
		 	    					
		 	    					indicatorVO.setTotalDemandAmount(String.valueOf(Long.valueOf(indicatorVO.getTotalDemandAmount())+Long.valueOf(taxesObj.get("Demand").toString())));//demandAmount.toString()));
			 	    				indicatorVO.setTotalCollectionAmount(String.valueOf(Long.valueOf(indicatorVO.getTotalCollectionAmount())+Long.valueOf(taxesObj.get("Collection").toString())));//collectionAmount.toString()));
			 	    				indicatorVO.setTotalBalanceAmount(String.valueOf(Long.valueOf(indicatorVO.getTotalDemandAmount())-Long.valueOf(indicatorVO.getTotalCollectionAmount())));//balAmount.toString()));
			 	    				indicatorVO.setTotalDemandUnits(indicatorVO.getTotalDemandUnits()+Long.valueOf(taxesObj.get("DemandAssmts").toString()));//demandUnits);
		 	    					indicatorVO.setTotalCollectionUnts(indicatorVO.getTotalCollectionUnts()+Long.valueOf(taxesObj.get("CollectionAssmts").toString()));//collectionUnits);
		 	    					indicatorVO.setTotalBalanceUnits(indicatorVO.getTotalDemandUnits()-indicatorVO.getTotalCollectionUnts());
		 	    					
		 	    					if(indicatorVO.getTotalDemandUnits() != null && indicatorVO.getTotalDemandUnits() > 0 && indicatorVO.getTotalCollectionUnts() != null
											&& indicatorVO.getTotalCollectionUnts() > 0){
			 	    					indicatorVO.setCollectionUnitsPerc(new BigDecimal(Double.valueOf(indicatorVO.getTotalCollectionUnts()) * 100.00/ Double.valueOf(indicatorVO.getTotalDemandUnits())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									} else {
										indicatorVO.setCollectionUnitsPerc("0.00");
									}
			 	    				
			 	    				if(indicatorVO.getTotalDemandUnits() != null && indicatorVO.getTotalDemandUnits() > 0 && indicatorVO.getTotalBalanceUnits() != null
											&& indicatorVO.getTotalBalanceUnits() > 0){
			 	    					indicatorVO.setBalUnitsPerc(new BigDecimal(Double.valueOf(indicatorVO.getTotalBalanceUnits()) * 100.00/ Double.valueOf(indicatorVO.getTotalDemandUnits())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									} else {
										indicatorVO.setBalUnitsPerc("0.00");
									}
			 	    			}
		 	    			}
 	    				}
 	    			}
 	    		}
 	    	}
 	      }
	       if(commonMethodsUtilService.isMapValid(indicatorsMap)){
	    	   returnList = new ArrayList<TaxesVO>(indicatorsMap.values());
	       }
	       
	       if(returnList != null && !returnList.isEmpty()){
	    	   for (TaxesVO indicatorvo : returnList) {
	    		   indicatorvo.setTotalDemandAmount(convertRupeesIntocrores(indicatorvo.getTotalDemandAmount()));
	    		   indicatorvo.setTotalCollectionAmount(convertRupeesIntocrores(indicatorvo.getTotalCollectionAmount()));
	     		   indicatorvo.setTotalBalanceAmount(convertRupeesIntocrores(indicatorvo.getTotalBalanceAmount()));
	     		   
	     		    if(indicatorvo.getTotalDemandAmount() != null && Double.valueOf(indicatorvo.getTotalDemandAmount()) > 0 && indicatorvo.getTotalCollectionAmount() != null && Double.valueOf(indicatorvo.getTotalCollectionAmount()) > 0){
	     			 indicatorvo.setCollectionAmuntPerc(new BigDecimal(Double.valueOf(indicatorvo.getTotalCollectionAmount()) * 100.00/ Double.valueOf(indicatorvo.getTotalDemandAmount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					} else {
						indicatorvo.setCollectionAmuntPerc("0.00");
					}
					if(indicatorvo.getTotalDemandAmount() != null && Double.valueOf(indicatorvo.getTotalDemandAmount()) > 0 && indicatorvo.getTotalBalanceAmount() != null && Double.valueOf(indicatorvo.getTotalBalanceAmount()) > 0){
						indicatorvo.setBalAmuntPerc(new BigDecimal(Double.valueOf(indicatorvo.getTotalBalanceAmount()) * 100.00/ Double.valueOf(indicatorvo.getTotalDemandAmount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					} else {
						indicatorvo.setBalAmuntPerc("0.00");
					}
	    	   }
	       }
		} catch (Exception e) {
			LOG.error("Exception raised at getTaxesIndicatorDetails  in TaxesDashBoardService service", e);
		}
		return returnList;
	}
	/* Author : Nandhini.k,
	 * Date : 27/02/2018,
	 * Input : locationId,location,fromDate,toDate,
	 * @Description  : To Get Taxes Default Overview Details 
	 */
	public List<TaxesVO> getTaxesDefaultOverviewDetails(InputVO inputVO){
		List<TaxesVO> returnList = new ArrayList<TaxesVO>(0);
		try {
			Long belowTwoAmount = 0L;
			Long twotoFiveAmount = 0L;
			Long fiveToTwentyAmount = 0L;
			Long twentyToFivtyAmount = 0L;
			Long fivtyToOneLacAmunt = 0L;
			Long aboveOneLacAmount = 0L;
			Long belowTwoUnits = 0L;
			Long twotoFiveUnits = 0L;
			Long twentyToFivtyUnits = 0L;
			Long fivtyToOneLacUnits = 0L;
			Long aboveOneLacUnits = 0L;
			Long fiveToTwentyUnits = 0L;
			Long totalAmount = 0L;
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://pris.ap.gov.in/api/taxes/taxstats.php?defaultersOverview=1&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"");
	        ClientResponse response = webResource.get(ClientResponse.class);
				
        	if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray districtArray = new JSONArray(output);
	 	    		if(districtArray!=null && districtArray.length()>0){
	 	    			for(int i=0;i<districtArray.length();i++){
	 	    				JSONObject districtObj = (JSONObject) districtArray.get(i);
	 	    				JSONArray taxesArray = (JSONArray) districtObj.get("defaulters");
	 	    				if(taxesArray!=null && taxesArray.length()>0){
			 	    			for(int j=0;j<taxesArray.length();j++){
			 	    				JSONObject taxesObj = (JSONObject) taxesArray.get(j);
			 	    				belowTwoAmount = belowTwoAmount+Long.valueOf(taxesObj.get("defaulter1").toString());
			 	    				twotoFiveAmount = twotoFiveAmount+Long.valueOf(taxesObj.get("defaulter2").toString());
			 	    				fiveToTwentyAmount = fiveToTwentyAmount+Long.valueOf(taxesObj.get("defaulter3").toString());
			 	    				twentyToFivtyAmount = twentyToFivtyAmount+Long.valueOf(taxesObj.get("defaulter4").toString());
			 	    				fivtyToOneLacAmunt = fivtyToOneLacAmunt+Long.valueOf(taxesObj.get("defaulter5").toString());
			 	    				aboveOneLacAmount = aboveOneLacAmount+Long.valueOf(taxesObj.get("defaulter6").toString());
			 	    				belowTwoUnits = belowTwoUnits+Long.valueOf(taxesObj.get("defaulter1Ass").toString());
			 	    				twotoFiveUnits = twotoFiveUnits+Long.valueOf(taxesObj.get("defaulter2Ass").toString());
			 	    				twentyToFivtyUnits = twentyToFivtyUnits+Long.valueOf(taxesObj.get("defaulter4Ass").toString());
			 	    				fivtyToOneLacUnits = fivtyToOneLacUnits+Long.valueOf(taxesObj.get("defaulter5Ass").toString());
			 	    				aboveOneLacUnits = aboveOneLacUnits+Long.valueOf(taxesObj.get("defaulter6Ass").toString());
			 	    				//fiveToTwentyUnits = 0;
			 	    				totalAmount = belowTwoAmount+twotoFiveAmount+fiveToTwentyAmount+twentyToFivtyAmount+fivtyToOneLacAmunt+aboveOneLacAmount;
		 	    			}
 	    				}
 	    			}
 	    		}
 	    	}
 	      }
        Long amount = 0L;
        Long units = 0L;
    	Long id = 0L;
    	String[] taxDefaultTemplateArr  = {"Below Rs.2,000","Rs.2,000 - 5,000","Rs.5,000 - 20,000","Rs.20,000 - 50,000","Rs.50,000 - 1 Lac","Above 1 Lac"};
		if(taxDefaultTemplateArr != null){
			for (String string : taxDefaultTemplateArr) {
				TaxesVO vo = new TaxesVO();
				id = id+1;
				vo.setId(id);
				vo.setName(string);
				if(id != null && id.longValue() == 1L){
					amount = belowTwoAmount;
					units = belowTwoUnits;
				}else if(id != null && id.longValue() == 2L){
					amount = twotoFiveAmount;
					units = twotoFiveUnits;
				}else if(id != null && id.longValue() == 3L){
					amount = fiveToTwentyAmount;
					units = fiveToTwentyUnits;
				}else if(id != null && id.longValue() == 4L){
					amount = twentyToFivtyAmount;
					units = twentyToFivtyUnits;
				}else if(id != null && id.longValue() == 5L){
					amount = fivtyToOneLacAmunt;
					units = fivtyToOneLacUnits;
				}else if(id != null && id.longValue() == 6L){
					amount = aboveOneLacAmount;
					units = aboveOneLacUnits;
				}
				
 				vo.setTotalAmount(convertRupeesIntocrores(amount.toString()));
				vo.setTotalUnits(units);
				if(totalAmount != null && totalAmount > 0 && amount != null && amount > 0 ){
					vo.setPercentage(new BigDecimal(Double.valueOf(amount) * 100.00/ Double.valueOf(totalAmount)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				} else {
					vo.setPercentage("0.00");
				}
				returnList.add(vo);
			}
		}
	        	
		} catch (Exception e) {
			LOG.error("Exception raised at getTaxesIndicatorDetails  in TaxesDashBoardService service", e);
		}
		return returnList;
	}
	
	public List<PanchayatTaxVO> getPanchyatTaxDashboardFilterWiseDetails(panchayatTaxInputVO inputVO){
		List<PanchayatTaxVO> returnList = new ArrayList<PanchayatTaxVO>();
		try {
			String url = getURLBasedOnFilter(inputVO);
			ClientResponse response = webServiceUtilService.callWebService(url, null,IConstants.REQUEST_METHOD_GET);
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(inputVO.getDefaultersType() != null && inputVO.getDefaultersType().equalsIgnoreCase("Defaulters"))
	 	    		returnList = setDefaultersDataToList(output);
	 	    	else if(inputVO.getDefaultersType() != null && inputVO.getDefaultersType().equalsIgnoreCase("Indicators"))
	 	    		returnList = setIndicatorsDataToList(output);
	 	    	else
	 	    		returnList = setDataToList(output);
	 	    		
	 	      }
		} catch (Exception e) {
			LOG.error("Exception raised at getPanchyatTaxDashboardFilterWiseDetails  in TaxesDashBoardService service", e);
		}
		return returnList;
	}
	
	public String getURLBasedOnFilter(panchayatTaxInputVO inputVO){
		String url = null;
		try {
			Long taxTypeId = 0L;
			if(inputVO.getTaxTypeId() != null && inputVO.getTaxTypeId() > 0L)
				taxTypeId = inputVO.getTaxTypeId();
			else if(inputVO.getFeeTypeId() != null && inputVO.getFeeTypeId() > 0L)
				taxTypeId = inputVO.getFeeTypeId();
			
			if(inputVO.getDefaultersType() != null && inputVO.getDefaultersType().equalsIgnoreCase("Defaulters")){
				url = "http://pris.ap.gov.in/api/taxes/taxstats.php?defaultersData=1&locationId="+inputVO.getLocationValue()+"&locationType="+inputVO.getLocationType()+"";
			}else if(inputVO.getDefaultersType() != null && inputVO.getDefaultersType().equalsIgnoreCase("Indicators")){
				if(taxTypeId != null && taxTypeId > 0L){
					if(inputVO.getIndicatorsId() != null && inputVO.getIndicatorsId() > 0L)
						url = "http://pris.ap.gov.in/api/taxes/taxstats.php?indicatorsOverview=1&locationId="+inputVO.getLocationValue()+"&locationType="+inputVO.getLocationType()+"&taxtype="+taxTypeId+"&fromDate="+inputVO.getFromDateStr()+"&toDate="+inputVO.getToDateStr()+"&usage_type="+inputVO.getIndicatorsId()+"";
					else
						url = "http://pris.ap.gov.in/api/taxes/taxstats.php?indicatorsOverview=1&locationId="+inputVO.getLocationValue()+"&locationType="+inputVO.getLocationType()+"&taxtype="+taxTypeId+"&fromDate="+inputVO.getFromDateStr()+"&toDate="+inputVO.getToDateStr()+"";
				}
				else{
					if(inputVO.getIndicatorsId() != null && inputVO.getIndicatorsId() > 0L)
						url = "http://pris.ap.gov.in/api/taxes/taxstats.php?indicatorsOverview=1&locationId="+inputVO.getLocationValue()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDateStr()+"&toDate="+inputVO.getToDateStr()+"&usage_type="+inputVO.getIndicatorsId()+"";
					else
						url = "http://pris.ap.gov.in/api/taxes/taxstats.php?indicatorsOverview=1&locationId="+inputVO.getLocationValue()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDateStr()+"&toDate="+inputVO.getToDateStr()+"";
				}
			}else{
				url = "http://pris.ap.gov.in/api/taxes/taxstats.php?taxStatistics=1&locationId="+inputVO.getLocationValue()+"&locationType="+inputVO.getLocationType()+"&taxtype="+taxTypeId+"&fromDate="+inputVO.getFromDateStr()+"&toDate="+inputVO.getToDateStr()+"";
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getURLBasedOnFilter  in TaxesDashBoardService service", e);
		}
		return url;
	}
	
	public List<PanchayatTaxVO> setDefaultersDataToList(String output){
		List<PanchayatTaxVO> returnList = new ArrayList<PanchayatTaxVO>();
		try {
			if(output != null && !output.isEmpty()){
 	    		JSONArray jsonArray = new JSONArray(output);
 	    		if(jsonArray!=null && jsonArray.length()>0){
 	    			for(int i=0;i<jsonArray.length();i++){
 	    				JSONObject jObj = (JSONObject) jsonArray.get(i);
 	    				PanchayatTaxVO vo = new PanchayatTaxVO();
 	    				vo.setDistrictId(jObj.has("id") ? jObj.getLong("id") : null);
 	    				vo.setDistrictName(jObj.has("district") ? jObj.getString("district") : null);
 	    				vo.setParliamentId(jObj.has("parliamentId") ? jObj.getLong("parliamentId") : null);
 	    				vo.setParliamentName(jObj.has("parliamentName") ? jObj.getString("parliamentName") : null);
 	    				vo.setAssemblyId(jObj.has("assemblyId") ? jObj.getLong("assemblyId") : null);
 	    				vo.setAssemblyName(jObj.has("assemblyName") ? jObj.getString("assemblyName") : null);
 	    				vo.setMandalId(jObj.has("mandalId") ? jObj.getLong("mandalId") : null);
 	    				vo.setMandalName(jObj.has("mandalName") ? jObj.getString("mandalName") : null);
 	    				vo.setPanchayatId(jObj.has("panchayat") ? jObj.getLong("panchayat") : null);
 	    				vo.setPanchyatName(jObj.has("panchayat_name") ? jObj.getString("panchayat_name") : null);
 	    				
 	    				vo.setTaxTypeId(jObj.has("taxtype") ? jObj.getLong("taxtype") : null);
 	    				vo.setTaxType(getTaxTypeNameById(vo.getTaxTypeId()));
 	    				vo.setName(jObj.has("name") ? jObj.getString("name") : null);
 	    				vo.setAmount(jObj.has("amount") ? jObj.getString("amount") : null);
 	    				vo.setDueYear(jObj.has("due_year") ? jObj.getString("due_year") : null);
 	    				
 	    				returnList.add(vo);
 	    			}
 	    		}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at setDefaultersDataToList  in TaxesDashBoardService service", e);
		}
		return returnList;
	}
	
	public List<PanchayatTaxVO> setIndicatorsDataToList(String output){
		List<PanchayatTaxVO> returnList = new ArrayList<PanchayatTaxVO>();
		try {
			Map<Long,PanchayatTaxVO> usageTypeMap = new LinkedHashMap<Long,PanchayatTaxVO>();
			
			if(output != null && !output.isEmpty()){
 	    		JSONArray jsonArray = new JSONArray(output);
 	    		if(jsonArray!=null && jsonArray.length()>0){
 	    			for(int i=0;i<jsonArray.length();i++){
 	    				JSONObject jObj = (JSONObject) jsonArray.get(i);
 	    				JSONArray usagesArr = jObj.has("usages") ? jObj.getJSONArray("usages") : null;
 	    				if(usagesArr!=null && usagesArr.length()>0){
 	    	    			for(int j=0;j<usagesArr.length();j++){
 	    	    				JSONObject usgJObj = (JSONObject) jsonArray.get(j);
 	    	    				Long usageId = usgJObj.has("usage_type") ? usgJObj.getLong("usage_type") : 0L;
 	    	    				PanchayatTaxVO vo = usageTypeMap.get(usageId);
 	    	    				if(vo == null){
 	    	    					vo = new PanchayatTaxVO();
 	    	    					vo.setUsageTypeId(usageId);
 	    	    					vo.setUsageName(usgJObj.has("usage_name") ? usgJObj.getString("usage_name") : null);
 	    	    					vo.setDemand(usgJObj.has("Demand") ? usgJObj.getString("Demand") : null);
 	    	    					vo.setBalance(usgJObj.has("Balance") ? usgJObj.getString("Balance") : null);
 	    	    					vo.setCollection(usgJObj.has("Collection") ? usgJObj.getString("Collection") : null);
 	    	    					vo.setDemandAssmts(usgJObj.has("DemandAssmts") ? usgJObj.getString("DemandAssmts") : null);
 	    	    					vo.setBalanceAssmts(usgJObj.has("BalanceAssmts") ? usgJObj.getString("BalanceAssmts") : null);
 	    	    					vo.setCollectionAssmts(usgJObj.has("CollectionAssmts") ? usgJObj.getString("CollectionAssmts") : null);
 	    	    					usageTypeMap.put(usageId, vo);
 	    	    				}else{
 	    	    					vo.setDemand(usgJObj.has("Demand") ? String.valueOf(Long.valueOf(vo.getDemand())+Long.valueOf(usgJObj.getString("Demand"))) : vo.getDemand());
 	    	    					vo.setCollection(usgJObj.has("Collection") ? String.valueOf(Long.valueOf(vo.getCollection())+Long.valueOf(usgJObj.getString("Collection"))) : vo.getCollection());
 	    	    					vo.setBalance(usgJObj.has("Balance") ? String.valueOf(Long.valueOf(vo.getBalance())+Long.valueOf(usgJObj.getString("Balance"))) : vo.getBalance());
 	    	    					vo.setDemandAssmts(usgJObj.has("DemandAssmts") ? String.valueOf(Long.valueOf(vo.getDemandAssmts())+Long.valueOf(usgJObj.getString("DemandAssmts"))) : vo.getDemandAssmts());
 	    	    					vo.setCollectionAssmts(usgJObj.has("CollectionAssmts") ? String.valueOf(Long.valueOf(vo.getCollectionAssmts())+Long.valueOf(usgJObj.getString("CollectionAssmts"))) : vo.getCollectionAssmts());
 	    	    					vo.setBalanceAssmts(usgJObj.has("BalanceAssmts") ? String.valueOf(Long.valueOf(vo.getBalanceAssmts())+Long.valueOf(usgJObj.getString("BalanceAssmts"))) : vo.getBalanceAssmts());
 	    	    				}
 	    	    			}
 	    				}
 	    			}
 	    		}
			}
			
			if(usageTypeMap != null)
				returnList = new ArrayList<PanchayatTaxVO>(usageTypeMap.values());
			
		} catch (Exception e) {
			LOG.error("Exception raised at setIndicatorsDataToList  in TaxesDashBoardService service", e);
		}
		return returnList;
	}
	
	public List<PanchayatTaxVO> setDataToList(String output){
		List<PanchayatTaxVO> returnList = new ArrayList<PanchayatTaxVO>();
		try {
			if(output != null && !output.isEmpty()){
 	    		JSONArray jsonArray = new JSONArray(output);
 	    		if(jsonArray!=null && jsonArray.length()>0){
 	    			for(int i=0;i<jsonArray.length();i++){
 	    				JSONObject jObj = (JSONObject) jsonArray.get(i);
 	    				PanchayatTaxVO mainvo = new PanchayatTaxVO();
 	    				mainvo.setDistrictId(jObj.has("id") ? jObj.getLong("id") : null);
 	    				mainvo.setDistrictName(jObj.has("district") ? jObj.getString("district") : null);
 	    				mainvo.setParliamentId(jObj.has("parliamentId") ? jObj.getLong("parliamentId") : null);
 	    				mainvo.setParliamentName(jObj.has("parliamentName") ? jObj.getString("parliamentName") : null);
 	    				mainvo.setAssemblyId(jObj.has("assemblyId") ? jObj.getLong("assemblyId") : null);
 	    				mainvo.setAssemblyName(jObj.has("assemblyName") ? jObj.getString("assemblyName") : null);
 	    				mainvo.setMandalId(jObj.has("mandalId") ? jObj.getLong("mandalId") : null);
 	    				mainvo.setMandalName(jObj.has("mandalName") ? jObj.getString("mandalName") : null);
 	    				mainvo.setPanchayatId(jObj.has("panchayat") ? jObj.getLong("panchayat") : null);
 	    				mainvo.setPanchyatName(jObj.has("panchayat_name") ? jObj.getString("panchayat_name") : null);
 	    				
 	    				JSONArray taxArr = new JSONArray();
 	    				if(taxArr != null && taxArr.length()>0){
 	    					for(int j=0;j<taxArr.length();j++){
 	    						JSONObject taxJObj = (JSONObject) jsonArray.get(j);
 	    	    				PanchayatTaxVO subvo = new PanchayatTaxVO();
 	    	    				subvo.setTaxTypeId(taxJObj.has("id") ? taxJObj.getLong("id") : null);
 	    	    				subvo.setTaxType(getTaxTypeNameById(subvo.getTaxTypeId()));
 	    	    				subvo.setArrearDemand(taxJObj.has("arrearDemand") ? taxJObj.getString("arrearDemand") : null);
 	    	    				subvo.setArrearCollection(taxJObj.has("arrearCollection") ? taxJObj.getString("arrearCollection") : null);
 	    	    				subvo.setArrearBalance(taxJObj.has("arrearRemainingTax") ? taxJObj.getString("arrearRemainingTax") : null);
 	    	    				subvo.setArrearDemandAssmts(taxJObj.has("arrearDemandAssmts") ? taxJObj.getString("arrearDemandAssmts") : null);
 	    	    				subvo.setArrearCollectionAssmts(taxJObj.has("arrearCollectionAssmts") ? taxJObj.getString("arrearCollectionAssmts") : null);
 	    	    				subvo.setArrearBalanceAssmts(taxJObj.has("arrearRemainingAssmts") ? taxJObj.getString("arrearRemainingAssmts") : null);
 	    	    				
 	    	    				subvo.setCurrentDemand(taxJObj.has("currentDemand") ? taxJObj.getString("currentDemand") : null);
 	    	    				subvo.setCurrentCollection(taxJObj.has("currentCollection") ? taxJObj.getString("currentCollection") : null);
 	    	    				subvo.setCurrentBalance(taxJObj.has("currentRemainingTax") ? taxJObj.getString("currentRemainingTax") : null);
 	    	    				subvo.setCurrentDemandAssmts(taxJObj.has("currentDemandAssmts") ? taxJObj.getString("currentDemandAssmts") : null);
 	    	    				subvo.setCurrentCollectionAssmts(taxJObj.has("currentCollectionAssmts") ? taxJObj.getString("currentCollectionAssmts") : null);
 	    	    				subvo.setCurrentBalanceAssmts(taxJObj.has("currentRemainingAssmts") ? taxJObj.getString("currentRemainingAssmts") : null);
 	    	    				
 	    	    				subvo.setDefaultersTax(taxJObj.has("defaultersTax") ? taxJObj.getString("defaultersTax") : null);
 	    	    				subvo.setDefaultersAssmts(taxJObj.has("defaultersAssmts") ? taxJObj.getString("defaultersAssmts") : null);
 	    	    				
 	    	    				subvo.setTotalDemand(String.valueOf(Long.valueOf(subvo.getArrearDemand())+Long.valueOf(subvo.getCurrentDemand())));
 	    	    				subvo.setTotalCollection(String.valueOf(Long.valueOf(subvo.getArrearCollection())+Long.valueOf(subvo.getCurrentCollection())));
 	    	    				subvo.setTotalBalance(String.valueOf(Long.valueOf(subvo.getArrearBalance())+Long.valueOf(subvo.getCurrentBalance())));
 	    	    				subvo.setTotalDemandAssmts(String.valueOf(Long.valueOf(subvo.getArrearDemandAssmts())+Long.valueOf(subvo.getCurrentDemandAssmts())));
 	    	    				subvo.setTotalCollectionAssmts(String.valueOf(Long.valueOf(subvo.getArrearCollectionAssmts())+Long.valueOf(subvo.getCurrentCollectionAssmts())));
 	    	    				subvo.setTotalBalanceAssmts(String.valueOf(Long.valueOf(subvo.getArrearBalanceAssmts())+Long.valueOf(subvo.getCurrentBalanceAssmts())));
 	    	    				
 	    	    				mainvo.setTotalDemand(String.valueOf(Long.valueOf(mainvo.getTotalDemand())+Long.valueOf(subvo.getTotalDemand())));
 	    	    				mainvo.setTotalCollection(String.valueOf(Long.valueOf(mainvo.getTotalCollection())+Long.valueOf(subvo.getTotalCollection())));
 	    	    				mainvo.setTotalBalance(String.valueOf(Long.valueOf(mainvo.getTotalBalance())+Long.valueOf(subvo.getTotalBalance())));
 	    	    				mainvo.setTotalDemandAssmts(String.valueOf(Long.valueOf(mainvo.getTotalDemandAssmts())+Long.valueOf(subvo.getTotalDemandAssmts())));
 	    	    				mainvo.setTotalCollectionAssmts(String.valueOf(Long.valueOf(mainvo.getTotalCollectionAssmts())+Long.valueOf(subvo.getTotalCollectionAssmts())));
 	    	    				mainvo.setTotalBalanceAssmts(String.valueOf(Long.valueOf(mainvo.getTotalBalanceAssmts())+Long.valueOf(subvo.getTotalBalanceAssmts())));
 	    	    				
 	    	    				mainvo.getSubList().add(subvo);
 	    					}
 	    				}
 	    				returnList.add(mainvo);
 	    			}
 	    		}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at setDataToList  in TaxesDashBoardService service", e);
		}
		return returnList;
	}
	
	public String getTaxTypeNameById(Long taxTypeId){
		String name = null;
		try {
			if(taxTypeId != null && taxTypeId == 1L)
				name = "House Tax";
			else if(taxTypeId != null && taxTypeId == 2L)
				name = "Kolagaaram Tax";
			else if(taxTypeId != null && taxTypeId == 1L)
				name = "Advertisement Tax";
			else if(taxTypeId != null && taxTypeId == 1L)
				name = "Trade License Fee";
			else if(taxTypeId != null && taxTypeId == 1L)
				name = "Auctions Fee";
			else if(taxTypeId != null && taxTypeId == 1L)
				name = "Private Tap Fee";
			else
				name = "Others";
		} catch (Exception e) {
			LOG.error("Exception raised at getTaxTypeNameById  in TaxesDashBoardService service", e);
		}
		return name;
	}
}

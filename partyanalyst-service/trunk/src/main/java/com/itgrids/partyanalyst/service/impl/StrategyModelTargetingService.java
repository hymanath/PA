package com.itgrids.partyanalyst.service.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPRPWeightegesDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartialBoothPanchayatDAO;
import com.itgrids.partyanalyst.dao.IStrategyMergPancListDAO;
import com.itgrids.partyanalyst.dao.ISuggestiveRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.AlliancePartyResultsVO;
import com.itgrids.partyanalyst.dto.EffectedBoothsResponse;
import com.itgrids.partyanalyst.dto.EffectedBoothsVo;
import com.itgrids.partyanalyst.dto.HouseHoldsVO;
import com.itgrids.partyanalyst.dto.ImpFamilesVO;
import com.itgrids.partyanalyst.dto.InfectedBoothsVO;
import com.itgrids.partyanalyst.dto.OrderOfPriorityVO;
import com.itgrids.partyanalyst.dto.PanchayatCountVo;
import com.itgrids.partyanalyst.dto.PanchayatVO;
import com.itgrids.partyanalyst.dto.PartyEffectVO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.StrategyVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.SuggestiveRange;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IStrategyModelTargetingService;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class StrategyModelTargetingService implements
		IStrategyModelTargetingService {
	
	private static final Logger LOG = Logger.getLogger(StrategyModelTargetingService.class);
	
	private IRegionServiceData regionServiceDataImp;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO ;
	private ISuggestiveRangeDAO suggestiveRangeDAO;
	private IElectionDAO electionDAO;
	private IHamletBoothElectionDAO hamletBoothElectionDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IBoothDAO boothDAO;
	private IStaticDataService staticDataService;
	private IPanchayatDAO panchayatDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IStrategyMergPancListDAO strategyMergPancListDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IVoterCastInfoDAO voterCastInfoDAO;
	private IPartialBoothPanchayatDAO partialBoothPanchayatDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IConstituencyDAO constituencyDAO;
	private ICandidateResultDAO candidateResultDAO;
	private ICasteStateDAO casteStateDAO;
	private ISuggestiveModelService suggestiveModelService;
	private IVoterFamilyInfoDAO voterFamilyInfoDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IPRPWeightegesDAO prpWeightegesDAO;
	
	private static Font style6 = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.NORMAL);
	private static Font style5 = new Font(Font.FontFamily.TIMES_ROMAN, 8,Font.NORMAL);
	private static Font style1 = new Font(FontFactory.getFont("arial",10,Font.BOLD));
	private static Font style2 = new Font(FontFactory.getFont("arial",8,Font.NORMAL));
	
	private static Font style3 = new Font(FontFactory.getFont("arial",6,Font.BOLD));
	private static Font style4 = new Font(FontFactory.getFont("arial",6,Font.NORMAL));

	
	public ISuggestiveModelService getSuggestiveModelService() {
		return suggestiveModelService;
	}

	public void setSuggestiveModelService(
			ISuggestiveModelService suggestiveModelService) {
		this.suggestiveModelService = suggestiveModelService;
	}

	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public void setSuggestiveRangeDAO(ISuggestiveRangeDAO suggestiveRangeDAO) {
		this.suggestiveRangeDAO = suggestiveRangeDAO;
	}

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public void setStrategyMergPancListDAO(
			IStrategyMergPancListDAO strategyMergPancListDAO) {
		this.strategyMergPancListDAO = strategyMergPancListDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}

	public void setPartialBoothPanchayatDAO(
			IPartialBoothPanchayatDAO partialBoothPanchayatDAO) {
		this.partialBoothPanchayatDAO = partialBoothPanchayatDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void setTotalCasteSort(Comparator<PanchayatVO> totalCasteSort) {
		this.totalCasteSort = totalCasteSort;
	}

	public void setYoungOldSort(Comparator<PanchayatVO> youngOldSort) {
		this.youngOldSort = youngOldSort;
	}

	
	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}

	
	
	public IPRPWeightegesDAO getPrpWeightegesDAO() {
		return prpWeightegesDAO;
	}

	public void setPrpWeightegesDAO(IPRPWeightegesDAO prpWeightegesDAO) {
		this.prpWeightegesDAO = prpWeightegesDAO;
	}

	public void setVoterFamilyInfoDAO(IVoterFamilyInfoDAO voterFamilyInfoDAO) {
		this.voterFamilyInfoDAO = voterFamilyInfoDAO;
	}

	public List<PartyPositionVO> getPartyPreviousTrends(StrategyVO strategyVO,Long constituencyId,Long partyId,List<Long> electionIds,Map<Long,PartyEffectVO> partyEffect,Long effectPartyId,Long effectElectionId,Map<Long,Double> currentResult)
	{
		List<PartyPositionVO> resultList = null;
		try{

			List<Long> assemblyEleIdsList = new ArrayList<Long>(0);
			List<Long> mandalIds = new ArrayList<Long>();
			List<Long> localbodyIds = new ArrayList<Long>();
			List<Long> panchayatIds = null;
			List<Object[]> boothIdsList = null;
	        //getting mandals and municipalities in that consti
			List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId,IConstants.PRESENT_YEAR, null);
			
			if(mandalsList != null && mandalsList.size() > 0){
			   for(SelectOptionVO vo : mandalsList){
			      if(vo.getId().toString().substring(0,1).equalsIgnoreCase("2")){
			         mandalIds.add(new Long(vo.getId().toString().substring(1)));
			      }else{
				     localbodyIds.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(vo.getId().toString().substring(1))).get(0));
			      }
			   }
			}
	
			 for(Long eleId : electionIds)
			  {
				 assemblyEleIdsList.add(eleId); 
			  } 	
			
			if(mandalIds != null && mandalIds.size() > 0){
				 //getting all panchayats in mandals
				 panchayatIds =hamletBoothElectionDAO.getPanchayatIdsByEleIdMandalIdConstituencyId(mandalIds,assemblyEleIdsList.get(0),constituencyId);
			}
				
			List<PartyPositionVO> prevPancResultList = new ArrayList<PartyPositionVO>();
			List<PartyPositionVO> currPancResultList = new ArrayList<PartyPositionVO>();
			
			
			if(assemblyEleIdsList != null && assemblyEleIdsList.size() > 0)
			{
				resultList = new ArrayList<PartyPositionVO>(0);
				List<SuggestiveRange> suggestiveRangeList = null;
			     if(!strategyVO.isConsiderRange()){
				   suggestiveRangeList = suggestiveRangeDAO.getSuggestiveRangeList();
			     }	  
				  PartyPositionVO partyPositionVO = null;
				  int count = 0;
				  for(Long eleId :assemblyEleIdsList){
					  count = count+1;
					Election election = electionDAO.get(eleId);
					partyPositionVO = new PartyPositionVO();
					List<PartyPositionVO> rangeList = new ArrayList<PartyPositionVO>();
					
					PartyPositionVO range = null;
					if(!strategyVO.isConsiderRange()){
					for(SuggestiveRange suggestiveRange:suggestiveRangeList)
					  {
						range = new PartyPositionVO();
						range.setName(suggestiveRange.getName());
						range.setMinValue(suggestiveRange.getMinValue());
						range.setMaxValue(suggestiveRange.getMaxValue());
						range.setTempVar(suggestiveRange.getColor());							
						rangeList.add(range);
						
					  }
					}else{
					   rangeList = getRangeList(strategyVO);
					}
					partyPositionVO.setPartyPositionVOList(rangeList);
					partyPositionVO.setName(election.getElectionYear() != null?election.getElectionYear():" ");
					partyPositionVO.setId(eleId);
					partyPositionVO.setConstituencyId(constituencyId);
					
										
					if(localbodyIds != null && localbodyIds.size() > 0){
						//getting all booths in municipality
					  boothIdsList = hamletBoothElectionDAO.getBoothsByLocalBodyNElectionId(localbodyIds,eleId);
					}
					
					if(count == 1){
						if(strategyVO.isAutoCalculate()){
							partyPositionVO.setWeakPollingPercentVOList(currPancResultList);
						}
					    getMandalWisePartyPerformanceReport(constituencyId,eleId, partyPositionVO, partyId,panchayatIds,boothIdsList,partyEffect,effectPartyId,effectElectionId,currentResult,currPancResultList);
					}else{
						getMandalWisePartyPerformanceReport(constituencyId,eleId, partyPositionVO, partyId,panchayatIds,boothIdsList,partyEffect,effectPartyId,effectElectionId,null,prevPancResultList);
						if(strategyVO.isAutoCalculate()){
						  partyPositionVO.setWeakPollingPercentVOList(prevPancResultList);
						}
					}
					 resultList.add(partyPositionVO);
				  }
			}
			
			Double prevPerc = 0d;
			int prevCount = 0;
			Double currentPerc = 0d;
			int currentCount = 0;
			if(strategyVO.isAutoCalculate()){
				List<PartyPositionVO> currResults = currPancResultList;
				List<PartyPositionVO> prevResults = prevPancResultList;
				
				for(PartyPositionVO result:currResults){
					
					if(result.getPartyPercentage() != null && result.getPartyPercentage() > 0){
						
						currentPerc = currentPerc+result.getPartyPercentage();
						currentCount = currentCount+1;
					}
				}
				
				for(PartyPositionVO result:prevResults){
					
					if(result.getPartyPercentage() != null && result.getPartyPercentage() > 0){
						prevPerc = prevPerc+result.getPartyPercentage();
						prevCount = prevCount+1;
					}
				}
				if(prevCount > 0)
				  prevPerc = new BigDecimal(prevPerc/prevCount).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				if(currentCount > 0)
				  currentPerc = new BigDecimal(currentPerc/currentCount).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				addRanges(prevPerc,currentPerc,resultList.get(0));
				addRanges(prevPerc,currentPerc,resultList.get(1));
			}	

			if(resultList != null && resultList.size() == 2){
			  List<PartyPositionVO> suggestedLocations = getSuggestiveLocationsForAParty(resultList);
			  resultList.get(0).setSuggestedLocations(suggestedLocations);
			}	
	
			 return resultList;
		}catch (Exception e) {
		 LOG.error(" Exception Occured in getPartyPreviousTrends() method, Exception-",e);
		 return resultList;
		}
	}
	
	public void addRanges(Double prevPerc,Double currentPerc,PartyPositionVO partyPositionVO){
		  List<PartyPositionVO> rangeList = new ArrayList<PartyPositionVO>();
		  Float worestMin = 0.0f;
		  Float worestMax = 0.0f;
		  Float veryPoorMin = 0.0f;
		  Float veryPoorMax = 0.0f;
		  Float poorMin = 0.0f;
		  Float poorMax = 0.0f;
		  Float okMin = 0.0f;
		  Float okMax = 0.0f;
		  Float strongMin = 0.0f;
		  Float strongMax = 0.0f;
		  Float veryStrongMin = 0.0f;
		  Float veryStrongMax = 100.00f;
		  Float constiAvgPerc = new BigDecimal((prevPerc+currentPerc)/2).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			okMin = constiAvgPerc-2.50f;
			okMax = constiAvgPerc+2.50f;
			Float minDifference = new BigDecimal(constiAvgPerc/5).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			poorMin = okMin-minDifference;
			poorMax = okMin-0.01f;
			veryPoorMin = poorMin-minDifference;
			veryPoorMax = poorMin-0.01f;
			worestMax = veryPoorMin-0.01f;
			strongMin = okMax+0.01f;
			strongMax = okMax+minDifference;
			veryStrongMin = strongMax+0.01f;
			LOG.info("consticurrentPerc: "+currentPerc+"constiprevPerc: "+prevPerc+" constiAvgPerc: "+constiAvgPerc+"okMin: "+okMin+" okMax: "+okMax+" minDifference: "+minDifference+" poorMin: "+poorMin+" poorMax: "+poorMax+"veryPoorMin: "+veryPoorMin+" veryPoorMax: "+veryPoorMax+" worestMin: "+worestMin+" worestMax: "+worestMax+" strongMin: "+strongMin+" strongMax: "+strongMax);
			PartyPositionVO range = null;

				
				range = new PartyPositionVO();
				range.setName("VERY STRONG");
				range.setMinValue(veryStrongMin.doubleValue());
				range.setMaxValue(veryStrongMax.doubleValue());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("STRONG");
				range.setMinValue(strongMin.doubleValue());
				range.setMaxValue(strongMax.doubleValue());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("OK");
				range.setMinValue(okMin.doubleValue());
				range.setMaxValue(okMax.doubleValue());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("POOR");
				range.setMinValue(poorMin.doubleValue());
				range.setMaxValue(poorMax.doubleValue());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("VERY POOR");
				range.setMinValue(veryPoorMin.doubleValue());
				range.setMaxValue(veryPoorMax.doubleValue());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("WORST");
				range.setMinValue(worestMin.doubleValue());
				range.setMaxValue(worestMax.doubleValue());
				rangeList.add(range);

			partyPositionVO.setPartyPositionVOList(rangeList);
			List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
			List<PartyPositionVO> resultVOList = partyPositionVO.getWeakPollingPercentVOList();
		      for(PartyPositionVO positionVO :partyPositionVOList)
		      {
		       for(PartyPositionVO result:resultVOList){
		    	if(result.getPartyPercentage() >= positionVO.getMinValue() && result.getPartyPercentage() <= positionVO.getMaxValue())
		    	{
		    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
		    	 if(locationList == null || locationList.size() == 0)
		    		locationList = new ArrayList<PartyPositionVO>(0);
		    		locationList.add(result);
		    		positionVO.setPartyPositionVOList(locationList);
		    	}
		      }
		      }
	  }
	
	public void getMandalWisePartyPerformanceReport(Long constituencyId,Long electionId,PartyPositionVO partyPositionVO,Long partyId,List<Long> panchaytIdsList,List<Object[]> localbodybooths,Map<Long,PartyEffectVO> partyEffect,Long effectPartyId,Long effectElectionId,Map<Long,Double> currentResult,List<PartyPositionVO> pancResultList)
	{
		try{
		Map<Long,List<Long>> boothIds = new HashMap<Long, List<Long>>();//Map<boothId,List<panchayatId>>
		Map<Long,Map<Long,Long>> resultMap = new HashMap<Long, Map<Long,Long>>(0);//Map<panchayatId,Map<partyId,totalvoters>>
		Map<Long,Map<Long,Long>> resultMap1 = new HashMap<Long,Map<Long,Long>>(0);//Map<localbodyname,Map<partyId,totalvoters>>
		Map<Long,List<Long>> boothIdMap = new HashMap<Long, List<Long>>(0);//<localBodyName,<boothIds>
		
		List<Long> booths = new ArrayList<Long>();
		if(localbodybooths != null && localbodybooths.size() > 0)
		{
		for(Object[] params: localbodybooths)
		{
			List<Long> boothIds1 = boothIdMap.get(params[2]);
				if(boothIds1 == null)
				{
				boothIds1 = new ArrayList<Long>(0);	
				boothIdMap.put((Long)params[2], boothIds1);
				}
				if(!boothIds1.contains((Long)params[0]))
				boothIds1.add((Long)params[0]);
		}
		
		for(Long localbodyId : boothIdMap.keySet())
		{
			booths = boothIdMap.get(localbodyId);
			//getting all results boothwise for municipality
			List<Object[]> partyResult = candidateBoothResultDAO.findBoothResultsForBoothsAndElectionAndAllParties( boothIdMap.get(localbodyId),electionId,null);
			Map<Long,Long> partyMap1 = null;
			for(Object[] params : partyResult)
			{
				partyMap1 = resultMap1.get(localbodyId);
				if(partyMap1 == null)
				{
					partyMap1 = new HashMap<Long, Long>(0);
					resultMap1.put(localbodyId, partyMap1);
				}
				Long votesEarned = partyMap1.get((Long)params[0]);
				if(votesEarned == null)
					partyMap1.put((Long)params[0], (Long)params[2]);	
				else
					partyMap1.put((Long)params[0], votesEarned+(Long)params[2]);	
			}
			
		}
		}
		if(panchaytIdsList != null && panchaytIdsList.size() > 0)
		{
		  //getting what are the booths present in a panchayat
		  List<Object[]> list = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchaytIdsList, electionId); 
		  if(list != null && list.size() > 0)
		  {
			  for(Object[] params:list)
			  {
				  List<Long> panchayatIds = boothIds.get((Long)params[1]);
				  if(panchayatIds == null)
				  {
				   panchayatIds = new ArrayList<Long>(0);
				   boothIds.put((Long)params[1], panchayatIds);
				  }
				  if(!panchayatIds.contains((Long)params[0]))
				   panchayatIds.add((Long)params[0]);
			  }
			 //getting all results boothwise for mandals
			  List<Object[]> resultList = candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionIdForSelElection(boothIds.keySet(), electionId);
			  if(resultList != null && resultList.size() > 0)
			  {
				 for(Object[] params:resultList)
				 {
				   List<Long> panchayatIdsList = boothIds.get((Long)params[0]);
				   if(panchayatIdsList != null && panchayatIdsList.size() > 0)
				   {
					 Map<Long,Long> partyMap = null;//Map<PartyId,totalvotes>
					 for(Long panchayatId :panchayatIdsList)
					 {
						 partyMap = resultMap.get(panchayatId);
						 if(partyMap == null)
						 {
							 partyMap = new HashMap<Long, Long>(0);
							 resultMap.put(panchayatId, partyMap);
						 }
						 Long votesEarned = partyMap.get((Long)params[1]);
						 if(votesEarned == null)
						  partyMap.put((Long)params[1],(Long)params[2]);
						 else
						  partyMap.put((Long)params[1], votesEarned+(Long)params[2]);
					 }
				   }
				   
				 }
			  }
			  
		  }
		}
		//getting alliance parties for selected party
		//used to consider alliance party votes if selected party is not participated
		AlliancePartyResultsVO alliancePartiesVO = staticDataService.getAlliancePartiesByElectionAndParty(electionId,partyId);
		if(resultMap != null && resultMap.size() > 0){
			//getting results for panchayats
			getPartyPerformanceForPanchayat(resultMap,partyPositionVO, partyId,electionId,partyEffect,alliancePartiesVO,effectPartyId,effectElectionId,currentResult,pancResultList); 
		}
		if(resultMap1 != null && resultMap1.size() > 0){
			//getting results for municipalities
		  getPartyPerformanceForLocalBody(partyPositionVO, partyId,resultMap1,boothIdMap,partyEffect,alliancePartiesVO,effectPartyId,effectElectionId,currentResult,pancResultList);
		}
		
		}catch (Exception e) {
			LOG.error(" Exception Occured in getMandalWisePartyPerformanceReport() method, Exception - ",e);
		  }
	}
	
	 public void getPartyPerformanceForPanchayat(Map<Long,Map<Long,Long>> resultMap,PartyPositionVO partyPositionVO, Long selectedpartyId, Long electionId,Map<Long,PartyEffectVO> partyEffect,AlliancePartyResultsVO alliancePartiesVO,Long effectPartyId,Long effectElectionId,Map<Long,Double> currentResult,List<PartyPositionVO> pancResultList)
		{
			try{
				
				//resultMap -- Map<panchayatId,Map<partyId,totalvoters>>
				//resultMap1 -- Map<localbodyName,Map<partyId,totalvoters>>
				
				Map<Long,List<Long>> boothIdsMap = new HashMap<Long, List<Long>>(0);//<panchayatId,List<boothIds>>
				Map<Long,Long> panchayatTotalVotersMap = new HashMap<Long, Long>(0);//<locationId,totalVoters>
									
				  List<Long> panchayatIdsList = new ArrayList<Long>(resultMap.keySet());
				  //getting booths in a panchayat
				  List<Object[]> boothList = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchayatIdsList, partyPositionVO.getId());
				  if(boothList != null && boothList.size() > 0)
				  {
					  for(Object[] params:boothList)
					  {
						  List<Long> boothIdsList = boothIdsMap.get((Long)params[0]);
						  if(boothIdsList == null)
						  {
							boothIdsList = new ArrayList<Long>(0);
							boothIdsMap.put((Long)params[0], boothIdsList);  
						  }
						  if(!boothIdsList.contains((Long)params[1]))
							  boothIdsList.add((Long)params[1]);  
					  }
					  
				  }				
				
			if(boothIdsMap != null && boothIdsMap.size() > 0)
			{
			  for(Long id : boothIdsMap.keySet()){
				 //setting total voters in each panchayat
				panchayatTotalVotersMap.put(id, boothDAO.getTotalVotesByBoothIdsList(boothIdsMap.get(id)));
			  }
			}
			Set<Long> mergePanchayatIds = new HashSet<Long>();
			Map<Long,Set<Long>> mergePanchayatMap = new HashMap<Long,Set<Long>>();
			List<Object[]> mergePanchayatsList = strategyMergPancListDAO.getPanchayatsToMerge(partyPositionVO.getConstituencyId(),"E", electionId);
			for(Object[] mergePanchayat:mergePanchayatsList){
			   if(mergePanchayat[0] != null){
				 mergePanchayatIds.add((Long)mergePanchayat[0]);
			   }
			   if(mergePanchayat[1] != null){
				 mergePanchayatIds.add((Long)mergePanchayat[1]);
			   }
			   
			   Set<Long> panchayats = mergePanchayatMap.get((Long)mergePanchayat[1]);
			   if(panchayats == null){
				   panchayats = new HashSet<Long>();
				   mergePanchayatMap.put((Long)mergePanchayat[1],panchayats);
				   panchayats.add((Long)mergePanchayat[1]);
			   }
			   panchayats.add((Long)mergePanchayat[0]);
			}
			//iterating all panchayats
			 for(Long id:resultMap.keySet())
			 {
				 if(!mergePanchayatIds.contains(id)){
				   calculateDifference(panchayatTotalVotersMap,resultMap,id,selectedpartyId,alliancePartiesVO,effectPartyId,effectElectionId,electionId,partyEffect,partyPositionVO,currentResult,pancResultList);
				 }
			 } 
			if(mergePanchayatMap.size() > 0){
				calculateDifferenceForMergePanchayats(panchayatTotalVotersMap,resultMap,selectedpartyId,alliancePartiesVO,effectPartyId,effectElectionId,electionId,partyEffect,partyPositionVO,mergePanchayatMap,currentResult,pancResultList);
			}
			}catch (Exception e) {
			 LOG.error(" Exception Occured in getPartyPerformanceForPanchayat() method, Exception - ",e);
			}
		}
	 
	 public void calculateDifference(Map<Long,Long> panchayatTotalVotersMap,Map<Long,Map<Long,Long>> resultMap,Long id,Long selectedpartyId,AlliancePartyResultsVO alliancePartiesVO,Long effectPartyId,Long effectElectionId,Long electionId,Map<Long,PartyEffectVO> partyEffect,PartyPositionVO partyPositionVO,Map<Long,Double> currentResult,List<PartyPositionVO> pancResultList){
		 

			Map<Long,Long> partyMap = resultMap.get(id);
			Long totalVotes = 0L;
				 
			for(Long partysId:partyMap.keySet())
			  totalVotes += partyMap.get(partysId); 
				 
			Long selectedPartyTotal = partyMap.get(selectedpartyId);
			
			//if party not partispated considering alliance
			if(selectedPartyTotal == null){
				 
				  if(alliancePartiesVO == null || alliancePartiesVO.getAllianceParties() == null)
					  selectedPartyTotal = 0L;
				  else
					  for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties())
						  if(selectedPartyTotal == null || selectedPartyTotal.longValue() == 0l)
						  selectedPartyTotal = partyMap.get(alianceParty.getId());
			  }
		   
		  if(selectedPartyTotal == null)
			  selectedPartyTotal = 0L;
		  

		  double difference = 0d;
		  if(totalVotes != null && totalVotes > 0){
			  difference =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		  }
		  
		  //getting other party effect
		  Double effectPartyperc = 0d;
		  if(effectElectionId != null && effectPartyId != null){
			  Long prp = null;
			if(electionId.longValue() == effectElectionId.longValue())
				prp = partyMap.get(effectPartyId);
			
		  
		     if(prp != null && totalVotes != null && totalVotes > 0){
		    	 effectPartyperc =  new BigDecimal((prp*100.0/totalVotes)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		     }
	    
		  }	
		  String locationName = panchayatDAO.getPanchayatNameById(id); 
		  PartyEffectVO effect = partyEffect.get(id);
	    	 if(effect == null){
	    	  effect = new PartyEffectVO();
	    	  effect.setId(id);
	    	  effect.setName(locationName);
	    	  partyEffect.put(id, effect);
	    	 }
	    	 if(effectElectionId != null && effectPartyId != null && electionId.longValue() == effectElectionId.longValue()){
	    		 effect.setPrpCurrentPerc(effectPartyperc);
	    		 effect.setTdpCurrentPerc(difference);
	    		 effect.setTdpCurrentVotes(selectedPartyTotal);
	    		 effect.setId(id);
	    		 effect.setReportLvl(3l);
	    	 }else{
	    		 effect.setTdpPrevPerc(difference);
	    		 effect.setTdpPrevVotes(selectedPartyTotal);
	    	 }
	    	 
	      List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
	      for(PartyPositionVO positionVO :partyPositionVOList)
	      {
	    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
	    	{
	    	 PartyPositionVO locationVO = null;
	    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
	    	 if(locationList == null || locationList.size() == 0)
	    		locationList = new ArrayList<PartyPositionVO>(0);
	    	 
	    	 locationVO = checkPartyPositionVOExist(id,locationList);
	    	 if(locationVO == null)
	    	 {
	    		 locationVO = new PartyPositionVO();
	    		 locationVO.setId(id);
	    		 locationVO.setName(locationName != null?locationName:" ");
	    		 locationVO.setPartyPercentage(difference);
	    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
	    		 locationVO.setTotalValidVotes(totalVotes);
	    		 locationVO.setTotalVoters(panchayatTotalVotersMap.get(id));
	    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/panchayatTotalVotersMap.get(id))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	    		 locationVO.setMargin(difference);
	    		 if(currentResult != null)
	    		   currentResult.put(id, difference);
	    		 locationList.add(locationVO);
	    		 positionVO.setPartyPositionVOList(locationList);
	    		 pancResultList.add(locationVO);
	    		 
	    	 }
	    	 
	    	}
	      }
	    	
		
		 
	 }
	 
	 public void calculateDifferenceForMergePanchayats(Map<Long,Long> panchayatTotalVotersMap,Map<Long,Map<Long,Long>> resultMap,Long selectedpartyId,AlliancePartyResultsVO alliancePartiesVO,Long effectPartyId,Long effectElectionId,Long electionId,Map<Long,PartyEffectVO> partyEffect,PartyPositionVO partyPositionVO,Map<Long,Set<Long>> mergePanchayatMap,Map<Long,Double> currentResult,List<PartyPositionVO> pancResultList){
		 
        for(Long key:mergePanchayatMap.keySet()){
        	Set<Long> panchayatIds = mergePanchayatMap.get(key);
			
		  Long totalVotes = 0L;
		  Long selectedPartyTotal = 0l;
		  Long prp = 0l;
		  Long panchayatTotalVoters = 0l;
		  for(Long id:panchayatIds){	
			 Map<Long,Long> partyMap = resultMap.get(id);
			if(partyMap != null) {
			 for(Long partysId:partyMap.keySet()){
			  totalVotes += partyMap.get(partysId); 
		     }	
		  
		     Long partyTotal = partyMap.get(selectedpartyId);
			
			 //if party not partispated considering alliance
			 if(partyTotal == null){
				  if(alliancePartiesVO == null || alliancePartiesVO.getAllianceParties() == null){
					  partyTotal = 0L;
				  }else{
					  for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties()){
						  if(partyTotal == null || partyTotal.longValue() == 0l){
							  partyTotal = partyMap.get(alianceParty.getId());
						  }
					  }
				  }
			  }
		   
		    if(partyTotal == null){
		 	  partyTotal = 0L;
		    }
		    selectedPartyTotal = selectedPartyTotal+partyTotal;
		    
		    if(effectElectionId != null && effectPartyId != null){
				if(electionId.longValue() == effectElectionId.longValue() && partyMap.get(effectPartyId) != null){
					prp = prp+partyMap.get(effectPartyId);
				}
		    }
		    
		    Long totVoters = panchayatTotalVotersMap.get(id);
		    if(totVoters != null){
		    	panchayatTotalVoters = panchayatTotalVoters+totVoters;
		    }
		  }
         }
         
			
		  double difference = 0d;
		  if(totalVotes != null && totalVotes > 0){
			  difference =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		  }
		  
		  //getting other party effect
		  Double effectPartyperc = 0d;
		  
		  
		     if(prp != null && totalVotes != null && totalVotes > 0){
		    	 effectPartyperc =  new BigDecimal((prp*100.0/totalVotes)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		     }
	    
		  
		  String locationName = panchayatDAO.getPanchayatNameById(key); 
		  PartyEffectVO effect = partyEffect.get(key);
	    	 if(effect == null){
	    	  effect = new PartyEffectVO();
	    	  effect.setName(locationName);
	    	  effect.setId(key);
	    	  partyEffect.put(key, effect);
	    	 }
	    	 if(effectElectionId != null && effectPartyId != null && electionId.longValue() == effectElectionId.longValue()){
	    		 effect.setPrpCurrentPerc(effectPartyperc);
	    		 effect.setTdpCurrentPerc(difference);
	    		 effect.setTdpCurrentVotes(selectedPartyTotal);
	    		 effect.setId(key);
	    		 effect.setReportLvl(3l);
	    	 }else{
	    		 effect.setTdpPrevPerc(difference); 
	    		 effect.setTdpPrevVotes(selectedPartyTotal);
	    	 }
	    	 
	      List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
	      for(PartyPositionVO positionVO :partyPositionVOList)
	      {
	    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
	    	{
	    	 PartyPositionVO locationVO = null;
	    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
	    	 if(locationList == null || locationList.size() == 0)
	    		locationList = new ArrayList<PartyPositionVO>(0);
	    	 
	    	 locationVO = checkPartyPositionVOExist(key,locationList);
	    	 if(locationVO == null)
	    	 {
	    		 locationVO = new PartyPositionVO();
	    		 locationVO.setId(key);
	    		 locationVO.setName(locationName != null?locationName:" ");
	    		 locationVO.setPartyPercentage(difference);
	    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
	    		 locationVO.setTotalValidVotes(totalVotes);
	    		 locationVO.setTotalVoters(panchayatTotalVoters);
	    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/panchayatTotalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	    		 locationVO.setMargin(difference);
	    		 if(currentResult != null)
	    		   currentResult.put(key, difference);
	    		 locationList.add(locationVO);
	    		 positionVO.setPartyPositionVOList(locationList);
	    		 pancResultList.add(locationVO);
	    	 }
	    	 
	    	}
	      }
	    	
	    }
		 
	 }
	 
	 public void getPartyPerformanceForLocalBody(PartyPositionVO partyPositionVO, Long selectedpartyId,Map<Long,Map<Long,Long>> resultMap1,Map<Long,List<Long>> boothIdMap,Map<Long,PartyEffectVO> partyEffect,AlliancePartyResultsVO alliancePartiesVO,Long effectPartyId,Long effectElectionId,Map<Long,Double> currentResult,List<PartyPositionVO> pancResultList)
	 {
		 try{
			 Long localbodytotalVoters = 0l;
		     
			 if(resultMap1 != null)
			 {
			 for(Long localbodyId : resultMap1.keySet())
			 {
				 localbodytotalVoters = boothDAO.getTotalaVotesByBoothIds(boothIdMap.get(localbodyId)).get(0);	
				 String localbodyName = localElectionBodyDAO.getLocalElectionBodyName1(localbodyId);
					Map<Long,Long> partyMap1 = resultMap1.get(localbodyId);
					Long totalVotes = 0L;
						 
					for(Long partysId:partyMap1.keySet())
					  totalVotes += partyMap1.get(partysId); 
						 
					Long selectedPartyTotal = partyMap1.get(selectedpartyId);
					
					if(selectedPartyTotal == null){
						 
						  if(alliancePartiesVO == null || alliancePartiesVO.getAllianceParties() == null)
							  selectedPartyTotal = 0L;
						  else
							  for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties())
								  if(selectedPartyTotal == null || selectedPartyTotal.longValue() == 0l)
								  selectedPartyTotal = partyMap1.get(alianceParty.getId());
					  }
				  if(selectedPartyTotal == null)
					  selectedPartyTotal = 0L;
				  
				  double selectedPartyTotalPercent = 0d;
				   if(totalVotes != null)
				     selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			     
			     
			      double difference = selectedPartyTotalPercent;
			      
			      Double effectPartyperc = 0d;
				  if(effectElectionId != null && effectPartyId != null){
					  Long prp = null;
					if(partyPositionVO.getId().longValue() == effectElectionId.longValue())
						prp = partyMap1.get(effectPartyId);
					
				  
				     if(prp != null && totalVotes != null && totalVotes > 0){
				    	 effectPartyperc =  new BigDecimal((prp*100.0/totalVotes)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				     }
			    
				  }	
				 
				  PartyEffectVO effect = partyEffect.get(localbodyId);
			    	 if(effect == null){
			    	  effect = new PartyEffectVO();
			    	  effect.setName(localbodyName);
			    	  effect.setId(localbodyId);
			    	  partyEffect.put(localbodyId, effect);
			    	 }
			    	 if(effectElectionId != null && effectPartyId != null && partyPositionVO.getId().longValue() == effectElectionId.longValue()){
			    		 effect.setPrpCurrentPerc(effectPartyperc);
			    		 effect.setTdpCurrentPerc(difference);
			    		 effect.setTdpCurrentVotes(selectedPartyTotal);
			    		 effect.setId(localbodyId);
			    		 effect.setReportLvl(5l);
			    	 }else{
			    		 effect.setTdpPrevPerc(difference); 
			    		 effect.setTdpPrevVotes(selectedPartyTotal);
			    	 } 
			    	
			      List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
			      for(PartyPositionVO positionVO :partyPositionVOList)
			      {
			    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
			    	{
			    	 PartyPositionVO locationVO = null;
			    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
			    	 if(locationList == null || locationList.size() == 0)
			    		locationList = new ArrayList<PartyPositionVO>(0);
			    	 
			    	 locationVO = checkPartyPositionVOExist(localbodyId,locationList);
			    	 if(locationVO == null)
			    	 {
			    		 locationVO = new PartyPositionVO();
			    		 locationVO.setId(localbodyId);
			    		 locationVO.setName(localbodyName != null?localbodyName:" ");
			    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
			    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
			    		 locationVO.setTotalValidVotes(totalVotes);
			    		 locationVO.setTotalVoters(localbodytotalVoters);
			    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/localbodytotalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			    		 locationVO.setMargin(difference);
			    		 locationList.add(locationVO);
			    		 if(currentResult != null)
			    		  currentResult.put(localbodyId, difference);
			    		 positionVO.setPartyPositionVOList(locationList);
			    		 pancResultList.add(locationVO);
			    		 
			    	 }
			    	 
			    	}
			      }
			    	
				
			 }
			 } 
		 }
		 catch(Exception e)
		 {
			 LOG.error(" Exception Occured in getPartyPerformanceForLocalBody() method, Exception - ",e);
		 }
	 }

	 public PartyPositionVO checkPartyPositionVOExist(Long locationId,List<PartyPositionVO> list)
		{
			try{
			if(list == null)
			 return null;
			for(PartyPositionVO positionVO:list)
			 if(positionVO.equals(locationId))
			  return positionVO;
				
			 return null;
			}catch (Exception e) {
			 LOG.error(" ExceptionOccured in checkPartyPositionVOExist() method, Exception - ",e);
			 return null;
			}
		}
	 public PartyPositionVO checkPartyPositionVOExists(String name,List<PartyPositionVO> localbodyList)
		{
			try{
				if(localbodyList == null || localbodyList.size() == 0)
					return null;
				for(PartyPositionVO vo : localbodyList)
					if(vo.getName().equalsIgnoreCase(name))
						return vo;
				return null;
			}
			catch(Exception e)
			{
				 LOG.error(" ExceptionOccured in checkPartyPositionVOExists() method, Exception - ",e);
				return null;	
			}
		}
	 public List<PartyPositionVO> getSuggestiveLocationsForAParty(List<PartyPositionVO> partyPositions){
		 List<PartyPositionVO> returnValues = new ArrayList<PartyPositionVO>(); 
	    try{
		 if(partyPositions.size() == 2){
			 List<PartyPositionVO> latestElec =  partyPositions.get(0).getPartyPositionVOList();
			 List<PartyPositionVO> prevElec =  partyPositions.get(1).getPartyPositionVOList();
			 Collections.reverse(latestElec);
			 Collections.reverse(prevElec);
			 int z = 1;
			 for(z = 1;z<latestElec.size();z++){
				  for(int j = 0;j<z;j++){//2009
					  List<PartyPositionVO> panchayaties = latestElec.get(j).getPartyPositionVOList();
					  if(panchayaties != null && panchayaties.size() >0){	
						for(int i = prevElec.size()-z+j;i>=prevElec.size()-z+j;i--)
						{//2004
							List<PartyPositionVO> prevPanchayaties = prevElec.get(i).getPartyPositionVOList();
							if(prevPanchayaties != null && prevPanchayaties.size() >0){	
								populateMachedValues(panchayaties,prevPanchayaties,returnValues,z);
							}
						}
					  }
					}
				  }
			 z = z-1;
			    for(int y = 2;y<=latestElec.size()-1;y++){
				  for(int j = y-2;j<y;j++){
					  z++;
					  List<PartyPositionVO> latestPanchayaties = latestElec.get(y-1).getPartyPositionVOList();
					  List<PartyPositionVO> prevPanchayaties = prevElec.get(j).getPartyPositionVOList();
					  populateMachedValues(latestPanchayaties,prevPanchayaties,returnValues,z);
						for(int i = j,l=y;i>0&&l<latestElec.size()-1;i--,l++)
						{   latestPanchayaties = latestElec.get(l).getPartyPositionVOList();
						    prevPanchayaties = prevElec.get(i-1).getPartyPositionVOList();
						    populateMachedValues(latestPanchayaties,prevPanchayaties,returnValues,z);
						}
					}
				  } 
			    z++;
			    for(int last=0;last < latestElec.size() ; last++ ){
			    	List<PartyPositionVO> latestPanchayaties = latestElec.get(latestElec.size()-1).getPartyPositionVOList();
			    	List<PartyPositionVO> prevPanchayaties = prevElec.get(last).getPartyPositionVOList();
				    populateMachedValues(latestPanchayaties,prevPanchayaties,returnValues,z);
			    }
			    populateMachedValues(latestElec.get(0).getPartyPositionVOList(),prevElec.get(0).getPartyPositionVOList(),returnValues,z);
			     Collections.reverse(latestElec);
				 Collections.reverse(prevElec);
		 }
	    }catch(Exception e){
	    	LOG.error(" Exception Occured in getSuggestiveLocationsForAParty() method, Exception - ",e);
	    }
	    return returnValues;
	 }
	 
	 
	 public void populateMachedValues(List<PartyPositionVO> panchayaties,List<PartyPositionVO> prevPanchayaties,List<PartyPositionVO> returnValues,int priorityOrder){
		if(prevPanchayaties != null && prevPanchayaties.size() > 0){
			 for(PartyPositionVO prev:prevPanchayaties){
				  if(panchayaties != null && panchayaties.size() > 0){ 
					    for(PartyPositionVO current:panchayaties){
						   if(current.getId().longValue() == prev.getId().longValue()){
							   current.setPriorityOrder(priorityOrder);
							  returnValues.add(current);
							}
						}
				  }
			 }
	    }
	 }
	 public List<Object> getYoungOldVotersEffectWithOutCaste(StrategyVO strategyVO,Map<Long,OrderOfPriorityVO> finalOrder){
		 Long publicationId = strategyVO.getPublicationId();
		 Long constituencyId = strategyVO.getConstituencyId();
		 Long partyId = strategyVO.getPartyId();
		 List<Object> priorityList = new ArrayList<Object>();
		   
		    List<PanchayatVO> youngCastesList = null;
		    
		    List<Object[]> totalVoters = new ArrayList<Object[]>();
			List<Object[]> youngTotal = new ArrayList<Object[]>();
			Map<Long,String> panchayatNames = new HashMap<Long,String>();
			
            Constituency constituency = constituencyDAO.get(constituencyId);
            
            Map<Long,Long> totalVotersMap = new HashMap<Long,Long>();
            Map<Long,Long> ageVotersMap = new HashMap<Long,Long>();
		    
		    Set<Long> mergePanchayatIds = new HashSet<Long>();
			Map<Long,Set<Long>> mergePanchayatMap = new HashMap<Long,Set<Long>>();
			getPanchayatsToMerge(mergePanchayatIds,mergePanchayatMap,constituencyId,"P",publicationId);
			
			 Double avgPerc = getPartyPreviousResultAverage(partyId,constituencyId);
			 
			 //getting partial panchayats
			   List<Object[]> partialPanchayatsList = partialBoothPanchayatDAO.getPartialPanchayatsList(constituencyId, publicationId);
			   List<Long> partialIds = new ArrayList<Long>();
			   for(Object[] partial:partialPanchayatsList){
					if(partial[0] !=null && !partialIds.contains((Long)partial[0])){
						partialIds.add((Long)partial[0]);
					}
					if(partial[1] !=null && !partialIds.contains((Long)partial[1])){
						partialIds.add((Long)partial[1]);
					}
			   }
			   
			   if(partialIds.size() > 0){
				   
				   //calculating total voters starts
				   List<Object[]> totalVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, partialIds, null, null);
				   List<Object[]> totalVotersPartialCount = boothPublicationVoterDAO.getTotalVotersCountForPartial(publicationId, constituencyId, partialIds, null, null);
		
				   if(totalVotersCount != null && totalVotersCount.size() > 0){
					   totalVoters.addAll(totalVotersCount);
				   }
				   if(totalVotersPartialCount != null && totalVotersPartialCount.size() > 0){
					   totalVoters.addAll(totalVotersPartialCount);
				   }
				   //calculating total voters ends
				   
				   //young voters calculation starts
				   List<Object[]> youngVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, partialIds, 18l, 22l);
				   List<Object[]> youngVotersPartialCount = boothPublicationVoterDAO.getTotalVotersCountForPartial(publicationId, constituencyId, partialIds, 18l, 22l);
		
				   if(youngVotersCount != null && youngVotersCount.size() > 0){
					   youngTotal.addAll(youngVotersCount);
				   }
				   if(youngVotersPartialCount != null && youngVotersPartialCount.size() > 0){
					   youngTotal.addAll(youngVotersPartialCount);
				   }
				    
				   //young voters calculation ends
				  
                 
			    }else{
				   List<Object[]> totalVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, null, null, null);

				   if(totalVotersCount != null && totalVotersCount.size() > 0){
					   totalVoters.addAll(totalVotersCount);
				   }
				   
				   //young voters calculation starts
				   List<Object[]> youngVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, null, 18l, 22l);
				   
				   if(youngVotersCount != null && youngVotersCount.size() > 0){
					   youngTotal.addAll(youngVotersCount);
				   }
				  			    
				   //young voters calculation ends
				 
			   }
			   if(constituency.getAreaType().equalsIgnoreCase("RURAL-URBAN")){
				   
				   List<Object[]> totalVoterForMunic = boothPublicationVoterDAO.getTotalVotersByAgeForMunicipality(publicationId,constituencyId,null, null);
				       if(totalVoterForMunic != null && totalVoterForMunic.size() > 0){
						   totalVoters.addAll(totalVoterForMunic);
					   }
					   
				       List<Object[]> totalYoungVoterForMunic = boothPublicationVoterDAO.getTotalVotersByAgeForMunicipality(publicationId,constituencyId, 18l, 22l);
					  
					   if(totalYoungVoterForMunic != null && totalYoungVoterForMunic.size() > 0){
						   youngTotal.addAll(totalYoungVoterForMunic);
					   }
					
			   }
			   
				 
				 for(Object[] count:totalVoters){
					 totalVotersMap.put((Long)count[0],(Long)count[1]);
					 panchayatNames.put((Long)count[0],count[2].toString());
				 }
				 mergeTotalVoters(totalVotersMap,mergePanchayatMap);
				 
				 calculateFinalTargetedCasteNotPres(partyId,constituencyId,totalVotersMap,finalOrder,panchayatNames,avgPerc);
				 for(Object[] count:youngTotal){
					 ageVotersMap.put((Long)count[0],(Long)count[1]);
				 }
				 mergeTotalVoters(ageVotersMap, mergePanchayatMap);
				 
			     youngCastesList = getOrderOfPriorWithOutCaste(totalVotersMap,ageVotersMap,panchayatNames,"young",finalOrder,strategyVO.getYoungWt(),avgPerc);
			     
			  
		   
		    priorityList.add(youngCastesList);
		    priorityList.add(null);
			return priorityList;
		 
	 }

	 public void calculateFinalTargetedCasteNotPres(Long partyId,Long constituencyId,Map<Long,Long> totalVotersMap,Map<Long,OrderOfPriorityVO> finalOrder,Map<Long,String> panchayatNames,Double avgPerc){
		
		 for(Long panchayatId:totalVotersMap.keySet()){
			 OrderOfPriorityVO priorVo = finalOrder.get(panchayatId);
			 if(priorVo == null){
				 priorVo = new OrderOfPriorityVO();
				 priorVo.setPanchayatId(panchayatId);
				 priorVo.setName(panchayatNames.get(panchayatId));
				 finalOrder.put(panchayatId, priorVo);
			 }
			 priorVo.setTotalVoters(totalVotersMap.get(panchayatId));
			 priorVo.setTargetedVoters(Math.round((totalVotersMap.get(panchayatId)*avgPerc)/100));
			 if(priorVo.getPreviousVoters() == null){
				 priorVo.setPreviousVoters(0l);
			 }//priorVo.getPrevElectionVotes() 2004 priorVo.getPreviousVoters()2009
			 if(!(priorVo.getTargetedVoters()-priorVo.getPreviousVoters() > 0)){
				 if(priorVo.getPrevElectionVotes() != null && (priorVo.getPrevElectionVotes()-priorVo.getPreviousVoters() > 0)){
					 priorVo.setOpportunity(priorVo.getPreviousVoters()-priorVo.getPrevElectionVotes());
					 priorVo.setTargetedVoters(priorVo.getPrevElectionVotes());
				 }else if(priorVo.getPreviousPerc() != null && priorVo.getCurrentPerc() != null && (priorVo.getPreviousPerc() - priorVo.getCurrentPerc()) > 0){
					 Double reqPerc = priorVo.getPreviousPerc() - priorVo.getCurrentPerc();
					 Long target = priorVo.getPreviousVoters()+(Math.round(priorVo.getTotalVoters()*reqPerc/100));
					 priorVo.setTargetedVoters(target);
					 priorVo.setOpportunity(priorVo.getPreviousVoters()-target);					 
				 }else{
					 priorVo.setOpportunity(0l);
				 }
			 }else{
				 priorVo.setOpportunity(priorVo.getPreviousVoters()-priorVo.getTargetedVoters());
			 }
			 priorVo.setOpportunityPerc(new BigDecimal((priorVo.getOpportunity()*100/priorVo.getTotalVoters().floatValue())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		 }
	 }
	 public List<PanchayatVO> getOrderOfPriorWithOutCaste(Map<Long,Long> totalVotersMap,Map<Long,Long> youngOldVotersList,Map<Long,String> panchayatNames,String type,Map<Long,OrderOfPriorityVO> finalOrder,Double wt,Double avgPerc){
		 
		List<PanchayatVO> totalPriorityList = new ArrayList<PanchayatVO>();
				 
			 PanchayatVO panchayatVo = null;
			 
			
			 for(Long panchayatId:youngOldVotersList.keySet()){
				 panchayatVo = new PanchayatVO();
				 panchayatVo.setPanchayatId(panchayatId);
				 panchayatVo.setCount(totalVotersMap.get(panchayatId));
				 panchayatVo.setTotalPanchayatVoters(totalVotersMap.get(panchayatId).intValue());//total voters
				 panchayatVo.setTotalVoters(youngOldVotersList.get(panchayatId).intValue());//18-22 aged
				 panchayatVo.setOthrExpctdVotes(((Long)Math.round((panchayatVo.getTotalVoters()*avgPerc)/100)).intValue());//targeted
				 panchayatVo.setPanchayatName(panchayatNames.get(panchayatId));
				 panchayatVo.setTotalTargetCount(new Long(panchayatVo.getOthrExpctdVotes()));
				 panchayatVo.setTargetPerc(new BigDecimal((panchayatVo.getTotalTargetCount()*100)/panchayatVo.getCount().floatValue()).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());//targeted perc
				 totalPriorityList.add(panchayatVo);
			 }
				
			 Collections.sort(totalPriorityList,youngOldSort);	
			 
			 Double maxPerc = totalPriorityList.get(0).getTargetPerc();
			 
			 for(PanchayatVO orderVo:totalPriorityList){
				 orderVo.setVoterPoints(new BigDecimal((orderVo.getTargetPerc()*100)/maxPerc).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
				 OrderOfPriorityVO priorVo = finalOrder.get(orderVo.getPanchayatId());
				 if(priorVo == null){
					 priorVo = new OrderOfPriorityVO();
					 priorVo.setPanchayatId(orderVo.getPanchayatId());
					 priorVo.setName(orderVo.getPanchayatName());
					 finalOrder.put(orderVo.getPanchayatId(), priorVo);
				 }
				 if(type.equalsIgnoreCase("young")){
				   priorVo.setYoungWeight(new BigDecimal(orderVo.getVoterPoints()*wt/100).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
				 }else{
					 priorVo.setAgeWeight(new BigDecimal(orderVo.getVoterPoints()*wt/100).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
				 }
			 }
			 
			 return totalPriorityList;
	 }
	 
	 public List<Object> getTotalYoungOldVotersEffectWithCaste(StrategyVO strategyVO,Map<Long,Double> currentResult,Map<Long,OrderOfPriorityVO> finalOrder){
		    List<Object> priorityList = new ArrayList<Object>();
		    Map<Long,Float> castePercents = strategyVO.getCastePercents();
		    Long publicationId = strategyVO.getPublicationId();
            Long constituencyId = strategyVO.getConstituencyId();
            Map<Long,Set<Long>> mergeCasteMap = strategyVO.getMergeCasteMap();
		    List<PanchayatVO> totalCastesList = null;
		    List<PanchayatVO> youngCastesList = null;
		    List<PanchayatVO> agedCastesList = null;
		    List<Long> panchayatIds = new ArrayList<Long>();
		    List<Long> lebIds = new ArrayList<Long>();
		    
		    Map<Long,String> panchayatNames = new HashMap<Long,String>();
		    Map<Long,Long> totalVotersMap = new HashMap<Long,Long>();
		    
		    List<Object[]> youngCaste = new ArrayList<Object[]>();
			List<Object[]> youngTotal = new ArrayList<Object[]>();
			List<Object[]> ageCaste = new ArrayList<Object[]>();
			List<Object[]> ageTotal = new ArrayList<Object[]>();
			
			
			
			List<Long> locationLvs = new ArrayList<Long>();
			 locationLvs.add(3l);
			 locationLvs.add(5l);
			 
		    Constituency constituency = constituencyDAO.get(constituencyId);
		    
		    Set<Long> mergePanchayatIds = new HashSet<Long>();
			Map<Long,Set<Long>> mergePanchayatMap = new HashMap<Long,Set<Long>>();
			getPanchayatsToMerge(mergePanchayatIds,mergePanchayatMap,constituencyId,"P",publicationId);
			
			//getting partial panchayats
			   List<Object[]> partialPanchayatsList = partialBoothPanchayatDAO.getPartialPanchayatsList(constituencyId, publicationId);
			   List<Long> partialIds = new ArrayList<Long>();
			   for(Object[] partial:partialPanchayatsList){
					if(partial[0] !=null && !partialIds.contains((Long)partial[0])){
						partialIds.add((Long)partial[0]);
					}
					if(partial[1] !=null && !partialIds.contains((Long)partial[1])){
						partialIds.add((Long)partial[1]);
					}
			   }
			   
			   Set<Long> allPanchayatIds = new HashSet<Long>();
			   allPanchayatIds.addAll(partialIds);
			  List<Object[]> ids =  boothDAO.getPanchayatAndLebIds(constituencyId, publicationId);
			  for(Object[] id:ids){
				  if(id[0] != null)
				    allPanchayatIds.add((Long)id[0]);
				  if(id[1] != null)
					allPanchayatIds.add((Long)id[1]);
			  }
			//total voters calculation starts
			   List<Object[]> totalVotersList = voterInfoDAO.getVoterCountByLevels(constituencyId, publicationId, locationLvs,allPanchayatIds);
			   for(Object[] total:totalVotersList){
				   if(((Long)total[2]).longValue() == 3l){
					   panchayatIds.add((Long)total[0]);
					   totalVotersMap.put((Long)total[0],(Long)total[1]);
				   }else{
					   lebIds.add((Long)total[0]); 
					   totalVotersMap.put((Long)total[0],(Long)total[1]);
				   }
			   }
			   
			   populateNames(panchayatIds,lebIds,panchayatNames);
			   List<Object[]> casteList = voterCastInfoDAO.getVotersCastInfoByCasteIds(locationLvs, constituencyId, publicationId, 1l, new ArrayList<Long>(castePercents.keySet()),allPanchayatIds);
			   
			    totalCastesList = getOrderOfPriorUsingCaste(castePercents,casteList,totalVotersList,mergePanchayatMap,mergeCasteMap,currentResult,"totalCaste",panchayatNames,totalVotersMap,strategyVO.getTotalCastWt(),finalOrder);
			 //total voters calculation ends  
			    
			    
			  
			  
			   if(partialIds.size() > 0){
				   //young voters calculation starts
				   List<Object[]> youngVotersCaste = boothPublicationVoterDAO.getCasteCount(castePercents.keySet(), publicationId, constituencyId, partialIds, 18l, 22l);
				   List<Object[]> youngVotersPartialCaste = boothPublicationVoterDAO.getCasteCountForPartial(castePercents.keySet(), publicationId, constituencyId, partialIds, 18l, 22l);
				   List<Object[]> youngVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, partialIds, 18l, 22l);
				   List<Object[]> youngVotersPartialCount = boothPublicationVoterDAO.getTotalVotersCountForPartial(publicationId, constituencyId, partialIds, 18l, 22l);
				   if(youngVotersCaste != null && youngVotersCaste.size() > 0){
					   youngCaste.addAll(youngVotersCaste);
				   }
				   if(youngVotersPartialCaste != null && youngVotersPartialCaste.size() > 0){
					   youngCaste.addAll(youngVotersPartialCaste);
				   }
				   if(youngVotersCount != null && youngVotersCount.size() > 0){
					   youngTotal.addAll(youngVotersCount);
				   }
				   if(youngVotersPartialCount != null && youngVotersPartialCount.size() > 0){
					   youngTotal.addAll(youngVotersPartialCount);
				   }
				    
				   //young voters calculation ends
				  
                   //aged voters calculation starts
				   List<Object[]> agedVotersCaste = boothPublicationVoterDAO.getCasteCount(castePercents.keySet(), publicationId, constituencyId, partialIds,  61l, 200l);
				   List<Object[]> agedVotersPartialCaste = boothPublicationVoterDAO.getCasteCountForPartial(castePercents.keySet(), publicationId, constituencyId, partialIds,  61l, 200l);
				   List<Object[]> agedVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, partialIds, 61l, 200l);
				   List<Object[]> agedVotersPartialCount = boothPublicationVoterDAO.getTotalVotersCountForPartial(publicationId, constituencyId, partialIds, 61l, 200l);
				   if(agedVotersCaste != null && agedVotersCaste.size() > 0){
					   ageCaste.addAll(agedVotersCaste);
				   }
				   if(agedVotersPartialCaste != null && agedVotersPartialCaste.size() > 0){
					   ageCaste.addAll(agedVotersPartialCaste);
				   }
				   if(agedVotersCount != null && agedVotersCount.size() > 0){
					   ageTotal.addAll(agedVotersCount);
				   }
				   if(agedVotersPartialCount != null && agedVotersPartialCount.size() > 0){
					   ageTotal.addAll(agedVotersPartialCount);
				   }
				   
				   //aged voters calculation ends
			   }else{

				   //young voters calculation starts
				   List<Object[]> youngVotersCaste = boothPublicationVoterDAO.getCasteCount(castePercents.keySet(), publicationId, constituencyId, null, 18l, 22l);
				   List<Object[]> youngVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, null, 18l, 22l);
				   if(youngVotersCaste != null && youngVotersCaste.size() > 0){
					   youngCaste.addAll(youngVotersCaste);
				   } 
				   if(youngVotersCount != null && youngVotersCount.size() > 0){
					   youngTotal.addAll(youngVotersCount);
				   }
				  
				    
				   //young voters calculation ends
				   
                   //aged voters calculation starts
				    List<Object[]> agedVotersCaste = boothPublicationVoterDAO.getCasteCount(castePercents.keySet(), publicationId, constituencyId, partialIds, 61l, 200l);  
				    List<Object[]> agedVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, partialIds, 61l, 200l);   
				    if(agedVotersCaste != null && agedVotersCaste.size() > 0){
						 ageCaste.addAll(agedVotersCaste);
					 }
				    if(agedVotersCount != null && agedVotersCount.size() > 0){
						 ageTotal.addAll(agedVotersCount);
					 }
				    
				   //aged voters calculation ends
			   
			   }
			   if(constituency.getAreaType().equalsIgnoreCase("RURAL-URBAN")){
				   
				       List<Object[]> totalYoungVoterForMunic = boothPublicationVoterDAO.getTotalVotersByAgeForMunicipality(publicationId,constituencyId, 18l, 22l);
					  
					   List<Object[]> casteYoungVoterForMunic = boothPublicationVoterDAO.getCasteCountForMunicipality(castePercents.keySet(),publicationId,constituencyId, 18l, 22l);
					   if(casteYoungVoterForMunic != null && casteYoungVoterForMunic.size() > 0){
						   youngCaste.addAll(casteYoungVoterForMunic);
					   } 
					   if(totalYoungVoterForMunic != null && totalYoungVoterForMunic.size() > 0){
						   youngTotal.addAll(totalYoungVoterForMunic);
					   }
					   List<Object[]> totalAgeVoterForMunic = boothPublicationVoterDAO.getTotalVotersByAgeForMunicipality(publicationId,constituencyId, 61l, 200l);
						  
					   List<Object[]> casteAgeVoterForMunic = boothPublicationVoterDAO.getCasteCountForMunicipality(castePercents.keySet(),publicationId,constituencyId, 61l, 200l);
					   if(casteAgeVoterForMunic != null && casteAgeVoterForMunic.size() > 0){
							 ageCaste.addAll(casteAgeVoterForMunic);
						 }
					    if(totalAgeVoterForMunic != null && totalAgeVoterForMunic.size() > 0){
							 ageTotal.addAll(totalAgeVoterForMunic);
						 }
			   }
			   youngCastesList = getOrderOfPriorUsingCaste(castePercents,youngCaste,youngTotal,mergePanchayatMap,mergeCasteMap,currentResult,"youngCaste",panchayatNames,totalVotersMap,strategyVO.getYoungWt(),finalOrder);
			   agedCastesList = getOrderOfPriorUsingCaste(castePercents,ageCaste,ageTotal,mergePanchayatMap,mergeCasteMap,currentResult,"agedCaste",panchayatNames,totalVotersMap,strategyVO.getAgedWt(),finalOrder);
			
			
			priorityList.add(totalCastesList);
		    priorityList.add(youngCastesList);
		    priorityList.add(agedCastesList);
			return priorityList;
	  }
	 
	 public void getPanchayatsToMerge(Set<Long> mergePanchayatIds,Map<Long,Set<Long>> mergePanchayatMap,Long constituencyId,String type,Long typeId){
		 List<Object[]> mergePanchayatsList = strategyMergPancListDAO.getPanchayatsToMerge(constituencyId,type, typeId);
			for(Object[] mergePanchayat:mergePanchayatsList){
			   if(mergePanchayat[0] != null){
				 mergePanchayatIds.add((Long)mergePanchayat[0]);
			   }
			   if(mergePanchayat[1] != null){
				 mergePanchayatIds.add((Long)mergePanchayat[1]);
			   }
			   
			   Set<Long> panchayats = mergePanchayatMap.get((Long)mergePanchayat[1]);
			   if(panchayats == null){
				   panchayats = new HashSet<Long>();
				   mergePanchayatMap.put((Long)mergePanchayat[1],panchayats);
			   }
			   panchayats.add((Long)mergePanchayat[0]);
			}
	 }
	 public List<PanchayatVO> getOrderOfPriorUsingCaste(Map<Long,Float> castePercents, List<Object[]> casteList,List<Object[]> totalVotersList,Map<Long,Set<Long>> mergePanchayatMap,Map<Long,Set<Long>> mergeCasteMap,Map<Long,Double> currentResult,String type,Map<Long,String> panchayatNames,Map<Long,Long> pancTotalVotersList,Double weight,Map<Long,OrderOfPriorityVO> finalOrder){
		 Map<Long,PanchayatVO> totalCastePriorityMap = new HashMap<Long,PanchayatVO>();
		 
		 PanchayatVO panchayatVo = null;
		 Map<Long,Long> totalVotersMap = new HashMap<Long,Long>();
		 if(type.equalsIgnoreCase("totalCaste")){	
			 totalVotersMap = pancTotalVotersList;
		 }else{
			 for(Object[] count:totalVotersList){
				 totalVotersMap.put((Long)count[0],(Long)count[1]);
			 }
		 }
		 for(Object[] caste:casteList){
			 panchayatVo = totalCastePriorityMap.get((Long)caste[0]);
			 if(panchayatVo == null){
				 panchayatVo = new PanchayatVO();
				 panchayatVo.setPanchayatId((Long)caste[0]);
				 panchayatVo.setPanchayatName(panchayatNames.get((Long)caste[0]));
				 if(pancTotalVotersList.get((Long)caste[0]) != null)
				   panchayatVo.setTotalPanchayatVoters(pancTotalVotersList.get((Long)caste[0]).intValue());
				 Map<Long,Long> casteMap = new HashMap<Long,Long>();
				 panchayatVo.setCasteMap(casteMap);
				 casteMap.put(0l, totalVotersMap.get((Long)caste[0]));
				 for(Long casteStateId:castePercents.keySet()){
					 if(casteStateId.longValue() != 0l){
					  casteMap.put(casteStateId, 0l);
					 }
				 }
				 totalCastePriorityMap.put((Long)caste[0],panchayatVo);
			 }
			 Map<Long,Long> casteMap = panchayatVo.getCasteMap();
			 casteMap.put((Long)caste[1],(Long)caste[2]);
			 casteMap.put(0l,casteMap.get(0l)-(Long)caste[2]);
			
		 }
		 if(mergePanchayatMap != null && mergePanchayatMap.size() > 0){
		     mergePanchayats(totalCastePriorityMap,mergePanchayatMap);
		     mergeTotalVoters(totalVotersMap,mergePanchayatMap);
		 }
		 if(mergeCasteMap != null && mergeCasteMap.size() > 0){
		     mergeCastes(totalCastePriorityMap,mergeCasteMap);
		 }
		 
		 for(Long panchayatId:totalCastePriorityMap.keySet()){
			 PanchayatVO panchayat = totalCastePriorityMap.get(panchayatId);
			 Map<Long,Long> casteMap = panchayat.getCasteMap();
			 for(Long casteStateId:casteMap.keySet()){
				 Long count = casteMap.get(casteStateId);
				 if(castePercents.get(casteStateId) != null && count != null){
				 panchayat.setOthrExpctdVotes(panchayat.getOthrExpctdVotes()+Math.round(count*castePercents.get(casteStateId)));
				 }else{
					 panchayat.setOthrExpctdVotes(0);
				 }
			 }
			 panchayat.setCasteMap(null);
			 
			 
			 Long totalVoters = totalVotersMap.get(panchayatId);
			 if(totalVoters != null)
			   panchayat.setTotalVoters(totalVoters.intValue());
			 if(totalVoters > 0){
			   panchayat.setTargetPerc(new BigDecimal((panchayat.getOthrExpctdVotes()*100.0/totalVoters)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
			 }else{
				 panchayat.setTargetPerc(0d);
			 }
			 
		   if(type.equalsIgnoreCase("totalCaste")){	 
			   OrderOfPriorityVO priority =  finalOrder.get(panchayatId);
			   if(priority == null){
				   priority = new OrderOfPriorityVO(); 
				   priority.setPanchayatId(panchayatId);
				   priority.setName(panchayat.getPanchayatName());
			   }
			   priority.setTotalVoters(totalVoters);
			   priority.setTargetedVoters(Long.valueOf(panchayat.getOthrExpctdVotes()));
			   if(priority.getPreviousVoters() == null){
				   priority.setPreviousVoters(0l);
			   }
			   priority.setOpportunity(priority.getPreviousVoters() - priority.getTargetedVoters());
			   priority.setOpportunityPerc(new BigDecimal((priority.getOpportunity()*100)/priority.getTotalVoters().floatValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			   
			 Double partyPerc = currentResult.get(panchayatId);
			 if(partyPerc != null){
			    panchayat.setPartyPerc(partyPerc);
			 }else{
				 panchayat.setPartyPerc(0d);
			 }
			 panchayat.setDifferencePerc(panchayat.getTargetPerc()-panchayat.getPartyPerc());
			 panchayat.setOpportunity(panchayat.getTargetPerc()-panchayat.getPartyPerc());
		   } 
		 }
		 List<PanchayatVO> returnList = new ArrayList<PanchayatVO>(totalCastePriorityMap.values());
		 if(type.equalsIgnoreCase("totalCaste")){	
		    calculateForTotalCaste(returnList,weight,finalOrder);
		 }else{
			 calculateForYoungOld(returnList,weight,finalOrder,type); 
		 }
		 return returnList;
	 }
	 
	 public void calculateForTotalCaste(List<PanchayatVO> returnList,Double weight,Map<Long,OrderOfPriorityVO> finalOrder){
		 Collections.sort(returnList,totalCasteSort);
		 Double addValue = 0d;
		 if(returnList.get(returnList.size()-1).getDifferencePerc() < 0){
			  addValue = returnList.get(returnList.size()-1).getDifferencePerc()*(-1);
		 }
			 for(PanchayatVO vo:returnList){
				 vo.setDifferencePerc( vo.getDifferencePerc()+addValue);
				 vo.setVoterPoints(new BigDecimal((vo.getDifferencePerc()*100/returnList.get(0).getDifferencePerc())).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
				 OrderOfPriorityVO priorVo = finalOrder.get(vo.getPanchayatId());
				 if(priorVo == null){
					 priorVo = new OrderOfPriorityVO();
					 priorVo.setPanchayatId(vo.getPanchayatId());
					 priorVo.setName(vo.getPanchayatName());
					 finalOrder.put(vo.getPanchayatId(), priorVo);
				 }
				 priorVo.setCasteWeight(new BigDecimal(vo.getVoterPoints()*weight/100).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
			 }
			 
		 
	 }
	 
	 public void calculateForYoungOld(List<PanchayatVO> returnList,Double weight,Map<Long,OrderOfPriorityVO> finalOrder,String type){
		 Collections.sort(returnList,youngOldSort);
		 for(PanchayatVO vo:returnList){
			 vo.setVoterPoints(new BigDecimal((vo.getTargetPerc()*100/returnList.get(0).getTargetPerc())).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
			 OrderOfPriorityVO priorVo = finalOrder.get(vo.getPanchayatId());
			 if(priorVo == null){
				 priorVo = new OrderOfPriorityVO();
				 priorVo.setPanchayatId(vo.getPanchayatId());
				 priorVo.setName(vo.getPanchayatName());
				 finalOrder.put(vo.getPanchayatId(), priorVo);
			 }
			 if(type.equalsIgnoreCase("youngCaste")){
			     priorVo.setYoungWeight(new BigDecimal(vo.getVoterPoints()*weight/100).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
			 }else{
				 priorVo.setAgeWeight(new BigDecimal(vo.getVoterPoints()*weight/100).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue()); 
			 }
			 
	     }
	 }
	 
	 public void mergePanchayats(Map<Long,PanchayatVO> totalCastePriorityMap,Map<Long,Set<Long>> mergePanchayatMap){
		 for(Long key:mergePanchayatMap.keySet()){
		  if(totalCastePriorityMap.get(key) != null){
			 Map<Long,Long> casteMap = totalCastePriorityMap.get(key).getCasteMap();
			 for(Long panchayatId:mergePanchayatMap.get(key)){
				 if(totalCastePriorityMap.get(panchayatId) != null){
					 Map<Long,Long> mergeMap = totalCastePriorityMap.get(panchayatId).getCasteMap();
					 for(Long casteStateId:casteMap.keySet()){
						 casteMap.put(casteStateId,casteMap.get(casteStateId)+mergeMap.get(casteStateId));
					 }
				 }
				 totalCastePriorityMap.remove(panchayatId);
			 }
		  }
		 }
	 }
	 
     public void mergeCastes(Map<Long,PanchayatVO> totalCastePriorityMap,Map<Long,Set<Long>> mergeCasteMap){
    	for(Long panchayatId:totalCastePriorityMap.keySet()){
    		 Map<Long,Long> casteMap = totalCastePriorityMap.get(panchayatId).getCasteMap();
    		 for(Long casteStateId:mergeCasteMap.keySet()){
    			 Long mainCount = casteMap.get(casteStateId);
    			 if(mainCount != null){
	    			 for(Long mergeCasteStateId:mergeCasteMap.get(casteStateId)){
	    				if(casteMap.get(mergeCasteStateId) != null){
	    					casteMap.put(casteStateId,casteMap.get(casteStateId)+casteMap.get(mergeCasteStateId));
	    				}
	    			 }
    			 }
    		 }
    	}
	 }
     
     public void mergeTotalVoters(Map<Long,Long> totalVotersMap,Map<Long,Set<Long>> mergePanchayatMap){
    	 for(Long key:mergePanchayatMap.keySet()){
			 for(Long panchayatId:mergePanchayatMap.get(key)){
				 totalVotersMap.put(key,totalVotersMap.get(key)+totalVotersMap.get(panchayatId));
				 totalVotersMap.remove(panchayatId);
			 }
		 }
 	 }
     
       public  Comparator<PanchayatVO> totalCasteSort = new Comparator<PanchayatVO>()
	   {
			  
		  public int compare(PanchayatVO loc1, PanchayatVO loc2)
			{
			   return (loc2.getDifferencePerc().compareTo(loc1.getDifferencePerc()));
			}
	   };
     		  
	   public  Comparator<PanchayatVO> youngOldSort = new Comparator<PanchayatVO>()
	   {
			  
		  public int compare(PanchayatVO loc1, PanchayatVO loc2)
			{
			   return (loc2.getTargetPerc().compareTo(loc1.getTargetPerc()));
			}
	   };
     	
	   public  Comparator<PartyEffectVO> prpSort = new Comparator<PartyEffectVO>()
			   {
					  
				  public int compare(PartyEffectVO loc1, PartyEffectVO loc2)
					{
					   return (loc2.getDifference().compareTo(loc1.getDifference()));
					}
			   };
	   public  Comparator<OrderOfPriorityVO> finalOrderSort = new Comparator<OrderOfPriorityVO>()
	   {
			  
		  public int compare(OrderOfPriorityVO loc1, OrderOfPriorityVO loc2)
			{
			   return (loc2.getTotalWeight().compareTo(loc1.getTotalWeight()));
			}
	   };
			   
       public void populateNames(List<Long> panchayatIds,List<Long> lebIds,Map<Long,String> panchayatNames){
    	   if(panchayatIds.size() > 0){
    		   List<Object[]> panchayatsList = panchayatDAO.getPanchayatsByPanchayatIdsList(panchayatIds);
    		   for(Object[] panchayat:panchayatsList){
    			   panchayatNames.put((Long)panchayat[0], panchayat[1].toString());
    		   }
    	   }
    	   if(lebIds.size() > 0){
    		   List<Object[]> panchayatsList = localElectionBodyDAO.getLocalElectionBodyNames(lebIds);
    		   for(Object[] panchayat:panchayatsList){
    			   panchayatNames.put((Long)panchayat[0], panchayat[1].toString());
    		   }
    	   }
       }
       
       public List<PartyEffectVO> calculateWeightsForPrpImpact(Map<Long,PartyEffectVO> prpEffect,Long publicationId,Map<Long,OrderOfPriorityVO> finalOrder,Double prpWt){
    	   List<PartyEffectVO> prpList =  new ArrayList<PartyEffectVO>(prpEffect.values());
   		for(Long location:prpEffect.keySet()){
   			PartyEffectVO locationVo = prpEffect.get(location);
   			OrderOfPriorityVO priority = finalOrder.get(location);
				if(priority == null){
					priority = new OrderOfPriorityVO();
					priority.setPanchayatId(locationVo.getId());
					priority.setName(locationVo.getName());
					
					finalOrder.put(locationVo.getId(), priority);
				}
				if(locationVo.getTdpCurrentVotes() != null){
				  priority.setPreviousVoters(locationVo.getTdpCurrentVotes());
				}if(locationVo.getTdpPrevVotes() != null){
				   priority.setPrevElectionVotes(locationVo.getTdpPrevVotes());
				}if(locationVo.getTdpCurrentPerc() != null){
					 priority.setCurrentPerc(locationVo.getTdpCurrentPerc());
				}
                 if(locationVo.getTdpPrevPerc() != null){
                	 priority.setPreviousPerc(locationVo.getTdpPrevPerc());
				}
   			if(locationVo.getPrpCurrentPerc() != null && locationVo.getTdpCurrentPerc() != null && locationVo.getTdpPrevPerc() != null){
   				Double prpPerc = locationVo.getPrpCurrentPerc();
   				Double partyDiff = null;
   			
   					
   					Double partyPrev = locationVo.getTdpPrevPerc();
   					Double partyCurrent = locationVo.getTdpCurrentPerc();
   				    partyDiff = partyCurrent-partyPrev;
   				   if(partyDiff < 0){
   					partyDiff = partyDiff*(-1);
   					if(prpPerc > partyDiff){
   						locationVo.setDifference(partyDiff);
   					}else{
   						locationVo.setDifference(prpPerc);
   					}
   				}
   			}
   		}
   		for(Long location:prpEffect.keySet()){
   			PartyEffectVO locationVo = prpEffect.get(location);
   			if(locationVo.getDifference() == null){
   				locationVo.setDifference(0d);
   			}
   		}
   		
   		Collections.sort(prpList,prpSort);
   		
   			for(PartyEffectVO partyEffectVO:prpList){
   				OrderOfPriorityVO priority = finalOrder.get(partyEffectVO.getId());
   				if(priority == null){
   					priority = new OrderOfPriorityVO();
   					priority.setPanchayatId(partyEffectVO.getId());
   					priority.setName(partyEffectVO.getName());
   					finalOrder.put(partyEffectVO.getId(), priority);
   				}
   				if(prpList.get(0).getDifference() != null && prpList.get(0).getDifference() > 0){
   					partyEffectVO.setPoints(new BigDecimal(partyEffectVO.getDifference()*100/prpList.get(0).getDifference()).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
   					priority.setPrpWeight(new BigDecimal(partyEffectVO.getPoints()*prpWt/100).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
   					List<Object[]> panchayatVoterCount = voterCastInfoDAO.getTopThreeCasteForPanchayat(partyEffectVO.getId(),partyEffectVO.getReportLvl(),publicationId,1l);//Long panchayatId,Long reportId,Long publicationId,Long userId
					int i = 0;
					StringBuilder cast = new StringBuilder("");
					 for(Object[] panchayat:panchayatVoterCount){
						 if(i==3){
						 break;
						 }
						 cast.append("("+panchayat[0].toString());
						 cast.append(","+panchayat[2].toString());
						 cast.append(","+panchayat[1].toString()+")");
						 i++;
					 }
					 partyEffectVO.setCastes(cast.toString());
   				}
   		   }
   		return prpList;
   	}
       public void calculateWeightsForPreviousTrents(List<PartyPositionVO> orderOfPriority,Map<Long,OrderOfPriorityVO> finalOrder,double prevTrendWeigthPerc){
    	   for(PartyPositionVO panchayat:orderOfPriority){
    		   OrderOfPriorityVO vo = new OrderOfPriorityVO();
    		   vo.setPanchayatId(panchayat.getId());
    		   vo.setName(panchayat.getName());
    		   vo.setPreviousVoters(panchayat.getSelectedPartyTotalVoters());
    		   finalOrder.put(panchayat.getId(), vo);
    		   double priorityVal = 0.0d;
				int priorityAreasCount = 14;
				priorityVal = new BigDecimal(100.00d/priorityAreasCount).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				Double points = new BigDecimal((priorityAreasCount+1-panchayat.getPriorityOrder())*priorityVal).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				vo.setPrevTrnzWeight(new BigDecimal(points*prevTrendWeigthPerc/100).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
    	   }
       }
       public List<OrderOfPriorityVO>  calculateFinalOrder(List<OrderOfPriorityVO>  finalOrder){
		   for(OrderOfPriorityVO priority:finalOrder){
			   if(priority.getOpportunity() == null){
				   priority.setOpportunity(0l);
			   }
			   if(priority.getOpportunityPerc() == null){
				   priority.setOpportunityPerc(0d);
			   }
			   priority.setTotalWeight(priority.getPrpWeight()+priority.getYoungWeight()+priority.getAgeWeight()+priority.getCasteWeight()+priority.getPrevTrnzWeight());
			  
		   }
		   Collections.sort(finalOrder,finalOrderSort);	
		   return finalOrder;
	   }
       
       public Double getPartyPreviousResultAverage(Long partyId,Long constituencyId){
    	   Double avgPerc = 0d;
    	   int totalYears = 0;
    	   List<Long> partyIds = new ArrayList<Long>();
		   partyIds.add(partyId);
    	   List<Long> electionIds = electionDAO.getAllAssemblyMainElectionsIdsInAP();
    	   for(Long electionId:electionIds){
    		   
    		   List<String> partyPerc = candidateResultDAO.getPartyPercentage(constituencyId, electionId, partyIds);
    		   if(partyPerc != null && partyPerc.size()> 0 && partyPerc.get(0) != null){
    			   avgPerc = avgPerc+Double.valueOf(partyPerc.get(0).trim());
    			   totalYears = totalYears+1;
    		   }else{
    			   AlliancePartyResultsVO alliancePartiesVO = staticDataService.getAlliancePartiesByElectionAndParty(electionId,partyId);
    			   if(alliancePartiesVO != null && alliancePartiesVO.getAllianceParties() != null){
    			    List<Long> allianceIds = new ArrayList<Long>();
 					  for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties()){
						 allianceIds.add(alianceParty.getId());
 					  }
    			    if(allianceIds.size() > 0){
    			    	List<String> alincPartyPerc = candidateResultDAO.getPartyPercentage(constituencyId, electionId, partyIds);
    			    	if(alincPartyPerc != null && alincPartyPerc.size()> 0 && alincPartyPerc.get(0) != null){
    		    			   avgPerc = avgPerc+Double.valueOf(alincPartyPerc.get(0).trim());
    		    			   totalYears = totalYears+1;
    		    		   }
    			    }
    		       }
    		   }
    	   }
    	   if(totalYears > 0){
    		   avgPerc = avgPerc/totalYears;
    	   }
    	   return avgPerc;
       }
       public List<OrderOfPriorityVO> calculateCriticalModerateEasyPanchs(List<OrderOfPriorityVO> finalOrderOfOriority){
    	   List<OrderOfPriorityVO>  panchClassific = new ArrayList<OrderOfPriorityVO>();
    	   Long totalOpportunity = 0l;
    	   
    	   Double highOppourtunity =0d;
    	   
    	   Double criticalOppourtunity =0d;
    	   
    	   Double mediumOppourtunity =0d;
    	   
    	   Double easyOppourtunity =0d;
    	   
    	   Double goodOppourtunity =0d;
    	   
    	   for(OrderOfPriorityVO priority:finalOrderOfOriority){
    		   totalOpportunity = totalOpportunity+priority.getOpportunity();
    	   }
    	   totalOpportunity = totalOpportunity*(-1);
    	   highOppourtunity = totalOpportunity*(0.4);
    	   criticalOppourtunity = totalOpportunity*(0.2);
    	   mediumOppourtunity = totalOpportunity*(0.15);
    	   easyOppourtunity = totalOpportunity*(0.15);
    	   goodOppourtunity = totalOpportunity*(0.1);
    	   
    	   int size = 0;
    	   int count = 0;
    	   Double gainablePerc = 0d;
    	   Long gainableVotes = 0l;
    	   OrderOfPriorityVO highCriticalPrior = new OrderOfPriorityVO();
    	   for(int i = 0;i< finalOrderOfOriority.size();i++){
    		   if(gainableVotes < highOppourtunity){
    		     count = count+1;
    		     gainableVotes = gainableVotes+((-1)*finalOrderOfOriority.get(i).getOpportunity());
    		     gainablePerc = gainablePerc+((-1)*finalOrderOfOriority.get(i).getOpportunityPerc());
    		     finalOrderOfOriority.get(i).setType("Highly Critical");
    		   }else{
    			   break;
    		   }
    		   size= size+1;
    		   
    	   }
    	   highCriticalPrior.setName("Highly Critical Panchayats");
    	   highCriticalPrior.setTotalVoters(Long.valueOf(count));
    	   highCriticalPrior.setOpportunityPerc(gainablePerc/count);
    	   highCriticalPrior.setOpportunity(gainableVotes);
    	   panchClassific.add(highCriticalPrior);
    	   
    	   if(size < finalOrderOfOriority.size()){
	    	    count = 0;
	    	    gainablePerc = 0d;
	    	    gainableVotes = 0l;
	    	    
	    	    OrderOfPriorityVO criticalPrior = new OrderOfPriorityVO();
	     	   for(int i = size;i< finalOrderOfOriority.size();i++){
	     		   if(gainableVotes < criticalOppourtunity){
	     		     count = count+1;
	     		     gainableVotes = gainableVotes+((-1)*finalOrderOfOriority.get(i).getOpportunity());
	     		     gainablePerc = gainablePerc+((-1)*finalOrderOfOriority.get(i).getOpportunityPerc());
	     		    finalOrderOfOriority.get(i).setType("Critical");
	     		   }else{
	     			   break;
	     		   }
	     		  size= size+1;
	     	   }
	     	 if(count > 0){
	     	  criticalPrior.setName("Critical Panchayats");
	     	  criticalPrior.setTotalVoters(Long.valueOf(count));
	     	  criticalPrior.setOpportunityPerc(gainablePerc/count);
	     	  criticalPrior.setOpportunity(gainableVotes);
	     	  panchClassific.add(criticalPrior);
	     	 }
    	   }
    	   if(size < finalOrderOfOriority.size()){
		 	    count = 0;
		 	    gainablePerc = 0d;
		 	    gainableVotes = 0l;
		 	    
		 	    OrderOfPriorityVO mediumPrior = new OrderOfPriorityVO();
		  	   for(int i = size;i< finalOrderOfOriority.size();i++){
		  		   if(gainableVotes < mediumOppourtunity){
		  		     count = count+1;
		  		     gainableVotes = gainableVotes+((-1)*finalOrderOfOriority.get(i).getOpportunity());
		  		     gainablePerc = gainablePerc+((-1)*finalOrderOfOriority.get(i).getOpportunityPerc());
		  		   finalOrderOfOriority.get(i).setType("Medium");
		  		   }else{
		  			   break;
		  		   }
		  		 size= size+1;
		  	   }
		  	 if(count > 0){
		  	   mediumPrior.setName("Medium Panchayats");
		  	   mediumPrior.setTotalVoters(Long.valueOf(count));
		  	   mediumPrior.setOpportunityPerc(gainablePerc/count);
		  	   mediumPrior.setOpportunity(gainableVotes);
		  	   panchClassific.add(mediumPrior);
		  	 }
    	   }
    	   if(size < finalOrderOfOriority.size()){
			    count = 0;
			    gainablePerc = 0d;
			    gainableVotes = 0l;
			    
			    OrderOfPriorityVO easyPrior = new OrderOfPriorityVO();
			   for(int i = size;i< finalOrderOfOriority.size();i++){
				   if(gainableVotes < easyOppourtunity){
				     count = count+1;
				     gainableVotes = gainableVotes+((-1)*finalOrderOfOriority.get(i).getOpportunity());
				     gainablePerc = gainablePerc+((-1)*finalOrderOfOriority.get(i).getOpportunityPerc());
				     finalOrderOfOriority.get(i).setType("Easy");
				   }else{
					   break;
				   }
				   size= size+1;
			   }
			  if(count > 0){
			   easyPrior.setName("Easy Panchayats");
			   easyPrior.setTotalVoters(Long.valueOf(count));
			   easyPrior.setOpportunityPerc(gainablePerc/count);
			   easyPrior.setOpportunity(gainableVotes);
			   panchClassific.add(easyPrior);
			  }
    	   }
    	   if(size < finalOrderOfOriority.size()){
			    count = 0;
			    gainablePerc = 0d;
			    gainableVotes = 0l;
			    
			    OrderOfPriorityVO goodPrior = new OrderOfPriorityVO();
			   for(int i = size;i< finalOrderOfOriority.size();i++){
				   if(gainableVotes < goodOppourtunity){
				     count = count+1;
				     gainableVotes = gainableVotes+((-1)*finalOrderOfOriority.get(i).getOpportunity());
				     gainablePerc = gainablePerc+((-1)*finalOrderOfOriority.get(i).getOpportunityPerc());
				     finalOrderOfOriority.get(i).setType("Good");
				   }else{
					   break;
				   }
				   size= size+1;
			   }
			  if(count > 0){
			   goodPrior.setName("Good Panchayats");
			   goodPrior.setTotalVoters(Long.valueOf(count));
			   goodPrior.setOpportunityPerc(gainablePerc/count);
			   goodPrior.setOpportunity(gainableVotes);
			   panchClassific.add(goodPrior);
			  }
    	   }
    	   
    	   return panchClassific;
       }       
       
       
	   @SuppressWarnings("unchecked")
	public List<Object> getPrioritiesToTarget(StrategyVO strategyVO,boolean partyPerformanceReq){
		   List<Object> targetingObjects = new ArrayList<Object>();
		   Map<Long,PartyEffectVO> partyEffect = new HashMap<Long,PartyEffectVO>();
		   Map<Long,Double> currentResult = new HashMap<Long,Double>();
		   Map<Long,OrderOfPriorityVO> finalOrder = new HashMap<Long,OrderOfPriorityVO>();
		   List<PanchayatVO> totalCastesList = null;
		   List<PanchayatVO> youngCastesList = null;
		   List<PanchayatVO> agedCastesList = null;
		   Map<Long,String> casteNameMap = null;
		   Map<String,Float> casteNamePercMap = null;
			
		   List<PartyPositionVO>  previousTrends = getPartyPreviousTrends(strategyVO,strategyVO.getConstituencyId(),strategyVO.getPartyId(),strategyVO.getElectionIds(),partyEffect,strategyVO.getEffectPartyId(),strategyVO.getEffectElectionId(),currentResult);
		   
		   calculateWeightsForPreviousTrents(previousTrends.get(0).getSuggestedLocations(),finalOrder,strategyVO.getPrevTrnzWt());
		   
		   List<PartyEffectVO> otherPartyEffect = calculateWeightsForPrpImpact(partyEffect,strategyVO.getPublicationId(),finalOrder,strategyVO.getPrpWt());
		   
		   if(strategyVO.getCastePercents() != null){
			   List<Object> castePriorities = getTotalYoungOldVotersEffectWithCaste(strategyVO,currentResult,finalOrder);
			   totalCastesList = (List<PanchayatVO>)castePriorities.get(0);
			   youngCastesList = (List<PanchayatVO>)castePriorities.get(1);
			   agedCastesList  = (List<PanchayatVO>)castePriorities.get(2);
		   }else{
			   List<Object> voterPriorities = getYoungOldVotersEffectWithOutCaste(strategyVO,finalOrder);
			   youngCastesList = (List<PanchayatVO>)voterPriorities.get(0);
			   agedCastesList  = (List<PanchayatVO>)voterPriorities.get(1);
		   }
		   List<OrderOfPriorityVO> finalOrderOfOriority = calculateFinalOrder(new ArrayList<OrderOfPriorityVO>(finalOrder.values()));
		   List<OrderOfPriorityVO> panchayatsClassification = calculateCriticalModerateEasyPanchs(finalOrderOfOriority);
   		   
		   List<ImpFamilesVO> impfamilesList = new ArrayList<ImpFamilesVO>();
		   getImpFamilesList(finalOrderOfOriority.get(0).getPanchayatId(),strategyVO.getPublicationId(),impfamilesList,"panchayat","top5");
		   
		   if(strategyVO.getCastePercents() != null && strategyVO.getCastePercents().size() > 0)
		   {
			   
			   List<Object[]> casteNames = casteStateDAO.getCasteNamesByCasteIds(new ArrayList<Long>(strategyVO.getCastePercents().keySet()));
			   if(casteNames != null && casteNames.size() > 0)
			   {
				   casteNamePercMap = new HashMap<String, Float>();
				   casteNameMap = new HashMap<Long, String>();
				   for (Object[] objects : casteNames)
				   {
					   casteNameMap.put((Long)objects[0], objects[1].toString());
				   }
				   for(Long casteStateId :casteNameMap.keySet())
				   {
					   casteNamePercMap.put(casteNameMap.get(casteStateId), strategyVO.getCastePercents().get(casteStateId));
				   }
			   }
		   }
		    //path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\PartyAnalyst\\";		   
/*		   Document document = new Document();
		   String filePath = "VMR"+"/1.pdf";
		   String FILE = path+filePath;
		   File file = new File(FILE);
		   PdfWriter writer = null;
		   try {
			   file.createNewFile();
			   writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
		   } catch (Exception e) {
			   LOG.debug("Exception raised in getPrioritiesToTarget() method",e);
		   }
		   document.open();
		  
		   if(casteNamePercMap != null && casteNamePercMap.size() > 0)
		   {
			   generateCasteWiseTable(document,casteNamePercMap);//1
		   }
		  
		   if(strategyVO.getCastePercents() != null){
		     panchayatWiseTargetVotesTable(document,totalCastesList);//2
		   }*/
		   List<PartyPositionVO> list = null;
		   try {
			  if(partyPerformanceReq){
			    list = suggestiveModelService.getPartyPerfromanceStratagicReport(strategyVO.getConstituencyId(),872l,strategyVO.getEffectElectionId());
			  }
		} catch (Exception e) {
			
		}
		  
		   
		  /* if(list != null && list.size() > 0)
		   {
			   //panchayatwisePartyPerformanceTable(document,list,1l);//3
			   //panchayatwisePartyPerformanceTable(document,list,2l);//3
			   //buildChartForPartyPerformanceReort(document,list);
		   }*/
		   
		 /*  generatePdfForMatrixReport(document,previousTrends);//4
		  
		     panchayatWiseTargetYoungVotesTable(document,youngCastesList,"18-22");//5
		     panchayatWiseTargetYoungVotesTable(document,agedCastesList,"Above 60");//6
		    
		   prpEffectTableTable(document,otherPartyEffect);//7
		   buildPiChart(document,panchayatsClassification,writer);//8
		   buildPanchayatsClassificationBlock(document,panchayatsClassification);//8
		   if(impfamilesList != null && impfamilesList.size() > 0)
		   {
			   generateImpFamilesTable(document,impfamilesList);//9
		   }		   
		   orderOFPriorityTable(document,finalOrderOfOriority,15);//10
		   document.close();*/
		   
		     targetingObjects.add(casteNamePercMap);//1
		     targetingObjects.add(totalCastesList);//2
		     targetingObjects.add(list);//3
		     targetingObjects.add(previousTrends);//4
		     targetingObjects.add(youngCastesList);//5
		     targetingObjects.add(agedCastesList);//6
		     targetingObjects.add(otherPartyEffect);//7
		     targetingObjects.add(panchayatsClassification);//8
		     targetingObjects.add(impfamilesList);//9
		     targetingObjects.add(finalOrderOfOriority);//10
		   return targetingObjects;
	   }
	   
	   
	   public void getImpFamilesList(Long panchayatId,Long publicationDateId,List<ImpFamilesVO> impfamilesList,String type,String reqFor)
       {
    	   try
    	   {
    		   LOG.info("Enterd into getImpFamilesList() method");
    		   List<Object[]> boothWiseDetails = null;
    		   if(type.endsWith("panchayat"))
    		   {
    			   boothWiseDetails = boothPublicationVoterDAO.getPanchayatwiseImpFamiles(publicationDateId,panchayatId);
    		   }
    		   else
    		   {
    			   boothWiseDetails = boothPublicationVoterDAO.getImpFamilesForMuncipality(publicationDateId,panchayatId);
    		   }
    		   
    		   if(boothWiseDetails != null && boothWiseDetails.size() > 0)
    		   {
    			   int impCount = 0;

    			   for (Object[] objects : boothWiseDetails)
    			   {
    				   if(impCount == 5)
    				   {
    					   break;
    				   }
        			   
    				   if(objects[1] != null && objects[2] != null && objects[3] != null)
    				   {
    					   
    					   ImpFamilesVO impFamilesVO = new ImpFamilesVO();
        				   impFamilesVO.setBoothId(Long.valueOf(objects[1].toString()));
        				   impFamilesVO.setHouseNo(objects[2].toString());
        				   impFamilesVO.setPanchayatName(objects[3].toString());
        				   impFamilesVO.setPanchayatId((Long)objects[4]);
        				   List<Object[]> voterDetails = boothPublicationVoterDAO.getElderPersonDetails(publicationDateId, (Long)objects[0], objects[2].toString());
        				   if(voterDetails != null && voterDetails.size() > 0)
        				   {
        					   int count = 0;
        					   int size = voterDetails.size();
        					   Boolean flag = false;
        					   for (Object[] objects2 : voterDetails)
        					   {
        						   if(count  == 0)
        						   {
        							   impFamilesVO.setElderPerson(objects2[1] != null ? objects2[1].toString() : null);
        							   impFamilesVO.setElderPersonAge(objects2[3] != null ? Long.valueOf(objects2[3].toString()) : null);
        							   impFamilesVO.setEldPersomGender(objects2[2] != null ? objects2[2].toString() : null);
        							   impFamilesVO.setElderVoterId(objects2[4] != null ? objects2[4].toString() : null);
        							   impFamilesVO.setCount(Long.valueOf(size));
        							   if(objects2[0] != null)
        							   {
        								   List<Long> casteStateIds = new ArrayList<Long>();
            							   casteStateIds.add(Long.valueOf(objects2[0].toString()));
            							   List<Object[]> castes = casteStateDAO.getCasteNamesByCasteIds(casteStateIds);
            							   impFamilesVO.setCaste(castes.get(0)[1].toString());
        							   }
        							   
        						   }
        						   impFamilesVO.setYoungerPerson(voterDetails.get(size-1)[1] != null ? voterDetails.get(size-1)[1].toString() : null);
    							   impFamilesVO.setYoungerPersonAge(voterDetails.get(size-1)[3] != null ? Long.valueOf(voterDetails.get(size-1)[3].toString()) : null);
    							   impFamilesVO.setYoungPersomGender(voterDetails.get(size-1)[2] != null ? voterDetails.get(size-1)[2].toString() : null);
    							   impFamilesVO.setYoungerVoterId(objects2[4] != null ? objects2[4].toString() : null);
        						   for (int i = size-1; i >= 1; i--)
        						   {
        							   if(voterDetails.get(i)[2] != null)
        							   {
        
        								   
        								   if(voterDetails.get(i)[2].toString().trim().equalsIgnoreCase("M"))
        								   {
        									   impFamilesVO.setYoungerPerson(voterDetails.get(i)[1] != null ? voterDetails.get(i)[1].toString() : null);
                							   impFamilesVO.setYoungerPersonAge(voterDetails.get(i)[3] != null ? Long.valueOf(voterDetails.get(i)[3].toString()) : null);
                							   impFamilesVO.setYoungPersomGender(voterDetails.get(i)[2] != null ? voterDetails.get(i)[2].toString() : null);
                							   impFamilesVO.setYoungerVoterId(objects2[4] != null ? objects2[4].toString() : null);
                							   flag = true;
                							   break;
        								   }
        							   }
        						   }
        						   if(flag)
        						   {
        							   break;
        						   }
        						   count++;
        					   }
        				   }
        				   impfamilesList.add(impFamilesVO);
    				   }
    				   if(reqFor != null)
        			   {
    					   impCount++;
        			   }
    				     
    			   }
    		   }
    		   
    	   } 
    	   catch (Exception e)
    	   {
    		   LOG.debug("Exception raised in getImpFamilesList() method ",e);
    	   }
       }
	   
	   public void generateImpFamilesTable(Document document , List<ImpFamilesVO> list,String reqType)
	   {
		   try 
		   {
			   LOG.info("Enterd into generateImpFamilesTable() method");
			   
			   Font calibriBold = FontFactory.getFont("Calibri",9,Font.BOLD);
			   calibriBold = FontFactory.getFont("Calibri",9,Font.ITALIC);
			  
			   Font topHeading = new Font(Font.FontFamily.TIMES_ROMAN, 20,Font.BOLD);
			   topHeading.setColor( new BaseColor(111,165,235));//69,109,142,
			   Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
			   int padding = 6;
			   subHeading.setColor(new BaseColor(69,109,142));
			   if(reqType == null)
			   {
				   
				   
				   PdfPTable infTable = new PdfPTable(5);
				   infTable.setWidthPercentage(100);
					
				   Paragraph heading = new Paragraph();
				   heading.setAlignment(Element.PTABLE);
				   heading.add( new Paragraph(" ") );
				   heading.add( new Paragraph("Other Factors",subHeading));
				   heading.add( new Paragraph(" ") );
				   document.add(heading); 
				   
				   Paragraph preface1 = new Paragraph();
				   preface1.setAlignment(Element.PTABLE);
				   preface1.add( new Paragraph(" ") );
				   preface1.add( new Paragraph("Influential People",subHeading));
				   preface1.add( new Paragraph(" ") );
				   document.add(preface1); 
				   infTable.setWidthPercentage(100);
					
				   PdfPCell cell1 ;
				   cell1 = new PdfPCell(new Phrase("SNO",calibriBold));
				   cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell1.setBackgroundColor(BaseColor.YELLOW);
				   cell1.setPadding(padding); 
				   infTable.addCell(cell1);
			  	   
			  	   cell1 = new PdfPCell(new Phrase("Panchayath Name",calibriBold));
			  	   cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell1.setBackgroundColor(BaseColor.YELLOW);
			  	   cell1.setPadding(padding); 
			  	   infTable.addCell(cell1);
			  	   
			  	   cell1 = new PdfPCell(new Phrase("Our People",calibriBold));
			  	   cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell1.setBackgroundColor(BaseColor.YELLOW);
			  	   cell1.setPadding(padding); 
			  	   infTable.addCell(cell1);
			  	   
			  	   cell1 = new PdfPCell(new Phrase("Opposition",calibriBold));
			  	   cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell1.setBackgroundColor(BaseColor.YELLOW);
			  	   cell1.setPadding(padding); 
			  	   infTable.addCell(cell1);
			  	   
			  	   cell1 = new PdfPCell(new Phrase("Neutral",calibriBold));
			  	   cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell1.setBackgroundColor(BaseColor.YELLOW);
			  	   cell1.setPadding(padding); 
			  	   infTable.addCell(cell1);
			  	   
			  	   for(int i= 0 ; i < 9 ; i++)
			  	   {
					   cell1 = new PdfPCell(new Phrase("",calibriBold));
					   cell1.setPadding(padding); 
					   infTable.addCell(cell1);
				  	   
				  	   cell1 = new PdfPCell(new Phrase("",calibriBold));
				  	   cell1.setPadding(padding); 
				  	   infTable.addCell(cell1);
				  	   
				  	   cell1 = new PdfPCell(new Phrase("",calibriBold));
				  	   cell1.setPadding(padding); 
				  	   infTable.addCell(cell1);
				  	   
				  	   cell1 = new PdfPCell(new Phrase("",calibriBold));
				  	   cell1.setPadding(padding); 
				  	   infTable.addCell(cell1);
				  	   
				  	   cell1 = new PdfPCell(new Phrase("",calibriBold));
				  	   cell1.setPadding(padding); 
				  	   infTable.addCell(cell1);
			  	   }
					
			  	    document.add(infTable);
			  	    Paragraph spacing = new Paragraph();
			  	    spacing.setAlignment(Element.PTABLE);
			  	    spacing.add( new Paragraph(" ") );
			  	    spacing.add( new Paragraph(" ") );
			  	    document.add(spacing); 
			   }
			   
		  	    
		  	    PdfPTable table = new PdfPTable(12);
				Paragraph preface = new Paragraph();
				preface.setAlignment(Element.PTABLE);
				preface.add( new Paragraph(" ") );
				preface.add( new Paragraph("TOP FAMILES",subHeading));
				preface.add( new Paragraph(" ") );
				document.add(preface); 
				table.setWidthPercentage(100);
				PdfPCell cell ;
			  	  
	          
				cell = new PdfPCell(new Phrase("Booth",style3));
			  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setBackgroundColor(BaseColor.YELLOW);
			    cell.setPadding(padding); 
			    table.addCell(cell);
			  	   
		  	   cell = new PdfPCell(new Phrase("House No",style3));
		  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	   cell.setBackgroundColor(BaseColor.YELLOW);
		  	   cell.setPadding(padding); 
		  	   table.addCell(cell);
		  	   
		  	   cell = new PdfPCell(new Phrase("Count",style3));
		  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	   cell.setBackgroundColor(BaseColor.YELLOW);
		  	   cell.setPadding(padding); 
		  	   table.addCell(cell);
		  	   
		  	   cell = new PdfPCell(new Phrase("Caste",style3));
		  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	   cell.setBackgroundColor(BaseColor.YELLOW);
		  	   cell.setPadding(padding); 
		  	   table.addCell(cell);
		  	   
		  	   cell = new PdfPCell(new Phrase("Eldest Person",style3));
		  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	   cell.setBackgroundColor(BaseColor.YELLOW);
		  	   cell.setPadding(padding); 
		  	   table.addCell(cell);
		  	   
		  	   cell = new PdfPCell(new Phrase("Voter Id",style3));
		  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	   cell.setBackgroundColor(BaseColor.YELLOW);
		  	   cell.setPadding(padding); 
		  	   table.addCell(cell);
		  	   
		  	   cell = new PdfPCell(new Phrase("Gender",style3));
		  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	   cell.setBackgroundColor(BaseColor.YELLOW);
		  	   cell.setPadding(padding); 
		  	   table.addCell(cell);
		  	   
		  	   cell = new PdfPCell(new Phrase("Age",style3));
		  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	   cell.setBackgroundColor(BaseColor.YELLOW);
		  	   cell.setPadding(padding); 
		  	   table.addCell(cell);
		  	   
		  	   cell = new PdfPCell(new Phrase("Youngest Person",style3));
		  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	   cell.setBackgroundColor(BaseColor.YELLOW);
		  	   cell.setPadding(padding); 
		  	   table.addCell(cell);
		  	 
		  	   cell = new PdfPCell(new Phrase("Voter Id",style3));
		  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	   cell.setBackgroundColor(BaseColor.YELLOW);
		  	   cell.setPadding(padding); 
		  	   table.addCell(cell);
		  	   
		  	   cell = new PdfPCell(new Phrase("Gender",style3));
		  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	   cell.setBackgroundColor(BaseColor.YELLOW);
		  	   cell.setPadding(padding);
		  	   table.addCell(cell);
		  	   
		  	   cell = new PdfPCell(new Phrase("Age",style3));
		  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	   cell.setBackgroundColor(BaseColor.YELLOW);
		  	   cell.setPadding(padding); 
		  	   table.addCell(cell);
		  	   
		  	   for (ImpFamilesVO impFamilesVO : list)
		  	   {
		  		   cell = new PdfPCell(new Phrase(impFamilesVO.getBoothId() != null ? impFamilesVO.getBoothId().toString() :"",style4));
			  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell.setPadding(padding); 
			  	   table.addCell(cell);
			  	   
			  	   cell = new PdfPCell(new Phrase(impFamilesVO.getHouseNo() != null ? impFamilesVO.getHouseNo().toString() :"",style4));
			  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell.setPadding(padding); 
			  	   table.addCell(cell);
			  	   
			  	   cell = new PdfPCell(new Phrase(impFamilesVO.getCount() != null ? impFamilesVO.getCount().toString() :"",style4));
			  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell.setPadding(padding); 
			  	   table.addCell(cell);
			  	   
			  	   cell = new PdfPCell(new Phrase(impFamilesVO.getCaste() != null ? impFamilesVO.getCaste().toString() :"",style4));
			  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell.setPadding(padding); 
			  	   table.addCell(cell);
			  	   
			  	   cell = new PdfPCell(new Phrase(impFamilesVO.getElderPerson() != null ? impFamilesVO.getElderPerson().toString() :"",style4));
			  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   table.addCell(cell);
			  	   
			  	   cell = new PdfPCell(new Phrase(impFamilesVO.getElderVoterId() != null ? impFamilesVO.getElderVoterId().toString() :"",style4));
			  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell.setPadding(padding); 
			  	   table.addCell(cell);
			  	   
			  	   cell = new PdfPCell(new Phrase(impFamilesVO.getEldPersomGender() != null ? impFamilesVO.getEldPersomGender().toString() :"",style4));
			  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell.setPadding(padding); 
			  	   table.addCell(cell);
			  	   
			  	   cell = new PdfPCell(new Phrase(impFamilesVO.getElderPersonAge() != null ? impFamilesVO.getElderPersonAge().toString() :"",style4));
			  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell.setPadding(padding); 
			  	   table.addCell(cell);
			  	   
			  	   cell = new PdfPCell(new Phrase(impFamilesVO.getYoungerPerson() != null ? impFamilesVO.getYoungerPerson().toString() :"",style4));
			  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell.setPadding(padding); 
			  	   table.addCell(cell);
			  	   
			  	   cell = new PdfPCell(new Phrase(impFamilesVO.getYoungerVoterId() != null ? impFamilesVO.getYoungerVoterId().toString() :"",style4));
			  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell.setPadding(padding); 
			  	   table.addCell(cell);
			  	   
			  	   cell = new PdfPCell(new Phrase(impFamilesVO.getYoungPersomGender() != null ? impFamilesVO.getYoungPersomGender().toString() :"",style4));
			  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell.setPadding(padding); 
			  	   table.addCell(cell);
			  	   
			  	   cell = new PdfPCell(new Phrase(impFamilesVO.getYoungerPersonAge() != null ? impFamilesVO.getYoungerPersonAge().toString() :"",style4));
			  	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	   cell.setPadding(padding); 
			  	   table.addCell(cell);
		  	   }
		  	    float[] widths = new float[] {1f,1f,.8f,1f,1f,1f,.5f,.8f,1f,1f,0.9f,.5f};
		  	  table.setWidths(widths);
		  	   table.setHeaderRows(1);
		  	   document.add(table);
		  	   if(reqType == null)
		  	   {
		  		  buildDevelopmentMeasuresAndProblems(document);
		  	   }
		  	   
		  	   //document.newPage();
		   } 
		   catch (Exception e)
		   {
			   LOG.debug("Exception raised in generateImpFamilesTable() method ",e);
		   }
	   }
	   
	   public void buildDevelopmentMeasuresAndProblems(Document document)
	   {
		   try 
		   {
			   LOG.info("Enterd into buildDevelopmentMeasuresAndProblems() method");
			   document.newPage();
			   int padding = 6;
			   
			   PdfPTable infTable = new PdfPTable(3);
			   Font calibriBold = FontFactory.getFont("Calibri",9,Font.BOLD);
			   //calibriBold = FontFactory.getFont("Calibri",9,Font.ITALIC);
			   infTable.setWidthPercentage(100);
			   Font topHeading = new Font(Font.FontFamily.TIMES_ROMAN, 20,Font.BOLD);
			   topHeading.setColor( new BaseColor(111,165,235));//69,109,142,
			   Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
			   subHeading.setColor(new BaseColor(69,109,142));
				
			   
			   
			   Paragraph preface1 = new Paragraph();
			   preface1.setAlignment(Element.PTABLE);
			   preface1.add( new Paragraph(" ") );
			   preface1.add( new Paragraph("Development Measures",subHeading));
			   preface1.add( new Paragraph(" ") );
			   document.add(preface1); 
			   infTable.setWidthPercentage(100);
				
			   PdfPCell cell1 ;
			   cell1 = new PdfPCell(new Phrase("SNO",calibriBold));
			   cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			   cell1.setBackgroundColor(BaseColor.YELLOW);
			   cell1.setPadding(padding); 
			   infTable.addCell(cell1);
		  	   
		  	   cell1 = new PdfPCell(new Phrase("Activity Name (Including Date & Time)",calibriBold));
		  	   cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	   cell1.setBackgroundColor(BaseColor.YELLOW);
		  	   cell1.setPadding(padding); 
		  	   infTable.addCell(cell1);
		  	   
		  	   cell1 = new PdfPCell(new Phrase("Reach",calibriBold));
		  	   cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	   cell1.setBackgroundColor(BaseColor.YELLOW);
		  	   cell1.setPadding(padding); 
		  	   infTable.addCell(cell1);
		  	   
		  	   
		  	   for(int i= 0 ; i < 4 ; i++)
		  	   {
				   cell1 = new PdfPCell(new Phrase(""));
				   cell1.setPadding(padding); 
				   infTable.addCell(cell1);
			  	   
			  	   cell1 = new PdfPCell(new Phrase(""));
			  	   cell1.setPadding(padding); 
			  	   infTable.addCell(cell1);
			  	   
			  	   cell1 = new PdfPCell(new Phrase(""));
			  	   cell1.setPadding(padding); 
			  	   infTable.addCell(cell1);
		  	   }
		  	    float[] widths = new float[] {0.5f,3f,2f};
		  	    infTable.setWidths(widths);
		  	    document.add(infTable);
		  	    Paragraph spacing = new Paragraph();
		  	    spacing.setAlignment(Element.PTABLE);
		  	    spacing.add( new Paragraph(" ") );
		  	    spacing.add( new Paragraph(" ") );
		  	    document.add(spacing); 
		  	    
		  	  PdfPTable table = new PdfPTable(2);
		  	  
		  	  Paragraph preface2 = new Paragraph();
		  	  preface2.setAlignment(Element.PTABLE);
		  	  preface2.add( new Paragraph(" ") );
		  	  preface2.add( new Paragraph("Problems",subHeading));
		  	  preface2.add( new Paragraph(" ") );
			  document.add(preface2); 
			  table.setWidthPercentage(100);
				
			   PdfPCell cell ;
			   cell = new PdfPCell(new Phrase("SNO",calibriBold));
			   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			   cell.setBackgroundColor(BaseColor.YELLOW);
			   cell.setPadding(padding); 
			   table.addCell(cell);
		  	   
			   cell = new PdfPCell(new Phrase("Problem Name (Including Date & Time)",calibriBold));
			   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			   cell.setBackgroundColor(BaseColor.YELLOW);
			   cell.setPadding(padding); 
			   table.addCell(cell);
		  	   
		  	   
		  	   
		  	   
		  	   for(int i= 0 ; i < 4 ; i++)
		  	   {
				   cell1 = new PdfPCell(new Phrase(""));
				   cell1.setPadding(padding); 
				   table.addCell(cell1);
			  	   
			  	   cell1 = new PdfPCell(new Phrase(""));
			  	   cell1.setPadding(padding); 
			  	   table.addCell(cell1);
		  	   }
		  	 float[] widths1 = new float[] {0.5f,5f};
		  	table.setWidths(widths1);
		  	 document.add(table);
		  	document.newPage();
		   }
		   catch (Exception e)
		   {
			   LOG.debug("Exception raised in buildDevelopmentMeasuresAndProblems() method ",e);
		   }
	   }
	   public void generateCasteWiseTable(Document document,Map<String,Float> casteNamePercMap)
	   {
		   try {
			   
			    Font topHeading = new Font(Font.FontFamily.TIMES_ROMAN, 20,Font.BOLD);
			    topHeading.setColor( new BaseColor(111,165,235));//69,109,142,
				Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
				subHeading.setColor(new BaseColor(69,109,142));
				Font calibriBold = FontFactory.getFont("Calibri",9,Font.BOLD);
				
			   LOG.info("Enterd into generateCasteWiseTable() method");
				  Paragraph preface = new Paragraph();
				  preface.setAlignment(Element.PTABLE);
				  preface.add( new Paragraph("Key Factors",subHeading));
				  preface.add( new Paragraph("Caste, Previous Trends, Young Voter Base, Aged Voter Base, & PRP Votes that can be",calibriBold));
				  preface.add( new Paragraph("regained."));
				  preface.add( new Paragraph(" ") );
				  preface.add( new Paragraph("Caste",subHeading));
				  preface.add( new Paragraph(" ") );
				  document.add(preface);
				  int totalTables = ((Double)(Math.ceil(casteNamePercMap.size()/6.0f))).intValue(); 
				  
				  int padding = 6;
				 List<String> names = new ArrayList<String>(casteNamePercMap.keySet());
				 Collections.sort(names);
				  PdfPCell cell ;
				  int count = 0;
				  int casteSize = casteNamePercMap.size();
				  for(int i=1;i<=totalTables;i++){
					  PdfPTable table = null;
					  int iterateMin = 0;
					  int iterateMax = 0;
					  if((casteSize-count) >= 6){
						  table = new PdfPTable(6);
						  table.setWidthPercentage(100);
						  iterateMin = count;
						  iterateMax = count+5;
					  }else{
						  table = new PdfPTable(casteSize-count);
						  iterateMin = count;
						  iterateMax = casteSize-1;
					  }
					  for (int j=iterateMin;j<=iterateMax;j++) {
						  count++;
					  	  cell = new PdfPCell(new Phrase(names.get(j),style1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	cell.setPadding(padding);
					  	  table.addCell(cell);
					  }	
					  for (int j=iterateMin;j<=iterateMax;j++) {
					  	  cell = new PdfPCell(new Phrase(Long.valueOf((long) (casteNamePercMap.get(names.get(j))*100)) + "%",style1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	cell.setPadding(padding);
					  	  table.addCell(cell);
					  }
					  table.setHorizontalAlignment(Element.ALIGN_LEFT);
					  document.add(table);
					   preface = new Paragraph();
					   preface.add( new Paragraph(" ") );
					   document.add(preface);
					   
					   Font calibriNormal = FontFactory.getFont("Calibri",9,Font.NORMAL);
					
						  Paragraph preface1 = new Paragraph();
						  preface1.setAlignment(Element.PTABLE);
						  preface1.add( new Paragraph(" ") );
						  preface1.add( new Paragraph(" ") );
						  preface1.add( new Paragraph("List of the Top Panchayats based on the Caste Assumption (Please note that these Panchayats are in order of priority",calibriNormal));
						  preface1.add( new Paragraph("which is though not the final list is though not the final list, you can find the full & final list in \"START HERE\" Section)",calibriNormal));
						  preface1.add( new Paragraph(" ") );
						  preface1.add( new Paragraph(" ") );
						  preface1.add( new Paragraph(" ") );
						  document.add(preface1); 
				  }
				  
		} catch (Exception e) {
			LOG.debug("Exception raised in generateCasteWiseTable() method ",e);
		}
	   }
	   
	   public void panchayatwisePartyPerformanceTable(Document document,List<PartyPositionVO> panchayatList,Long rank,String heading)
	   {
		   try{
			 LOG.info("Entered into panchayatwisePartyPerformanceTable() method in VoterModifiationPdfsGenerations Class");
			 PdfPTable table = new PdfPTable(7);
			 table.setWidthPercentage(100);
		    Font subHeading = FontFactory.getFont("Calibri",11,Font.BOLD);
    	    subHeading.setColor(new BaseColor(69,109,142)); 
    	     Paragraph p =   new Paragraph( heading,subHeading);
     		//p.setFont(subHeading);
     	     document.add(p );
     	     document.add(new Paragraph(" "));
			 PdfPCell cell ;
			 int padding = 6;
			 DecimalFormat df = new DecimalFormat("##.##");
			 cell = new PdfPCell(new Phrase("Panchayat",style1));
			 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  cell.setBackgroundColor(BaseColor.YELLOW);
			  cell.setPadding(padding);
		  	  table.addCell(cell);
		  	  
		  	 cell = new PdfPCell(new Phrase("Total Votes",style1));
			 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  cell.setBackgroundColor(BaseColor.YELLOW);
			  cell.setPadding(padding);
		  	  table.addCell(cell);
		  	  
		  	 cell = new PdfPCell(new Phrase("Votes Polled",style1));
			 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  cell.setBackgroundColor(BaseColor.YELLOW);
			  cell.setPadding(padding);
		  	  table.addCell(cell);
		  	  
		  	 cell = new PdfPCell(new Phrase("TDP Gained",style1));
			 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  cell.setBackgroundColor(BaseColor.YELLOW);
			  cell.setPadding(padding);
		  	  table.addCell(cell);
		  	  
		  	 cell = new PdfPCell(new Phrase("margin",style1));
			 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  cell.setBackgroundColor(BaseColor.YELLOW);
			  cell.setPadding(padding);
		  	  table.addCell(cell);
		  	  
		 	 cell = new PdfPCell(new Phrase("2013 Total Votes",style1));
			 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  cell.setBackgroundColor(BaseColor.YELLOW);
			  cell.setPadding(padding);
		  	  table.addCell(cell);
		  	  
		  	  
		 	 cell = new PdfPCell(new Phrase("Win Party Name",style1));
			 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  cell.setBackgroundColor(BaseColor.YELLOW);
			  cell.setPadding(padding);
		  	  table.addCell(cell);
		  	 
		  	  for(PartyPositionVO partyPositionVO :panchayatList)
		  	  {
		  		  if(partyPositionVO.getRank().equals(rank))
		  		  {
		  			  
		  		  cell = new PdfPCell(new Phrase(partyPositionVO.getName(),style2));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);  
			  	  
			  	  cell = new PdfPCell(new Phrase(Long.valueOf(partyPositionVO.getTotalVoters()).toString(),style2));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);  
			  	  
			  	  
			  	  cell = new PdfPCell(new Phrase(Long.valueOf(partyPositionVO.getTotalValidVotes()).toString(),style2));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);  
			  	  
			  	  
			  	  cell = new PdfPCell(new Phrase(Long.valueOf(partyPositionVO.getSelectedPartyTotalVoters()).toString(),style2));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);  
			  	  
			  	  
			  	  cell = new PdfPCell(new Phrase(df.format(partyPositionVO.getMargin()).toString(),style2));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);
			  	  
			  	  
			  	 cell = new PdfPCell(new Phrase(Long.valueOf(partyPositionVO.getWinPartyTotal()).toString(),style2));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);  
			  
			  	  cell = new PdfPCell(new Phrase(partyPositionVO.getWinPartyName(),style2));
			  	  if(rank == 1 && !partyPositionVO.getWinPartyName().equalsIgnoreCase("TDP"))
				  cell.setBackgroundColor(BaseColor.RED);
			  	  if(rank == 2 && partyPositionVO.getWinPartyName().equalsIgnoreCase("TDP"))
					  cell.setBackgroundColor(BaseColor.GREEN);
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);  
				  }
			 }
		  	table.setHeaderRows(1);
		  	table.setHorizontalAlignment(Element.ALIGN_LEFT);
			 document.add(table);
			 document.newPage();
			  
		   }
		   catch(Exception e)
		   {
			   LOG.error("Exception Occured in panchayatwisePartyPerformanceTable() method",e);
		   }
	   }
	   public void panchayatWiseTargetVotesTable(Document document,List<PanchayatVO> totalCastesList)
		  {
			  try {
				  LOG.info("Enterd into panchayatWiseTargetVotesTable() method in VoterModifiationPdfsGenerations Class");
				  PdfPTable table = new PdfPTable(6);
				  table.setWidthPercentage(100);
				  Font calibriItelac = FontFactory.getFont("Calibri",9,Font.BOLDITALIC);
					Font calibriItelac2 = FontFactory.getFont("Calibri",7,Font.BOLDITALIC);
				  PdfPCell cell ;
				  int padding = 6;  
			        cell = new PdfPCell(new Phrase("Panchayath",calibriItelac));
				  	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				  	cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	table.addCell(cell);
					  
				  	  
				  	  cell = new PdfPCell(new Phrase("2014 Voters",calibriItelac));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	  cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("Total Targeted",calibriItelac));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	  cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("Targeted Percentage",calibriItelac));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	  cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("2009 TDP Voting Percentage",calibriItelac));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	  cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("Opportunity %",calibriItelac));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	  cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  int count = 0;
				  	  for (PanchayatVO panchayatVO : totalCastesList)
				  	  {
				  		  if(count == 14)
				  		  {
				  			  break;
				  		  }
				  		  cell = new PdfPCell(new Phrase(panchayatVO.getPanchayatName(),calibriItelac2));
					  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					  	  cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	  cell = new PdfPCell(new Phrase(Long.valueOf(panchayatVO.getTotalVoters()).toString(),calibriItelac2));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	  cell = new PdfPCell(new Phrase(Long.valueOf(panchayatVO.getOthrExpctdVotes()).toString(),calibriItelac2));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	  cell = new PdfPCell(new Phrase(new BigDecimal(panchayatVO.getTargetPerc()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),calibriItelac2));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	  cell = new PdfPCell(new Phrase(new BigDecimal(panchayatVO.getPartyPerc()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),calibriItelac2));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	  cell = new PdfPCell(new Phrase(new BigDecimal(panchayatVO.getOpportunity()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),calibriItelac2));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	  count ++;
					}
				  	table.setHeaderRows(1);
				  	table.setHorizontalAlignment(Element.ALIGN_LEFT);
					document.add(table);
					document.newPage();
			} catch (Exception e) {
				LOG.debug("Exception raised in panchayatWiseTargetVotesTable() method in VoterModifiationPdfsGenerations Class",e);
			}
		  }
		  
		  public void panchayatWiseTargetYoungVotesTable(Document document,List<PanchayatVO> totalCastesList,String type)
		  {
			  try {
				  	
				  LOG.info("Enterd into panchayatWiseTargetVotesTable() method in VoterModifiationPdfsGenerations Class");
				    PdfPTable table = new PdfPTable(5);
				    table.setWidthPercentage(100);
				    Font calibriNormal = FontFactory.getFont("Calibri",9,Font.NORMAL);
				    Paragraph preface = new Paragraph();
				    preface.setAlignment(Element.PTABLE);
				    preface.add( new Paragraph("Panchayath Wise :",calibriNormal));
				    preface.add( new Paragraph(" ") );
				    Font calibriBold = FontFactory.getFont("Calibri",9,Font.BOLD);
				   
				    document.add(preface);
				    int padding = 6;
			        PdfPCell cell ;
				  	  
			        cell = new PdfPCell(new Phrase("Panchayath",calibriBold));
				  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	  cell.setPadding(padding);
				  	  table.addCell(cell);
					  
				  	  
				  	  cell = new PdfPCell(new Phrase("Total Voters",calibriBold));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase(type,calibriBold));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("Targeted",calibriBold));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("Targeted %",calibriBold));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	 int count = 0;
				  	  for (PanchayatVO panchayatVO : totalCastesList)
				  	  {
				  		  if(count == 14)
				  		  {
				  			  break;
				  		  }
				  		  cell = new PdfPCell(new Phrase(panchayatVO.getPanchayatName(),calibriNormal));
					  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					  	cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	  cell = new PdfPCell(new Phrase(Long.valueOf(panchayatVO.getTotalPanchayatVoters()).toString(),calibriNormal));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	  cell = new PdfPCell(new Phrase(Long.valueOf(panchayatVO.getTotalVoters()).toString(),calibriNormal));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	  cell = new PdfPCell(new Phrase(Long.valueOf(panchayatVO.getOthrExpctdVotes()).toString(),calibriNormal));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	  cell = new PdfPCell(new Phrase(new BigDecimal(panchayatVO.getTargetPerc()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),calibriNormal));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	  count ++;
					  	  
					  	
					}
				  	table.setHorizontalAlignment(Element.ALIGN_LEFT);
				  	table.setHeaderRows(1);
					document.add(table);
					document.newPage();
			} catch (Exception e) {
				LOG.debug("Exception raised in panchayatWiseTargetVotesTable() method in VoterModifiationPdfsGenerations Class",e);
			}
		  }
		  
		  public void prpEffectTableTable(Document document,List<PartyEffectVO> list)
		  {
			  try {
				  	
				  LOG.info("Enterd into panchayatWiseTargetVotesTable() method in VoterModifiationPdfsGenerations Class");
				  
				  DecimalFormat df = new DecimalFormat("##.##"); 
				  int padding = 6;
				  Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
					subHeading.setColor(new BaseColor(69,109,142));
				  PdfPTable table = new PdfPTable(4);
				  table.setWidthPercentage(100);
				    Paragraph preface = new Paragraph();
				    preface.setAlignment(Element.PTABLE);
				    preface.add( new Paragraph("PRP Votes to Regain",subHeading));
				    preface.add( new Paragraph(" ") );
				    document.add(preface);
				    
				    
			        
			        PdfPCell cell ;
				  	  
			        cell = new PdfPCell(new Phrase("Panchayath",style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
					  
				  	  
				  	  cell = new PdfPCell(new Phrase("PRP Gain %",style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  
				  	  
				  	  cell = new PdfPCell(new Phrase("PRP Effect on TDP Party",style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("Major Castes %",style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	 
				  	  for (PartyEffectVO partyEffectVO : list)
				  	  {
				  		  if( partyEffectVO.getDifference() > 0.0)
				  		  {
				  			  cell = new PdfPCell(new Phrase(partyEffectVO.getName(),style2));
						  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  	cell.setPadding(padding);
						  	  table.addCell(cell);
						  	  
						  	  cell = new PdfPCell(new Phrase(df.format(partyEffectVO.getPrpCurrentPerc()).toString(),style2));
						  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						  	cell.setPadding(padding);
						  	  table.addCell(cell);
						  	  
						  	  cell = new PdfPCell(new Phrase(df.format(partyEffectVO.getDifference()).toString(),style2));
						  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						  	cell.setPadding(padding);
						  	  table.addCell(cell);
						  	  
						  	  cell = new PdfPCell(new Phrase(partyEffectVO.getCastes(),style2));
						  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						  	cell.setPadding(padding);
						  	  table.addCell(cell);
						  	  
				  		  }

					}
				  	//table.setHorizontalAlignment(Element.ALIGN_LEFT);
				  	  table.setHeaderRows(1);
					  document.add(table);
					  document.newPage();
			} catch (Exception e) {
				LOG.debug("Exception raised in panchayatWiseTargetVotesTable() method in VoterModifiationPdfsGenerations Class",e);
			}
		  }
		  public void generatePdfForMatrixReport(Document document,List<PartyPositionVO>  previousTrends)
		  {
			  try {
				  LOG.info("Enterd into generatePdfsForImpFamiles() method in VoterModifiationPdfsGenerations Class");
				  int padding = 8;
				  Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
				  subHeading.setColor(new BaseColor(69,109,142));
				    PdfPTable table = new PdfPTable(7);
				    table.setWidthPercentage(100);
				    Paragraph preface = new Paragraph();
				    preface.setAlignment(Element.PTABLE);
				   
				    preface.add( new Paragraph("Previous Trends",subHeading));
				    preface.add( new Paragraph(" ") );
				    document.add(preface);
				    
					  
					  PdfPCell cell ;
				  	  cell = new PdfPCell(new Phrase(previousTrends.get(0).getName()+ "/" + previousTrends.get(1).getName(),style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(new BaseColor(214, 195, 139));
				  	  cell.setPadding(padding);
				  	  table.addCell(cell);
					  
				  	  
				  	  cell = new PdfPCell(new Phrase("WORST",style5));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(new BaseColor(255, 0, 0));
				  	  cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("VERY POOR",style5));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(new BaseColor(204, 102, 0));
				  	 cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("POOR",style5));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(new BaseColor(255, 153, 102));
				  	 cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("OK",style5));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	 cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("STRONG",style5));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(new BaseColor(51, 153, 255));
				  	 cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("VERY STRONG",style5));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(new BaseColor(0, 153, 0));
				  	 cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	List<PartyPositionVO> previousElectionList = previousTrends.get(0).getPartyPositionVOList();
				  	List<PartyPositionVO> presentElectionList  = previousTrends.get(1).getPartyPositionVOList();
				  	Collections.reverse(previousElectionList);
				  	Collections.reverse(presentElectionList);
				  	int i = 0;
				  	  for (PartyPositionVO partyPositionVO : previousElectionList)
				  	  {
				  		  cell = new PdfPCell(new Phrase(partyPositionVO.getName(),style5));
				  		 cell.setPadding(padding);
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  if(i == 0)
					  	  {
					  		 cell.setBackgroundColor(BaseColor.RED);
					  	  }  
					  	  else if(i == 1)
					  	  {
					  		 cell.setBackgroundColor(new BaseColor(204, 102, 0));
					  	  }
					  	  else if(i == 2)
					  	  {
					  		 cell.setBackgroundColor(new BaseColor(255, 153, 102));
					  	  }
					  	  else if(i == 3)
					  	  {
					  		 cell.setBackgroundColor(BaseColor.YELLOW); 
					  	  }
					  	  else if(i == 4)
					  	  {
					  		 cell.setBackgroundColor(new BaseColor(51, 153, 255)); 
					  	  }
					  	  else if(i == 5)
					  	  {
					  		 cell.setBackgroundColor(new BaseColor(0, 153, 0));
					  	  }
					  	  
					  	  table.addCell(cell);
					  	  
					  	 
					  	  
					  	  
				  		  for (PartyPositionVO partyPositionVO1 : presentElectionList)
				  		  {
					  			StringBuffer sb = new StringBuffer("");
					  			if(partyPositionVO.getPartyPositionVOList() != null && partyPositionVO.getPartyPositionVOList().size() > 0)
					  			{
					  				for(PartyPositionVO namesPositionVO : partyPositionVO.getPartyPositionVOList())
								  	  {
							  			
									  		  for(PartyPositionVO namesPositionVO1 : partyPositionVO1.getPartyPositionVOList())
										  	  {
									  			  if(namesPositionVO.getId().equals(namesPositionVO1.getId()))
									  				  sb.append(namesPositionVO.getName()+ "\n\n");
										  	  }
		
								  	  }
					  			}
				  			  cell = new PdfPCell(new Phrase(sb.toString(),style5));
						  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						  	  table.addCell(cell);
				  		  }
				  		  i++;
				  	  }
				  	table.setHeaderRows(2);
				  	document.add(table);
				  	document.newPage();
				  	
			} catch (Exception e) {
				LOG.debug("Exception raised in generatePdfForMatrixReport() method in VoterModifiationPdfsGenerations Class",e);
			}
		  }
		  
		  public void orderOFPriorityTable(Document document,List<OrderOfPriorityVO> list,int count)
		  {
			  try {
				  LOG.info("Enterd into orderOFPriorityTable() method ");
				  
				      PdfPTable table = new PdfPTable(6);
				      table.setWidthPercentage(100);
				      Paragraph preface = new Paragraph();
				      preface.setAlignment(Element.PTABLE);
				      int padding = 6;
				      document.newPage();
				      Font topHeading = new Font(Font.FontFamily.TIMES_ROMAN, 20,Font.BOLD);
					    topHeading.setColor( new BaseColor(111,165,235));//69,109,142,
						Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
						subHeading.setColor(new BaseColor(69,109,142));
						Font calibriBold = FontFactory.getFont("Calibri",9,Font.BOLD);
				      preface.add( new Paragraph("Panchayats – Top Priority & Opportunity",subHeading));
				      preface.add( new Paragraph(" ") );
				      document.add(preface);
				      table.setHorizontalAlignment(Element.ALIGN_LEFT);
				      PdfPCell cell;
				      
				      cell = new PdfPCell(new Phrase("Panchayath",style1));
				      cell.setRowspan(2);
				  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("Total Voters",style1));
				  	  cell.setRowspan(2);
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("Targeted 2014 vs 2009",style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setColspan(2);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("Difference",style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setColspan(2);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	
				  	  cell = new PdfPCell(new Phrase("Targeted Votes",style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("2009 TDP Votes",style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("Votes",style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("%",style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  //int size = 0;
				  	  for(OrderOfPriorityVO orderOfPriorityVO : list)
				  	  {
				  		  /*if(size == count)
				  		  {
				  			  break;
				  		  }*/
				  		  if(orderOfPriorityVO.getType().equalsIgnoreCase("Highly Critical") || orderOfPriorityVO.getType().equalsIgnoreCase("Critical"))
				  		  {
				  			  cell = new PdfPCell(new Phrase(orderOfPriorityVO.getName(),style2));
						  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						  	cell.setPadding(padding);
						  	  table.addCell(cell);
						  	  if(orderOfPriorityVO.getTotalVoters() != null)
						  	   cell = new PdfPCell(new Phrase(orderOfPriorityVO.getTotalVoters().toString(),style2));
						  	  else
						  		cell = new PdfPCell(new Phrase("",style2));  
						  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						  	cell.setPadding(padding);
						  	  table.addCell(cell);
						  	  
						  	  if(orderOfPriorityVO.getTargetedVoters() != null)
						  	  cell = new PdfPCell(new Phrase(orderOfPriorityVO.getTargetedVoters().toString(),style2));
						  	  else
						  	  cell = new PdfPCell(new Phrase("",style2)); 
						  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						  	cell.setPadding(padding);
						  	  table.addCell(cell);
						  	  
						  	  if(orderOfPriorityVO.getPreviousVoters() != null)
						  	  cell = new PdfPCell(new Phrase(orderOfPriorityVO.getPreviousVoters().toString(),style2));
						  	  else
						  	  cell = new PdfPCell(new Phrase("",style2));	
						  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						  	cell.setPadding(padding);
						  	  table.addCell(cell);
						  	  
						  	  cell = new PdfPCell(new Phrase(orderOfPriorityVO.getOpportunity().toString(),style2));
						  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						  	cell.setPadding(padding);
						  	  table.addCell(cell);
						  	  
						  	  cell = new PdfPCell(new Phrase(orderOfPriorityVO.getOpportunityPerc().toString(),style2));
						  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						  	cell.setPadding(padding);
						  	  table.addCell(cell);
						  	  //size++;
				  		  }
				  	  }
				  	  table.setHeaderRows(2);
				  	
				  	  document.add(table);
				  	document.newPage();
			} catch (Exception e) {
				LOG.debug("Exception raised in orderOFPriorityTable() method",e);
			}
		  }
		  
       
		  
		  public void buildPanchayatsClassificationBlock(Document document, List<OrderOfPriorityVO>  panchayatsClassific)
		  {
			  try {
				  	
				  LOG.info("Enterd into buildPanchayatsClassificationBlock() method in StrategyModelTargetingService Class");
				    PdfPTable table = new PdfPTable(4);
				    table.setWidthPercentage(100);
				    Paragraph preface = new Paragraph();
				    preface.setAlignment(Element.PTABLE);
				    Font topHeading = new Font(Font.FontFamily.TIMES_ROMAN, 20,Font.BOLD);
				    topHeading.setColor( new BaseColor(111,165,235));//69,109,142,
					Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
					subHeading.setColor(new BaseColor(69,109,142));
					Font calibriBold = FontFactory.getFont("Calibri",9,Font.BOLD);
					preface.add( new Paragraph("Step 4 – Start Here",topHeading));
					preface.add( new Paragraph(" ") );
					preface.add( new Paragraph(" ") );
				    document.add(preface);
				    //table.setWidthPercentage(100);
				    table.setHorizontalAlignment(Element.ALIGN_LEFT);
			        PdfPCell cell ;
				  	 int padding = 6;
			        cell = new PdfPCell(new Phrase("Status",style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	  cell.setPadding(padding);
				  	  table.addCell(cell);
					  
				  	  
				  	  cell = new PdfPCell(new Phrase("No of Panchayats",style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("Gainable Votes%",style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase("Gainable Votes",style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  cell.setBackgroundColor(BaseColor.YELLOW);
				  	cell.setPadding(padding);
				  	  table.addCell(cell);
				  	  
				  	  

				  	  for (OrderOfPriorityVO classificationVo : panchayatsClassific)
				  	  {
				  		 
				  		  cell = new PdfPCell(new Phrase(classificationVo.getName(),style2));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	  cell = new PdfPCell(new Phrase(classificationVo.getTotalVoters().toString(),style2));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	  if(classificationVo.getOpportunity() > 0)
					  	   cell = new PdfPCell(new Phrase(new BigDecimal(classificationVo.getOpportunityPerc()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),style2));
					  	  else
					  		cell = new PdfPCell(new Phrase("N/A",style2));  
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	 if(classificationVo.getOpportunity() > 0)
					  	  cell = new PdfPCell(new Phrase(classificationVo.getOpportunity().toString(),style2));
					  	 else
					  	  cell = new PdfPCell(new Phrase("",style2));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	cell.setPadding(padding);
					  	  table.addCell(cell);
					  	  
					  	  
					  	
					}
					document.add(table);
					document.newPage();
			} catch (Exception e) {
				LOG.debug("Exception raised in buildPanchayatsClassificationBlock() ",e);
			}
		  }
		  
		  public void buildChart(Document doc)
          {
                  try {
                       DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
                       dataSet.setValue(791, "Margin", "Panchayat_Name-2013");
                       dataSet.setValue(978, "Margin", "Panchayat_Name-2009");
                       dataSet.setValue(1262, "Population", "1850 AD");
                       dataSet.setValue(1650, "Population", "1900 AD");
                       dataSet.setValue(2519, "Population", "1950 AD");
                       dataSet.setValue(6070, "Population", "2000 AD");
               
                       JFreeChart chart = ChartFactory.createBarChart(
                               "World Population growth", "Year", "Population in millions",
                               dataSet, PlotOrientation.VERTICAL, false, true, false);
                       
                          Document document = new Document();
                          PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\PartyAnalyst\\VMR\\2.pdf"));
                          document.open();
                          PdfContentByte cb = writer.getDirectContent();
                          PdfTemplate bar = cb.createTemplate(400, 500);
                          Graphics2D g2d2 = bar.createGraphics(350,450,new DefaultFontMapper());
                          Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, 300,400);
                                 
                       chart.draw(g2d2, rectangle2d);
                       g2d2.dispose();
                       cb.addTemplate(bar,0.0f,0.0f);
                       document.close();
               } catch (Exception e) {
                       e.printStackTrace();
               }
          }

 
		   public void buildChartForPartyPerformanceReort(Document document,List<PartyPositionVO> list,PdfWriter writer,String heading)
	          {
	                  try {
	                	  
	                	  int size = list.size();
	                	  int increment = 15;
	                	  int maxindex =0;
	                	  //Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
	                	  //Paragraph p =   new Paragraph(heading ,SMALLFONT);
	              		//p.setFont(subHeading);
	              	   
	                     for(int i=0;i<size;i=i+increment)
	                	  {
	                        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
	                        if(list != null && list.size() > 0)
	                       {
		                        	Long total = 0l;
		                    	   for(PartyPositionVO vo : list.subList(maxindex, list.size() - 0))
		                    	   {
		                    		   if(total == 15)
		                    			break;
		                    		if(vo.getRank() == 1) 
		                    		{
		                    			 dataSet.setValue(vo.getMargin(), "Margin", vo.getName().toString()+" -2009");	
			                    			if(vo.getWinPartyName().equalsIgnoreCase("TDP"))
			                    			dataSet.setValue(vo.getMargin(), "Margin", vo.getName().toString()+" -2013");
			                    			else
			                                dataSet.setValue(vo.getMargin()/2, "Margin", vo.getName().toString()+" -2013");
			                    		   
			                    	}
		                    		else
		                    		{
		                    			  dataSet.setValue(vo.getMargin(), "Margin", vo.getName().toString()+" -2009");	
			                    			if(vo.getWinPartyName().equalsIgnoreCase("TDP"))
			                    		      dataSet.setValue(2 * (vo.getMargin()), "Margin", vo.getName().toString()+" -2013");
			                    			else
			                    			  dataSet.setValue(vo.getMargin(), "Margin", vo.getName().toString()+" -2013");
			                    			
		                    		}
		                    		total ++;
		                    		}
		                    	   if(size >= increment)
		                    		   maxindex = maxindex + increment;
	                              }
	                       
	                       JFreeChart chart = ChartFactory.createBarChart(
	                               "", "Panchayat ", "Margin",
	                               dataSet, PlotOrientation.HORIZONTAL, false, true, false);
	                       chart.setTitle(
	                    		   new org.jfree.chart.title.TextTitle(""+heading+"",
	                    		       new java.awt.Font("SansSerif", java.awt.Font.BOLD, 12)
	                    		   ));
	                    
	                       final CategoryPlot plot = chart.getCategoryPlot();
	                       final CategoryItemRenderer renderer = new CustomRenderer(
	                               new Paint[] {Color.green,
	                            		   Color.BLUE}
	                           );
	                       	
	                          renderer.setItemLabelsVisible(true);
	                         
	                          final ItemLabelPosition p1 = new ItemLabelPosition(
	                                  ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 10.0
	                              );
	                          renderer.setPositiveItemLabelPosition(p1);
	                          plot.setRenderer(renderer);
	                         // Document document = new Document();
	                         // PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 6.0\\webapps\\PartyAnalyst\\VMR\\"+pageNo+".pdf"));
	                         // document.open();
	                          
	                          PdfContentByte cb = writer.getDirectContent();
	                          PdfTemplate bar = cb.createTemplate(800, 800);
	                          Graphics2D g2d2 = bar.createGraphics(750,750,new DefaultFontMapper());
	                          Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, 600,750);
		                       chart.draw(g2d2, rectangle2d);
		                       g2d2.dispose();
		                       cb.addTemplate(bar,0.0f,0.0f);
	                      // document.close();
	                      // pageNo++;
	                       document.newPage();
	                	  }
	               } catch (Exception e) {
	                       e.printStackTrace();
	               }
	          }		
		   public void buildPiChart(Document document,List<OrderOfPriorityVO> panchayatsClassification,PdfWriter writer)
		  {
		  try{
			  DefaultPieDataset  dataSet = new DefaultPieDataset ();
			  for(OrderOfPriorityVO order:panchayatsClassification){
				  dataSet.setValue(order.getName(),order.getTotalVoters());
			  }
			   
			  JFreeChart chart = ChartFactory.createPieChart("Strategy Suggestive Model", dataSet, true, true, false);
			  chart.setBackgroundPaint(Color.white);
			  PiePlot plot = (PiePlot)chart.getPlot();
			  for(int i=0;i<panchayatsClassification.size();i++){
				   switch(i){
				    case 0:plot.setSectionPaint("Highly Critical Panchayats",new Color(255, 0, 0));
				           plot.setExplodePercent("Highly Critical Panchayats", 0.08);
				    	   break;
				    case 1: plot.setSectionPaint("Critical Panchayats",new Color(204, 102, 0));
				    	   break;
				    case 2:plot.setSectionPaint("Medium Panchayats",new Color(255, 153, 102));
				    	   break;
				    case 3:plot.setSectionPaint("Easy Panchayats", new Color(51, 153, 255));
				    	   break;
				    case 4:plot.setSectionPaint("Good Panchayats", new Color(0, 153, 0));
				    	   break;

	             }
			  }
			
		      //plot.setSimpleLabels(true);
		      
		      PdfContentByte cb = writer.getDirectContent();
			  PdfTemplate bar = cb.createTemplate(600, 800);
			  Graphics2D g2d2 = bar.createGraphics(600,800);
			  Rectangle2D rectangle2d = new Rectangle2D.Double(30,200, 530,390);
			  
			  chart.draw(g2d2, rectangle2d);
			  g2d2.dispose();
			  cb.addTemplate(bar,0.0f,0.0f);
		   }catch(Exception e){
			  LOG.debug("Exception raised in buildPanchayatsClassificationBlock() ",e);
			}
		  }

		  public List<PartyPositionVO> getRangeList(StrategyVO strategyVO){
			  List<PartyPositionVO> rangeList = new ArrayList<PartyPositionVO>();
			  PartyPositionVO range = new PartyPositionVO();
				range.setName("VERY STRONG");
				range.setMinValue(strategyVO.getVeryStrongMin());
				range.setMaxValue(strategyVO.getVeryStrongMax());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("STRONG");
				range.setMinValue(strategyVO.getStrongMin());
				range.setMaxValue(strategyVO.getStrongMax());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("OK");
				range.setMinValue(strategyVO.getOkMin());
				range.setMaxValue(strategyVO.getOkMax());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("POOR");
				range.setMinValue(strategyVO.getPoorMin());
				range.setMaxValue(strategyVO.getPoorMax());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("VERY POOR");
				range.setMinValue(strategyVO.getVeryPoorMin());
				range.setMaxValue(strategyVO.getVeryPoorMax());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("WORST");
				range.setMinValue(strategyVO.getWorstMin());
				range.setMaxValue(strategyVO.getWorstMax());
				rangeList.add(range);
			  return rangeList;
		  }
		  
		public void getTopPanchayats(StrategyVO strategyVO,Document document,PdfWriter writer){
			try {
				 List<Object> targetingAreas = getPrioritiesToTarget(strategyVO,false);
				 Map<String,Float> casteNamePercMap =  (Map<String,Float>)targetingAreas.get(0);//1
					List<PanchayatVO> totalCastesList = (List<PanchayatVO>)targetingAreas.get(1);//2
					List<PartyPositionVO> partyPerformance = (List<PartyPositionVO>)targetingAreas.get(2);//3
					List<PartyPositionVO> previousTrends = (List<PartyPositionVO>)targetingAreas.get(3);//4
					List<PanchayatVO> youngCastesList = (List<PanchayatVO>)targetingAreas.get(4);//5
					List<PanchayatVO> agedCastesList = (List<PanchayatVO>)targetingAreas.get(5);//6
					List<PartyEffectVO> otherPartyEffect = (List<PartyEffectVO>)targetingAreas.get(6);//7
					List<OrderOfPriorityVO> panchayatsClassification = (List<OrderOfPriorityVO>)targetingAreas.get(7);//8
					//List<ImpFamilesVO> impfamilesList = (List<ImpFamilesVO>)targetingAreas.get(8);//9
					List<OrderOfPriorityVO> finalOrderOfOriority = (List<OrderOfPriorityVO>)targetingAreas.get(9);//10
					Paragraph preface = new Paragraph();
				    preface.setAlignment(Element.PTABLE);
				    preface.add( new Paragraph(" ") );
				    preface.setAlignment(Element.ALIGN_CENTER);
				    preface.add( new Paragraph(delimitationConstituencyDAO.getConstituencyNo(strategyVO.getConstituencyId(), 2009l)+": "+constituencyDAO.get(strategyVO.getConstituencyId()).getName() + "  CRITICAL PANCHAYAT - TOP FAMILYS DETAILS" , style1));
				    preface.add( new Paragraph(" ") );
				    preface.add( new Paragraph(" ") );
				    document.add(preface);
					buildPiChart(document,panchayatsClassification,writer);//8
					buildPanchayatsClassificationBlock(document,panchayatsClassification);//8
					orderOFPriorityTable(document,finalOrderOfOriority,15);
					for(OrderOfPriorityVO VO : finalOrderOfOriority)
					{
						if(VO.getType() != null)
						{
							if( VO.getType().equalsIgnoreCase("Highly Critical") || VO.getType().equalsIgnoreCase("Critical"))
							{
								List<ImpFamilesVO> impfamilesList = new ArrayList<ImpFamilesVO>();
								if(VO.getName().contains("MUNCIPALITY") || VO.getName().contains("CORPORATION") || VO.getName().contains("Greater Municipal Corp"))
								{
									getImpFamilesList(VO.getPanchayatId(),strategyVO.getPublicationId(),impfamilesList,"mincipality",null);
								}
								else
								{
									getImpFamilesList(VO.getPanchayatId(),strategyVO.getPublicationId(),impfamilesList,"panchayat",null);
								}
								buildPdfForHouseHolds(strategyVO,document,writer,VO.getName(),VO.getPanchayatId()); 
								generateImpFamilesTable(document,impfamilesList,"IMP");
								document.newPage();
							}
						}
						
					}
					
			} catch (Exception e) {
				LOG.error("Exception occured in getTopPanchayats",e);
			}
			
				
		}
		public void buildPdfForHouseHolds(StrategyVO strategyVO,Document document,PdfWriter writer,String name,Long id) 
		{
		try{
			List<Object[]> houseHoldsDetails = null;
			List<HouseHoldsVO> houseHoldsVOList = null;
			HouseHoldsVO houseHoldsVO = null;
			if(name.contains("MUNCIPALITY") || name.contains("CORPORATION") || name.contains("Greater Municipal Corp")){
				houseHoldsDetails = voterFamilyInfoDAO.getTotalFamilies(strategyVO.getConstituencyId(), strategyVO.getPublicationId(), id, 5l);
				Paragraph preface = new Paragraph();
			    preface.setAlignment(Element.PTABLE);
			    preface.add( new Paragraph(" ") );
			    preface.add( new Paragraph(" ") );
			    preface.add( new Paragraph(name));
			    preface.add( new Paragraph(" ") );
			    document.add(preface);
			}else{
				houseHoldsDetails = voterFamilyInfoDAO.getTotalFamilies(strategyVO.getConstituencyId(), strategyVO.getPublicationId(), id, 3l);
				Paragraph preface = new Paragraph();
			    preface.setAlignment(Element.PTABLE);
			    preface.add( new Paragraph(" ") );
			    preface.add( new Paragraph(" ") );
			    preface.add( new Paragraph(name + " PANCHAYAT") );
			    preface.add( new Paragraph(" ") );
			    document.add(preface);
			}
			if(houseHoldsDetails != null && houseHoldsDetails.size()>0){
				houseHoldsVOList = new ArrayList<HouseHoldsVO>();
				houseHoldsVO = new HouseHoldsVO();
				for (Object[] houseHolds : houseHoldsDetails) {
					HouseHoldsVO ohuseholds = new HouseHoldsVO();
					
					ohuseholds.setFamiliRange(houseHolds[1] != null ? houseHolds[1].toString():"");
					ohuseholds.setFamilyCount(houseHolds[2] != null ? houseHolds[2].toString():"");
					ohuseholds.setFamilyPercentage(houseHolds[3] != null ? houseHolds[3].toString():"");
					houseHoldsVOList.add(ohuseholds);
				}
				houseHoldsVO.setHouseHoldsVOList(houseHoldsVOList);
				
			}
			 
			 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);

			  
			    
			
			PdfPCell c1;
			
			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100);
			int padding = 6;
				
			     c1 = new PdfPCell(new Phrase("Range",BIGFONT));
			 	 c1.setBackgroundColor(BaseColor.YELLOW);
				 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				 c1.setPadding(padding);
				 table.addCell(c1);

			  	for (HouseHoldsVO prev : houseHoldsVO.getHouseHoldsVOList()) {
			  	String nameType = "";
			  	 if(prev.getFamiliRange().equalsIgnoreCase("0-3"))
			  	 {
			  		nameType = "Voters Below 3";
			  	 }
			  	 else if(prev.getFamiliRange().equalsIgnoreCase("4-6"))
			  	 {
			  		nameType = "Voters Between 4-6";
			  	 }
			  	 else if(prev.getFamiliRange().equalsIgnoreCase("7-10"))
			  	 {
			  		nameType = "Voters Between 7-10"; 
			  	 }
			  	 else
			  	 {
			  		nameType = "Voters Above 10"; 
			  	 }
				 c1 = new PdfPCell(new Phrase(nameType,BIGFONT));
			 	 c1.setBackgroundColor(BaseColor.YELLOW);
				 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				 c1.setPadding(padding);
				 table.addCell(c1);
				
			  	}
			  	 c1 = new PdfPCell(new Phrase("No Of Families",BIGFONT));
			 	 c1.setBackgroundColor(BaseColor.YELLOW);
				 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				 c1.setPadding(padding);
				 table.addCell(c1);
		          
				for (HouseHoldsVO prev :  houseHoldsVO.getHouseHoldsVOList()) {

					 c1 = new PdfPCell(new Phrase(prev.getFamilyCount(),BIGFONT));
					 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					 c1.setPadding(padding);
					 table.addCell(c1);
			  	}
				
				 c1 = new PdfPCell(new Phrase(" Families %",BIGFONT));
			 	 c1.setBackgroundColor(BaseColor.YELLOW);
				 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				 c1.setPadding(padding);
				 table.addCell(c1);
				
		      for (HouseHoldsVO prev :  houseHoldsVO.getHouseHoldsVOList()) {

		    		 c1 = new PdfPCell(new Phrase(prev.getFamilyPercentage(),BIGFONT));
				 
					 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					 c1.setPadding(padding);
					 table.addCell(c1);
			  	}
			  	document.add(table);
		 }catch(Exception e){
			LOG.error("Exception",e);	
			}
		
		}
	public List<Object> getCriticalPanchayats(Long constituencyId){
		 StrategyVO strategyVO = new StrategyVO();
		Long publicationId =  voterInfoDAO.getLatestPublicationDate(constituencyId);
		  strategyVO.setConstituencyId(constituencyId);
			strategyVO.setPartyId(872l);
			List<Long> electionIds = new ArrayList<Long>();
			electionIds.add(38l);
			electionIds.add(3l);
			strategyVO.setPublicationId(publicationId);
			strategyVO.setAutoCalculate(true);
			strategyVO.setElectionIds(electionIds);
			Double regainVotrsWeigthPerc = null;
			try{
			 regainVotrsWeigthPerc = prpWeightegesDAO.getPRPWeightageByConstiId(strategyVO.getConstituencyId());
			}catch(Exception e){
				
			}
			 if(regainVotrsWeigthPerc == null || regainVotrsWeigthPerc == 0d)
				  regainVotrsWeigthPerc = 0d;
			  Double prevTrendWeigthPerc = 90d-regainVotrsWeigthPerc;
			  
			strategyVO.setCastePercents(null);
			strategyVO.setPrevTrnzWt(prevTrendWeigthPerc);
			strategyVO.setYoungWt(10d);
			strategyVO.setPrpWt(regainVotrsWeigthPerc);
			strategyVO.setAgedWt(0d);
			strategyVO.setTotalCastWt(0d);
			strategyVO.setAutoCalculate(true);
			strategyVO.setEffectPartyId(662l);
			strategyVO.setEffectElectionId(38l);
		List<Object> criticalPanchayats = new ArrayList<Object>();
		List<Object> priorityList = getPrioritiesToTarget(strategyVO,false);
		criticalPanchayats.add(priorityList.get(7));
		criticalPanchayats.add(priorityList.get(9));
		return criticalPanchayats;
	}
		public EffectedBoothsResponse getPanchayatCategoriesForInfectedBooths(Long constituencyId){
			StrategyVO strategyVO=new StrategyVO();
			Map<Long,PartyEffectVO> partyEffect = new HashMap<Long,PartyEffectVO>();
			Map<Long,Double> currentResult = new HashMap<Long,Double>();
			
			strategyVO.setConstituencyId(constituencyId);
			strategyVO.setPartyId(872l); //TDP PARTY ID
			List<Long> electionIds=new ArrayList<Long>();
			electionIds.add(38l);//2009 ELECTION ID
			electionIds.add(3l);//2004 ELECITON ID
			
			strategyVO.setElectionIds(electionIds);
			strategyVO.setEffectPartyId(662l);//PRP PARTY ID
			strategyVO.setEffectElectionId(38l);//2009 ELECTION ID
			
			strategyVO.setAutoCalculate(true);
			
			
			List<PartyPositionVO>  previousTrends = getPartyPreviousTrends(strategyVO,strategyVO.getConstituencyId(),strategyVO.getPartyId(),strategyVO.getElectionIds(),partyEffect,strategyVO.getEffectPartyId(),strategyVO.getEffectElectionId(),currentResult);
			
			List<PartyPositionVO> voList=previousTrends.get(0).getPartyPositionVOList();
			
			
			List<InfectedBoothsVO> levelVOs=new ArrayList<InfectedBoothsVO>();
			InfectedBoothsVO infvo=null;
			
			infvo=new InfectedBoothsVO();
			infvo.setName("VERY STRONG");
			levelVOs.add(infvo);
			
			infvo=new InfectedBoothsVO();
			infvo.setName("STRONG");
			levelVOs.add(infvo);
			
			infvo=new InfectedBoothsVO();
			infvo.setName("OK");
			levelVOs.add(infvo);
			
			for(PartyPositionVO pvo:voList){
				InfectedBoothsVO infectedVO=getMatchedInfectedLevel(levelVOs,pvo.getName());
				if(infectedVO!=null){
					List<PartyPositionVO> pvoList=pvo.getPartyPositionVOList();
					if(pvoList.size()>0){
						for(PartyPositionVO temp:pvoList){
							InfectedBoothsVO infLocal=new InfectedBoothsVO();
							infLocal.setPanchayatId(temp.getId());
							infLocal.setName(temp.getName());
							
							List<Long> inTtlPanchList=infectedVO.getTotalPanchaytIds();
							if(inTtlPanchList==null){
								inTtlPanchList=new ArrayList<Long>();
								infectedVO.setTotalPanchaytIds(inTtlPanchList);
							}
							inTtlPanchList.add(temp.getId());
							
							List<InfectedBoothsVO> infectedPanchayats=infectedVO.getPanchayatsList();
							if(infectedPanchayats==null){
								infectedPanchayats=new ArrayList<InfectedBoothsVO>();
								infectedVO.setPanchayatsList(infectedPanchayats);
							}
							infectedPanchayats.add(infLocal);
							
						}
					}
				}
				
				
			}
			
			InfectedBoothsVO mainVO=new InfectedBoothsVO();
			EffectedBoothsVo inputs = new EffectedBoothsVo();
			
			List<Long> overAllPIds=new ArrayList<Long>();
			for(InfectedBoothsVO invo:levelVOs){
				overAllPIds.addAll(invo.getTotalPanchaytIds());
			}
			mainVO.setLevelVOs(levelVOs);
			mainVO.setOverAllPanchayatIds(overAllPIds);
			inputs.setPanchayatIds(overAllPIds);
			
			List<Object[]> previousElectionWinnersList=candidateResultDAO.getPreviousElectionWinningPartyByConstituency(constituencyId);
			if(previousElectionWinnersList.size()>0){
				mainVO.setPartyId(Long.valueOf(previousElectionWinnersList.get(0)[0].toString()));
				mainVO.setParty(previousElectionWinnersList.get(0)[1].toString());
				mainVO.setYear(previousElectionWinnersList.get(0)[3].toString());
				inputs.setWonPartyId(Long.valueOf(previousElectionWinnersList.get(0)[0].toString()));
			}
			
			
			inputs.setConstId(constituencyId);
			
		/*	
			EffectedBoothsVo st = new EffectedBoothsVo();
			st.setConstId(232L);*/
	         ClientConfig clientConfig = new DefaultClientConfig();

	         clientConfig.getFeatures().put(
	                  JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
		 
	        WebResource webResource = client
	             .resource("http://192.168.3.73:8080/Survey/WebService/getEffectedPanchayats");

	          ClientResponse response = webResource.accept("application/json")
	                  .type("application/json").post(ClientResponse.class, inputs);
	          if (response.getStatus() != 200) {
	              throw new RuntimeException("Failed : HTTP error code : "
	                      + response.getStatus());
	         }
		 
	          EffectedBoothsResponse output = response.getEntity(EffectedBoothsResponse.class);

	          System.out.println("Server response .... \n");
			
			List<PanchayatCountVo> list = new ArrayList<PanchayatCountVo>();
			/*PanchayatCountVo pvo = null ;
			pvo = new PanchayatCountVo();
			pvo.setPanchayatId(101l);
			pvo.setPanchayatName("ABPalem");
			List<Long> boothNos = new ArrayList<Long>();
			boothNos.add(61l);
			boothNos.add(62l);
			pvo.setBooths(boothNos);
			StringBuilder boothParts=new StringBuilder();
			for(Long booth:boothNos){
				boothParts.append(booth);
				boothParts.append(",");
			}
			
			pvo.setBoothsList(boothParts.deleteCharAt(boothParts.length()-1).toString());
			pvo.setEffectedCount(2);
			pvo.setEffected(true);
			list.add(pvo);
			
			pvo = new PanchayatCountVo();
			pvo.setPanchayatId(101l);
			pvo.setPanchayatName("S.Kota");
			List<Long> boothNos1 = new ArrayList<Long>();
			boothNos1.add(121l);
			boothNos1.add(122l);
			pvo.setBooths(boothNos1);
			StringBuilder boothParts1=new StringBuilder();
			for(Long booth:boothNos1){
				boothParts1.append(booth);
				boothParts1.append(",");
			}
			pvo.setBoothsList(boothParts1.deleteCharAt(boothParts1.length()-1).toString());
			pvo.setEffected(true);
			pvo.setEffectedCount(1);
			list.add(pvo);
			
			
			EffectedBoothsResponse effectedResponse = new EffectedBoothsResponse();
			effectedResponse.setPanchayats(list);
			*/
			for (PanchayatCountVo panchayatCountVo : output.getPanchayats()) {
				panchayatCountVo.setQuestionsMap(null);
				
			}
			return output;
			
		}
		
		public InfectedBoothsVO getMatchedInfectedLevel(List<InfectedBoothsVO> invos,String name){
			for(InfectedBoothsVO invo:invos){
				if(invo.getName().equalsIgnoreCase(name)){
					return invo;
				}
			}
			return null;
		}
		
}

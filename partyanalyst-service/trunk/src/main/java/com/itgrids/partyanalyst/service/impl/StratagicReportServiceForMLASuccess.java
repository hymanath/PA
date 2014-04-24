package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyCensusDetailsDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.CasteStratagicReportVO;
import com.itgrids.partyanalyst.dto.HouseHoldsVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportHelperVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;
import com.itgrids.partyanalyst.dto.StrategicCensusVO;
import com.itgrids.partyanalyst.dto.VoterStratagicReportVo;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.service.IStratagicReportServiceForMLASuccess;
import com.itgrids.partyanalyst.utils.IConstants;

public class StratagicReportServiceForMLASuccess implements IStratagicReportServiceForMLASuccess{
	public static Logger LOG = Logger.getLogger(StratagicReportServiceForMLASuccess.class);
	
	@Autowired public IVoterCastInfoDAO voterCastInfoDAO;
	
	@Autowired public IVoterAgeInfoDAO voterAgeInfoDAO;
	   
	@Autowired public IVoterInfoDAO voterInfoDAO;
	
	@Autowired public IVoterFamilyInfoDAO voterFamilyInfoDAO;
	
	@Autowired
	public IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO;
	
	@Autowired public IConstituencyDAO constituencyDAO;
	
	@Autowired IStateDAO stateDAO;
	
	@Autowired 	public ICensusDAO censusDAO;
	
	@Autowired public IDistrictDAO districtDAO;
	
	@Autowired public IBoothDAO boothDAO;
	
	@Autowired public IPartyTrendsDAO partyTrendsDAO;
	
	public HouseHoldsVO getHouseHoldInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId){
		LOG.info(" entered into getHouseHoldInfoByConstituency() in StratagicReportServiceForMLASuccess class.");
		HouseHoldsVO houseHoldsVO = null;
		List<HouseHoldsVO> houseHoldsVOList = null;
		try {
			Long houseHoldsByNewVoterList = 0L; 
			Long houseHoldsByCensus = 0L;
			/*
			List<Long> yearIds = new ArrayList<Long>();
			yearIds.add(2011L);
						
			List<ConstituencyCensusDetails> censusDetailsList = constituencyCensusDetailsDAO.getCensusConstituencyByConstituencyIdAndYears(constituencyId,yearIds);
			
			if(censusDetailsList != null && censusDetailsList.size()>0){
				ConstituencyCensusDetails censusDetails = censusDetailsList.get(0);
				
				houseHoldsByCensus = censusDetails.getHouseHolds();
			}
			
			
			*/
			List<Long> tehsilIds = boothDAO.getTehsildByConstituency(constituencyId, publicationDateId);
			List<Long> houstholdsDetials = censusDAO.getCensusDetailsInConstituency(tehsilIds,2011L);
			if(houstholdsDetials != null && houstholdsDetials.size()>0){		
				Set<Long> houstholdsCount = new HashSet<Long>();
				houstholdsCount.addAll(houstholdsDetials);
				
				for (Long houseHolds : houstholdsCount) {
					houseHoldsByCensus = houseHoldsByCensus + houseHolds;
				}
			}
			
			List<Object[]> houseHoldsDetails = voterFamilyInfoDAO.getTotalFamiliesByCosntituency(constituencyId,publicationDateId,constituencyId);
			if(houseHoldsDetails != null && houseHoldsDetails.size()>0){
				houseHoldsVOList = new ArrayList<HouseHoldsVO>();
				houseHoldsVO = new HouseHoldsVO();
				for (Object[] houseHolds : houseHoldsDetails) {
					HouseHoldsVO ohuseholds = new HouseHoldsVO();
					
					ohuseholds.setFamiliRange(houseHolds[1] != null ? houseHolds[1].toString():"");
					ohuseholds.setFamilyCount(houseHolds[2] != null ? houseHolds[2].toString():"");
					ohuseholds.setFamilyPercentage(houseHolds[3] != null ? houseHolds[3].toString():"");
					
					houseHoldsByNewVoterList = houseHoldsByNewVoterList + Long.valueOf(houseHolds[2].toString());
					houseHoldsVOList.add(ohuseholds);
				}
				houseHoldsVO.setHouseHoldsVOList(houseHoldsVOList);
			}
			
			houseHoldsVO.setMessage(" As we understand that it is very important to know the Basic " +
					"Building Block of the entire structure and so we have put forward a measure of the " +
					"families in the constituency based on the total number of voter base available in each " +
					"of them respectively.");
			

			houseHoldsVO.setCalcMessage(" Please Note: Total Households: "+houseHoldsByCensus+" (According to the Census), "+houseHoldsByNewVoterList+" (According to the Voters List)");
			
			houseHoldsVO.setHeading(" Households ");
			
		} catch (Exception e) {
			LOG.error(" exception occured in getHouseHoldInfoByConstituency() of StratagicReportServiceForMLASuccess class. ",e);
		}
		
		return houseHoldsVO;
	}

	public VoterStratagicReportVo getVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId){
		LOG.info(" entered into getVotersInfoByConstituency() in StratagicReportServiceForMLASuccess class.");
		VoterStratagicReportVo voterStratagicReportVo = null;
		List<VoterStratagicReportVo> ageWiseReportVOList = null;
		try {
			Long reportLelelValue = constituencyId;
				List<Object[]> voterInfoList = voterInfoDAO.getVoterDetailedCountByLocation(1L,reportLelelValue,publicationDateId,constituencyId);
			
				if(voterInfoList != null && voterInfoList.size()>0){
					ageWiseReportVOList = new ArrayList<VoterStratagicReportVo>();
					voterStratagicReportVo = new VoterStratagicReportVo();
					
					for (Object[] voterInfo : voterInfoList) {
							VoterStratagicReportVo voterInfoReportVO = new VoterStratagicReportVo();	
							
							voterInfoReportVO.setTotalVoters(voterInfo[0] != null ?(Long)voterInfo[0]:0L);
							voterInfoReportVO.setMaleVotersCount(voterInfo[1] != null ?(Long)voterInfo[1]:0L);
							voterInfoReportVO.setFemaleVotersCount(voterInfo[2] != null ?(Long)voterInfo[2]:0L);
							voterInfoReportVO.setMaleTotalPercentage(voterInfo[3] != null ?(Double)voterInfo[3]:0.0D);
							voterInfoReportVO.setFemaleTotalPercentage(voterInfo[4] != null ?(Double)voterInfo[4]:0.0D);
							ageWiseReportVOList.add(voterInfoReportVO);									
					}
				}
				
				voterStratagicReportVo.setVoterStategicReportVOList(ageWiseReportVOList);	
				voterStratagicReportVo.setMessage("  The Data furnished below is in accordance with the " +
						" latest Voter Database released by the Election Commission. With thoughts on making " +
						" the information available more easier understand and target we have categorized " +
						" Voter Base into Age Group, Caste, Urban Vs Rural %, Density across each Panchayath." +
						"  ");
				voterStratagicReportVo.setHeading(" Voters ");
		} catch (Exception e) {
			LOG.error(" exception occured in getVotersInfoByConstituency() of StratagicReportServiceForMLASuccess class. ",e);
		}
		
		return voterStratagicReportVo;
	}

	public VoterStratagicReportVo getFirstTimeVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId){
		LOG.info(" entered into getFirstTimeVotersInfoByConstituency() in StratagicReportServiceForMLASuccess class.");
		VoterStratagicReportVo voterStratagicReportVo = null;
		List<VoterStratagicReportVo> ageWiseReportVOList = null;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("##.##");
			List<Object[]> voterAgeInfoList = voterAgeInfoDAO.getVoterAgeInfoListByconstituency(constituencyId, publicationDateId);
		
			if(voterAgeInfoList != null && voterAgeInfoList.size()>0){
				ageWiseReportVOList = new ArrayList<VoterStratagicReportVo>();
				voterStratagicReportVo = new VoterStratagicReportVo();
				Long totalVotersCount = 0L;
				
				for (Object[] voterAgeCount : voterAgeInfoList) {
					if(!voterAgeCount[0].toString().equalsIgnoreCase("1"))
						totalVotersCount = totalVotersCount + Long.valueOf(voterAgeCount[2].toString());
				}
				
				for (Object[] voterAge : voterAgeInfoList) {
					if(voterAge[0].toString().equalsIgnoreCase("1")){ // only Young Voters
						VoterStratagicReportVo agewiseReportVO = new VoterStratagicReportVo();
						
						agewiseReportVO.setVoterAgeRange(voterAge[1].toString());
						Float ageWiseTotalCount = Float.valueOf(voterAge[2].toString());
						Float totalCount = Float.valueOf(voterAge[3].toString());
						Double percentage = Double.valueOf(decimalFormat.format(totalCount*100/ageWiseTotalCount));
						agewiseReportVO.setMaleVotersCount(totalCount.longValue());
						agewiseReportVO.setMaleTotalPercentage(percentage);
						
						totalCount = Float.valueOf(voterAge[4].toString());
						percentage = Double.valueOf(decimalFormat.format(totalCount*100/ageWiseTotalCount));
						agewiseReportVO.setFemaleVotersCount(totalCount.longValue());
						agewiseReportVO.setFemaleTotalPercentage(percentage);
						
						totalCount = Float.valueOf(voterAge[2].toString());
						percentage = Double.valueOf(decimalFormat.format(totalCount*100/totalVotersCount));
						agewiseReportVO.setTotalVoters(totalCount.longValue());										
						agewiseReportVO.setTotalPercentage(percentage);
						
						ageWiseReportVOList.add(agewiseReportVO);
					}					
				}
			}
			
			voterStratagicReportVo.setVoterStategicReportVOList(ageWiseReportVOList);	
			voterStratagicReportVo.setHeading(" First Time Voters ");
		} catch (Exception e) {
			LOG.error(" exception occured in getFirstTimeVotersInfoByConstituency() of StratagicReportServiceForMLASuccess class. ",e);
		}
		
		return voterStratagicReportVo;
	}

	public VoterStratagicReportVo getAgeWiseVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId){
		LOG.info(" entered into getAgeWiseVotersInfoByConstituency() in StratagicReportServiceForMLASuccess class.");
		VoterStratagicReportVo voterStratagicReportVo = null;
		List<VoterStratagicReportVo> ageWiseReportVOList = null;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("##.##");
			List<Object[]> voterAgeInfoList = voterAgeInfoDAO.getVoterAgeInfoListByconstituency(constituencyId, publicationDateId);
		
			if(voterAgeInfoList != null && voterAgeInfoList.size()>0){
				ageWiseReportVOList = new ArrayList<VoterStratagicReportVo>();
				voterStratagicReportVo = new VoterStratagicReportVo();
				Long totalVotersCount = 0L;
				
				for (Object[] voterAgeCount : voterAgeInfoList) {
					if(!voterAgeCount[0].toString().equalsIgnoreCase("1"))
					totalVotersCount = totalVotersCount + Long.valueOf(voterAgeCount[2].toString());
				}
				
				for (Object[] voterAge : voterAgeInfoList) {
					if(!voterAge[0].toString().equalsIgnoreCase("1")){// not adding young voter details
						
						VoterStratagicReportVo agewiseReportVO = new VoterStratagicReportVo();
						Float ageWiseTotalCount = Float.valueOf(voterAge[2].toString());
						agewiseReportVO.setVoterAgeRange(voterAge[1].toString());
						
						Float totalCount = Float.valueOf(voterAge[3].toString());
						Double percentage = Double.valueOf(decimalFormat.format(totalCount*100/ageWiseTotalCount));
						agewiseReportVO.setMaleVotersCount(totalCount.longValue());
						agewiseReportVO.setMaleTotalPercentage(percentage);
						
						totalCount = Float.valueOf(voterAge[4].toString());
						percentage = Double.valueOf(decimalFormat.format(totalCount*100/ageWiseTotalCount));
						agewiseReportVO.setFemaleVotersCount(totalCount.longValue());
						agewiseReportVO.setFemaleTotalPercentage(percentage);
						
						totalCount = Float.valueOf(voterAge[2].toString());
						percentage = Double.valueOf(decimalFormat.format(totalCount*100/totalVotersCount));
						agewiseReportVO.setTotalVoters(totalCount.longValue());										
						agewiseReportVO.setTotalPercentage(percentage);
						
						ageWiseReportVOList.add(agewiseReportVO);
					}					
				}
			}
			
			voterStratagicReportVo.setVoterStategicReportVOList(ageWiseReportVOList);
			voterStratagicReportVo.setHeading(" Voters by Age Group ");
		} catch (Exception e) {
			LOG.error(" exception occured in getAgeWiseVotersInfoByConstituency() of StratagicReportServiceForMLASuccess class. ",e);
		}
		
		return voterStratagicReportVo;
	}

	public CasteStratagicReportVO getCasteWiseVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId){
		LOG.info(" entered into getCasteWiseVotersInfoByConstituency() in StratagicReportServiceForMLASuccess class.");
		CasteStratagicReportVO stratagicVO = null;
		List<CasteStratagicReportVO> stratagicVOList = null;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("##.##");
			List<Object[]> votersCastInfo = voterCastInfoDAO.getVoterCasteInfoListByConstituency(constituencyId,publicationDateId,userId);
			
			if(votersCastInfo != null && votersCastInfo.size()>0){
				stratagicVOList = new ArrayList<CasteStratagicReportVO>();
				stratagicVO = new CasteStratagicReportVO();
				Long otherCastesTotalCount = 0L;
				Long otherCastesMaleCount = 0L;
				Long otherCastesFemaleCount = 0L;
				Float totalCastePerce = 0.0F; 
				for(int i = 0 ;i<votersCastInfo.size();i++ ){
					Object[] casteVoter = votersCastInfo.get(i);
					if(i <15){ // top 15 caste Information
										
						CasteStratagicReportVO casteReportVO = new CasteStratagicReportVO();
						
						casteReportVO.setCaste(casteVoter[1].toString());
						casteReportVO.setCasteCategory(casteVoter[3].toString());
						casteReportVO.setTotalCasteVoters(Long.valueOf(casteVoter[4].toString()));
						casteReportVO.setMaleCasteVoters(Long.valueOf(casteVoter[5].toString()));
						casteReportVO.setFemaleCasteVoters(Long.valueOf(casteVoter[6].toString()));
						
						String perc = decimalFormat.format(Double.valueOf(casteVoter[7].toString()));
						casteReportVO.setCastePercentage(Float.parseFloat(perc));
						
						totalCastePerce = totalCastePerce + Float.valueOf(casteVoter[7].toString());
						
						stratagicVOList.add(casteReportVO);
					}
					else{ // other castes Info
						
						otherCastesTotalCount = otherCastesTotalCount + Long.valueOf(casteVoter[4].toString());
						otherCastesMaleCount = otherCastesMaleCount + Long.valueOf(casteVoter[5].toString());
						otherCastesFemaleCount = otherCastesFemaleCount + Long.valueOf(casteVoter[6].toString());						
					}
				}
				
				if(otherCastesTotalCount != 0){
					
					CasteStratagicReportVO casteReportVO = new CasteStratagicReportVO();
					casteReportVO.setCaste("");
					casteReportVO.setCasteCategory("");
					casteReportVO.setTotalCasteVoters(otherCastesTotalCount);
					casteReportVO.setMaleCasteVoters(Long.valueOf(otherCastesMaleCount));
					casteReportVO.setFemaleCasteVoters(Long.valueOf(otherCastesFemaleCount));
					
					totalCastePerce = (100.0F - totalCastePerce);
					String perc = decimalFormat.format(Double.valueOf(totalCastePerce));
					casteReportVO.setCastePercentage(Float.parseFloat(perc));
					
					stratagicVOList.add(casteReportVO);
				}
				stratagicVO.setStrategicVOList(stratagicVOList);
				stratagicVO.setHeading(" Voters by Caste ");
			}			
			
		} catch (Exception e) {
			LOG.error(" exception occured in getCasteWiseVotersInfoByConstituency() of StratagicReportServiceForMLASuccess class. ",e);
		}
		
		return stratagicVO;
	}
	

	
	public StrategicCensusVO getCensusDetailsForAConstituency(Long constituencyId)
	{
		LOG.debug("Entered into the getCensusDetailsForAConstituency service method StratagicReportServiceForMLASuccess class.");
		StrategicCensusVO resultVO = new StrategicCensusVO();
		
		try
		{
			LinkedList<Long> years = new LinkedList<Long>();			
			years.add(2001L);
			years.add(2011L);

			
			List<Long> tehsilIds = boothDAO.getTehsildByConstituency(constituencyId,10L);

			List<StrategicCensusVO> censusList = new ArrayList<StrategicCensusVO>();
			
			for(int i = 0 ;i<years.size();i++){
			
				List<Object[]> censusDeltails = censusDAO.getCensusDetailsInConstituencyByTehsilIdsAndYears(tehsilIds,years.get(i));

				 if(censusDeltails != null && censusDeltails.size()>0){
					 
					   for (Object[] census : censusDeltails) {
						   
						   StrategicCensusVO censusDetailsVO = new StrategicCensusVO();
						   
						   censusDetailsVO.setYear(census[106] != null ?Integer.parseInt(	census[106].toString()):0);
						   censusDetailsVO.setTotalPopulation(census[1] !=null?Long.valueOf(census[1].toString()):0L);
						   censusDetailsVO.setTotalPopulationPercentage("100 % ".toString());
						   
						   censusDetailsVO.setMalePopulation(census[2] !=null?Long.valueOf(census[2].toString()):0L);
						   censusDetailsVO.setFemalePopulation(census[3] !=null?Long.valueOf(census[3].toString()):0L);
							
						   censusDetailsVO.setMalePopulationPercentage(roundTo2DigitsFloatValue((float) censusDetailsVO.getMalePopulation() *100f/censusDetailsVO.getTotalPopulation()));
						   censusDetailsVO.setFemalePopulationPercentage(roundTo2DigitsFloatValue((float)censusDetailsVO.getFemalePopulation()*100f/censusDetailsVO.getTotalPopulation()));
						   
						   censusDetailsVO.setHouseHolds(census[0] !=null? (Long)census[0]:0L);
						   censusDetailsVO.setHouseHoldsPercentage(roundTo2DigitsFloatValue((float)censusDetailsVO.getTotalPopulation()/censusDetailsVO.getHouseHolds()));
							
						   censusDetailsVO.setPopulationSC(census[7] !=null?Long.valueOf(census[7].toString()):0L);
						   censusDetailsVO.setPopulationSCPercent(BigDecimal.valueOf(Double.valueOf(roundTo2DigitsFloatValue((float)censusDetailsVO.getPopulationSC()*100f/censusDetailsVO.getTotalPopulation()))));
						   
						   censusDetailsVO.setPopulationST(census[10] !=null?Long.valueOf(census[10].toString()):0L);
						   censusDetailsVO.setPopulationSTPercent(BigDecimal.valueOf(Double.valueOf(roundTo2DigitsFloatValue((float)censusDetailsVO.getPopulationST()*100f/censusDetailsVO.getTotalPopulation()))));						
							
						   censusDetailsVO.setWorkingPeople(census[19] !=null?Long.valueOf(census[19].toString()):0L);
						   censusDetailsVO.setWorkingPeoplePercentage(roundTo2DigitsFloatValue((float)censusDetailsVO.getWorkingPeople()*100f/censusDetailsVO.getTotalPopulation()));
							
							censusDetailsVO.setWorkingMale(census[20] !=null?Long.valueOf(census[20].toString()):0L);
							censusDetailsVO.setTotalWorkingMalePercentage(Double.parseDouble(roundTo2DigitsFloatValue((float)censusDetailsVO.getWorkingMale()*100f/censusDetailsVO.getTotalPopulation()).toString()));
								
							censusDetailsVO.setWorkingFemale(census[21] !=null?Long.valueOf(census[21].toString()):0L);
							censusDetailsVO.setTotalWorkingFemalePercentage(Double.parseDouble(roundTo2DigitsFloatValue((float)censusDetailsVO.getWorkingFemale()*100f/censusDetailsVO.getTotalPopulation()).toString()));					
								
							censusDetailsVO.setNonWorkingPeople(census[52] !=null?Long.valueOf(census[52].toString()):0L);
							censusDetailsVO.setNonWorkingPeoplePercent(BigDecimal.valueOf(Double.valueOf(roundTo2DigitsFloatValue((float)censusDetailsVO.getNonWorkingPeople()*100f/censusDetailsVO.getTotalPopulation()))));
								
							censusDetailsVO.setPopulationUnderSix(census[4] !=null?Long.valueOf(census[4].toString()):0L);
							censusDetailsVO.setPopulationUnderSixPercentage(roundTo2DigitsFloatValue((float)censusDetailsVO.getPopulationUnderSix()*100f/censusDetailsVO.getTotalPopulation()));
								
							censusDetailsVO.setLiterates(census[13] !=null?Long.valueOf(census[13].toString()):0L);
							censusDetailsVO.setLiteratesPercentage(roundTo2DigitsFloatValue((float)censusDetailsVO.getLiterates()*100f/censusDetailsVO.getTotalPopulation()));
								
							censusDetailsVO.setMaleLiterates(census[14] !=null?Long.valueOf(census[14].toString()):0L);
							censusDetailsVO.setMaleLiteraturePercentage(Double.valueOf(roundTo2DigitsFloatValue((float)censusDetailsVO.getMaleLiterates()*100f/censusDetailsVO.getTotalPopulation()).toString()));
							
							censusDetailsVO.setFemaleLiterates(census[15] !=null?Long.valueOf(census[15].toString()):0L);
							censusDetailsVO.setFemaleLiteraturePercentage(Double.valueOf(roundTo2DigitsFloatValue((float)censusDetailsVO.getFemaleLiterates()*100f/censusDetailsVO.getTotalPopulation()).toString()));
								
							censusList.add(censusDetailsVO);
					   }
				   }
			}
			 
			resultVO.setCensusDetailsList(censusList);
			resultVO.setCount(censusList.size());
			StrategicCensusVO previousDetails = censusList.get(0);
			StrategicCensusVO currentDetails = censusList.get(1);
			
			resultVO.setDifferencePopulation(currentDetails.getTotalPopulation() - previousDetails.getTotalPopulation());
			resultVO.setDifferencePopulationPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferencePopulation()*100f/previousDetails.getTotalPopulation()));
			
			String populationStatus = null;
			if(currentDetails.getTotalPopulation() - previousDetails.getTotalPopulation() > 0){
				populationStatus = " increased ";
			}
			else if(currentDetails.getTotalPopulation() - previousDetails.getTotalPopulation() < 0){
				populationStatus = " decreased ";
			}
			resultVO.setDifferenceMalePopulation(currentDetails.getMalePopulation() - previousDetails.getMalePopulation());
			resultVO.setDifferenceMalePercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceMalePopulation()*100f/previousDetails.getMalePopulation()));
			resultVO.setDifferenceFemalePopulation(currentDetails.getFemalePopulation() - previousDetails.getFemalePopulation());
			resultVO.setDifferenceFemalePercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceFemalePopulation()*100f/previousDetails.getFemalePopulation()));
			
			resultVO.setDifferenceHouseHolds(currentDetails.getHouseHolds() - previousDetails.getHouseHolds());
			resultVO.setDifferenceHouseHoldsPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceHouseHolds()*100f/previousDetails.getHouseHolds()));
			
			resultVO.setDifferenceSC(currentDetails.getPopulationSC() - previousDetails.getPopulationSC());
			resultVO.setDifferenceSCPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceSC()*100f / previousDetails.getPopulationSC()));
			
			resultVO.setDifferenceST(currentDetails.getPopulationST() - previousDetails.getPopulationST());
			resultVO.setDifferenceSTPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceST()*100f/previousDetails.getPopulationST()));
			
			resultVO.setDifferenceWorkingPeople(currentDetails.getWorkingPeople() - previousDetails.getWorkingPeople());
			resultVO.setDifferenceWorkingPeoplePercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceWorkingPeople()*100f / previousDetails.getWorkingPeople()));
			
			String emplmentStatus = null; 
			if(currentDetails.getWorkingPeople() - previousDetails.getWorkingPeople() >0){
				emplmentStatus = " improved ";
			}
			else if(currentDetails.getWorkingPeople() - previousDetails.getWorkingPeople() >0){
				emplmentStatus = " not improved ";
			}
			resultVO.setDifferenceMaleWorkingPeople(currentDetails.getWorkingMale() - previousDetails.getWorkingMale());
			resultVO.setDifferenceMaleWorkingPeoplePercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceMaleWorkingPeople()*100f / previousDetails.getWorkingMale()));
			
			resultVO.setDifferenceFemaleWorkingPeople(currentDetails.getWorkingFemale() - previousDetails.getWorkingFemale());
			resultVO.setDifferenceFemaleWorkingPeoplePercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceFemaleWorkingPeople()*100f / previousDetails.getWorkingFemale()));
			
			resultVO.setDifferenceNonWorkingPeople(currentDetails.getNonWorkingPeople() - previousDetails.getNonWorkingPeople());
			resultVO.setDifferenceNonWorkingPeoplePercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceNonWorkingPeople()*100f/previousDetails.getNonWorkingPeople()));
			
			resultVO.setDifferenceLessthan6Population(currentDetails.getPopulationUnderSix() - previousDetails.getPopulationUnderSix());
			resultVO.setDifferenceLessthan6Percent(roundTo2DigitsFloatValue((float) resultVO.getDifferenceLessthan6Population()*100f/ previousDetails.getPopulationUnderSix()));
			
			resultVO.setDifferenceLiterates(currentDetails.getLiterates() - previousDetails.getLiterates());
			resultVO.setDifferenceLiteratesPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceLiterates() *100f/previousDetails.getLiterates()));
			
			
			String literatusStatus = null; 
			if(currentDetails.getLiterates() - previousDetails.getLiterates() >0){
				literatusStatus = " increased ";
			}
			else if(currentDetails.getWorkingPeople() - previousDetails.getWorkingPeople() >0){
				literatusStatus = " decreased ";
			}
			
			resultVO.setDifferenceMaleLiterates(currentDetails.getMaleLiterates() - previousDetails.getMaleLiterates());
			resultVO.setDifferenceMaleLiteratesPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceMaleLiterates()*100f/previousDetails.getMaleLiterates()));
			resultVO.setDifferenceFemaleLiterates(currentDetails.getFemaleLiterates() - previousDetails.getFemaleLiterates());
			resultVO.setDifferenceFemaleLiteratesPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceFemaleLiterates() *100f /previousDetails.getFemaleLiterates()));
			
			Constituency constituency = constituencyDAO.get(constituencyId);
			
			Long stateId = constituency.getState().getStateId();
			Long districtId = constituency.getDistrict().getDistrictId();

			String stateName = constituency.getState().getStateName();
			String districtName = constituency.getDistrict().getDistrictName();
			String constituencyName = constituency.getName();
			
			resultVO.setStateName(stateName != null  ?stateName:"");
			resultVO.setDistrictName(districtName != null ? districtName:"");
			resultVO.setConstituencyName(constituencyName != null ?constituencyName:"");
			
			List<Object[]> districtCensusDetails = censusDAO.getDistrictPopulationForDifferentYears(districtId,years);
			List<Object[]> stateCensusDetails = censusDAO.getStatePopulationForDifferentYears(stateId,years);
			
			StrategicCensusVO districtVO = new StrategicCensusVO();
			StrategicCensusVO stateVO = new StrategicCensusVO();
			
			
			setValuesToCensusVO(districtCensusDetails.get(0),districtCensusDetails.get(1), districtVO);
			setValuesToCensusVO(stateCensusDetails.get(0),stateCensusDetails.get(1), stateVO);
			
			
			resultVO.setDistrictDetails(districtVO);
			resultVO.setStateDetails(stateVO);
			
			resultVO.setMessage(" We all know that the Indian Census has always been misinterpreted, " +
					" mis-communicated over the period of years, rightfully we always wanted to have " +
					" appropriate knowledge to help us going along the way. We have put in our effort to " +
					" bring Most Accurate & Most Recent Census based on Population, SC, ST, Literates for the " +
					" years of 2001 and 2011 ");
			
			StringBuffer conclusion = new StringBuffer();
				conclusion.append("  Population were "+populationStatus+" in this Constituency.  ,");			
			//	conclusion.append("  ST’s were Improved where as SC’s were decreased when compare to district and State.  ,");
				conclusion.append("  Employment resources are "+emplmentStatus+"  where as decreased for Women  ,");
				conclusion.append("  Education Facilities are "+literatusStatus+".  ");
				
			resultVO.setConclusion(conclusion.toString());
			resultVO.setHeading(" Census \"A Snapshot\" ");
		}catch(Exception e)
		{
			LOG.error("Exception occured in the StratagicReportServiceForMLASuccess service method");
			e.printStackTrace();
		}
		
		return resultVO;
	}

	private void setValuesToCensusVO(Object[] previousDetails , Object[] currentDetails,StrategicCensusVO censusDetailsVO)
	{
		LOG.error("Entered into the setValuesToCensusVO method StratagicReportServiceForMLASuccess class");
		//CensusVO censusDetailsVO = new CensusVO();
		try
		{

			censusDetailsVO.setDifferencePopulation((Long)currentDetails[0] - (Long)previousDetails[0]);
			censusDetailsVO.setDifferencePopulationPercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferencePopulation()*100f/(Long)previousDetails[0]));
			
			censusDetailsVO.setDifferenceMalePopulation((Long)currentDetails[1] -(Long) previousDetails[1]);
			censusDetailsVO.setDifferenceMalePercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceMalePopulation()*100f/(Long)previousDetails[1]));
			
			censusDetailsVO.setDifferenceFemalePopulation((Long)currentDetails[2] - (Long)previousDetails[2]);
			censusDetailsVO.setDifferenceFemalePercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceFemalePopulation()*100f/(Long)previousDetails[2]));
			
			censusDetailsVO.setDifferenceHouseHolds((Long)currentDetails[3] - (Long)previousDetails[3]);
			censusDetailsVO.setDifferenceHouseHoldsPercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceHouseHolds()*100f/(Long)previousDetails[3]));
			
			censusDetailsVO.setDifferenceSC((Long)currentDetails[4] - (Long)previousDetails[4]);
			censusDetailsVO.setDifferenceSCPercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceSC()*100f / (Long)previousDetails[4]));
			
			censusDetailsVO.setDifferenceST((Long)currentDetails[5] - (Long)previousDetails[5]);
			censusDetailsVO.setDifferenceSTPercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceST()*100f/(Long)previousDetails[5]));
			
			censusDetailsVO.setDifferenceWorkingPeople((Long)currentDetails[6] - (Long)previousDetails[6]);
			censusDetailsVO.setDifferenceWorkingPeoplePercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceWorkingPeople()*100f / (Long)previousDetails[6]));
			
			censusDetailsVO.setDifferenceMaleWorkingPeople((Long)currentDetails[7] - (Long)previousDetails[7]);
			censusDetailsVO.setDifferenceMaleWorkingPeoplePercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceMaleWorkingPeople()*100f / (Long)previousDetails[7]));
			
			censusDetailsVO.setDifferenceFemaleWorkingPeople((Long)currentDetails[8] - (Long)previousDetails[8]);
			censusDetailsVO.setDifferenceFemaleWorkingPeoplePercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceFemaleWorkingPeople()*100f / (Long)previousDetails[8]));
			
			censusDetailsVO.setDifferenceNonWorkingPeople((Long)currentDetails[9] - (Long)previousDetails[9]);
			censusDetailsVO.setDifferenceNonWorkingPeoplePercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceNonWorkingPeople()*100f/ (Long)previousDetails[9]));
			
			censusDetailsVO.setDifferenceLessthan6Population((Long)currentDetails[10] - (Long)previousDetails[10]);
			censusDetailsVO.setDifferenceLessthan6Percent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceLessthan6Population()*100f/ (Long)previousDetails[10]));
			
			censusDetailsVO.setDifferenceLiterates((Long)currentDetails[11] - (Long)previousDetails[11]);
			censusDetailsVO.setDifferenceLiteratesPercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceLiterates() *100f/(Long)previousDetails[11]));
			
			censusDetailsVO.setDifferenceMaleLiterates((Long)currentDetails[12] - (Long)previousDetails[12]);
			censusDetailsVO.setDifferenceMaleLiteratesPercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceMaleLiterates()*100f/(Long)previousDetails[12]));
			
			censusDetailsVO.setDifferenceFemaleLiterates((Long)currentDetails[13] - (Long) previousDetails[13]);
			censusDetailsVO.setDifferenceFemaleLiteratesPercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceFemaleLiterates() *100f /(Long)previousDetails[13]));
			

			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in the setValuesToCensusVO method StratagicReportServiceForMLASuccess class",e);
			
		}
	}
	
	private String roundTo2DigitsFloatValue(Float number){
		
		NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
		f.setMaximumFractionDigits(2);  
		f.setMinimumFractionDigits(2);
		
		return f.format(number);
		
	}
	
	
	//start mymethods
	
public List<PartyElectionTrendsReportVO> getPreviousTrendsReport(Long constId){
	List<PartyElectionTrendsReportVO> finalRes= new ArrayList<PartyElectionTrendsReportVO>();
	try{
		List<Object[]> ids = (List<Object[]>)partyTrendsDAO.getPreviousTrendsData(null, constId);
		Map<Long,PartyElectionTrendsReportVO> maps = new HashMap<Long, PartyElectionTrendsReportVO>();
       
		System.out.println(ids.size());
		
		for (Object[] object : ids) {
			
		if(maps.containsKey(Long.valueOf(object[0].toString())))
		{
			PartyElectionTrendsReportVO vo =maps.get(Long.valueOf(object[0].toString()));
	        vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
	        vo.setTotalVoters(((Double)object[1]).longValue());
	        vo.setTotalVotesPolled(((Double)object[2]).longValue());
	        vo.setDistrictId(Long.valueOf(object[9].toString()));
	        vo.setElectionId(Long.valueOf(object[11].toString()));
	        //vo.setElectionIdForConst(Long.valueOf(object[11].toString()));
	        PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
	        voh.setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((Double)object[7])));
	        voh.setVotesEarned(((Double)object[4]).longValue());
	        voh.setRank((Long)object[8]);
	        voh.setName(object[10].toString());
	      
	        if(((Long)object[3]).equals(872L))
	        {
	     	   voh.setMarginVotes(((Double)object[5]).longValue());
	     	  // voh.setMarginVotesPercentage((Double.valueOf(object[6].toString())));
	     	   voh.setMarginVotesPercentage(Double.valueOf(roundTo2DigitsDoubleValue( (double)(((double)voh.getMarginVotes()/(double)vo.getTotalVotesPolled())*100))));
	     	   vo.setTdpVo(voh);
	        }else if(((Long)object[3]).equals(362L))
	        {
	     	   vo.setIncVo(voh);

	        }else if(((Long)object[3]).equals(662L)||((Long)object[3]).equals(1117L)  )
	        {
	     	   vo.setPrpVo(voh);

	        }
	        else if(Long.valueOf(object[9].toString())<11){
	        	 if(((Long)object[3]).equals(886L)){
	        		vo.setTrsVo(voh);
	        	}
	        	 else {
	  	     	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
	  	     	   if(vo1!=null){
	  	     		   vo1.setVotesEarned(vo1.getVotesEarned()+((Double)object[4]).longValue()) ;
	  	     		  if( vo1.getRank()>(Long)object[8] )
	          			  vo1.setRank((Long)object[8]); 
	  	     	   }
	  	        
	  	     	   else 
	  	     	   {
	  	     		   vo1= new PartyElectionTrendsReportHelperVO();
	  	     		   vo1.setVotesEarned(((Double)object[4]).longValue()) ;
	  		 	     	  vo1.setRank((Long)object[8]); 

	  	     	   }
	  	     	  //vo1.setRank((Long)object[8]); 
	  	     	  vo.setOthersVo(vo1);
	  	        }
	        }
	        else {
	     	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
	     	   if(vo1!=null){
	     		   vo1.setVotesEarned(vo1.getVotesEarned()+((Double)object[4]).longValue()) ;
	     		  if( vo1.getRank()>(Long)object[8] )
        			  vo1.setRank((Long)object[8]); 
	     	   }
	        
	     	   else 
	     	   {
	     		   vo1= new PartyElectionTrendsReportHelperVO();
	     		   vo1.setVotesEarned(((Double)object[4]).longValue()) ;
		 	     	  vo1.setRank((Long)object[8]); 

	     	   }
	     	  //vo1.setRank((Long)object[8]); 
	     	  vo.setOthersVo(vo1);
	        }
			
		}
		else
		{
			           PartyElectionTrendsReportVO vo =new PartyElectionTrendsReportVO();
			           vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
			           vo.setTotalVoters(((Double)object[1]).longValue());
			           vo.setTotalVotesPolled(((Double)object[2]).longValue());
			           vo.setDistrictId(Long.valueOf(object[9].toString()));
				       vo.setElectionId(Long.valueOf(object[11].toString()));
				       //vo.setElectionIdForConst(Long.valueOf(object[11].toString()));

			           PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
			           voh.setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((Double)object[7])));
				        voh.setVotesEarned(((Double)object[4]).longValue());
				        voh.setRank((Long)object[8]);
				        voh.setName(object[10].toString());
			           if(((Long)object[3]).equals(872L))
			           {
			        	   voh.setMarginVotes(((Double)object[5]).longValue());
				     	   //voh.setMarginVotesPercentage((Double.valueOf(object[6].toString())));
			        	   voh.setMarginVotesPercentage(Double.valueOf(roundTo2DigitsDoubleValue( (double)(((double)voh.getMarginVotes()/(double)vo.getTotalVotesPolled())*100))));
				     	   vo.setTdpVo(voh);
			           }else if(((Long)object[3]).equals(362L))
			           {
			        	   vo.setIncVo(voh);

			           }else if(((Long)object[3]).equals(662L)||((Long)object[3]).equals(1117L)  )
			           {
			        	   vo.setPrpVo(voh);

			           }
			           else if(Long.valueOf(object[9].toString())<11){
			           		 if(((Long)object[3]).equals(886L)){
			           			vo.setTrsVo(voh);
			           		}
			           	 else {
				        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
				        	   if(vo1!=null){
				        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Double)object[4]).longValue()) ;
				        		  if( vo1.getRank()<(Long)object[8] )
				        			  vo1.setRank((Long)object[8]); 
				        	   }
				        	   else 
				        	   {
				        		   vo1= new PartyElectionTrendsReportHelperVO();
				        		   vo1.setVotesEarned(((Double)object[4]).longValue()) ;
						 	     	  vo1.setRank((Long)object[8]); 

				        	   }
	   
				        	   vo.setOthersVo(vo1);
				           }
			           }
			           
			           else {
			        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
			        	   if(vo1!=null){
			        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Double)object[4]).longValue()) ;
			        		  if( vo1.getRank()>(Long)object[8] )
			        			  vo1.setRank((Long)object[8]); 
			        	   }
			        	   else 
			        	   {
			        		   vo1= new PartyElectionTrendsReportHelperVO();
			        		   vo1.setVotesEarned(((Double)object[4]).longValue()) ;
					 	     	  vo1.setRank((Long)object[8]); 

			        	   }
   
			        	   vo.setOthersVo(vo1);
			           }
			           
		maps.put(Long.valueOf(object[0].toString()),vo );
		}
		}
		System.out.println(maps);
		
		for(Long year:maps.keySet())
		{
			PartyElectionTrendsReportVO vo=	maps.get(year);
			if(vo.getTdpVo()==null){
				PartyElectionTrendsReportHelperVO tdpVo=new PartyElectionTrendsReportHelperVO();
				vo.setTdpVo(tdpVo);
			}
			if(vo.getDistrictId()>10)
			alliancesCheck(vo, constId,vo.getElectionId());
			Long votesPolled = vo.getTotalVotesPolled();
			Long totalVotes = 0l;
			if(vo.getBjpVo()!=null){
				totalVotes +=vo.getBjpVo().getVotesEarned();
			}
			if(vo.getIncVo()!=null){
				totalVotes +=vo.getIncVo().getVotesEarned();
			}
			if(vo.getTdpVo()!=null){
				totalVotes +=vo.getTdpVo().getVotesEarned();
			}
			if(vo.getPrpVo()!=null){
				totalVotes +=vo.getPrpVo().getVotesEarned();
			}
			if(vo.getTrsVo()!=null){
				totalVotes +=vo.getTrsVo().getVotesEarned();
			}
			
			if(vo.getOthersVo()!=null){
				totalVotes +=vo.getOthersVo().getVotesEarned();
				//vo.getOthersVo().setVotesEarned(vo.getTotalVotesPolled()-( + vo.getIncVo()!=null ?vo.getIncVo().getVotesEarned().longValue():0L));
				vo.getOthersVo().setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((double)((double)vo.getOthersVo().getVotesEarned()/(double)vo.getTotalVotesPolled())*100)));
			}else{
				PartyElectionTrendsReportHelperVO othersVo=new PartyElectionTrendsReportHelperVO();
				vo.setOthersVo(othersVo);
			}
		 // check for aliances
			Long rejectedVotes = votesPolled - totalVotes;
			vo.setRejectedVotes(rejectedVotes);
			finalRes.add(vo);
		}
		Collections.sort(finalRes);
	}catch(Exception e){
		LOG.error("Exception raised in getPreviousTrendsReport service method",e);
	}
		return finalRes;
	}
	
 

public String roundTo2DigitsDoubleValue(Double number){
	  
	 LOG.debug("Entered into the roundTo2DigitsFloatValue service method");
	  
	  String result = "";
	  try
	  {
		  
		
		NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
		f.setMaximumFractionDigits(2);  
		f.setMinimumFractionDigits(2);
		
		result =  f.format(number);
	  }catch(Exception e)
	  {
		  LOG.error("Exception raised in roundTo2DigitsFloatValue service method");
		  e.printStackTrace();
	  }
	  return result;
  }
 

public void alliancesCheck(PartyElectionTrendsReportVO partyVos,Long constId,Long electionId)
   {
	   List<Object[]> obj=null;
	   if(partyVos.getTdpVo()==null)
	   {
		   // get aliance for tdp and add it to current vo by rank 
		   obj= getData(IConstants.TDP_PARTY_ID,electionId,constId, Long.valueOf(partyVos.getElectionYear().toString()));
		
			   //get aliances for inc and add it to current vo
			   if(obj !=null && obj.size()>0 ){
				   buildHelperVoForConst(partyVos,IConstants.TDP,obj.get(0));
			   }
			   }
  
	   
	   List<Object[]> obj1=null;
	     if(partyVos.getIncVo() == null || partyVos.getIncVo().getVotesEarned().equals(0L) )
	    {
		   //get aliances for inc and add it to current vo
		   obj1= getData(IConstants.INC_PARTY_ID,partyVos.getElectionId(),constId, Long.valueOf(partyVos.getElectionYear().toString()));
		   if(obj1 !=null && obj1.size()>0 ){
			   buildHelperVoForConst(partyVos,IConstants.INC,obj1.get(0));
		   }else{
			   
		   }
		   }
	   
   }

public void buildHelperVoForConst(PartyElectionTrendsReportVO partyVos ,String name,Object[] object)
{
	
	 PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
	 voh.setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((Double)object[7])));
     voh.setVotesEarned(((Double)object[4]).longValue());
     voh.setRank((Long)object[8]);  
     voh.setName(object[10].toString());
     if(name.equalsIgnoreCase(IConstants.TDP))
     {
      /* voh.setMarginVotes(((Double)object[5]).longValue());
	   voh.setMarginVotesPercentage(((Double)object[6]));*/
	   
	   voh.setMarginVotes(((Double)object[5]).longValue());
 	   voh.setMarginVotesPercentage((Double.valueOf(object[6].toString())));
	   partyVos.setTdpVo(voh);
     }
	   
	   if(name.equalsIgnoreCase(IConstants.INC)) 
	   {
		   partyVos.setIncVo(voh);
	   }
	   
	   PartyElectionTrendsReportHelperVO  othersVo=partyVos.getOthersVo();
	   if(othersVo!=null)
	   othersVo.setVotesEarned( othersVo.getVotesEarned()-((Double)object[4]).longValue());
}

  public List<Object[]> getData(Long partyId,Long ElectionId,Long constId,Long Year)
  {
	  List<Long> obj=	partyTrendsDAO.getWithAlliance(partyId, ElectionId);
		 for (Long long1 : obj) {
			System.out.println();
		} 
		 if(obj !=null && obj.size()>0 ){
				List<?> obj1= partyTrendsDAO.getPreviousTrendsDataWithAlliance(obj, constId,Year);
				return (List<Object[]>) obj1;
		 
		 }else return null;
	  
  }
	
  public List<PartyElectionTrendsReportVO> getPreviousTrendsReportParliament(Long constId){
		

		Map<Long,Long> idMap = new HashMap<Long, Long>();
		List<Object[]> obj=	partyTrendsDAO.getTotalVotersForConst(constId);
		for (Object[] objects : obj) {
			idMap.put(Long.valueOf(objects[0].toString()), ((Double)objects[1]).longValue());
			
		}
		//List<Object[]> ids1  =(List<Object[]>)partyTrendsDAO.getTotalCountForParleament(232L, 2012L);

		List<Object[]> ids = (List<Object[]>)partyTrendsDAO.getPreviousTrendsDataForParleament(null, constId);
		Map<Long,PartyElectionTrendsReportVO> maps = new HashMap<Long, PartyElectionTrendsReportVO>();

		System.out.println(ids.size());
		
		// get alliances for tdp inc based on year and election type
		
		
		
		for (Object[] object : ids) {
			
		if(maps.containsKey(Long.valueOf(object[0].toString())))
		{
			PartyElectionTrendsReportVO vo =maps.get(Long.valueOf(object[0].toString()));
			  vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
			   if(idMap.containsKey(Long.valueOf(object[0].toString())))
			   vo.setTotalVoters(idMap.get(Long.valueOf(object[0].toString()))!=null ?idMap.get(Long.valueOf(object[0].toString())):0L );
			   else
			   {
				   System.out.println("----nottfound for the key"+Long.valueOf(object[0].toString()));
			
				   List<Object[]> objs1= partyTrendsDAO.getTotalVotersForConstFormBooth(constId, Long.valueOf(object[0].toString()));
			       for (Object[] objects : objs1) {
				   idMap.put(Long.valueOf(objects[1].toString()),Long.valueOf(objects[0].toString())); 
				   vo.setTotalVoters(idMap.get(Long.valueOf(objects[0].toString()))!=null ?idMap.get(Long.valueOf(objects[0].toString())):0L );

			}
			   
			   }	        
			   PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();

			   if((object[0].toString().trim().equalsIgnoreCase("2009") || object[0].toString().trim().equalsIgnoreCase("2012")) && Long.valueOf(object[1].toString() )>vo.getTotalVoters())
			   {
				   vo.setTotalVotesPolled(Long.valueOf(object[1].toString())/2);
		           voh.setVotesEarned(((Long)object[3]).longValue()/2);


			   }else
			   {
				   vo.setTotalVotesPolled(Long.valueOf(object[1].toString()));
		           voh.setVotesEarned(((Long)object[3]).longValue());

  
			   }
	           vo.setDistrictId(Long.valueOf(object[5].toString()));
	           vo.setElectionId(Long.valueOf(object[8].toString()));
	           vo.setElectionIdForConst(Long.valueOf(object[11].toString()));
	           voh.setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((double)(((double)((Long)object[3]).longValue()/(double)Long.valueOf(object[1].toString()))*100))));
	           voh.setRank(((Long)object[6]).longValue());
		       voh.setName(object[7].toString());

	           if(((Long)object[2]).equals(872L))
	           {
	        	 /*  voh.setMarginVotes((Long)object[5]);
	        	   voh.setMarginVotesPercentage(((Double)object[6]));*/
	        	   vo.setTdpVo(voh);
	           }else if(((Long)object[2]).equals(362L))
	           {
	        	   vo.setIncVo(voh);

	           }else if(((Long)object[2]).equals(662L)||((Long)object[2]).equals(1117L)  )
	           {
	        	   vo.setPrpVo(voh);

	           }
	           else if(Long.valueOf(object[5].toString())<10){
	           		/*if(((Long)object[2]).equals(163L)){
	           			vo.setBjpVo(voh);
	           		}
	           		else*/ if(((Long)object[2]).equals(886L)){
	           			vo.setTrsVo(voh);
	           		}
	           		else {
			        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
			        	   if(vo1!=null){
			        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Long)object[3]).longValue()) ;
			        	   		if( vo1.getRank()<(Long)object[6] ){
			          			  vo1.setRank((Long)object[6]);
			        	   		}
			        	   }
			        	   else 
			        	   {
			        		   vo1= new PartyElectionTrendsReportHelperVO();
			        		   vo1.setVotesEarned(((Long)object[3]).longValue()) ;
			        		   vo1.setRank((Long)object[6]); 
			        	   }
			        		   
			        	   vo.setOthersVo(vo1);
			           }
	           }
	           else {
	        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
	        	   if(vo1!=null)
	        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Long)object[3]).longValue()) ;
	        	   else 
	        	   {
	        		   vo1= new PartyElectionTrendsReportHelperVO();
	        		   vo1.setVotesEarned(((Long)object[3]).longValue()) ;

	        	   }
	        		   
	        	   vo.setOthersVo(vo1);
	        }
			
		}
		else
		{
			PartyElectionTrendsReportVO vo =new PartyElectionTrendsReportVO();
			           vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
			     	  if(idMap.containsKey(Long.valueOf(object[0].toString())))
				       vo.setTotalVoters(idMap.get(Long.valueOf(object[0].toString())));
			           PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();

			     	 if((object[0].toString().trim().equalsIgnoreCase("2009") || object[0].toString().trim().equalsIgnoreCase("2012")) && Long.valueOf(object[1].toString() )>vo.getTotalVoters() )
					   {
						   vo.setTotalVotesPolled(Long.valueOf(object[1].toString())/2);
				           voh.setVotesEarned(((Long)object[3]).longValue()/2);


					   }else
					   {
						   vo.setTotalVotesPolled(Long.valueOf(object[1].toString()));
				           voh.setVotesEarned(((Long)object[3]).longValue());

		  
					   }			           vo.setDistrictId(Long.valueOf(object[5].toString()));
			           vo.setElectionId(Long.valueOf(object[8].toString()));
			           vo.setElectionIdForConst(Long.valueOf(object[11].toString()));

			           voh.setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((double)(((double)(((Long)object[3]).longValue())/(double)(Long.valueOf(object[1].toString()).longValue()))*100))));
			           voh.setVotesEarned(((Long)object[3]).longValue());
			           voh.setRank(((Long)object[6]).longValue());
			           voh.setName(object[7].toString());
			           if(((Long)object[2]).equals(872L))
			           {
			        	 /*  voh.setMarginVotes((Long)object[5]);
			        	   voh.setMarginVotesPercentage(((Double)object[6]));*/
			        	   vo.setTdpVo(voh);
			           }else if(((Long)object[2]).equals(362L))
			           {
			        	   vo.setIncVo(voh);

			           }else if(((Long)object[2]).equals(662L)||((Long)object[2]).equals(1117L)  )
			           {
			        	   vo.setPrpVo(voh);

			           } else if(Long.valueOf(object[5].toString())<10){
			           		/*if(((Long)object[2]).equals(163L)){
			           			vo.setBjpVo(voh);
			           		}
			           		else*/ if(((Long)object[2]).equals(886L)){
			           			vo.setTrsVo(voh);
			           		}
			           		else {
					        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
					        	   if(vo1!=null){
					        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Long)object[3]).longValue()) ;
					        	   		
					        		   if( vo1.getRank()<(Long)object[6] ){
					          			  vo1.setRank((Long)object[6]); 
					        		   }
					        	   }
					        	   else 
					        	   {
					        		   vo1= new PartyElectionTrendsReportHelperVO();
					        		   vo1.setVotesEarned(((Long)object[3]).longValue()) ;
					        		   vo1.setRank((Long)object[6]); 
					        	   }
					        		   
					        	   vo.setOthersVo(vo1);
					           }
			           }
			           
			           else {
			        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
			        	   if(vo1!=null)
			        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Long)object[3]).longValue()) ;
			        	   else 
			        	   {
			        		   vo1= new PartyElectionTrendsReportHelperVO();
			        		   vo1.setVotesEarned(((Long)object[3]).longValue()) ;

			        	   }
			        		   
			        	   vo.setOthersVo(vo1);
			           }
			           
		maps.put(Long.valueOf(object[0].toString()),vo );
		}
		}
		System.out.println(maps);
		List<PartyElectionTrendsReportVO> finalRes= new ArrayList<PartyElectionTrendsReportVO>();

		for(Long year:maps.keySet())
		{
			PartyElectionTrendsReportVO vo=maps.get(year);
		
		   //here aliance check for tdp in seemandara
		     if(vo.getDistrictId()>10)
			alliancesCheck(vo, constId,vo.getElectionIdForConst());
		
			Long tdp=vo.getTdpVo()!=null ?vo.getTdpVo().getVotesEarned():0L;
			
			Long inc =vo.getIncVo()!=null ? vo.getIncVo().getVotesEarned():0L;
			 
			Long prp11=null;
			if(vo.getDistrictId()>10)					
				prp11=vo.getPrpVo()!=null ? vo.getPrpVo().getVotesEarned() :0L;
				else
					prp11=vo.getTrsVo()!=null ? vo.getTrsVo().getVotesEarned() :0L;
			 
			Long others = vo.getOthersVo().getVotesEarned();
			
			
			if(vo.getTdpVo()==null){
				PartyElectionTrendsReportHelperVO tdpVo=new PartyElectionTrendsReportHelperVO();
				vo.setTdpVo(tdpVo);
			}
			if(year.equals(2012L))
				vo.getOthersVo().setVotesEarned(others/2);
			if(year.equals(2009L)  )
			{
				vo.getOthersVo().setVotesEarned(others/2);

				if(vo.getTotalVotesPolled()>vo.getTotalVoters())
				if(maps.containsKey(2012L))
				{  
					if(vo.getTotalVotesPolled()>vo.getTotalVoters())
					{
						vo.setTotalVotesPolled(vo.getTotalVotesPolled()/2);
						vo.getOthersVo().setVotesEarned(others/4);
						vo.getTdpVo().setVotesEarned(tdp/2);
						vo.getIncVo().setVotesEarned(inc/2);
						if(vo.getDistrictId()>10)
						{
							vo.getPrpVo().setVotesEarned(prp11/2);
						}
						else
						{
							vo.getTrsVo().setVotesEarned(prp11/2);
						}
						
					}
				}
				else
				{   
					vo.setTotalVotesPolled(vo.getTotalVotesPolled()/2);
					vo.getOthersVo().setVotesEarned(others/2);
					vo.getTdpVo().setVotesEarned(tdp/2);
					vo.getIncVo().setVotesEarned(inc/2);
					if(vo.getDistrictId()>10)
					{
						vo.getPrpVo().setVotesEarned(prp11/2);
					}
					else
					{
						vo.getTrsVo().setVotesEarned(prp11/2);
					}
						
					
				}
				
			}
	 tdp=vo.getTdpVo()!=null ?vo.getTdpVo().getVotesEarned():0L;
			
			 inc =vo.getIncVo()!=null ? vo.getIncVo().getVotesEarned():0L;
			 
			
			if(vo.getDistrictId()>10)					
				prp11=vo.getPrpVo()!=null ? vo.getPrpVo().getVotesEarned() :0L;
				else
					prp11=vo.getTrsVo()!=null ? vo.getTrsVo().getVotesEarned() :0L;
			 
			 others = vo.getOthersVo().getVotesEarned();
			
			Long max=inc;
			if(prp11!=null && prp11>inc )
				max=prp11;
			else if(others !=null && others>inc && (vo.getIncVo() !=null && (vo.getIncVo().getRank()!=1 || vo.getIncVo().getRank()==0)))
				max=others;
			
			
			
			vo.getTdpVo().setMarginVotes(tdp-max);
			System.out.println(vo.getTdpVo().getMarginVotes());
			System.out.println(vo.getTotalVotesPolled());
			System.out.println((double)(vo.getTdpVo().getMarginVotes()/vo.getTotalVotesPolled())*100); 
			
			vo.getTdpVo().setMarginVotesPercentage(Double.valueOf(roundTo2DigitsDoubleValue( (double)(((double)vo.getTdpVo().getMarginVotes()/(double)vo.getTotalVotesPolled())*100))));
			
			//PartyElectionTrendsReportVO vo=	maps.get(year);
			
			//vo.getOthersVo().setVotesEarned(vo.getTotalVotesPolled()-( + vo.getIncVo()!=null ?vo.getIncVo().getVotesEarned().longValue():0L));
			vo.getOthersVo().setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((double)(((double)vo.getOthersVo().getVotesEarned()/(double)vo.getTotalVotesPolled())*100))));
			finalRes.add(vo);
		}
		
	
		Collections.sort(finalRes);
	return finalRes	;
	}
  
 
  
  
}

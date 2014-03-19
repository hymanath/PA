package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyCensusDetailsDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.CasteStratagicReportVO;
import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.HouseHoldsVO;
import com.itgrids.partyanalyst.dto.VoterStratagicReportVo;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyCensusDetails;
import com.itgrids.partyanalyst.service.IStratagicReportServiceForMLASuccess;

public class StratagicReportServiceForMLASuccess implements IStratagicReportServiceForMLASuccess{
	public static Logger LOG = Logger.getLogger(StratagicReportServiceForMLASuccess.class);
	
	@Autowired IVoterCastInfoDAO voterCastInfoDAO;
	
	@Autowired IVoterAgeInfoDAO voterAgeInfoDAO;
	   
	@Autowired IVoterInfoDAO voterInfoDAO;
	
	@Autowired IVoterFamilyInfoDAO voterFamilyInfoDAO;
	
	@Autowired IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO;
	
	@Autowired IConstituencyDAO constituencyDAO;
	
	@Autowired IStateDAO stateDAO;
	
	@Autowired ICensusDAO censusDAO;
	
	@Autowired IDistrictDAO districtDAO;
	
	public HouseHoldsVO getHouseHoldInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId){
		LOG.info(" entered into getHouseHoldInfoByConstituency() in StratagicReportsService class.");
		HouseHoldsVO houseHoldsVO = null;
		List<HouseHoldsVO> houseHoldsVOList = null;
		try {
			List<Object[]> houseHoldsDetails = voterFamilyInfoDAO.getTotalFamiliesByCosntituency(232L,8L,232L);
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
			
			houseHoldsVO.setMessage("<p>As we understand that it is very important to know the Basic " +
					"Building Block of the entire structure and so we have put forward a measure of the " +
					"families in the constituency based on the total number of voter base available in each " +
					"of them respectively.</p>");
			Long houseHoldsByCensus = 0L;
			Long houseHoldsByNewVoterList = 0L;
			houseHoldsVO.setCalcMessage("<p>Please Note: Total Households: "+houseHoldsByCensus+" (According to the Census), "+houseHoldsByNewVoterList+" (According to the Voters List)</p>");
		} catch (Exception e) {
			LOG.error(" exception occured in getHouseHoldInfoByConstituency() of StratagicReportsService class. ",e);
		}
		
		return houseHoldsVO;
	}

	public VoterStratagicReportVo getVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId){
		LOG.info(" entered into getVotersInfoByConstituency() in StratagicReportsService class.");
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
							
							ageWiseReportVOList.add(voterInfoReportVO);									
					}
				}
				
				voterStratagicReportVo.setVoterStategicReportVOList(ageWiseReportVOList);	
				voterStratagicReportVo.setMessage("<p> The Data furnished below is in accordance with the " +
						" latest Voter Database released by the Election Commission. With thoughts on making " +
						" the information available more easier understand and target we have categorized " +
						" Voter Base into Age Group, Caste, Urban Vs Rural %, Density across each Panchayath." +
						"  </p>");
		} catch (Exception e) {
			LOG.error(" exception occured in getVotersInfoByConstituency() of StratagicReportsService class. ",e);
		}
		
		return voterStratagicReportVo;
	}

	public VoterStratagicReportVo getFirstTimeVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId){
		LOG.info(" entered into getFirstTimeVotersInfoByConstituency() in StratagicReportsService class.");
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
					totalVotersCount = totalVotersCount + Long.valueOf(voterAgeCount[2].toString());
				}
				
				for (Object[] voterAge : voterAgeInfoList) {
					if(voterAge[1].toString().equalsIgnoreCase("1")){ // only Young Voters
						VoterStratagicReportVo agewiseReportVO = new VoterStratagicReportVo();
						
						agewiseReportVO.setVoterAgeRange(voterAge[1].toString());
						
						Float totalCount = Float.valueOf(voterAge[3].toString());
						Double percentage = Double.valueOf(decimalFormat.format(totalCount*100/totalVotersCount));
						agewiseReportVO.setMaleVotersCount(totalCount.longValue());
						agewiseReportVO.setMaleTotalPercentage(percentage);
						
						totalCount = Float.valueOf(voterAge[4].toString());
						percentage = Double.valueOf(decimalFormat.format(totalCount*100/totalVotersCount));
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
		} catch (Exception e) {
			LOG.error(" exception occured in getFirstTimeVotersInfoByConstituency() of StratagicReportsService class. ",e);
		}
		
		return voterStratagicReportVo;
	}

	public VoterStratagicReportVo getAgeWiseVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId){
		LOG.info(" entered into getAgeWiseVotersInfoByConstituency() in StratagicReportsService class.");
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
					totalVotersCount = totalVotersCount + Long.valueOf(voterAgeCount[2].toString());
				}
				
				for (Object[] voterAge : voterAgeInfoList) {
					if(!voterAge[1].toString().equalsIgnoreCase("1")){// not adding young voter details
						VoterStratagicReportVo agewiseReportVO = new VoterStratagicReportVo();
						
						agewiseReportVO.setVoterAgeRange(voterAge[1].toString());
						
						Float totalCount = Float.valueOf(voterAge[3].toString());
						Double percentage = Double.valueOf(decimalFormat.format(totalCount*100/totalVotersCount));
						agewiseReportVO.setMaleVotersCount(totalCount.longValue());
						agewiseReportVO.setMaleTotalPercentage(percentage);
						
						totalCount = Float.valueOf(voterAge[4].toString());
						percentage = Double.valueOf(decimalFormat.format(totalCount*100/totalVotersCount));
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
		} catch (Exception e) {
			LOG.error(" exception occured in getAgeWiseVotersInfoByConstituency() of StratagicReportsService class. ",e);
		}
		
		return voterStratagicReportVo;
	}

	public CasteStratagicReportVO getCasteWiseVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId){
		LOG.info(" entered into getCasteWiseVotersInfoByConstituency() in StratagicReportsService class.");
		CasteStratagicReportVO stratagicVO = null;
		List<CasteStratagicReportVO> stratagicVOList = null;
		try {
			List<Object[]> votersCastInfo = voterCastInfoDAO.getVoterCasteInfoListByConstituency(constituencyId,publicationDateId,userId);
			
			if(votersCastInfo != null && votersCastInfo.size()>0){
				stratagicVOList = new ArrayList<CasteStratagicReportVO>();
				stratagicVO = new CasteStratagicReportVO();
				for (Object[] casteVoter : votersCastInfo) {
					CasteStratagicReportVO casteReportVO = new CasteStratagicReportVO();
					
					casteReportVO.setCaste(casteVoter[1].toString());
					casteReportVO.setCasteCategory(casteVoter[3].toString());
					casteReportVO.setTotalCasteVoters(Long.valueOf(casteVoter[4].toString()));
					casteReportVO.setMaleCasteVoters(Long.valueOf(casteVoter[5].toString()));
					casteReportVO.setFemaleCasteVoters(Long.valueOf(casteVoter[6].toString()));
					casteReportVO.setCastePercentage(Double.valueOf(casteVoter[7].toString()));
					
					stratagicVOList.add(casteReportVO);
				}
			}			
			stratagicVO.setStrategicVOList(stratagicVOList);
			
		} catch (Exception e) {
			LOG.error(" exception occured in getCasteWiseVotersInfoByConstituency() of StratagicReportsService class. ",e);
		}
		
		return stratagicVO;
	}
	

	
	public CensusVO getCensusDetailsForAConstituency(Long constituencyId)
	{
		LOG.debug("Entered into the getCensusDetailsForAConstituency service method StratagicReportServiceForMLASuccess class.");
		CensusVO resultVO = new CensusVO();
		
		try
		{
			List<Long> years = new ArrayList<Long>();
			
			years.add(2001L);
			years.add(2011L);
		
			String stateName = null;
			String districtName = null;
			List<Object[]> stateInfo=stateDAO.getStateDetailsByconstituencyId(constituencyId);			
			List<Object[]> districtInfo=districtDAO.getDistrictIdAndNameByConstituency(constituencyId);
			
			for (Object[] parms : stateInfo) {
				stateName = parms[1].toString();
			}
			
			for (Object[] parms : districtInfo) {
				districtName = parms[1].toString();
			}
			
			List<ConstituencyCensusDetails> censusDetailsList = constituencyCensusDetailsDAO
					.getCensusConstituencyByConstituencyIdAndYears(constituencyId, years);
					
			resultVO.setCount(Integer.valueOf(censusDetailsList.size()));			
			resultVO.setStateName(stateInfo.size() != 0 ?stateName:"");
			resultVO.setDistrictName(districtInfo.size()>0 ? districtName:"");
								
			
			List<CensusVO> censusList = new ArrayList<CensusVO>();
			
			for(ConstituencyCensusDetails details:censusDetailsList)
			{

					CensusVO censusDetailsVO = new CensusVO();				
					
					censusDetailsVO.setYear(Integer.parseInt(details.getYear().toString()!= null ? details.getYear().toString():"0"));
					censusDetailsVO.setTotalPopulation(details.getTotalPopulation());
					censusDetailsVO.setTotalPopulationPercentage("".toString());
					
					
					censusDetailsVO.setMalePopulation(details.getTotalMalePopulation());
					censusDetailsVO.setFemalePopulation(details.getTotalFemalePopulation());				
					censusDetailsVO.setMalePopulationPercentage(roundTo2DigitsFloatValue((float)details.getTotalMalePopulation()*100f/details.getTotalPopulation()));
					censusDetailsVO.setFemalePopulationPercentage(roundTo2DigitsFloatValue((float)details.getTotalFemalePopulation()*100f/details.getTotalPopulation()));
					
					censusDetailsVO.setHouseHolds(details.getHouseHolds());
					censusDetailsVO.setHouseHoldsPercentage(details.getPopHHPercentage().toString()!=null?details.getPopHHPercentage().toString():"0.0");
					
					censusDetailsVO.setPopulationSC(details.getPopulationSC());
					censusDetailsVO.setPopulationSCPercent(BigDecimal.valueOf(details.getPercentageSC()));
					
					censusDetailsVO.setPopulationST(details.getPopulationST());
					censusDetailsVO.setPopulationSTPercent(BigDecimal.valueOf(details.getPercentageST()));
					
					censusDetailsVO.setWorkingPeople(details.getWorkingPopulation());
					censusDetailsVO.setWorkingPeoplePercentage(details.getTotalWorkingPopPercentage().toString()!= null ? details.getTotalWorkingPopPercentage().toString():"0.0");
					
					censusDetailsVO.setWorkingMale(details.getWorkingMale());
					censusDetailsVO.setTotalWorkingMalePercentage(Double.parseDouble(roundTo2DigitsFloatValue((float)details.getWorkingMale()*100f/details.getWorkingPopulation())));
					
					censusDetailsVO.setWorkingFemale(details.getWorkingFemale());
					censusDetailsVO.setTotalWorkingFemalePercentage(Double.parseDouble(roundTo2DigitsFloatValue((float)details.getWorkingFemale()*100f/details.getWorkingPopulation())));
					
					censusDetailsVO.setNonWorkingPeople(details.getNonWorkingPopulation());
					censusDetailsVO.setNonWorkingPeoplePercent(BigDecimal.valueOf(100-details.getTotalWorkingPopPercentage()));
					
					censusDetailsVO.setPopulationUnderSix(details.getPopulationUnderSix());
					censusDetailsVO.setPopulationUnderSixPercentage(roundTo2DigitsFloatValue((float)censusDetailsVO.getPopulationUnderSix()*100f/censusDetailsVO.getTotalPopulation()).toString());
					
					censusDetailsVO.setLiterates(details.getPopulationLiterates());
					censusDetailsVO.setLiteratesPercentage(roundTo2DigitsFloatValue((float)censusDetailsVO.getLiterates()*100f/censusDetailsVO.getTotalPopulation()).toString());
					
					censusDetailsVO.setMaleLiterates(details.getMaleLiterates());
					censusDetailsVO.setMaleLiteraturePercentage(Double.parseDouble(roundTo2DigitsFloatValue((float)details.getMaleLiterates()*100f/details.getPopulationLiterates())));
					
					censusDetailsVO.setFemaleLiterates(details.getFemaleLiterates());
					censusDetailsVO.setFemaleLiteraturePercentage(Double.parseDouble(roundTo2DigitsFloatValue((float)details.getFemaleLiterates()*100f/details.getPopulationLiterates())));
					
					censusList.add(censusDetailsVO);
					

			}
			
			resultVO.setCensusDetailsList(censusList);
			
			CensusVO previousDetails = censusList.get(0);
			CensusVO currentDetails = censusList.get(1);
			
			resultVO.setDifferencePopulation(currentDetails.getTotalPopulation() - previousDetails.getTotalPopulation());
			resultVO.setDifferencePopulationPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferencePopulation()*100f/previousDetails.getTotalPopulation()));
			
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
			
			resultVO.setDifferenceMaleLiterates(currentDetails.getMaleLiterates() - previousDetails.getMaleLiterates());
			resultVO.setDifferenceMaleLiteratesPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceMaleLiterates()*100f/previousDetails.getMaleLiterates()));
			resultVO.setDifferenceFemaleLiterates(currentDetails.getFemaleLiterates() - previousDetails.getFemaleLiterates());
			resultVO.setDifferenceFemaleLiteratesPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceFemaleLiterates() *100f /previousDetails.getFemaleLiterates()));
			
			Constituency constituency = constituencyDAO.get(constituencyId);
			
			Long stateId = constituency.getState().getStateId();
			Long districtId = constituency.getDistrict().getDistrictId();
			
			
			List<Object[]> districtCensusDetails = censusDAO.getDistrictPopulationForDifferentYears(districtId,years);
			
			List<Object[]> stateCensusDetails = censusDAO.getStatePopulationForDifferentYears(stateId,years);
			
			CensusVO districtVO = new CensusVO();
			CensusVO stateVO = new CensusVO();
			
			
			setValuesToCensusVO(districtCensusDetails.get(0),districtCensusDetails.get(1), districtVO);
			setValuesToCensusVO(stateCensusDetails.get(0),stateCensusDetails.get(1), stateVO);
			
			
			resultVO.setDistrictDetails(districtVO);
			resultVO.setStateDetails(stateVO);
			
			//List<ConstituencyCensusDetails> censusDetailsList = censusDAO.getCensusConstituencyForStateAndDistrict(constituencyId, years);
			
			resultVO.setMessage("<p>We all know that the Indian Census has always been misinterpreted, " +
					" mis-communicated over the period of years, rightfully we always wanted to have " +
					" appropriate knowledge to help us going along the way. We have put in our effort to " +
					" bring Most Accurate & Most Recent Census based on Population, SC, ST, Literates for the " +
					" years of 2001 and 2011</p>");
			
		}catch(Exception e)
		{
			LOG.error("Exception occured in the StratagicReportServiceForMLASuccess service method");
		}
		
		return resultVO;
	}

	private void setValuesToCensusVO(Object[] currentDetails , Object[] previousDetails,CensusVO censusDetailsVO)
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
	
}

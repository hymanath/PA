package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyCensusDetailsDAO;
import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyCensusDetails;

public class ConstituencyCensusDetailsDAOHibernateTest extends BaseDaoTestCase{
	
	private IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO;
	private ICensusDAO censusDAO;
	
	public void setCensusDAO(ICensusDAO censusDAO) {
		this.censusDAO = censusDAO;
	}

	public void setConstituencyCensusDetailsDAO(
			IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO) {
		this.constituencyCensusDetailsDAO = constituencyCensusDetailsDAO;
	}
	/*public void test()
	{
		constituencyCensusDetailsDAO.getAll();
	}*/
	
	/*public void testFindConstituencyWiseCensusDetails()
	{
		List<Object[]> list =  constituencyCensusDetailsDAO.findConstituencyWiseCensusDetails(1l, 232l, 2001l);
		System.out.println(list.size());
		for(Object obj:list.get(0))
		{
			System.out.println(obj.toString());
		}
	}*/
	
	/*public void testCheckForConstituencyExistance()
	{
		List<Long> list =  constituencyCensusDetailsDAO.checkForConstituencyExistance(232l);
		System.out.println(list.size());
		
		for(Long id:list){
			System.out.println(id);
			if(232l==id)
			{
				System.out.println("y");
			}
		}
	}*/
	/*public void testGetConstituencyIdsAndPercentages()
	{
		//String censusParam = "model.percentageSC";
		//String censusParam = "model.percentageST";
		//String censusParam = "model.popLiteraturePercentage";
		//String censusParam = "(100-model.popLiteraturePercentage)";
		String censusParam = "model.totalWorkingPopPercentage";
		//String censusParam = "model.nonWorkingPopPercentage";
		List<Object[]> list = constituencyCensusDetailsDAO.getConstituencyIdsAndPercentages(censusParam, 1l);
		
		System.out.println(list.size());
		for(Object[] obj: list)
		{
			System.out.print("==Constituency Id --"+obj[0].toString());
			System.out.println("==percentage      --"+obj[1].toString());
		}
	}*/
	
	/*public void testGetCensusConstituencyByConstituencyId()
	{
		List<ConstituencyCensusDetails> list = constituencyCensusDetailsDAO.getCensusConstituencyByConstituencyId(1l);
		
		ConstituencyCensusDetails constituencyCensusDetails = list.get(0);
		
		System.out.println(constituencyCensusDetails.getTru());
	}*/
	
	public void testGetVAlues(){
		CensusVO resultVO = new CensusVO();
		List<Long> years = new ArrayList<Long>();
		
		years.add(2001L);
		years.add(2011L);
		List<ConstituencyCensusDetails> censusDetailsList = constituencyCensusDetailsDAO
				.getCensusConstituencyByConstituencyIdAndYears(181L, years);
				
		resultVO.setCount(Integer.valueOf(censusDetailsList.size()));			
		resultVO.setStateName("Andhra Pradesh");
		resultVO.setDistrictName(" KAVALI ");
							
		
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
		
		//Constituency constituency = constituencyDAO.get(constituencyId);
		
		Long stateId = 1L;
		Long districtId = 19L;;
		
		
		List<Object[]> districtCensusDetails = censusDAO.getDistrictPopulationForDifferentYears(districtId,years);
		
		List<Object[]> stateCensusDetails = censusDAO.getStatePopulationForDifferentYears(stateId,years);
		
		CensusVO districtVO = new CensusVO();
		CensusVO stateVO = new CensusVO();
		
		
		setValuesToCensusVO(districtCensusDetails.get(0),districtCensusDetails.get(1), districtVO);
		setValuesToCensusVO(stateCensusDetails.get(0),stateCensusDetails.get(1), stateVO);
		
		
		resultVO.setDistrictDetails(districtVO);
		resultVO.setStateDetails(stateVO);
		
	}
	
private String roundTo2DigitsFloatValue(Float number){
		
		NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
		f.setMaximumFractionDigits(2);  
		f.setMinimumFractionDigits(2);
		
		return f.format(number);
		
	}
	
	private void setValuesToCensusVO(Object[] currentDetails , Object[] previousDetails,CensusVO censusDetailsVO)
	{
		
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
			e.printStackTrace();
		}
	}
}

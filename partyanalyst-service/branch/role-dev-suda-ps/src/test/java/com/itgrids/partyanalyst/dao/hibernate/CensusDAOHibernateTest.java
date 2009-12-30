/*package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Set;
import junit.framework.Assert;

import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.model.Census;


import org.appfuse.dao.BaseDaoTestCase;

public class CensusDAOHibernateTest extends BaseDaoTestCase {
	private ICensusDAO censusDAO;
	
	public void setCensusDAO(ICensusDAO censusDao){
		this.censusDAO = censusDao;
	}
	
	public void testFindByStateIdAndYear(){
		Set<Census> stateCensus = censusDAO.findByStateIdAndYear(new Long(1), 2001);
		
		for(Census census: stateCensus){
			if(census.getTru().equals("Total")){
				Assert.assertEquals(6.59244658, census.getPercentageST());
			} else if(census.getTru().equals("Urban")){
				Assert.assertEquals(1.812591127, census.getPercentageST());
			} else if(census.getTru().equals("Rural")){
				Assert.assertEquals(8.387786105, census.getPercentageST());
			}
		}
	}
	
	public void testFindByDistrictIdAndYear(){
		Set<Census> districtCensus = censusDAO.findByDistrictIdAndYear(new Long(23), 2001);
		
		for(Census census: districtCensus){
			if(census.getTru().equals("Total")){
				Assert.assertEquals(66.77287396, census.getPopLiteraturePercentage());
			} else if(census.getTru().equals("Rural")){
				Assert.assertEquals(62.99643768, census.getPopLiteraturePercentage());
			} else if(census.getTru().equals("Urban")){
				Assert.assertEquals(80.23567595, census.getPopLiteraturePercentage());
			}
		}
	}
	
	public void testFindByTehsilIdAndYear(){
		Set<Census> tehsilCensus = censusDAO.findByTehsilIdAndYear(new Long(45), 2001);
		
		for(Census census: tehsilCensus){
			if(census.getTru().equals("Total")){
				Assert.assertEquals(new Long(32016), census.getTotalPopulation());
			} else if(census.getTru().equals("Rural")){
				Assert.assertEquals(new Long(32016), census.getTotalPopulation());
			} else if(census.getTru().equals("Urban")){
				Assert.assertEquals(new Long(0), census.getTotalPopulation());
			}
		}
	}
	
	public void testFindByTownshipIdAndYear_Town(){
		Set<Census> townCensus = censusDAO.findByTownshipIdAndYear(new Long(4752), 2001);
		Assert.assertEquals(1, townCensus.size());
		for(Census census: townCensus){
			if(census.getTru().equals("Urban")){
				Assert.assertEquals(new Long(1870068), census.getTotalMalePopulation());
			} 
		}
	}
	
	public void testFindByTownshipIdAndYear_Village(){
		Set<Census> villageCensus = censusDAO.findByTownshipIdAndYear(new Long(4756), 2001);
		Assert.assertEquals(1, villageCensus.size());
		for(Census census: villageCensus){
			if(census.getTru().equals("Rural")){
				Assert.assertEquals(new Long(449), census.getTotalMalePopulation());
			} 
		}
	}
	
	public void testFindByWardIdAndYear(){
		Set<Census> villageCensus = censusDAO.findByWardIdAndYear(new Long(3991), 2001);
		Assert.assertEquals(1, villageCensus.size());
		for(Census census: villageCensus){
			if(census.getTru().equals("Urban")){
				Assert.assertEquals(new Long(2315), census.getNonWorkingPopulation());
			} 
		}
	}
}
*/
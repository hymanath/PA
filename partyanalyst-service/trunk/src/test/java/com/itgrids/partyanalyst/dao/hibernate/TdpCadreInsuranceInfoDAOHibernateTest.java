package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITdpCadreInsuranceInfoDAO;

public class TdpCadreInsuranceInfoDAOHibernateTest extends BaseDaoTestCase{

	private ITdpCadreInsuranceInfoDAO tdpCadreInsuranceInfoDAO;

	
	public void setTdpCadreInsuranceInfoDAO(
			ITdpCadreInsuranceInfoDAO tdpCadreInsuranceInfoDAO) {
		this.tdpCadreInsuranceInfoDAO = tdpCadreInsuranceInfoDAO;
	}
	  
	public void testGetDeathsAndHospitalizationDetails(){
		try{
			List<Object[]> deathDetails=tdpCadreInsuranceInfoDAO.getDeathsAndHospitalizationDetails(261L,"constituency");
			System.out.println(deathDetails.size());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}
	
	
}

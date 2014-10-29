package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadrePreviousRolesDAO;

public class CadrePreviousRolesDAOHibernateTest extends BaseDaoTestCase{

	private ICadrePreviousRolesDAO cadrePreviousRolesDAO;

	public void setCadrePreviousRolesDAO(
			ICadrePreviousRolesDAO cadrePreviousRolesDAO) {
		this.cadrePreviousRolesDAO = cadrePreviousRolesDAO;
	}
	/*
	public void testDetails()
	{
		List<Object[]> participationInfo = cadrePreviousRolesDAO.getexistingRolesForTdpCadreByTdpCadreId(1400565L);
		List<GenericVO> returnList = new ArrayList<GenericVO>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		try {
			
			if(participationInfo != null && participationInfo.size()>0)
			{
				for (Object[] participation : participationInfo)
				{
					//participation
					String date = format.format(format1.parse(participation[2].toString()));
					System.out.println(date);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}*/
}

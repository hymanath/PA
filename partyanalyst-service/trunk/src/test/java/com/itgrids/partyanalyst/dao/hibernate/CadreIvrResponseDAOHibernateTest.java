package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreIvrResponseDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreIvrResponseDAOHibernateTest extends BaseDaoTestCase{
	
	private ICadreIvrResponseDAO cadreIvrResponseDAO;

	public void setCadreIvrResponseDAO(ICadreIvrResponseDAO cadreIvrResponseDAO) {
		this.cadreIvrResponseDAO = cadreIvrResponseDAO;
	}
	
	
	/*public void test()
	{
		Date convertedDate = null;
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 convertedDate = sdf.parse("2014-12-19");
		}
		catch(Exception e)
		{
			
		}
	List<Object[]> list =cadreIvrResponseDAO.getIvrCadreDetails(convertedDate,null,"total",0,201);
	System.out.println(list.size());
	
	}*/
	
	public void testGetStateWiseCommitterIvrDetails()
	{
		List<Long> distIds = new ArrayList<Long>();
		distIds.add(11l);
		distIds.add(12l);
		distIds.add(13l);
		distIds.add(14l);
		distIds.add(15l);
		distIds.add(16l);
		distIds.add(17l);
		distIds.add(18l);
		distIds.add(19l);
		distIds.add(20l);
		distIds.add(21l);
		distIds.add(22l);
		distIds.add(23l);
		List<Object[]> list = cadreIvrResponseDAO.getStateWiseCommitterIvrDetails(distIds,3l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}

}

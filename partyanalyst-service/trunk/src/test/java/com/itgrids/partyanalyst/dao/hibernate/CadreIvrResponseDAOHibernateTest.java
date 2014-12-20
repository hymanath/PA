package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
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
	
	
	public void test()
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
	
	}

}

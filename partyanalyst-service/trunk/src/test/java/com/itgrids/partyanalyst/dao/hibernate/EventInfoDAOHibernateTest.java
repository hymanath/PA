package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IEventInfoDAO;

public class EventInfoDAOHibernateTest extends BaseDaoTestCase{
	private IEventInfoDAO eventInfoDAO;

	public void setEventInfoDAO(IEventInfoDAO eventInfoDAO) {
		this.eventInfoDAO = eventInfoDAO;
	}
	
	public void testGetEventDataByReportLevelId()
	{
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date stratDate = sdf.parse("2016-03-11");
		Date endDate = sdf.parse("2016-03-11");
		List<Long> eventIds = new ArrayList<Long>(0);
		
		eventIds.add(2l);
		eventIds.add(3l);
		eventIds.add(4l);
		eventIds.add(5l);
		eventIds.add(6l);
		eventIds.add(14l);
		eventIds.add(15l);
		
		List<Object[]> list = eventInfoDAO.getEventDataByReportLevelId(4L,1L,1L,eventIds,stratDate,endDate);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}

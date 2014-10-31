package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreTxnDetailsDAO;

public class CadreTxnDetailsDAOHibernateTest extends  BaseDaoTestCase	{

	private ICadreTxnDetailsDAO cadreTxnDetailsDAO;

	public void setCadreTxnDetailsDAO(ICadreTxnDetailsDAO cadreTxnDetailsDAO) {
		this.cadreTxnDetailsDAO = cadreTxnDetailsDAO;
	}
	
	public void testDetals()
	{
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date today = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			
			cal.add(Calendar.DATE, -1);
			Date yesterDay = cal.getTime();
			
			System.out.println(yesterDay);
			List<Long> list = cadreTxnDetailsDAO.getTotalCadreSurveyTxnTeamSize(yesterDay);
			System.out.println(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}

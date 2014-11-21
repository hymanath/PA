package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IZebraPrintDetailsDAO;

public class ZebraPrintDetailsDAOHibernateTest extends BaseDaoTestCase{

	private IZebraPrintDetailsDAO zebraPrintDetailsDAO;

	public IZebraPrintDetailsDAO getZebraPrintDetailsDAO() {
		return zebraPrintDetailsDAO;
	}
	public void setZebraPrintDetailsDAO(IZebraPrintDetailsDAO zebraPrintDetailsDAO) {
		this.zebraPrintDetailsDAO = zebraPrintDetailsDAO;
	}

	public void testgetMemberShipCardPrintStatusByDate()
	{
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		List<Long> constituencyIds = new ArrayList<Long>();
		constituencyIds.add(2L);
		try {
			Date fromDate = format.parse("20-11-2014");
			Date toDate = format.parse("20-11-2014");
			
			List<Object[]> totalCount = zebraPrintDetailsDAO.getMemberShipCardPrintStatusByDate(constituencyIds, fromDate, toDate, null);
			List<Object[]> successCount = zebraPrintDetailsDAO.getMemberShipCardPrintStatusByDate(constituencyIds, fromDate, toDate, "printStatus");
			List<Object[]> failureCount = zebraPrintDetailsDAO.getMemberShipCardPrintStatusByDate(constituencyIds, fromDate, toDate, "errorStatus");
			
			System.out.println(totalCount);
			System.out.println(successCount);
			System.out.println(failureCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

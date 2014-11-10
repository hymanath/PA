package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreRegAmountDetailsDAO;

public class CadreRegAmountDetailsDAOHibernateTest extends BaseDaoTestCase {

	private ICadreRegAmountDetailsDAO cadreRegAmountDetailsDAO;

	public void setCadreRegAmountDetailsDAO(
			ICadreRegAmountDetailsDAO cadreRegAmountDetailsDAO) {
		this.cadreRegAmountDetailsDAO = cadreRegAmountDetailsDAO;
	}
	
	/*public void test(){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dt = "2014-10-02";
			String tdt = "2014-10-30";
			Date fromDate = format.parse(dt);
			Date toDate = format.parse(tdt);
			List<Object[]> list = cadreRegAmountDetailsDAO.getAmountDetailsOfUser(fromDate,toDate);
			list.size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/*public void test1(){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dt = "2014-09-30";
			Date amountDate = format.parse(dt);
			List<Object[]> list = cadreRegAmountDetailsDAO.getAmountDetailsOfUser(amountDate);
			list.size();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public void test(){
		List<Object[]> list = cadreRegAmountDetailsDAO.getAmountDetailsDateWise();
		System.out.println(list.size());
	}
	
}

package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyCallStatusDAO;

public class SurveyCallStatusDAOHibernateTest extends BaseDaoTestCase{

		private ISurveyCallStatusDAO surveyCallStatusDAO;

		public void setSurveyCallStatusDAO(ISurveyCallStatusDAO surveyCallStatusDAO) {
			this.surveyCallStatusDAO = surveyCallStatusDAO;
		}
		/*public void test()
		{
			List<Long> userIds = new ArrayList<Long>();
			List<Long> boothIds = new ArrayList<Long>();
			Long userTypeId = 1l;
		userIds.add(1l)		;;
		boothIds.add(370994l);

					
		List<Object[]> list = surveyCallStatusDAO.getStatusListForUser(userIds,boothIds,userTypeId);
		System.out.println(list.size());
		}*/
		
	/*	public void test(){
			List<Long> boothIds = new ArrayList<Long>();
			boothIds.add(439808l);
			boothIds.add(439964l);
			
			List<Object[]> list = surveyCallStatusDAO.getCasteStatusForBooth(boothIds);
			System.out.println(list.size());
		}
		*/
		
		public void test(){
			List<Object[]> list = surveyCallStatusDAO.getSurveyCallDtalsByboothId(403940L,null);
			System.out.println(list.size());
		}
		
		
		
		
}

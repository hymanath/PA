package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRegAmountDetailsDAO;
import com.itgrids.partyanalyst.model.CadreRegAmountDetails;

public class CadreRegAmountDetailsDAO extends GenericDaoHibernate<CadreRegAmountDetails, Long> implements ICadreRegAmountDetailsDAO{

	public CadreRegAmountDetailsDAO() {
		super(CadreRegAmountDetails.class);
	} 
	
	public List<Object[]> getAmountDetailsOfUser(Date fromDate,Date toDate){
		Query query = getSession().createQuery(" select model.cadreSurveyUser.cadreSurveyUserId," +
				" model.cadreSurveyUser.userName," +
				" model.cadreSurveyUser.name," +
				" model.cadreSurveyUser.mobileNo," +
				" sum(model.amount)" +
				//" model.branch" +
				" from CadreRegAmountDetails model " +
				" where model.cadreSurveyUser.isDeleted = 'N'" +
				" and date(model.cadreRegAmountFile.date) >= :fromDate" +
				" and date(model.cadreRegAmountFile.date) <= :toDate" +
				" group by model.cadreSurveyUser.cadreSurveyUserId");
		
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	
	
	public List<Object[]> getAmountDetailsOfUserByDate(Date fromDate,Date toDate){
		Query query = getSession().createQuery(" select model.cadreSurveyUser.cadreSurveyUserId," +
				" model.cadreSurveyUser.userName," +
				" model.cadreSurveyUser.name," +
				" model.cadreSurveyUser.mobileNo," +
				" sum(model.amount) , " +
				" date(model.cadreRegAmountFile.date)" +
				" from CadreRegAmountDetails model " +
				" where model.cadreSurveyUser.isDeleted = 'N'" +
				" and date(model.cadreRegAmountFile.date) >= :fromDate" +
				" and date(model.cadreRegAmountFile.date) <= :toDate" +
				" group by model.cadreSurveyUser.cadreSurveyUserId,model.cadreRegAmountFile.date");
		
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	
	public List<Object[]> getAmountDetailsDateWise(){
		Query query = getSession().createQuery(" select sum(model.amount)," +
				" date(model.cadreRegAmountFile.date) from CadreRegAmountDetails model" +
				" group by date(model.cadreRegAmountFile.date)" +
				" order by date(model.cadreRegAmountFile.date)");
		
		return query.list();
	}

}

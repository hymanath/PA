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
	
	public List<Object[]> getAmountDetailsOfWebUser(Date fromDate,Date toDate){
		Query query = getSession().createQuery(" select model.webUser.userId," +
				" model.webUser.userName," +
				" model.webUser.firstName," +
				" model.webUser.mobile," +
				" sum(model.amount)" +
				" from CadreRegAmountDetails model" +
				" where date(model.cadreRegAmountFile.date) >= :fromDate" +
				" and date(model.cadreRegAmountFile.date) <= :toDate" +
				" group by model.webUser.userId");
		
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	
	public List<Object[]> getAmountDetailsOfWebUserByDate(Date fromDate,Date toDate){
		Query query = getSession().createQuery(" select model.webUser.userId," +
				" model.webUser.userName," +
				" model.webUser.firstName," +
				" model.webUser.mobile," +
				" sum(model.amount)," +
				" date(model.cadreRegAmountFile.date)" +
				" from CadreRegAmountDetails model" +
				" where date(model.cadreRegAmountFile.date) >= :fromDate" +
				" and date(model.cadreRegAmountFile.date) <= :toDate" +
				" group by model.webUser.userId");
		
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
	
	public List<Object[]> getAmountDetailsDateWise(String type){
		
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(model.amount)," +
				" date(model.cadreRegAmountFile.date) from CadreRegAmountDetails model");
		if(!type.equalsIgnoreCase("all")){
			sb.append(" where model.registrationType =:type ");
		}
		
		sb.append(" group by date(model.cadreRegAmountFile.date)" +
				" order by date(model.cadreRegAmountFile.date)");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(!type.equalsIgnoreCase("all")){
			query.setParameter("type", type);
		}
		
		return query.list();
	}

	public List<Object[]> getPaidAmountDetailsOfWebUserByDateANDType(Long webUserId, Date fromDate,Date toDate,String type)
	{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select distinct date(model.cadreRegAmountFile.date), sum(model.amount) from CadreRegAmountDetails model ");
		//sb.append(" where model.registrationType = "+type+"  and  model.webUser.userId =:webUserId ");
		
		sb.append(" where model.registrationType = '"+type+"'  and  model.cadreSurveyUser.cadreSurveyUserId =:webUserId ");
		
		if(fromDate != null && toDate != null)
		{
			sb.append(" and ( date(model.cadreRegAmountFile.date) >= :fromDate and date(model.cadreRegAmountFile.date) <= :toDate  )  ");
		}
		
		sb.append(" group by date(model.cadreRegAmountFile.date)  order by date(model.cadreRegAmountFile.date) desc ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("webUserId", webUserId);
		if(fromDate != null && toDate != null)
		{
			query.setDate("fromDate",fromDate);	
			query.setDate("toDate",toDate);	
		}
		
		return query.list();
	}
	
}

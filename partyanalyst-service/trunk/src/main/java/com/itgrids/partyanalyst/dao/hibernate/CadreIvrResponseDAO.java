package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreIvrResponseDAO;
import com.itgrids.partyanalyst.model.CadreIvrResponse;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreIvrResponseDAO extends GenericDaoHibernate<CadreIvrResponse, Long> implements ICadreIvrResponseDAO{

	public CadreIvrResponseDAO() {
		super(CadreIvrResponse.class);
		
	}

	
	public List<Date> getDates()
	{
		Query query = getSession().createQuery("select distinct date(model.date) from CadreIvrResponse model");
		return query.list();
	}
	
	public Long getIvrStatusCount(Date date,Long Id,String searchType)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreIvrResponseId) from CadreIvrResponse model where ");
		if(date != null)
			str.append(" date(model.date) =:date");
		else if(Id != null)
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId =:Id and model.isDeleted = 'N' ");
		if(searchType.equalsIgnoreCase(IConstants.Received))
		 str.append(" and model.responseKey = 1");	
		else if(searchType.equalsIgnoreCase(IConstants.NotReceived))
			 str.append(" and model.responseKey = 2");	
		else if(searchType.equalsIgnoreCase(IConstants.NotRegistered))
			 str.append(" and model.responseKey = 3");	
		Query query = getSession().createQuery(str.toString());
		if(date != null)
			query.setDate("date", date);
		else if(Id != null)
			query.setParameter("Id", Id);
		return (Long) query.uniqueResult();
	}
	public List<Object[]> getIvrCadreDetails(Date date,Long Id,String searchType,Integer startIndex,Integer maxIndex)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCadre.firstname,model.mobileNo,model.currentStatus");
		str.append(" ,constituency.name");	
		str.append(" ,tehsil.tehsilName");
		str.append(" ,panc.panchayatName");
		str.append(" ,localElectionBody.name");
		str.append(" from CadreIvrResponse model left join model.userAddress.panchayat panc ");
		str.append(" left join model.userAddress.tehsil tehsil ");
	    str.append(" left join model.userAddress.constituency constituency ");
		str.append(" left join model.userAddress.localElectionBody localElectionBody ");
		str.append(" where");
		if(date != null)
			str.append(" date(model.date) =:date");
		if(Id != null)
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId =:Id and model.isDeleted = 'N' ");
		if(searchType.equalsIgnoreCase(IConstants.Received))
		 str.append(" and model.responseKey = 1");	
		else if(searchType.equalsIgnoreCase(IConstants.NotReceived))
			 str.append(" and model.responseKey = 2");	
		else if(searchType.equalsIgnoreCase(IConstants.NotRegistered))
			 str.append(" and model.responseKey = 3");	
		else if(searchType.equalsIgnoreCase(IConstants.Response))
			 str.append(" and model.responseKey in (1,2,3)");
		Query query = getSession().createQuery(str.toString());
		if(date != null)
			query.setDate("date", date);
		else if(Id != null)
			query.setParameter("Id", Id);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	public  Long  getTotalIvrCount()
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreIvrResponseId) from CadreIvrResponse model where model.isDeleted = 'N' ");
		
		Query query = getSession().createQuery(str.toString());
		return (Long) query.uniqueResult();
	}
	public List<Object[]> getIvrCountByDate(Date date)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreIvrResponseId),model.userAddress.district.districtId,model.responseKey from CadreIvrResponse model where model.isDeleted = 'N' ");
		if(date != null)
		str.append(" and date(model.startTime) =:date");
		str.append(" group by model.userAddress.district.districtId,model.responseKey");
		Query query = getSession().createQuery(str.toString());
		if(date != null)
			query.setDate("date", date);
		return query.list();
	}
}

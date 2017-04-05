package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomReportLocationDAO;
import com.itgrids.partyanalyst.model.CustomReportLocation;

public class CustomReportLocationDAO extends GenericDaoHibernate<CustomReportLocation, Long> implements ICustomReportLocationDAO{
	public CustomReportLocationDAO() {
		super(CustomReportLocation.class);
	}
		
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocationDetails(Long programId){
		Query query =getSession().createQuery("select crl.customReportId," +
			"crl.locationScopeId," +
			" crl.locationScope.scope,crl.locationValue,crl.customReportLocationId " +
			" from CustomReportLocation crl where crl.customReport.customReportProgramId = :programId and crl.isDeleted='N'");
		query.setParameter("programId", programId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocationDetailsForReportId(Long programId,String type){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.customReportId," +
			"model.locationScopeId," +
			" model.locationScope.scope,model.locationValue,model.customReportLocationId " +
			" from CustomReportLocation model where model.customReport.customReportProgramId = :programId and model.isDeleted='N'");
		if(type != null && !type.trim().isEmpty()){
			queryStr.append(" and model.customReport.isSubmitted =:type ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		  query.setParameter("programId", programId);
		 if(type != null && !type.trim().isEmpty()){
			query.setParameter("type", type);
		}
		return query.list();
	}
}

package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomReportObserverDAO;
import com.itgrids.partyanalyst.model.CustomReportObserver;
import com.itgrids.partyanalyst.model.CustomReportProgram;

public class CustomReportObserverDAO extends GenericDaoHibernate<CustomReportObserver, Long> implements ICustomReportObserverDAO {

	public CustomReportObserverDAO(){
		super(CustomReportObserver.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getObserverDetails(Long programId){
		Query query = getSession().createQuery(" select cro.customReportId,cro.tdpCadre.tdpCadreId,cro.tdpCadre.firstname " +
				" from CustomReportObserver cro " +
				" where cro.customReport.customReportProgramId = :programId and cro.isDeleted='N'");
	   query.setParameter("programId", programId);
	   return query.list();
	}
	
	public List<Object[]> getObserversForAReport(Long reportId){
		Query query = getSession().createQuery(" select cro.tdpCadre.tdpCadreId,cro.tdpCadre.memberShipNo,cro.tdpCadre.firstname,cro.tdpCadre.image,voter.voterIDCardNo,cro.tdpCadre.mobileNo  "
				+ " from CustomReportObserver cro"
				+ " left join cro.tdpCadre.voter voter "
				+ " where cro.customReportId=:reportId and cro.isDeleted='N' ");
		
		query.setParameter("reportId", reportId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getObserverDetailsForReportId(Long programId,String type){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.customReportId,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname " +
				" from CustomReportObserver model " +
				" where model.customReport.customReportProgramId = :programId and model.isDeleted='N'");
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

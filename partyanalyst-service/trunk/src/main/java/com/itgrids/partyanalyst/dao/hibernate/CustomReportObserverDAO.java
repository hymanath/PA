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
}

package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IImportantLeadersDAO;
import com.itgrids.partyanalyst.model.ImportantLeaders;

public class ImportantLeadersDAO extends GenericDaoHibernate<ImportantLeaders, Long> implements IImportantLeadersDAO{

	public ImportantLeadersDAO() {
		super(ImportantLeaders.class);
		// TODO Auto-generated constructor stub
	}

	public List<Object[]> getImportantLeadersInfoByLocation(Long locationId,String searchType){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.tdpCadre.tdpCadreId," +
					" model.tdpCadre.firstname," +
					" model.importantLeadersType.position," +
					" model.importantLeadersLevel.importantLeadersLevelId," +
					" model.importantLeadersLevel.scope," +
					" model.tdpCadre.mobileNo," +
					" model.constituencyId" +
					" from ImportantLeaders model");
		if(searchType.equalsIgnoreCase("mandal"))
			sb.append(" where model.tdpCadre.userAddress.tehsil.tehsilId = :locationId");
		else if(searchType.equalsIgnoreCase("leb"))
			sb.append(" where model.tdpCadre.userAddress.localElectionBody.localElectionBodyId = :locationId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("locationId", locationId);
		return query.list();
	}
	
	public List<Object[]> getImportantLeadersDesignationByCadreList(List<Long> tdpCadreIds){
		Query query = getSession().createQuery("select model.tdpCadre.tdpCadreId," +
												" model.importantLeadersType.importantLeadersTypeId," +
												" model.importantLeadersType.position," +
												" model.importantLeadersLevel.importantLeadersLevelId," +
												" model.importantLeadersLevel.scope," +
												" model.constituencyId," +
												" model.fromDate," +
												" model.toDate" +
												" from ImportantLeaders model" +
												" where model.tdpCadre.tdpCadreId in (:tdpCadreIds)");
		query.setParameterList("tdpCadreIds", tdpCadreIds);
		
		return query.list();
	}
}

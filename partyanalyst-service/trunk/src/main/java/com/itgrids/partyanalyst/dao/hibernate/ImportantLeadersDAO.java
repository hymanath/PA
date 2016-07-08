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
		sb.append("select model.importantLeadersId," +
					" model.leaderName," +
					" model.importantLeadersType.position," +
					" model.locationScope.scope," +
					" model.mobileNo" +
					" from ImportantLeaders model");
		if(searchType.equalsIgnoreCase("mandal"))
			sb.append(" where model.tdpCadre.userAddress.tehsil.tehsilId = :locationId");
		else if(searchType.equalsIgnoreCase("leb"))
			sb.append(" where model.tdpCadre.userAddress.localElectionBody.localElectionBodyId = :locationId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("locationId", locationId);
		return query.list();
	}
}

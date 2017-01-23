package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityDocumentDAO;
import com.itgrids.partyanalyst.model.ActivityDocument;

public class ActivityDocumentDAO extends GenericDaoHibernate<ActivityDocument, Long> implements IActivityDocumentDAO{

	public ActivityDocumentDAO() {
		super(ActivityDocument.class);
		
	}
	
	public List<Object[]> getImagesCoveredAndTotalImagesForConstituencies(Long districtId,Long activityScopeId,String  searchType,String  type){
		
			
			StringBuilder queryStr = new StringBuilder();
			
			queryStr.append("select ");
			
			if(searchType != null && searchType.equalsIgnoreCase("constituency"))
				queryStr.append(" model.userAddress.constituency.constituencyId," );
			
			
			if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("panchayat")){
				queryStr.append(" count(distinct model.userAddress.panchayat.panchayatId), ");
			}else if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("ward")){
				queryStr.append("  count(distinct model.userAddress.ward.constituencyId), ");
			}else if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("mandal")){
				queryStr.append("  count( distinct model.userAddress.tehsil.tehsilId), ");
			}else if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("town")){
				queryStr.append("  count(distinct model.userAddress.localElectionBody.localElectionBodyId), ");
			}else if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("constituency")){
				queryStr.append("  count(distinct model.userAddress.constituency.constituencyId), ");
			}
			
			queryStr.append(" count(model1.activityDocumentId) " +
					" from ActivityDocument model1,ActivityInfoDocument model where model1.activityDocumentId = model.activityDocument.activityDocumentId   " );
			
			
			queryStr.append(" and model1.activityScope.activityScopeId =:activityScopeId  ");
			
			if(districtId != null && districtId.longValue() > 0l)
				queryStr.append("  and model.userAddress.district.districtId = :districtId ");
			
			
			queryStr.append(" group by model.userAddress.constituency.constituencyId ");
			/*if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("panchayat")){
				queryStr.append(" group by model.userAddress.constituency.constituencyId,model.userAddress.panchayat.panchayatId ");
			}else if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("ward")){
				queryStr.append(" group by model.userAddress.constituency.constituencyId,model.userAddress.ward.constituencyId ");
			}else if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("mandal")){
				queryStr.append(" group by model.userAddress.constituency.constituencyId,model.userAddress.tehsil.tehsilId ");
			}else if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("town")){
				queryStr.append(" group by model.userAddress.constituency.constituencyId,model.userAddress.localElectionBody.localElectionBodyId ");
			}else if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("constituency")){
				queryStr.append(" group by model.userAddress.constituency.constituencyId ");
			}*/
			
			Query query = getSession().createQuery(queryStr.toString());
			
			if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId",districtId );
			
			query.setParameter("activityScopeId",activityScopeId );
			
			return query.list();
		
		
	}

}

package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityDocumentDAO;
import com.itgrids.partyanalyst.model.ActivityDocument;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActivityDocumentDAO extends GenericDaoHibernate<ActivityDocument, Long> implements IActivityDocumentDAO{

	public ActivityDocumentDAO() {
		super(ActivityDocument.class);
		
	}
	
	public List<Object[]> getImagesCoveredAndTotalImagesForConstituencies(List<Long> districtsList,List<Long> activityScopeIdsLis,String  searchType,String  type,Long stateId){
		
			
			StringBuilder queryStr = new StringBuilder();
			
			queryStr.append("select ");
			
			if(type != null && type.equalsIgnoreCase("constituency"))
				queryStr.append(" model.userAddress.constituency.constituencyId,count(distinct model.userAddress.constituency.constituencyId)," );
			else if(type != null && type.equalsIgnoreCase("panchayat") || searchType != null && searchType.equalsIgnoreCase("onlyvillage"))
				queryStr.append("  model.userAddress.panchayat.panchayatId, count(distinct model.userAddress.panchayat.panchayatId),");
			else if(type != null && type.equalsIgnoreCase("ward"))
				queryStr.append("  model.userAddress.ward.constituencyId,count(distinct model.userAddress.ward.constituencyId), ");
			else if( type != null && type.equalsIgnoreCase("mandal"))
				queryStr.append("  model.userAddress.tehsil.tehsilId,count( distinct model.userAddress.tehsil.tehsilId), ");
			else if( type != null && type.equalsIgnoreCase("town"))
				queryStr.append("  model.userAddress.constituency.localElectionBody.localElectionBodyId,count(distinct model.userAddress.localElectionBody.localElectionBodyId), ");
			else if(type != null && type.equalsIgnoreCase("district"))
				queryStr.append(" model.userAddress.district.districtId,count(distinct model.userAddress.district.districtId)," );
			else if(type != null && type.equalsIgnoreCase("state"))
				queryStr.append(" model.userAddress.state.stateId,count(distinct model.userAddress.state.stateId)," );
			
			queryStr.append(" count(model1.activityDocumentId) " );
			queryStr.append(" ,model1.activityScope.activityScopeId ");
			queryStr.append("  from ActivityDocument model1,ActivityInfoDocument model where model.isDeleted='N'  and " +
					" model1.activityDocumentId = model.activityDocument.activityDocumentId   " );
			
			queryStr.append(" and model1.activityScope.activityScopeId  in (:activityScopeIdsLis)  ");
			
				if(districtsList != null && districtsList.size() > 0l){
				
					if(type != null && type.equalsIgnoreCase("constituency"))
						queryStr.append("  and model.userAddress.district.districtId in  (:districtsList) ");
					else if(type != null && type.equalsIgnoreCase("panchayat"))
						queryStr.append("  and model.userAddress.tehsil.tehsilId in  (:districtsList) ");
					else if(type != null && type.equalsIgnoreCase("ward"))
						queryStr.append("  and model.userAddress.constituency.localElectionBody.localElectionBodyId in  (:districtsList) ");
					else if( type != null && type.equalsIgnoreCase("mandal")  || type.equalsIgnoreCase("town"))
						queryStr.append("  and model.userAddress.constituency.constituencyId in  (:districtsList) ");
					else if(searchType != null && searchType.equalsIgnoreCase("onlyvillage"))
						queryStr.append("  and model.userAddress.panchayat.panchayatId in  (:districtsList) ");
				}else{
					if(stateId != null && stateId.longValue() == 1l){
						queryStr.append("  and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
					}else if(stateId != null && stateId.longValue() == 2l){
						queryStr.append("  and model.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
					}else if(stateId != null && stateId.longValue() == 0l){
						queryStr.append("  and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
					}
				
			}
			
			queryStr.append(" group by model1.activityScope.activityScopeId ");
			
			if(type != null && type.equalsIgnoreCase("constituency"))
				queryStr.append(" ,model.userAddress.constituency.constituencyId " );
			else if(type != null && type.equalsIgnoreCase("panchayat"))
				queryStr.append("  ,model.userAddress.panchayat.panchayatId ");
			else if(type != null && type.equalsIgnoreCase("ward"))
				queryStr.append("  ,model.userAddress.ward.constituencyId  ");
			else if( type != null && type.equalsIgnoreCase("mandal"))
				queryStr.append("  ,model.userAddress.tehsil.tehsilId ");
			else if( type != null && type.equalsIgnoreCase("town"))
				queryStr.append(" , model.userAddress.constituency.localElectionBody.localElectionBodyId ");
			else if(type != null && type.equalsIgnoreCase("district"))
				queryStr.append(", model.userAddress.district.districtId " );
			else if(type != null && type.equalsIgnoreCase("state"))
				queryStr.append(", model.userAddress.state.stateId " );
			
			Query query = getSession().createQuery(queryStr.toString());
			
			if(districtsList != null && districtsList.size() > 0l){
				if(districtsList.get(0).longValue()>0L)
					query.setParameterList("districtsList",districtsList );
			}
			if(activityScopeIdsLis != null && activityScopeIdsLis.size()>0)
				query.setParameterList("activityScopeIdsLis",activityScopeIdsLis );
			
			return query.list();
		
		
	}

}

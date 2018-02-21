package com.itgrids.dao.impl;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ITehsilConstituencyDAO;
import com.itgrids.model.TehsilConstituency;
import com.itgrids.utils.IConstants;

@Repository
public class TehsilConstituencyDAO  extends GenericDaoHibernate<TehsilConstituency, Long> implements ITehsilConstituencyDAO{
	
	@Autowired	
	SessionFactory sessionFactory; 
	
	public TehsilConstituencyDAO() {
		super(TehsilConstituency.class);
	}
	
	public List<Object[]> getNonFundedLocations(Set<Long> keysList,Long searchLevelId,Long searchScopeId,List<Long> searchScopeValuesList){
		
		StringBuilder  sb = new StringBuilder();
		
		sb.append(" select state.stateId,state.stateName,district.districtId,district.districtName," +
				"parliamentAssm.constituencyId,parliamentAssm.name," +
				"constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName,panchayat.panchayatId,panchayat.panchayatName "
				+ " from " +
				"   LocationAddress modal " +
				" 	left join modal.tehsil tehsil " + 
				" 	left join modal.constituency constituency " +
				" 	left join modal.parliament parliamentAssm " +
				" 	left join constituency.district district " +
				" 	left join district.state state  " +
				" 	left join modal.panchayat panchayat  " +
				" 	where  state.stateId is not null ");
		
		if(keysList != null && keysList.size() > 0){
			if(searchLevelId != null && searchLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
				sb.append(" and state.stateId not in (:keysList) ");
			}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" and district.districtId  not in (:keysList) ");
			}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" and constituency.constituencyId not in (:keysList) ");
			}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" and parliamentAssm.constituencyId not in (:keysList) ");
			}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" and tehsil.tehsilId not in (:keysList)  ");
			}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				sb.append(" and panchayat.panchayatId not in (:keysList)  ");
			}
		}
		
		if(searchScopeValuesList != null && searchScopeValuesList.size()>0){
			if(searchScopeId != null && searchScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
				sb.append(" and state.stateId in (:searchScopeValuesList) ");
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" and district.districtId  in (:searchScopeValuesList) ");
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" and constituency.constituencyId in (:searchScopeValuesList) ");
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" and parliamentAssm.constituencyId in (:searchScopeValuesList) ");
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" and tehsil.tehsilId in (:searchScopeValuesList)  ");
			}
		}
		
		
		if(searchLevelId != null && searchLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			sb.append(" group by state.stateId  ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append("  group by district.districtId ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append("  group by constituency.constituencyId ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append("  group by parliamentAssm.constituencyId  ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" group by  tehsil.tehsilId  ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		if(keysList != null && keysList.size() > 0){
			query.setParameterList("keysList", keysList);
		}
		
		if(searchScopeValuesList != null && searchScopeValuesList.size() > 0){
			query.setParameterList("searchScopeValuesList", searchScopeValuesList);
		}
		return query.list(); 
	      
	}

	public List<Object[]> getTehsilIdAndName(Long constId){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.tehsil.tehsilId,model.tehsil.tehsilName from TehsilConstituency model ");
	    		if(constId != null && constId.longValue()>0){
	    	    	sb.append(" where model.constituency.constituencyId =:constId ");
	    	    }
	    	    Query query = getSession().createQuery(sb.toString());
	    	    
	    	   if(constId != null && constId.longValue()>0){
	    		   query.setParameter("constId", constId);
	    	   }
	    	   
	    	   return query.list();
	}

	public List<Object[]> getConstituencyOfTehsil(Long tehsilId){
		//0-tehsilId,1-tehsilName,2-contId,3-contName
		Query query = getSession().createQuery(" select model.tehsilId,model.tehsil.tehsilName,model.constituencyId,model.constituency.name "
				+ " from TehsilConstituency model "
				+ " where model.tehsilId=:tehsilId ");
		query.setParameter("tehsilId", tehsilId);
		return query.list();
	}
}

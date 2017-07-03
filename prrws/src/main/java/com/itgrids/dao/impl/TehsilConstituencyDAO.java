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
	
	public List<Object[]> getNonFundedLocations(Set<Long> keysList,Long searchLevelId){
		
		StringBuilder  sb = new StringBuilder();
		
		sb.append(" select state.stateId,state.stateName,district.districtId,district.districtName," +
				"parliamentAssm.assemblyId,parliamentAssm.parliament.name," +
				"constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName from ParliamentAssembly parliamentAssm," +
				"TehsilConstituency modal " +
				" left join modal.tehsil tehsil " +
				" left join modal.constituency constituency" +
				" left join constituency.district district" +
				" left join district.state state  " +
				"  where  parliamentAssm.assemblyId = modal.constituency.constituencyId ");
		
		if(keysList != null && keysList.size() > 0){
			if(searchLevelId != null && searchLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
				sb.append(" and state.stateId not in (:keysList) ");
			}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" and district.districtId  not in (:keysList) ");
			}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" and constituency.constituencyId not in (:keysList) ");
			}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" and parliamentAssm.assemblyId not in (:keysList) ");
			}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" and tehsil.tehsilId not in (:keysList)  ");
			}
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		if(keysList != null && keysList.size() > 0){
			query.setParameterList("keysList", keysList);
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

}

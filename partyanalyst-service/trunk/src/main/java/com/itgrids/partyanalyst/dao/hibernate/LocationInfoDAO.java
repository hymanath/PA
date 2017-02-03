package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ILocationInfoDAO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.LocationInfo;

public class LocationInfoDAO extends GenericDaoHibernate<LocationInfo, Long> implements ILocationInfoDAO{

	public LocationInfoDAO() {
		super(LocationInfo.class);
	}
    
	public List<Object[]> getLocationWiseTotalCounts(List<Long> levelIds,List<Long> locationIds,Long scopeId){
	
		Query query=getSession().createQuery(" " +
				  " select   model.scopeValue,sum(model.count) ,model.levelId,model.regionScopes.scope " +
				  " from     LocationInfo model " +
				  " where    model.scopeId =:scopeId and model.scopeValue in (:locationIds) and model.levelId in (:levelIds)" +
				  " group by model.scopeValue order by model.levelId ");
		query.setParameterList("locationIds",locationIds);
		query.setParameterList("levelIds",levelIds);
		query.setParameter("scopeId",scopeId);
		return query.list();
	}
	
	public List<Object[]> areaCountListByAreaIdsOnScope(SearchAttributeVO searchAttributeVO,Long stateId)
	{
		StringBuilder queryStr = new StringBuilder();
		if(searchAttributeVO.getScopeId() == 2L && stateId != null && stateId.longValue()>0L)
			searchAttributeVO.setScopeValue(stateId);
		
		queryStr.append(" select distinct model.levelId, model.count from LocationInfo model where model.scopeId =:scopeId and model.scopeValue =:scopeValue ");
		if(searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
			queryStr.append(" and model.levelId in (:requiredAreasIds) ");
		queryStr.append(" order by model.levelId ");
		Query query = getSession().createQuery(queryStr.toString());
		
		if(searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
			query.setParameterList("requiredAreasIds", searchAttributeVO.getLocationTypeIdsList());
		query.setParameter("scopeId", searchAttributeVO.getScopeId());
		query.setParameter("scopeValue", searchAttributeVO.getScopeValue());
		
		return query.list();
	}
	
	
	public List<Object[]> areaCountDetailsListByAreaIdsOnScope(SearchAttributeVO searchAttributeVO,Long stateId,Long publicationDateId)
	{
		StringBuilder queryStr = new StringBuilder();
		if(searchAttributeVO.getScopeId() == 2L && stateId != null && stateId.longValue()>0L)
			searchAttributeVO.setScopeValue(stateId);
		
		queryStr.append(" select distinct model.levelId, model.count,model.scopeValue from LocationInfo model where model.scopeId =:scopeId ");
		if(searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
			queryStr.append(" and model.levelId in (:requiredAreasIds)" );
		if(publicationDateId != null && publicationDateId.longValue()>0)
			queryStr.append(" and model.publicationDateId =:publicationDateId ");
		queryStr.append(" order by model.levelId ");
		Query query = getSession().createQuery(queryStr.toString());
		
		if(searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
			query.setParameterList("requiredAreasIds", searchAttributeVO.getLocationTypeIdsList());
		query.setParameter("scopeId", searchAttributeVO.getScopeId());
		if(publicationDateId != null && publicationDateId.longValue()>0L)
		query.setParameter("publicationDateId", publicationDateId);
		//query.setParameter("scopeValue", searchAttributeVO.getScopeValue());
		
		return query.list();
	}
	
	public Long getTotalCountByScope(Long levelId,Long scopeId,Long scopeValue,Long publicationDateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select sum(model.count)" +
					" from LocationInfo model" +
					" where model.scopeId = :scopeId" +
					" and model.scopeValue = :scopeValue "); 
		if(publicationDateId != null && publicationDateId.longValue()>0)
			sb.append(" and model.publicationDateId =:publicationDateId ");
		if(levelId != null && levelId > 0l){
			if(levelId == 1l)
				sb.append(" and model.levelId in (6,8)");
			else if(levelId == 2l)
				sb.append(" and model.levelId in (5,7)");
			else if(levelId == 3l)
				sb.append(" and model.levelId in (3)");
			else if(levelId == 4l)
				sb.append(" and model.levelId in (2)");
			else if(levelId == 5l)
				sb.append(" and model.levelId in (4)");
		}
			
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("scopeId", scopeId);
		query.setParameter("scopeValue", scopeValue);
		if(publicationDateId != null && publicationDateId.longValue()>0)
		query.setParameter("publicationDateId", publicationDateId);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getDistrictWiseTotalCountsByLevelId(Long levelId,Long publicationDateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select sum(model.count)" +
					" from LocationInfo model" +
					" where model.scopeId = 3");
		if(publicationDateId != null && publicationDateId.longValue()>0)
			sb.append(" and model.publicationDateId =:publicationDateId ");
		if(levelId != null && levelId > 0l){
			if(levelId == 1l)
				sb.append(" and model.levelId in (6,8)");
			else if(levelId == 2l)
				sb.append(" and model.levelId in (5,7)");
			else if(levelId == 3l)
				sb.append(" and model.levelId in (3)");
			else if(levelId == 4l)
				sb.append(" and model.levelId in (2)");
			else if(levelId == 5l)
				sb.append(" and model.levelId in (1)");
		}
		
		Query query = getSession().createQuery(sb.toString());
	if(publicationDateId != null && publicationDateId.longValue()>0)
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	public List<Object[]> getTotalActivityLocationWise(List<Long> levelIds,Long scopeId,Set<Long> locationValues){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select model.scopeValue,sum(model.count) from LocationInfo model where model.levelId in (:levelIds) " +
		 		         " and model.scopeId=:scopeId and model.scopeValue in (:locationValues)  group by scopeValue ");
		 Query query = getSession().createQuery(queryStr.toString());
		 query.setParameterList("levelIds", levelIds);
		 query.setParameter("scopeId", scopeId);
		 query.setParameterList("locationValues", locationValues);
		 return query.list();
	}
	
}

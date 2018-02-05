package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.columns.enums.DistrictColumnNames;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.utils.IConstants;

public class DistrictDAO extends GenericDaoHibernate<District, Long> implements IDistrictDAO {
	
	public DistrictDAO() {
		super(District.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<District> findByProperty(DistrictColumnNames propertyName, Object value){
		return getHibernateTemplate().find("from District where " + propertyName + "=? order by districtName", value);
	}
	

	public List<District> findByDistrictName(Object districtName){
		return findByProperty(DistrictColumnNames.DISTRICT_NAME, districtName);
	}

	public List<District> findByDistrictCapital(Object districtCapital){
		return findByProperty(DistrictColumnNames.DISTRICT_CAPITAL, districtCapital);
	}

	public List<District> findByArea(Object area){
		return findByProperty(DistrictColumnNames.AREA, area);
	}

	public List<District> findByPopulation(Object population){
		return findByProperty(DistrictColumnNames.POPULATION, population);
	}

	public List<District> findByDistrictCode(Object districtCode){
		return findByProperty(DistrictColumnNames.DISTRICT_CODE, districtCode);
	}

	@SuppressWarnings("unchecked")
	public List<District> findByStateId(Long stateId){
		return getHibernateTemplate().find("from District model where model.state.stateId=? order by districtName", stateId);
	}

	@SuppressWarnings("unchecked")
	public List<District> getAllOrderByName() {
		return getHibernateTemplate().find("from District model order by districtName");
	}
	
	@SuppressWarnings("unchecked")
	public List getStateDistrictByDistrictID(Long districtID){
		return getHibernateTemplate().find("Select model.state.stateId, model.state.stateName, model.districtName from District model where model.districtId=? order by model.state.stateName",districtID);
		
	}

	@SuppressWarnings("unchecked")
	public List getDistrictNameByDistrictId(Long districtId) {
		return getHibernateTemplate().find("Select model.districtName,model.state.stateId from District model where model.districtId=? ", districtId);
	}
	
	@SuppressWarnings("unchecked")
	public List<District> getDistrictIDByStateIDAndDistrictName(Long stateID, String districtName){
		Object[] params = {stateID, districtName};
		return getHibernateTemplate().find("from District model where model.state.stateId=? and model.districtName=? ", params);
	}
	@SuppressWarnings("unchecked")
	public List getStateToDistrictByDistrict(String districtIDs){
		return getHibernateTemplate().find("select model.state.stateId, model.state.stateName, " +
				"model.districtId, model.districtName " +
				" from District model where model.districtId in("+districtIDs+") ");
	}
	
	@SuppressWarnings("unchecked")
	public List getDistrictIdAndNameByState(Long stateId){
		return getHibernateTemplate().find("select model.districtId,model.districtName from District model where model.state.stateId = ? order by model.districtName",stateId);
	}
	
	@SuppressWarnings("unchecked")
	public List getDistrictsForState(Long stateId){
		Long sid=1l;
		StringBuilder query = new StringBuilder();
		query.append("select model.districtId,model.districtName from District model where model.state.stateId = ? ");
		if(stateId.longValue() == 0L)
		{
			//query.append(" and model.districtId between 1 and 23 ");
			query.append(" and model.districtId in("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId.longValue()==1L){
			query.append(" and model.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		}
		else if(stateId.longValue()==36L){
			query.append(" and model.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
		}else{
			return null;    
		}
		query.append(" order by model.districtName");     
		return getHibernateTemplate().find(query.toString(),sid);
	}
	

	@SuppressWarnings("unchecked")
	public List<Long> getAllDistrictByStateIds(List<Long> stateIds) {	
		StringBuilder query = new StringBuilder();
		query.append("select model.districtId from District model where model.state.stateId in ( :stateIds)");		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("stateIds", stateIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getDistrictIdByStateIdAndDistrictName(Long stateID,
			String districtName) {
		Object[] params = {stateID, districtName};
		return getHibernateTemplate().find("select model.districtId from District model where model.state.stateId = ? and model.districtName = ?", params);
	}
	
	public Object getDistrictNameById(Long districtId)
	{
		Query query = getSession().createQuery("select model.districtName from District model where model.districtId = ?");
		query.setParameter(0,districtId);
		return query.uniqueResult();
	}
	
public List<Object[]> getAllDistrictDetails(Long stateId){
		
		Query query = getSession().createQuery("select model.districtId , model.districtName from District model where model.state.stateId=? order by model.districtName");
		query.setParameter(0,stateId );
		return query.list();
		
		
	}

public List<Object[]> getAllDistrictInfoDetails(){
	
	Query query = getSession().createQuery("select model.districtId , model.districtName from District model");
	return query.list();
	
	
}
/**
 * This method is used for getting district details, only nominated districts 
 * based on state,party and electionIds.
 * @author srishailam
 * @param Long stateId
 * @param Long partyId
 * @param List<Long> electionIds
 * @return List<Object[]>
 * @date 20th April,2013
 */
@SuppressWarnings("unchecked")
public List<Object[]> findByPartyNominationDetails(Long stateId,Long partyId,List electionIds){
	StringBuilder query = new StringBuilder();
	query.append("select model.districtId , model.districtName from District model where model.state.stateId = :stateId ");
	query.append(" and model.districtId in( select distinct model1.district.districtId from PartyElectionDistrictResult model1 ");
	query.append(" where model1.party.partyId = :partyId and model1.election.electionId in (:electionIds) ) order by model.districtName");
	Query queryObject = getSession().createQuery(query.toString());
	queryObject.setParameter("stateId", stateId);
	queryObject.setParameter("partyId", partyId);
	queryObject.setParameterList("electionIds", electionIds);
	return queryObject.list();
}

@SuppressWarnings("unchecked")
public List<Object[]> getDistrictList()
{
	return getHibernateTemplate().find("select model.districtId,model.districtName,model.state.stateId from District model ");
}

@SuppressWarnings("unchecked")
public List<Object[]> getDistrictIdAndNameByConstituency(Long constituencyId){
	return getHibernateTemplate().find("select model.district.districtId,model.district.districtName from Constituency model where model.constituencyId = ?",constituencyId);
}


@SuppressWarnings("unchecked")
public List getDistrictIdAndNameByStateForRegion(Long stateId,String region){
	StringBuilder str = new StringBuilder();
	str.append("select distinct model.districtId,model.districtName from District model where model.state.stateId = :stateId ");
	if(region.equalsIgnoreCase("Telangana"))
	str.append("and model.districtId between 1 and 10 order by model.districtName");
	else if(region.equalsIgnoreCase("Andhra Pradesh"))
	{
		str.append("and model.districtId between 11 and 23 order by model.districtName");	
	}
	else
	{
		str.append("and model.districtId between 1 and 23 order by model.districtName");	
	}
	Query query = getSession().createQuery(str.toString());
	query.setParameter("stateId", stateId);
	return query.list();
	
	
}

@SuppressWarnings("unchecked")
public List<Object[]> getDistrictIdAndNameByStateForStateTypeId(Long stateId,Long stateTypeId){
	StringBuilder str = new StringBuilder();
	str.append("select distinct model.districtId,model.districtName from District model where model.state.stateId = :stateId ");
	if(stateTypeId.longValue() == 0L)
	{
		//str.append(" and model.districtId between 1 and 23 ");
		str.append(" and model.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+"+IConstants.TS_NEW_DISTRICTS_IDS_LIST+"+") ");
	}
	else if(stateTypeId.longValue() == 1L)
	{
		//str.append(" and model.districtId between 11 and 23 ");
		str.append(" and model.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
	}
	else if(stateTypeId.longValue() == 2L || stateTypeId.longValue() == 36L)
	{
		//str.append(" and model.districtId between 1 and 10 ");
		str.append(" and model.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
	}
	str.append(" order by model.districtName asc"); 
	
	Query query = getSession().createQuery(str.toString());
	query.setParameter("stateId", stateId);
	return query.list();
}

public List<Object[]> getDistrictDetailsByDistrictIds(List<Long> districtIds)
{
	Query query = getSession().createQuery("select D.districtId,D.districtName from District D where " +
			"D.districtId in (:districtIds)");
	query.setParameterList("districtIds", districtIds);
	return query.list();
}

	public List<Long> getDistrictsInAState(Long stateId) {
	    StringBuilder str = new StringBuilder();
	      str.append("select distinct model.districtId from District model where model.state.stateId =1 ");
			if(stateId.longValue() == 1){
				str.append(" and model.districtId > 10 ");
			}else{
				str.append(" and model.districtId < 11 ");
			}
			Query query = getSession().createQuery(str.toString());
			return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Long> getDistrictIdsByConstituency(List<Long> constituencyIds){
		Query query = getSession().createQuery("select distinct model.district.districtId from Constituency model where model.constituencyId in(:constituencyIds)");
		
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
		
	}

	public List<Object[]> getDistrictDetailsById(Long districtId)
	{
		Query query = getSession().createQuery("select model.districtId, model.districtName from District model where model.districtId = ?");
		query.setParameter(0,districtId);
		return query.list();
	}
    public List<Object[]> getAllDistrictDetailsForAState(Long stateId){
		
		Query query = getSession().createQuery("select model.districtId , model.districtName from District model where model.state.stateId=? order by  model.districtName");
		query.setParameter(0,stateId );
		return query.list();
		
	}
    
    @SuppressWarnings("unchecked")
	public List<Object[]> getNewDistrictForState(Long stateId){
		
		if(stateId != null && stateId.longValue() == 2L) //AP stateId = 1, TS StateId = 2 , ALL stateId = 0;
		{
			stateId = 36L; //Actual TG stateId
		}
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.districtId,model.districtName from District model where model.districtId > 516 and ");
		if(stateId.longValue() >0L){
			queryStr.append(" model.state.stateId =:stateId ");
		}
		else
		{
			queryStr.append(" model.state.stateId in (1,36) ");
		}
		queryStr.append(" order by model.districtName asc ");

		Query query = getSession().createQuery(queryStr.toString());
		
		if(stateId.longValue() >0L){
			query.setParameter("stateId", stateId);
		}
		return query.list();
	}
	
	public List<Object[]> getDistrictNamesIds(List<Long> districtIds){
		
		StringBuilder queryStr = new StringBuilder();
		if(districtIds != null && districtIds.size()>0)
		{
			queryStr.append(" select model.districtId,model.districtName from District model where model.state.stateId in (1,36) " +
					" and model.districtId in (:districtIds) order by model.districtName  ");
		}
		else
		{
			queryStr.append(" select model.districtId,model.districtName from District model where model.state.stateId in (1,36)" +
					" order by model.districtName  ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if(districtIds != null && districtIds.size()>0)
		{
			query.setParameterList("districtIds", districtIds);
		}
		return query.list();
	}
	

	public String getDitrictNmaeById(Long districtID){
		Query query = getSession().createQuery("Select distinct model.districtName from District model where model.districtId=:districtID order by model.state.stateName");
		query.setParameter("districtID", districtID);
		return (String) query.uniqueResult();
		
	}
	
	public List<Object[]> getDistrictsWithNewSplitted(Long stateId){
		StringBuilder str = new StringBuilder();
	    str.append("select distinct model.districtId, model.districtName " +
	    		" from District model where " );
	    if(stateId!=0){
	    	str.append(" model.state.stateId =:stateId");
	    }else{
	    	str.append(" model.state.stateId in(1,36)");
	    }
		if(stateId.longValue() == 1){
			str.append(" and model.districtId > 10 or model.districtId = 517");
		}else if(stateId.longValue()==36){
			str.append(" and model.districtId = 518 or model.districtId < 11");
		}
	     str.append(" order by  model.districtName ");
		  
		Query query = getSession().createQuery(str.toString());
		if(stateId!=0){
			query.setParameter("stateId", stateId);
		}
		return query.list();
	}
	
	public List<Object[]> getStatesForDistricts(List<Long> distIds){
		Query query = getSession().createQuery(" select model.state.stateId, model.state.stateName, model.districtId " +
				" from District model" +
				" where model.districtId in(:distIds)");
		query.setParameterList("distIds", distIds);
		return query.list();
	}
	
	public Long getDistrictByStateIdAndDistrictName(Long stateId,String districtName)
	{
		Query query = getSession().createQuery("Select model.districtId from District model where model.state.stateId = :stateId and model.districtName = :districtName");
		query.setParameter("stateId",stateId);
		query.setParameter("districtName",districtName);
		return (Long)query.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictsList(){
		
		Query query = getSession().createQuery(" select model.districtId,model.districtName from District model where model.state.stateId in (1,36) order by model.districtName ");
			
			return query.list();
		}
	
	
	
	  public List<Long> getLocalBodiesOfDistrict(List<Long> distrctIds){
		  
		  StringBuilder str = new StringBuilder();
		  
		  str.append(" select local_election_body_id from local_election_body where election_type_id in (:Town) ");
		  
		  if(distrctIds !=null && distrctIds.size()>0){
			  str.append(" and district_id in (:distrctIds) ");
		  }
		  
		  Query query = getSession().createSQLQuery(str.toString())
		  
		  .addScalar("local_election_body_id",Hibernate.LONG);	  
		  
		  if(distrctIds !=null && distrctIds.size()>0){
			  query.setParameterList("distrctIds", distrctIds); 
		  }
		  query.setParameter("Town", IConstants.TOWN_TYPE_IDS);
		  
		  return query.list();
	  }
	  public List<Object[]> getAllDistrictList(Long stateId){
		  Query query = getSession().createQuery("select model.districtId,model.districtName from District model where model.state.stateId = :stateId order by model.districtId");
		  query.setParameter("stateId", stateId);  
		  return query.list();
	  }
	  
	  
	  public List<Object[]> getDistrictNamesByIds(List<Long> districtIds){
		  Query query = getSession().createQuery(" select model.districtId,model.districtName from District model where model.districtId in (:districtIds) ");
		  query.setParameterList("districtIds", districtIds);
		  return query.list();  
	  }
	  public List<Object[]> getStateWiseDistrict(Long stateId){
		  Query query = getSession().createQuery("select model.districtId,model.districtName from District model where model.state.stateId = :stateId order by model.districtName");
		  query.setParameter("stateId", stateId);  
		  return query.list();
	  }
	 public List<Object[]> getDistrictListBystateId(Long stateId){
		    StringBuilder queryStr = new StringBuilder();
		     queryStr.append("select model.districtId,model.districtName from District model ");
		   if(stateId != null && stateId.longValue()==1l){
			 queryStr.append(" where model.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");   
		   }else{
		     queryStr.append(" where model.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");    
		   }
		  Query query = getSession().createQuery(queryStr.toString());
		  return query.list();
	 }
	 @SuppressWarnings("unchecked")
		public List<Object[]> getDistrictForState(Long stateId){
			
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select model.districtId,model.districtName from District model where ");
			if(stateId != null && stateId.longValue()==1l){
				queryStr.append(" model.districtId between 11 and 23 or model.districtId =517 ");
			}else if(stateId != null && stateId.longValue()==36l){
				queryStr.append(" model.districtId between 1 and 10 ");
			}
			/*if(stateId.longValue() >0L){
				queryStr.append(" and model.state.stateId =:stateId ");
			}
			else
			{
				queryStr.append(" and model.state.stateId in (1,36) ");
			}*/
			queryStr.append(" order by model.districtName asc ");

			Query query = getSession().createQuery(queryStr.toString());
			
			/*if(stateId.longValue() >0L){
				query.setParameter("stateId", stateId);
			}*/
			return query.list();
		}
		public List<Object[]> getSpecificDistrictIdAndName(List<Long> districtIds){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select model.districtId,model.districtName from District model where model.districtId in (:districtIds) ");
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameterList("districtIds", districtIds);
			return query.list();
		}
		
		public List<Object[]> getDistrictsWithNewDistricts(){
			StringBuilder str = new StringBuilder();
		    
			str.append("select distinct model.districtId," +
							" model.districtName" +
		    				" from District model" +
		    				" where model.districtId between 11 and 23 or model.districtId = 517");
			str.append(" order by  model.districtName ");
			  
			Query query = getSession().createQuery(str.toString());
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getDistrictForGrievanceRequest(Long stateId){
			
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select model.districtId,model.districtName from District model where model.state.stateId = :stateId");
			if(stateId != null && stateId.longValue()==1l){
				queryStr.append(" and model.districtId between 11 and 23");
			}else if(stateId != null && stateId.longValue()==36l){
				queryStr.append(" and model.districtId between 1 and 10 ");
			}
			/*if(stateId.longValue() >0L){
				queryStr.append(" and model.state.stateId =:stateId ");
			}
			else
			{
				queryStr.append(" and model.state.stateId in (1,36) ");
			}*/
			queryStr.append(" order by model.districtName asc ");

			Query query = getSession().createQuery(queryStr.toString());
			
			//if(stateId.longValue() >0L){
				query.setParameter("stateId", stateId);
			//}
			return query.list();
		}
		
		 public List<Object[]> getAllNewDistrictDetailsForAState(Long stateId, List<Long> districtIdsArr){
				
				Query query = getSession().createQuery("select model.districtId , model.districtName from District model where model.state.stateId=:stateId and " +
						" model.districtId in (:districtIdsArr) order by  model.districtName");
				query.setParameter("stateId",stateId );
				query.setParameterList("districtIdsArr", districtIdsArr);
				return query.list();
				
			}
		 
		 @SuppressWarnings("unchecked")
			public List<Object[]> getDistrictIdsByState(List<Long> stateId){
			 Query query = getSession().createQuery("select model.districtId,model.districtName from District model " +
						"where model.state.stateId in (:stateId) " +
						"and model.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") order by model.districtName");
			 query.setParameterList("stateId",stateId );
			 return query.list();
			}
			public List<Long> getAllDistrictIds(){
				 Query query = getSession().createQuery("select model.districtId from District model " +
							"where  " +
							" model.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") order by model.districtName");
				 //query.setParameterList("stateId",stateId );
				 return query.list();
				}
}

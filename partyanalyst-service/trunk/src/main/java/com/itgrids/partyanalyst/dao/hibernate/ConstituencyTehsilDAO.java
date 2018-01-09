package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.itgrids.partyanalyst.dao.IConstituencyTehsilDAO;
import com.itgrids.partyanalyst.model.ConstituencyTehsil;
import com.itgrids.partyanalyst.model.Tehsil;

public class ConstituencyTehsilDAO extends GenericDaoHibernate<ConstituencyTehsil, Long> implements IConstituencyTehsilDAO{

	public ConstituencyTehsilDAO() {
		super(ConstituencyTehsil.class);
}

	   public List<Long> getAllStateWiseTehsilIds(Long stateId){
		
		    StringBuilder queryStr = new StringBuilder();
		    queryStr.append(" select distinct model.tehsil.tehsilId from ConstituencyTehsil model where model.isDeleted='N' ");
		  
		    if(stateId != null && stateId.longValue() == 1l){
			  	 queryStr.append(" and model.constituency.district.districtId > 10 and model.constituency.state.stateId=1");
			}else if(stateId != null && stateId.longValue() == 36l){
			  	 queryStr.append(" and model.constituency.district.districtId < 11 "); 
			}else if(stateId != null && stateId.longValue() == 0l){
				 queryStr.append(" and model.constituency.district.districtId <= 23 ");
			}
		    Query query = getSession().createQuery(queryStr.toString());
		    return query.list();
	 }
	public List<Object[]> getTehsilDtlsStateWise(Long stateId,List<Long> mandalIds){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
			 		   " d.district_id as districtId," +
			 		   " d.district_name as districtName," +
			 		   " c.constituency_id as constituencyId," +
			 		   " c.name as name," +
			 		   " t.tehsil_id as tehsilId," +
			 		   " t.tehsil_name as tehsilName " +
			 		   " from constituency_tehsil ct," +
			 		   " constituency c," +
			 		   " tehsil t," +
			 		   " district d " +
			 		   " where ct.constituency_id=c.constituency_id and ct.tehsil_id=t.tehsil_id and c.district_id=d.district_id and ct.is_deleted='N' ");
			 if(stateId != null && stateId.longValue() == 1l){
				 queryStr.append(" and d.district_id > 10 and d.state_id=1 "); 
			 }else if(stateId != null && stateId.longValue() == 36l){
				queryStr.append(" and d.district_id < 11 "); 
			 }
			 if(mandalIds != null && mandalIds.size() > 0){
			   queryStr.append(" and ct.tehsil_id in (:mandalIds)");	
			 }
			 queryStr.append(" group by t.tehsil_id ");
		   Session session = getSession();
	       SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
	     	 sqlQuery.addScalar("districtId",Hibernate.LONG); 
	    	 sqlQuery.addScalar("districtName",Hibernate.STRING); 
	         sqlQuery.addScalar("constituencyId",Hibernate.LONG); 
		     sqlQuery.addScalar("name",Hibernate.STRING); 
		     sqlQuery.addScalar("tehsilId",Hibernate.LONG); 
			 sqlQuery.addScalar("tehsilName",Hibernate.STRING); 
		 if(mandalIds != null && mandalIds.size() >0){
		 	sqlQuery.setParameterList("mandalIds", mandalIds);	
		 }
		 return sqlQuery.list();
	}
	
	public List<Tehsil> getTehsilInfoOfConstuencyByTehsilName(Long constituencyId,String tehsilName){
		
		Query query = getSession().createQuery(" select distinct model.tehsil from ConstituencyTehsil model where model.isDeleted='N' " +
				" and model.constituency.constituencyId=:constituencyId" +
				" and model.tehsil.tehsilName =:tehsilName ");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("tehsilName", tehsilName);
		
		return query.list();
		
	}
	   
	   
}

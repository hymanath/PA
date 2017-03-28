package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreGenderInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreGenderInfo;

public class TdpCadreGenderInfoDAO extends GenericDaoHibernate<TdpCadreGenderInfo,Long> implements ITdpCadreGenderInfoDAO {
	
	public TdpCadreGenderInfoDAO() {
		
		super(TdpCadreGenderInfo.class);
	}
	
	public int pushCadreCountsLocationWiseByGender(){
		Query query = getSession().createSQLQuery("CALL tdp_cadre_gender_info(); ");
		return query.executeUpdate();  
	}
	
	public List<Object[]> stateWiseCadreGenderCounts(Long stateId){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select info.gender , sum(info.cadre2014) , sum(info.cadre2016) , sum(info.newCadre) , sum(info.renewalCadre) " +
				"   from   TdpCadreGenderInfo info ");
		if(stateId != null && stateId > 0l){
			sb.append(" where info.stateId = :stateId ");
		}
		sb.append(" group by info.gender");
		Query query = getSession().createQuery(sb.toString());
		
		if(stateId != null && stateId > 0l){
		  query.setParameter("stateId",stateId);
		}
		return query.list();
	}
	
	public List<Object[]> districtWiseCadreGenderCounts(Long stateId , Long districtId ){
		
		StringBuilder sb = new StringBuilder();
		
		
		sb.append(" select  info.gender , sum(info.cadre2014) , sum(info.cadre2016) , sum(info.newCadre) , sum(info.renewalCadre), " +//4
				"           info.district.districtId,info.district.districtName,info.state.stateId,info.state.stateName " +//8
				"   from   TdpCadreGenderInfo info " +
				"   where  info.locationScopeId = 4 ");
		
		if(stateId != null && stateId > 0l){
			sb.append(" and info.stateId = :stateId ");
		}
		if(districtId != null && districtId > 0l){
			sb.append(" and info.districtId = :districtId ");
		}
		sb.append(" group by info.districtId , info.gender " +
				"   order by info.state.stateName ,info.district.districtName ");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(stateId != null && stateId > 0l){
			  query.setParameter("stateId",stateId);
		}
		if(districtId != null && districtId > 0l){
			  query.setParameter("districtId",districtId);
		}
		return query.list();
	}
	
	public List<Object[]> constituencyWiseCadreGenderCounts(Long stateId , Long districtId ){
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("select info.gender , sum(info.cadre2014) , sum(info.cadre2016) , sum(info.newCadre) , sum(info.renewalCadre) ," +
    			"         C.constituencyId,C.name,info.districtId ,info.district.districtName" +
    			"  from   TdpCadreGenderInfo info , Constituency C " +
    			"  where  info.locationValue = C.constituencyId and " +
    			"         info.locationScopeId = 4 ");
    	if(stateId != null && stateId > 0l){
			sb.append(" and info.stateId = :stateId");
		}
    	if(districtId != null && districtId > 0l){
    		sb.append(" and info.districtId = :districtId");
    	}
    	sb.append(" group by C.constituencyId, info.gender ");
    	
    	Query query = getSession().createQuery(sb.toString());
    	if(stateId != null && stateId > 0l){
    		query.setParameter("stateId", stateId);
		}
    	if(districtId != null && districtId > 0l){
    		query.setParameter("districtId", districtId);
    	}
    	return query.list();
    }
	public List<Object[]> privilegedStateWiseCadreGenderCounts(List<Long> locationIdList, String accessType){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select info.gender , sum(info.cadre2014) , sum(info.cadre2016) , sum(info.newCadre) , sum(info.renewalCadre) " +
				"   from   TdpCadreGenderInfo info where ");
		if(accessType.equalsIgnoreCase("district")){
			sb.append(" info.districtId in (:locationIdList) ");
		}else{
			sb.append(" info.locationScopeId = 4 and info.locationValue in (:locationIdList) ");
		}
		sb.append(" group by info.gender");
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("locationIdList",locationIdList);
		
		return query.list();
	}
	public List<Object[]> privilegedDistrictWiseCadreGenderCounts(List<Long> districtIdList ){
		
		StringBuilder sb = new StringBuilder();
		
		
		sb.append(" select  info.gender , sum(info.cadre2014) , sum(info.cadre2016) , sum(info.newCadre) , sum(info.renewalCadre), " +//4
				"           info.district.districtId,info.district.districtName,info.state.stateId,info.state.stateName " +//8
				"   from   TdpCadreGenderInfo info " +
				"   where  ");
		
		
		if(districtIdList != null && districtIdList.size() > 0){
			sb.append(" info.districtId in (:districtIdList) ");
		}
		sb.append(" group by info.districtId , info.gender " +
				 "  order by info.state.stateName ,info.district.districtName ");
		
		Query query = getSession().createQuery(sb.toString());
		
		
		if(districtIdList != null && districtIdList.size() > 0){
			query.setParameterList("districtIdList",districtIdList);
		}
		return query.list();
	}
	public List<Object[]> privilegedConstituencyWiseCadreGenderCounts(List<Long> locationIdList ){
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("select info.gender , sum(info.cadre2014) , sum(info.cadre2016) , sum(info.newCadre) , sum(info.renewalCadre) ," +
    			"         C.constituencyId,C.name,info.districtId ,info.district.districtName" +
    			"  from   TdpCadreGenderInfo info , Constituency C " +
    			"  where  info.locationValue = C.constituencyId and " +
    			"         info.locationScopeId = 4 and info.locationValue in (:locationIdList) ");
    	
    	sb.append(" group by C.constituencyId, info.gender ");
    	
    	Query query = getSession().createQuery(sb.toString());
    	query.setParameterList("locationIdList",locationIdList);
    	return query.list();  
    }
	
	public int deleteAllRecords(List<Long> locationScopeIds){
    	
    	Query query = getSession().createSQLQuery(" delete from tdp_cadre_gender_info where location_scope_id in (:locationScopeIds) ");
    	query.setParameterList("locationScopeIds",locationScopeIds);
    	return query.executeUpdate();
    }
	
	 public int insertTdpCadreLocationInfoUpToLowLevelByGender(){
	    	
    	Query query = getSession().createSQLQuery("" +
    	"  INSERT INTO tdp_cadre_gender_info( gender,constituency_id,location_scope_id , location_value,cadre_2014," +
    	"                                  cadre_2016,new_cadre,new_cadre_percent,renewal_cadre,renewal_cadre_percent,inserted_time" +
    	"                                 ) " +
        "         SELECT TEMP.gender,TEMP.constituency_id,TEMP.location_scope_id,TEMP.location_value,TEMP.cadre_2014," +
        "                TEMP.cadre_2016,TEMP.new_cadre,TEMP.new_cadre_percent,TEMP.renewal_cadre,TEMP.renewal_cadre_percent,TEMP.inserted_time " +
        "         FROM   tdp_cadre_gender_info_temp TEMP " );
    	return query.executeUpdate();
	 }
}

package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreAgeInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreAgeInfo;

public class TdpCadreAgeInfoDAO extends GenericDaoHibernate<TdpCadreAgeInfo,Long> implements ITdpCadreAgeInfoDAO {
	
	public TdpCadreAgeInfoDAO() {
		super(TdpCadreAgeInfo.class);
	}
	
	public int pushCadreCountsLocationWiseByAge(){
		Query query = getSession().createSQLQuery("CALL tdp_cadre_age_info(); ");
		return query.executeUpdate();  
	}
	
	public int deleteAllRecords(List<Long> locationScopeIds){
    	
    	Query query = getSession().createSQLQuery(" delete from tdp_cadre_age_info where location_scope_id in (:locationScopeIds) ");
    	query.setParameterList("locationScopeIds",locationScopeIds);
    	return query.executeUpdate();
    }
	
	 public int insertTdpCadreLocationInfoUpToLowLevelByAgeRange(){
	    	
    	Query query = getSession().createSQLQuery("" +
    	"  INSERT INTO tdp_cadre_age_info( age_range_id,constituency_id,location_scope_id , location_value,cadre_2014," +
    	"                                  cadre_2016,new_cadre,new_cadre_percent,renewal_cadre,renewal_cadre_percent,inserted_time" +
    	"                                 ) " +
        "         SELECT TEMP.age_range_id,TEMP.constituency_id,TEMP.location_scope_id,TEMP.location_value,TEMP.cadre_2014," +
        "                TEMP.cadre_2016,TEMP.new_cadre,TEMP.new_cadre_percent,TEMP.renewal_cadre,TEMP.renewal_cadre_percent,TEMP.inserted_time " +
        "         FROM   tdp_cadre_age_info_temp TEMP " );
    	return query.executeUpdate();
	 }
	 
   public List<Object[]> getStateWiseAgeWiseCadreCounts(Long stateId){
		
		StringBuilder sb = new StringBuilder();
		sb.append("select info.ageRangeId , sum(info.cadre2014) , sum(info.cadre2016) , sum(info.newCadre) , sum(info.renewalCadre) " +
		         " from   TdpCadreAgeInfo info " +
		         " where  info.locationScopeId = 4 ");
		
		if(stateId != null ){
			sb.append(" and info.stateId = :stateId");
		}
		sb.append(" group by info.ageRangeId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("stateId", stateId);
		return query.list();
	}
    public List<Object[]> getDistrictwiseAgeWiseCadreCounts(Long stateId ){
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("select info.districtId ,info.district.districtName,info.stateId,info.state.stateName,info.ageRangeId, " +//4
    			"         sum(info.cadre2014) , sum(info.cadre2016) , sum(info.newCadre) , sum(info.renewalCadre) " +//8
    			"  from   TdpCadreAgeInfo info " +
    			"  where  info.locationScopeId = 4 ");
    	if(stateId != null && stateId > 0l){
			sb.append(" and info.stateId = :stateId");
		}
    	
    	sb.append(" group by info.districtId , info.ageRangeId " +
    			"   order by info.state.stateName , info.district.districtName ");
    	
    	Query query = getSession().createQuery(sb.toString());
    	if(stateId != null && stateId > 0l){
    		query.setParameter("stateId", stateId);
		}
    	
    	return query.list();
    }
    public List<Object[]> getConstituencyWiseAgeWiseCadreCounts(Long stateId , Long districtId ){
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("select C.constituencyId,C.name,info.districtId ,info.district.districtName," +
    			"         info.ageRangeId , sum(info.cadre2014) , sum(info.cadre2016) , sum(info.newCadre) , sum(info.renewalCadre) " +
    			"  from   TdpCadreAgeInfo info , Constituency C " +
    			"  where  info.locationValue = C.constituencyId and " +
    			"         info.locationScopeId = 4 ");
    	if(stateId != null && stateId > 0l){
			sb.append(" and info.stateId = :stateId");
		}
    	if(districtId != null && districtId > 0l){
    		sb.append(" and info.districtId = :districtId");
    	}
    	
    	sb.append(" group by C.constituencyId, info.ageRangeId ");
    	
    	Query query = getSession().createQuery(sb.toString());
    	if(stateId != null && stateId > 0l){
    		query.setParameter("stateId", stateId);
		}
    	if(districtId != null && districtId > 0l){
    		query.setParameter("districtId", districtId);
    	}
    	return query.list();
    }
    
   
	public List<Object[]> getStateWiseAgeWiseCadreCountsByConstituencies(Long stateId){
		
		StringBuilder sb = new StringBuilder();
		sb.append("select info.ageRangeId , sum(info.cadre2014) , sum(info.cadre2016) , sum(info.newCadre) , sum(info.renewalCadre) " +
		         " from   TdpCadreAgeInfo info , Constituency C " +
		         " where  info.locationValue = C.constituencyId and " +
		         "        info.locationScopeId = 4 ");
		
		if(stateId != null && stateId == 1L){
			sb.append(" and C.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId == 36L){
			sb.append(" and C.district.districtId between 1 and 10 ");
		}
		sb.append(" group by info.ageRangeId ");
		Query query = getSession().createQuery(sb.toString());
		
		return query.list();
	}
	public List<Object[]> privilegedGetStateWiseAgeWiseCadreCounts(List<Long> locationIdList, String accessType){
		
		StringBuilder sb = new StringBuilder();
		sb.append("select info.ageRangeId , sum(info.cadre2014) , sum(info.cadre2016) , sum(info.newCadre) , sum(info.renewalCadre) " +
		         " from   TdpCadreAgeInfo info " +
		         " where ");
		
		if(accessType.equalsIgnoreCase("district")){
			sb.append(" info.districtId in (:locationIdList) ");
		}else{
			sb.append(" info.locationScopeId = 4 and  info.locationValue in (:locationIdList) ");
		}
		sb.append(" group by info.ageRangeId ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("locationIdList",locationIdList);
		return query.list();    
	}
	public List<Object[]> privilegedDistrictwiseAgeWiseCadreCounts(List<Long> distIdList ){
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("select info.districtId ,info.district.districtName,info.stateId,info.state.stateName,info.ageRangeId, " +//4
    			"         sum(info.cadre2014) , sum(info.cadre2016) , sum(info.newCadre) , sum(info.renewalCadre) " +//8
    			"  from   TdpCadreAgeInfo info " +
    			"  where  info.districtId in (:distIdList) ");
    	
    	sb.append(" group by info.districtId , info.ageRangeId " +
    			"   order by info.state.stateName , info.district.districtName ");
    	
    	Query query = getSession().createQuery(sb.toString());
    	query.setParameterList("distIdList",distIdList);
    	return query.list();
    }
	public List<Object[]> privilegedConstituencyWiseAgeWiseCadreCounts(List<Long> constIdList ){
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append(" select C.constituencyId, C.name, info.districtId , info.district.districtName," +
    			  " info.ageRangeId , sum(info.cadre2014) , sum(info.cadre2016) , sum(info.newCadre) , sum(info.renewalCadre) " +
    			  " from TdpCadreAgeInfo info , Constituency C " + 
    			  " where info.locationValue = C.constituencyId and " +
    			  " info.locationScopeId = 4 ");
    	sb.append(" and info.locationValue in (:constIdList) ");
    	sb.append(" group by C.constituencyId, info.ageRangeId ");
    	
    	Query query = getSession().createQuery(sb.toString());
    	query.setParameterList("constIdList",constIdList);
    	return query.list();
    }
	public List<Object[]> getTehsilWiseAgeReport(Long constituecnyId, Long locationScopeId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		if(locationScopeId.longValue() == 5L){
			sb.append(" info.tehsil.tehsilId, " +//0
					  " info.tehsil.tehsilName, ");//1
		}else{
			sb.append(" info.localElectionBody.localElectionBodyId, " +//0
					  " info.localElectionBody.name, ");//1
		}
		
		sb.append(" info.ageRangeId, " +//2
				  " sum(info.cadre2014), " +//3
				  " sum(info.cadre2016), " +//4
				  " sum(info.newCadre), " +//5
				  " sum(info.renewalCadre) " +//6
				  " from TdpCadreAgeInfo info " +
				  " where " +
				  " info.constituencyId = :constituecnyId and " +
				  " info.locationScopeId = :locationScopeId " +
				  " group by ");
		if(locationScopeId.longValue() == 5L){
			sb.append(" info.tehsil.tehsilId, info.ageRangeId " +
					  " order by info.tehsil.tehsilId, info.ageRangeId ");
		}else{
			sb.append(" info.localElectionBody.localElectionBodyId, info.ageRangeId " +
					  " order by info.localElectionBody.localElectionBodyId, info.ageRangeId ");
		}
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("constituecnyId",constituecnyId);
		query.setParameter("locationScopeId",locationScopeId);
		
		return query.list();      
	}
	
}

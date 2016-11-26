package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreAgeInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreAgeInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreAgeInfoDAO extends GenericDaoHibernate<TdpCadreAgeInfo,Long> implements ITdpCadreAgeInfoDAO {
	
	public TdpCadreAgeInfoDAO() {
		super(TdpCadreAgeInfo.class);
	}
	
	public int pushCadreCountsLocationWiseByAge(){
		Query query = getSession().createSQLQuery("CALL tdp_cadre_age_info(); ");
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
    	
    	sb.append("select info.constituencyId,info.constituencyName,info.districtId ,info.districtName," +
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
		         "        info.locationScopeId = :locationScopeId ");
		
		if(stateId != null && stateId == 1L){
			sb.append(" and C.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId == 36L){
			sb.append(" and C.district.districtId between 1 and 10 ");
		}
		sb.append(" group by info.ageRangeId ");
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("locationScopeId", IConstants.LOCATION_SCOPE_ID_CONSTITUENCY);
		return query.list();
	}
	
	
	
}

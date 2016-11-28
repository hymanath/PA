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
}

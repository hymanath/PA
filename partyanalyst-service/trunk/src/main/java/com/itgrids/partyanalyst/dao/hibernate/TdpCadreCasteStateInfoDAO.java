package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreCasteStateInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreCasteStateInfo;

public class TdpCadreCasteStateInfoDAO extends GenericDaoHibernate<TdpCadreCasteStateInfo,Long> implements ITdpCadreCasteStateInfoDAO {
	
	public TdpCadreCasteStateInfoDAO() {
		super(TdpCadreCasteStateInfo.class);
	}
	
	public int pushCadreCountsLocationWiseByCasteState(){
		Query query = getSession().createSQLQuery("CALL tdp_cadre_caste_info(); ");
		return query.executeUpdate();  
	}
	
	
	public List<Object[]> casteCategoryWiseTdpCadreCounts(Long stateId , String minorityCasteIds){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.casteState.casteCategoryGroup.casteCategory.casteCategoryId,model.casteState.casteCategoryGroup.casteCategory.categoryName, " +//1
				"          sum(model.cadre2014), sum(model.cadre2016),sum(model.newCadre),sum(model.renewalCadre) " +//5
				"   from   TdpCadreCasteStateInfo model ");
		
		sb.append(" where model.casteState.caste.casteId not in ("+minorityCasteIds+") ");
				
		if(stateId != null && stateId > 0l){
			sb.append(" and model.stateId = :stateId ");
		}
		sb.append(" group by model.casteState.casteCategoryGroup.casteCategory.casteCategoryId ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("stateId", stateId );
		return query.list();
	}
	
	public Object[] minorityCastesTdpCadreCounts(Long stateId , String minorityCasteIds){
		
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select sum(model.cadre2014), sum(model.cadre2016),sum(model.newCadre),sum(model.renewalCadre) " +//3
				"    from   TdpCadreCasteStateInfo model " +
				"    where  model.casteState.caste.casteId  in ("+minorityCasteIds+")");
		if(stateId != null && stateId > 0l){
			sb.append(" and model.stateId = :stateId ");
		}
		Query query = getSession().createQuery(sb.toString());
		if(stateId != null && stateId > 0l){
			query.setParameter("stateId", stateId );
		}
		return (Object[])query.uniqueResult();
	}
	
	//state wise
	public List<Object[]> stateWiseTdpCadreCasteCounts(Long stateId , String minorityCasteIds){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.casteState.casteCategoryGroup.casteCategory.casteCategoryId,model.casteState.casteCategoryGroup.casteCategory.categoryName," +//1
				"          model.casteState.caste.casteId , model.casteState.caste.casteName," +//3
				"          sum(model.cadre2014), sum(model.cadre2016),sum(model.newCadre),sum(model.renewalCadre) " +//7
				"   from TdpCadreCasteStateInfo model " +
				"   where model.locationScopeId = 4 ");
		if(stateId != null && stateId > 0l){
			sb.append(" and model.stateId = :stateId ");
		}
		sb.append(" and model.casteState.caste.casteId not in ("+minorityCasteIds+") ");
		sb.append(" group by model.casteState.casteCategoryGroup.casteCategory.casteCategoryId ,model.casteState.caste.casteId " +
				"   order by sum(model.cadre2014) desc");
		Query query = getSession().createQuery(sb.toString());
		if(stateId != null && stateId > 0l){
			query.setParameter("stateId", stateId );
		}
		return query.list();
	}
	public List<Object[]> stateWiseTdpCadreMinorityCasteCounts(Long stateId , String minorityCasteIds){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.casteState.caste.casteId , model.casteState.caste.casteName," +//1
				"          sum(model.cadre2014), sum(model.cadre2016),sum(model.newCadre),sum(model.renewalCadre) " +//5
				"   from TdpCadreCasteStateInfo model " +
				"   where model.locationScopeId = 4 ");
		if(stateId != null && stateId > 0l){
			sb.append(" and model.stateId = :stateId ");
		}
		sb.append(" and model.casteState.caste.casteId  in ("+minorityCasteIds+") ");
		sb.append(" group by model.casteState.caste.casteId " +
				"   order by sum(model.cadre2014) desc ");
		Query query = getSession().createQuery(sb.toString());
		if(stateId != null && stateId > 0l){
			query.setParameter("stateId", stateId );
		}
		return query.list();
	}
	//district wise
	public List<Object[]> districtWiseTdpCadreCasteCounts(Long stateId , Long districtId,String minorityCasteIds){
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("select model.casteState.casteCategoryGroup.casteCategory.casteCategoryId,model.casteState.casteCategoryGroup.casteCategory.categoryName,"+//1
    			"         model.casteState.caste.casteId , model.casteState.caste.casteName, " +//3
    			"         sum(model.cadre2014) , sum(model.cadre2016) , sum(model.newCadre) , sum(model.renewalCadre)," +//7
    			"         model.districtId ,model.district.districtName  " +//9
    			"  from   TdpCadreCasteStateInfo model " +
    			"  where  model.locationScopeId = 4 ");
    	if(stateId != null && stateId > 0l){
			sb.append(" and model.stateId = :stateId");
		}
    	if(districtId != null && districtId > 0l){
			sb.append(" and model.districtId = :districtId ");
		}
    	sb.append(" and model.casteState.caste.casteId not in ("+minorityCasteIds+") ");
    	sb.append(" group by model.districtId  , model.casteState.casteCategoryGroup.casteCategory.casteCategoryId , model.casteState.caste.casteId " +
    			"   order by model.district.districtName , sum(model.cadre2014) desc");
    	
    	Query query = getSession().createQuery(sb.toString());
    	if(stateId != null && stateId > 0l){
    		query.setParameter("stateId", stateId);
		}
    	if(districtId != null && districtId > 0l){
			  query.setParameter("districtId",districtId);
		}
    	return query.list();
    }
	public List<Object[]> districtWiseTdpCadreMinorityCasteCounts(Long stateId , Long districtId,String minorityCasteIds){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.casteState.caste.casteId , model.casteState.caste.casteName," +//1
				"          sum(model.cadre2014), sum(model.cadre2016),sum(model.newCadre),sum(model.renewalCadre)," +//5
				"           model.districtId ,model.district.districtName " +//7
				"   from TdpCadreCasteStateInfo model " +
				"   where model.locationScopeId = 4 ");
		if(stateId != null && stateId > 0l){
			sb.append(" and model.stateId = :stateId ");
		}
		if(districtId != null && districtId > 0l){
			sb.append(" and model.districtId = :districtId ");
		}
		sb.append(" and model.casteState.caste.casteId  in ("+minorityCasteIds+") ");
		sb.append(" group by model.districtId , model.casteState.caste.casteId  " +
				 "  order by model.district.districtName , sum(model.cadre2014) desc");
		Query query = getSession().createQuery(sb.toString());
		if(stateId != null && stateId > 0l){
			query.setParameter("stateId", stateId );
		}
		if(districtId != null && districtId > 0l){
			  query.setParameter("districtId",districtId);
		}
		return query.list();
	}
	
	//constituency wise
	public List<Object[]> constituencyWiseTdpCadreCasteCounts(Long stateId , Long districtId ){
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("select C.constituencyId,C.name,model.districtId ,model.district.districtName ," +//3
    			"         model.casteState.caste.casteId , model.casteState.caste.casteName, " +//5
    			"         sum(model.cadre2014) , sum(model.cadre2016) , sum(model.newCadre) , sum(model.renewalCadre) " +//9
    			"  from   TdpCadreCasteStateInfo model , Constituency C" +
    			"  where  model.locationValue = C.constituencyId and model.locationScopeId = 4 ");
    	if(stateId != null && stateId > 0l){
			sb.append(" and model.stateId = :stateId");
		}
    	if(districtId != null && districtId > 0l){
			sb.append(" and model.districtId = :districtId ");
		}
    	sb.append(" group by C.constituencyId , model.casteState.caste.casteId " +
    			"   order by model.district.districtName ,C.name, sum(model.cadre2014) desc");
    	
    	Query query = getSession().createQuery(sb.toString());
    	if(stateId != null && stateId > 0l){
    		query.setParameter("stateId", stateId);
		}
    	if(districtId != null && districtId > 0l){
			  query.setParameter("districtId",districtId);
		}
    	return query.list();
    }
	public List<Object[]> privilegedDistrictWiseTdpCadreCasteCounts(List<Long> distIdList,String minorityCasteIds){
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("select model.casteState.casteCategoryGroup.casteCategory.casteCategoryId,model.casteState.casteCategoryGroup.casteCategory.categoryName,"+//1
    			"         model.casteState.caste.casteId , model.casteState.caste.casteName, " +//3
    			"         sum(model.cadre2014) , sum(model.cadre2016) , sum(model.newCadre) , sum(model.renewalCadre)," +//7
    			"         model.districtId ,model.district.districtName  " +//9
    			"  from   TdpCadreCasteStateInfo model " +
    			"  where  ");
    	
    	if(distIdList != null && distIdList.size() > 0){
			sb.append(" model.districtId in (:distIdList) ");
		}
    	sb.append(" and model.casteState.caste.casteId not in ("+minorityCasteIds+") ");
    	sb.append(" group by model.districtId  , model.casteState.casteCategoryGroup.casteCategory.casteCategoryId , model.casteState.caste.casteId " +
    			"   order by model.district.districtName , sum(model.cadre2014) desc");
    	
    	Query query = getSession().createQuery(sb.toString());
    	
    	if(distIdList != null && distIdList.size() > 0){
			  query.setParameterList("distIdList",distIdList);
		}
    	return query.list();
    }
	public List<Object[]> privilegedDistrictWiseTdpCadreMinorityCasteCounts(List<Long> distIdList, String minorityCasteIds){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.casteState.caste.casteId , model.casteState.caste.casteName," +//1
				"          sum(model.cadre2014), sum(model.cadre2016),sum(model.newCadre),sum(model.renewalCadre)," +//5
				"           model.districtId ,model.district.districtName " +//7
				"   from TdpCadreCasteStateInfo model " +
				"   where model.casteState.caste.casteId is not null ");
		
		if(distIdList != null && distIdList.size() > 0){
			sb.append(" and model.districtId in (:distIdList) ");
		}
		sb.append(" and model.casteState.caste.casteId  in ("+minorityCasteIds+") ");
		sb.append(" group by model.districtId , model.casteState.caste.casteId  " +
				 "  order by model.district.districtName , sum(model.cadre2014) desc");
		Query query = getSession().createQuery(sb.toString());
		
		if(distIdList != null && distIdList.size() > 0){
			query.setParameterList("distIdList",distIdList);
		}
		return query.list();
	}
	public List<Object[]> privilegedConstituencyWiseTdpCadreCasteCounts(List<Long> locationIdList){
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("select C.constituencyId,C.name,model.districtId ,model.district.districtName ," +//3
    			"         model.casteState.caste.casteId , model.casteState.caste.casteName, " +//5
    			"         sum(model.cadre2014) , sum(model.cadre2016) , sum(model.newCadre) , sum(model.renewalCadre) " +//9
    			"  from   TdpCadreCasteStateInfo model , Constituency C" +
    			"  where  model.locationValue = C.constituencyId and model.locationScopeId = 4 ");
    	
    	if(locationIdList != null && locationIdList.size() > 0){
			sb.append(" and model.locationValue in (:locationIdList) ");
		}
    	sb.append(" group by C.constituencyId , model.casteState.caste.casteId " +
    			"   order by model.district.districtName ,C.name, sum(model.cadre2014) desc");
    	
    	Query query = getSession().createQuery(sb.toString());
    	
    	if(locationIdList != null && locationIdList.size() > 0){
			  query.setParameterList("locationIdList",locationIdList);    
		}
    	return query.list();  
    }
	public List<Object[]> privilegedCasteCategoryWiseTdpCadreCounts(List<Long> locationIdList , String minorityCasteIds, String accessType){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.casteState.casteCategoryGroup.casteCategory.casteCategoryId,model.casteState.casteCategoryGroup.casteCategory.categoryName, " +//1
				"          sum(model.cadre2014), sum(model.cadre2016),sum(model.newCadre),sum(model.renewalCadre) " +//5
				"   from   TdpCadreCasteStateInfo model ");
		
		sb.append(" where model.casteState.caste.casteId not in ("+minorityCasteIds+") ");
		if(accessType.equalsIgnoreCase("district")){
			sb.append(" and model.districtId in (:locationIdList) ");
		}else{
			sb.append(" and model.locationScopeId = 4 and  model.locationValue in (:locationIdList) ");
		}
		
		sb.append(" group by model.casteState.casteCategoryGroup.casteCategory.casteCategoryId ");
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameterList("locationIdList",locationIdList); 
		
		return query.list();
	}
	public Object[] privilegedMinorityCastesTdpCadreCounts(List<Long> locationIdList , String minorityCasteIds, String accessType){
		
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select sum(model.cadre2014), sum(model.cadre2016),sum(model.newCadre),sum(model.renewalCadre) " +//3
				"    from   TdpCadreCasteStateInfo model " +
				"    where  model.casteState.caste.casteId  in ("+minorityCasteIds+")");
		 if(accessType.equalsIgnoreCase("district")){
			sb.append(" and model.districtId in (:locationIdList) ");
		}else{
			sb.append(" and model.locationScopeId = 4 and  model.locationValue in (:locationIdList) ");
		}
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("locationIdList",locationIdList);
		return (Object[])query.uniqueResult();
	}
	public List<Object[]> privilegedStateWiseTdpCadreCasteCounts(List<Long> locationIdList , String minorityCasteIds, String accessType){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.casteState.casteCategoryGroup.casteCategory.casteCategoryId,model.casteState.casteCategoryGroup.casteCategory.categoryName," +//1
				"          model.casteState.caste.casteId , model.casteState.caste.casteName," +//3
				"          sum(model.cadre2014), sum(model.cadre2016),sum(model.newCadre),sum(model.renewalCadre) " +//7
				"   from TdpCadreCasteStateInfo model " +
				"   where ");
		if(accessType.equalsIgnoreCase("district")){
			sb.append(" model.districtId in (:locationIdList) ");
		}else{
			sb.append(" model.locationScopeId = 4 and  model.locationValue in (:locationIdList) ");
		}  
		sb.append(" and model.casteState.caste.casteId not in ("+minorityCasteIds+") ");
		sb.append(" group by model.casteState.casteCategoryGroup.casteCategory.casteCategoryId ,model.casteState.caste.casteId " +
				"   order by sum(model.cadre2014) desc");
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("locationIdList",locationIdList);
		return query.list();
	}
	public List<Object[]> privilegedStateWiseTdpCadreMinorityCasteCounts(List<Long> locationIdList , String minorityCasteIds, String accessType){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.casteState.caste.casteId , model.casteState.caste.casteName," +//1
				"          sum(model.cadre2014), sum(model.cadre2016),sum(model.newCadre),sum(model.renewalCadre) " +//5
				"   from TdpCadreCasteStateInfo model " +
				"   where ");
		if(accessType.equalsIgnoreCase("district")){
			sb.append(" model.districtId in (:locationIdList) ");
		}else{
			sb.append(" model.locationScopeId = 4 and  model.locationValue in (:locationIdList) ");
		}
		sb.append(" and model.casteState.caste.casteId  in ("+minorityCasteIds+") ");
		sb.append(" group by model.casteState.caste.casteId " +
				"   order by sum(model.cadre2014) desc ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("locationIdList",locationIdList);
		return query.list();
	}
	
	public int deleteAllRecords(List<Long> locationScopeIds){
    	
    	Query query = getSession().createSQLQuery(" delete from tdp_cadre_caste_state_info where location_scope_id in (:locationScopeIds) ");
    	query.setParameterList("locationScopeIds",locationScopeIds);
    	return query.executeUpdate();
    }
	
	 public int insertTdpCadreLocationInfoUpToLowLevelByCasteState(){
	    	
	    	Query query = getSession().createSQLQuery("" +
	    	"  INSERT INTO tdp_cadre_caste_state_info( caste_state_id,constituency_id,location_scope_id , location_value,cadre_2014," +
	    	"                                  cadre_2016,new_cadre,new_cadre_percent,renewal_cadre,renewal_cadre_percent,inserted_time" +
	    	"                                 ) " +
	        "         SELECT TEMP.caste_state_id,TEMP.constituency_id,TEMP.location_scope_id,TEMP.location_value,TEMP.cadre_2014," +
	        "                TEMP.cadre_2016,TEMP.new_cadre,TEMP.new_cadre_percent,TEMP.renewal_cadre,TEMP.renewal_cadre_percent,TEMP.inserted_time " +
	        "         FROM   tdp_cadre_caste_state_info_temp TEMP " );
	    	return query.executeUpdate();
	}
}


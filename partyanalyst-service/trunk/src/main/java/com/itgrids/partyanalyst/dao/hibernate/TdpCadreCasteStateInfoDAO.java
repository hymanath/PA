package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
}

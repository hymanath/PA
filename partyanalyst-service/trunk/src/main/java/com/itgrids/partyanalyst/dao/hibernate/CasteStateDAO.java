package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.utils.IConstants;

public class CasteStateDAO extends GenericDaoHibernate<CasteState, Long> implements 
ICasteStateDAO {
	public CasteStateDAO(){
		super(CasteState.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getStatewiseCastNames(Long casteCategoryGroupId){
		
		Query query = getSession().createQuery("select distinct model.caste.casteId,model.caste.casteName from CasteState model where model.casteCategoryGroup.casteCategoryGroupId = ?");
		query.setParameter(0,casteCategoryGroupId);
			
		return query.list();
	}
	
	public List<Object[]> getCasteNamesByAutoPopulate(Long stateId,String searchString) {
		String cName = ""+searchString+"%";
		Query queryObject = getSession().createQuery("select model.caste.casteId,model.caste.casteName from CasteState model where model.state.stateId=? and model.caste.casteName like ?");
		queryObject.setLong(0,stateId);
		queryObject.setString(1, cName);
	
		return queryObject.list();		
	}
	
	public List<Object[]> getAllCasteDetails(){
		
		Query query = getSession().createQuery("select model.caste.casteId , model.caste.casteName from CasteState model");
		
		return query.list();
		
		
	}
/*	
public List<Object[]> getAllCasteInfoDetails(){
		
		//Query query = getSession().createQuery("select model.casteId , model.casteName from Caste model");
		return query.list();
		
		
	}*/
	
    public List<Object[]> getAllCasteDetailsForVoters(Long stateId){
		
		Query query = getSession().createQuery("select distinct model.casteStateId , model.caste.casteName from CasteState model where model.state.stateId = :stateId order by model.caste.casteName");
		query.setParameter("stateId", stateId);
		return query.list();
		
		
	}
    @SuppressWarnings("unchecked")
	public List<Object[]> getAllCastesForVoters(Long stateId,Long userId){
		
		Query query = getSession().createQuery("select distinct(model.casteStateId) , model.caste.casteName, model.casteCategoryGroup.casteCategoryGroupName from CasteState model where model.state.stateId = :stateId and (model.isGlobal = :isGlobal or model.user.userId = :userId) order by model.caste.casteName");
		query.setParameter("stateId", stateId);
		query.setParameter("userId", userId);
		query.setParameter("isGlobal", IConstants.TRUE);
		return query.list();
		
		
	}
	
	public CasteState getCasteStateByCasteId(Long userId, Long stateId,Long casteId, Long casteCategoryId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("from CasteState model where model.user.userId = :userId and model.caste.casteId =:casteId ");
		stringBuilder.append(" and model.state.stateId =:stateId and model.casteCategoryGroup.casteCategoryGroupId =:casteCategoryId ");
		Query query = getSession().createQuery(stringBuilder.toString());
		query.setParameter("userId", userId);
		query.setParameter("stateId", stateId);
		query.setParameter("casteId", casteId);
		query.setParameter("casteCategoryId", casteCategoryId);
		return (CasteState)query.uniqueResult();
	}
	/*public CasteState getCasteStateByCasteId(Long userId, Long stateId,Long casteId, Long casteCategoryId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("from CasteState model where model.user.userId = :userId and model.caste.casteId =:casteId ");
		stringBuilder.append(" and model.state.stateId =:stateId ");
		Query query = getSession().createQuery(stringBuilder.toString());
		query.setParameter("userId", userId);
		query.setParameter("stateId", stateId);
		query.setParameter("casteId", casteId);
		return (CasteState)query.uniqueResult();
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteStateList()
	{
		return getHibernateTemplate().find(" select model.casteStateId,model.state.stateId,model.casteCategoryGroup.casteCategoryGroupId, " +
				" model.caste.casteId from CasteState model ");
	}
	
	public List<Object[]> getCasteNamesByCasteIds(List<Long> casteIds){
		Query query=getSession().createQuery("select model.casteStateId,model.caste.casteName from CasteState model" +
				" where model.casteStateId in (:casteIds)");
		
		query.setParameterList("casteIds", casteIds);
		return query.list();
	}
	

	public List<Object[]> getCasteListByCasteIds(List<Long> casteIds)
	{
		Query query  = getSession().createQuery("select  model.casteStateId,model.caste.casteName from CasteState model " +
				" where model.casteStateId in (:casteIds)");
		//query.setParameter("userId", userId);
		query.setParameterList("casteIds", casteIds);
		return query.list();
	}
	public List<CasteState> gettingCasteStateBasedOnCaste(Long casteId)
	{
		Query query  = getSession().createQuery("from CasteState model where model.caste.casteId=:casteId");
		query.setParameter("casteId", casteId);
		return query.list();
	}
	 
	public List<Object[]> getAllCasteInfo()
	{
		Query query = getSession().createQuery("select distinct model.casteStateId,model.caste.casteId,model.casteCategoryGroup.casteCategoryGroupId,model.state.stateId from CasteState model");
		
		return query.list();	
	}
	public List<Object[]> getAllUpdatedCasteInfo()
	{
		Query query = getSession().createQuery("select distinct model.casteStateId,model.caste.casteId,model.casteCategoryGroup.casteCategoryGroupId,model.state.stateId,model.caste.casteName from CasteState model where model.isNew = 'Y' ");
		
		return query.list();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getStatewiseCastNamesByGroupId(Long casteCategoryGroupId,Long stateId){
		
		Query query = getSession().createQuery("select distinct model.casteStateId,model.caste.casteName from CasteState model where model.state.stateId = :stateId and model.casteCategoryGroup.casteCategory.casteCategoryId =:casteCategoryGroupId");
		query.setParameter("casteCategoryGroupId",casteCategoryGroupId);
		query.setParameter("stateId", stateId);	
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getStatewisesCastNamesByGroupId(Long casteCategoryGroupId,Long stateId){
		
		Query query = getSession().createQuery("select distinct model.casteStateId,model.caste.casteName from CasteState model where model.state.stateId = :stateId and model.casteCategoryGroup.casteCategoryGroupId =:casteCategoryGroupId order by model.caste.casteName ");
		query.setParameter("casteCategoryGroupId",casteCategoryGroupId);
		query.setParameter("stateId", stateId);	
		return query.list();
	}
	
	public List<Object[]> getAllCastesInfo()
	{
		Query query = getSession().createQuery("select distinct model.casteStateId,model.caste.casteId,model.casteCategoryGroup.casteCategoryGroupId,model.state.stateId,model.caste.casteName from CasteState model order by model.caste.casteName asc  ");
		
		return query.list();	
	}
	
   public List<Object[]> getStatewisesCastNames(Long stateId){
		
		Query query = getSession().createQuery("select distinct model.casteStateId,model.caste.casteName from CasteState model where model.state.stateId = :stateId order by model.caste.casteName ");
		query.setParameter("stateId", stateId);	
		return query.list();
	}
   public List<Object[]> getStatewiseCastNamesByCasteCategoryGroupId(List<Long> casteCategoryGroupId,Long stateId){
	   StringBuilder str = new StringBuilder();
	   str.append("select distinct model.casteStateId,model.caste.casteName,model.caste.casteId from CasteState model where model.state.stateId = :stateId " );
	   if(casteCategoryGroupId != null && casteCategoryGroupId.size()>0){
		   str.append("and model.casteCategoryGroup.casteCategory.casteCategoryId in(:casteCategoryGroupId)");
	   }
	   Query query = getSession().createQuery(str.toString());
	   if(casteCategoryGroupId != null && casteCategoryGroupId.size()>0){
		query.setParameterList("casteCategoryGroupId", casteCategoryGroupId);
	   }
		query.setParameter("stateId", stateId);	
		return query.list();
	}
	
}

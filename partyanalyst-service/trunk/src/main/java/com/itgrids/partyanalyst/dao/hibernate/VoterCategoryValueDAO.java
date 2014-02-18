package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterCategoryValueDAO;
import com.itgrids.partyanalyst.model.VoterCategoryValue;

public class VoterCategoryValueDAO extends GenericDaoHibernate<VoterCategoryValue, Long> implements IVoterCategoryValueDAO{

	public VoterCategoryValueDAO() {
		super(VoterCategoryValue.class);
		// TODO Auto-generated constructor stub
	}
	
	/*public void flushAndclearSession(){
		getSession().flush();
		getSession().clear();
	}
	*/
	@SuppressWarnings("unchecked")
	public List<VoterCategoryValue> getVoterCategoryValues1(){
		/*Query queryObject = getSession().createQuery("select model.voter.voterId from VoterCategoryValues model");
		return queryObject.list();*/
		return getHibernateTemplate().find("from VoterCategoryValue model");
	}
	
	public List<Long> getVoterCategoryValue(Long userId,Long voterId,Long categoryId){
		Object[] values = {userId,voterId,categoryId};
		return getHibernateTemplate().find("select model.userVoterCategoryValue.userVoterCategoryValueId from VoterCategoryValue model " +
				" where model.user.userId =? and model.voter.voterId = ? and model.userVoterCategoryValue.userVoterCategory.userVoterCategoryId = ?",values);
	}
	
	public List<VoterCategoryValue> getVoterAllCategoryValues(Long userId , Long voterId){
		
		Object[] values = {userId , voterId};
		
		return getHibernateTemplate().find("select model from VoterCategoryValue model where " +
				"model.user.userId = ? and model.voter.voterId = ?",values);
		
		
	}
    
	public List<VoterCategoryValue> getVoterCategoryValues(Long userId,Long voterId,Long categoryId){
		Object[] values = {userId,voterId,categoryId};
		return getHibernateTemplate().find("select model from VoterCategoryValue model " +
				" where model.user.userId =? and model.voter.voterId = ? and model.userVoterCategoryValue.userVoterCategory.userVoterCategoryId = ?",values);
	}
	
	public List<Object[]> getVoterCategoryValuesForVoters(Long userId,List<Long> voterIds){
		Query query = getSession().createQuery("select model.voter.voterId,model.userVoterCategoryValue.userVoterCategory.userVoterCategoryId,model.userVoterCategoryValue.categoryValue " +
				" from VoterCategoryValue model where model.user.userId = :userId and model.voter.voterId in(:voterIds) ");
		query.setParameterList("voterIds", voterIds);
		query.setParameter("userId",userId);
		return query.list();
	}
	
	public List<Object[]> getVoterCategoryValuesForVotersForHHSurvey(Long userId,List<Long> voterIds,List<Long> categoriesList){
		Query query = getSession().createQuery("select model.voter.voterId,model.userVoterCategoryValue.userVoterCategory.userVoterCategoryId,model.userVoterCategoryValue.categoryValue " +
				" from VoterCategoryValue model where model.user.userId = :userId and model.voter.voterId in(:voterIds) " +
				" and model.userVoterCategoryValue.userVoterCategory.userVoterCategoryId in (:categoriesList)");
		query.setParameterList("voterIds", voterIds);
		query.setParameterList("categoriesList", categoriesList);
		query.setParameter("userId",userId);
		return query.list();
	}
	
	public void removeVoterCategoryValue(Long id)
	{
		Query query = getSession().createQuery("delete from VoterCategoryValue model where model.voterCategoryValueId = ?");
		
		query.setParameter(0, id);

        query.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserVoterCategories(List<Long> userId)
	{
		Query query = getSession().createQuery("select distinct model.userVoterCategoryValue.userVoterCategory.userVoterCategoryId, model.userVoterCategoryValue.userVoterCategory.categoryName " +
				" from VoterCategoryValue model where model.user.userId in(:userId) order by model.userVoterCategoryValue.userVoterCategory.categoryName ");
		query.setParameterList("userId", userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> checkCategoeryValues(List<Long> categoeryIds , Long userId)
	{
		Query query = getSession().createQuery("Select model.userVoterCategoryValue.categoryValue," +
				" model.userVoterCategoryValue.userVoterCategoryValueId ," +
				" count(model.userVoterCategoryValue.userVoterCategoryValueId) from VoterCategoryValue model " +
				" where model.userVoterCategoryValue.userVoterCategoryValueId in (:categoeryIds)" +
				" and model.user.userId =:userId group by model.userVoterCategoryValue.userVoterCategoryValueId");
		query.setParameterList("categoeryIds", categoeryIds);
		query.setParameter("userId", userId);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserVoterCategoriesForHHSurvey(List<Long> userId,List<Long> categoriesList)
	{
		Query query = getSession().createQuery("select distinct model.userVoterCategoryValue.userVoterCategory.userVoterCategoryId, model.userVoterCategoryValue.userVoterCategory.categoryName " +
				" from VoterCategoryValue model where model.user.userId in(:userId) " +
				" and model.userVoterCategoryValue.userVoterCategory.userVoterCategoryId in (:categoriesList)" +
				" order by model.userVoterCategoryValue.userVoterCategory.categoryName ");
		query.setParameterList("userId", userId);
		query.setParameterList("categoriesList", categoriesList);
		return query.list();
	}
	
	
}

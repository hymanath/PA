package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterCategoryValuesDAO;
import com.itgrids.partyanalyst.model.CategoryValues;
import com.itgrids.partyanalyst.model.VoterCategoryValues;

public class VoterCategoryValuesDAO extends GenericDaoHibernate<VoterCategoryValues, Long> implements IVoterCategoryValuesDAO{

	public VoterCategoryValuesDAO() {
		super(VoterCategoryValues.class);
		// TODO Auto-generated constructor stub
	}
	
	/*public void flushAndclearSession(){
		getSession().flush();
		getSession().clear();
	}
	*/
	@SuppressWarnings("unchecked")
	public List<VoterCategoryValues> getVoterCategoryValues1(){
		/*Query queryObject = getSession().createQuery("select model.voter.voterId from VoterCategoryValues model");
		return queryObject.list();*/
		return getHibernateTemplate().find("from VoterCategoryValues model");
	}
	
	public List<Long> getVoterCategoryValue(Long userId,Long voterId,Long categoryId){
		Object[] values = {userId,voterId,categoryId};
		return getHibernateTemplate().find("select model.categoryValues.categoryValuesId from VoterCategoryValues model " +
				" where model.user.userId =? and model.voter.voterId = ? and model.categoryValues.userCategoryValues.userCategoryValuesId = ?",values);
	}
    
	public List<VoterCategoryValues> getVoterCategoryValues(Long userId,Long voterId,Long categoryId){
		Object[] values = {userId,voterId,categoryId};
		return getHibernateTemplate().find("select model from VoterCategoryValues model " +
				" where model.user.userId =? and model.voter.voterId = ? and model.categoryValues.userCategoryValues.userCategoryValuesId = ?",values);
	}
}

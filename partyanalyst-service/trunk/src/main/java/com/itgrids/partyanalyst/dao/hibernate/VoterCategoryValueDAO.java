package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

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
    
	public List<VoterCategoryValue> getVoterCategoryValues(Long userId,Long voterId,Long categoryId){
		Object[] values = {userId,voterId,categoryId};
		return getHibernateTemplate().find("select model from VoterCategoryValue model " +
				" where model.user.userId =? and model.voter.voterId = ? and model.userVoterCategoryValue.userVoterCategory.userVoterCategoryId = ?",values);
	}
}

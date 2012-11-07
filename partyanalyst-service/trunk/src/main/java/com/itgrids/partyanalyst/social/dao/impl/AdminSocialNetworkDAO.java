package com.itgrids.partyanalyst.social.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.social.dao.IAdminSocialNetworkDAO;
import com.itgrids.partyanalyst.social.dao.ICandidateSocialDAO;
import com.itgrids.partyanalyst.social.model.CandidateSocial;
import com.mysql.jdbc.PreparedStatement;

public class AdminSocialNetworkDAO extends GenericDaoHibernate<CandidateSocial, Long> implements IAdminSocialNetworkDAO{

	public AdminSocialNetworkDAO(Class<CandidateSocial> persistentClass) {
		super(persistentClass);
		// TODO Auto-generated constructor stub
	}
	public List<Object[]> getAdminDetails(){
		
/*
 * 
 * public List getCadreBySkillAndCadreIds(Long skillId, List<Long> cadreIds) {
		Query queryObject = getSession().createQuery("select model.cadre.cadreId from CadreSkills model where model.partyCadreSkills.partyCadreSkillId = ? and "+
				"model.cadre.cadreId in (:cadreIds)");
		queryObject.setParameter(0, skillId);
		queryObject.setParameterList("cadreIds", cadreIds);
		return queryObject.list();
	}
 
		String m=null;
		ResultSet rs= (ResultSet) getSession().createQuery("select party_id from party where party_name='party_name'");
		String x=rs.getString("party_id");
	 PreparedStatement p=(PreparedStatement) getSession().createQuery("insert into candidatesocial values(?,snid,profile_id)");	
	 p.set
	 if(i==1){
		 m="inserted";
	 }
	 else{
		 m="notsaved";
	 }
	 return m;
	 
	}
	
	/*
	private Object getSession() {
	// TODO Auto-generated method stub
	return null;
	}


	Query queryObject = getSession().createQuery("select count(model.candidateSubscriptionsId) from CandidateSubscriptions model where " +
			" model.user.userId = ? and model.candidate.candidateId = ?");
	queryObject.setParameter(0, userId);
	queryObject.setParameter(1, candidateId);
	return (Long) queryObject.uniqueResult();
		
		
		*/
		return null;
		
	}
}

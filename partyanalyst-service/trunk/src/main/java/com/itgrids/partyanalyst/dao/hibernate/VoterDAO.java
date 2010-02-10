package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.model.Voter;

public class VoterDAO extends GenericDaoHibernate<Voter, Long> implements IVoterDAO{
	
	public VoterDAO(){
		super(Voter.class);
	}

	@SuppressWarnings("unchecked")
	public List<Voter> findByVoterFirstNameLastNameRelativeFirstNameLastNameAndVoterIdNo(
			String firstName, String lastName, String relativeFirstName,
			String relativeLastName, String voterIDCardNo) {
		Object[] params = {firstName, lastName, relativeFirstName, relativeLastName, voterIDCardNo};
		return getHibernateTemplate().find("from Voter model where model.firstName = ?" +
				" and model.lastName = ? and model.relativeFirstName = ?" +
				"and model.relativeLastName = ? and model.voterIDCardNo = ?",params);
	}
	@SuppressWarnings("unchecked")
	public List findCastWiseVotersForMandal(Long mandalID){
		return getHibernateTemplate().find("select model.cast, count(model.cast) from Voter model " +
				"where model.hamlet.township.tehsil.tehsilId=? " +
				"group by model.cast order by model.cast",mandalID);
	}
	
	public List findGenderAgeWiseVotersForMandal(Long mandalID,Long minAge, Long maxAge){
		Object[] params = {mandalID,minAge, maxAge};
		return getHibernateTemplate().find("select model.gender, count(model.gender) from Voter model " +
				"where model.hamlet.township.tehsil.tehsilId=? and model.age>=? and " +
				" model.age<? group by model.gender",params);
	}
	
}
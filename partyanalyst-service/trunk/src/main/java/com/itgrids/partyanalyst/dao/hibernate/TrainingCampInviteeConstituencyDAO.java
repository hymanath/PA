package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampInviteeConstituencyDAO;
import com.itgrids.partyanalyst.model.TrainingCampInviteeConstituency;

public class TrainingCampInviteeConstituencyDAO extends GenericDaoHibernate<TrainingCampInviteeConstituency, Long> implements ITrainingCampInviteeConstituencyDAO {
	public TrainingCampInviteeConstituencyDAO(){
		super(TrainingCampInviteeConstituency.class);
	}
	public List<Long> getExtraConstituencyList(List<Long> trainingCompProgramIds){
		StringBuilder sb = new StringBuilder();
		sb.append(" select " +
				" model.constituencyId " +
				" from " +
				" TrainingCampInviteeConstituency model " +
				" where " +
				" model.trainingCampProgramId in (:trainingCompProgramIds) and " +
				" model.isInviteeConstituency = 'Y' ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("trainingCompProgramIds", trainingCompProgramIds);
		return query.list();
	}
}

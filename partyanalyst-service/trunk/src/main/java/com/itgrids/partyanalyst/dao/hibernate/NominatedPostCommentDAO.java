package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostCommentDAO;
import com.itgrids.partyanalyst.model.NominatedPostComment;

public class NominatedPostCommentDAO extends GenericDaoHibernate<NominatedPostComment, Long> implements INominatedPostCommentDAO{

	public NominatedPostCommentDAO() {
		super(NominatedPostComment.class);
		// TODO Auto-generated constructor stub
	}

	public List<Object[]> getCommentsCountForCandidateIds(Set<Long> candidateIds){
		Query query = getSession().createQuery("select model.nominatedPostApplication.nominationPostCandidate.nominationPostCandidateId," +
											" count(model.nominatedPostCommentId)" +
											" from NominatedPostComment model" +
											" where model.nominatedPostApplication.nominationPostCandidate.nominationPostCandidateId in (:candidateIds)" +
											" and model.nominatedPostApplication.nominationPostCandidate.isDeleted = 'N'" +
											" group by model.nominatedPostApplication.nominationPostCandidate.nominationPostCandidateId");
		query.setParameterList("candidateIds", candidateIds);
		return query.list();
	}
	
	public List<Object[]> getFinalyzedCommentsForCandidate(Long candidateId){
		Query query = getSession().createQuery("select model.remarks," +
										" model.insertedTime," +
										" model1.firstName," +
										" model1.lastName" +
										" from NominatedPostComment model,User model1" +
										" where model.insertedBy = model1.userId" +
										" and model.nominatedPostFinal.nominationPostCandidateId = :candidateId" +
										" and model.nominatedPostFinalId is not null");
		query.setParameter("candidateId", candidateId);
		return query.list();
	}
	
	public List<Object[]> getShortListingCommentsForCandidate(Long candidateId){
		Query query = getSession().createQuery("select model.remarks," +
										" model.insertedTime," +
										" model1.firstName," +
										" model1.lastName" +
										" from NominatedPostComment model,User model1" +
										" where model.insertedBy = model1.userId" +
										" and model.nominatedPostApplication.nominationPostCandidateId = :candidateId" +
										" and model.nominatedPostFinalId is null");
		query.setParameter("candidateId", candidateId);
		return query.list();
	}
}

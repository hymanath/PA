package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemFileDAO;
import com.itgrids.partyanalyst.model.CadreProblemDetails;
import com.itgrids.partyanalyst.model.ProblemFile;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemFileDAO extends GenericDaoHibernate<ProblemFile, Long>
		implements IProblemFileDAO {

	public ProblemFileDAO() {
		super(ProblemFile.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getProblemImagesBasedHistoryId(Long probHistoryId){
		return getHibernateTemplate().find("select model.file.fileName ,model.file.fileTitle,model.file.fileDescription ,model.file.filePath from ProblemFile model where model.isApproved='true' and model.problemHistory.problemHistoryId =?",probHistoryId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllNonApprovedImagesBetweenDatesWithCompleteData(){		
		return getHibernateTemplate().find("select model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription,model.problemFileId  from ProblemFile model " +
				" where model.isApproved = null");
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Object[]> getAllNonApprovedFilesAndProblemDetails()
	{
		return getHibernateTemplate().find(" select model.problemFileId,model.problemHistory.problemLocation.problemAndProblemSource.problem.problem,model.file.fileTitle,model.file.fileDescription,model.problemHistory.problemLocation.problemImpactLevel.scope," +
				" model.problemHistory.problemLocation.problemAndProblemSource.problem.existingFrom, model.problemHistory.problemLocation.problemAndProblemSource.problem.identifiedOn,model.problemHistory.problemLocation.problemAndProblemSource.externalUser.name," +
				" model.problemHistory.problemLocation.problemAndProblemSource.externalUser.lastName,model.file.fileName,model.isApproved from ProblemFile model where model.isApproved is null");
	}*/
	@SuppressWarnings("unchecked")
	public List<ProblemFile> getAllNonApprovedFilesAndProblemDetails()
	{
		return getHibernateTemplate().find("select model from ProblemFile model where model.isApproved is null");
	}
@SuppressWarnings("unchecked")
	public List<ProblemFile> getAllApprovedImagesBetweenDates(Date startDate,Date endDate,String status)
	{
		Query query = getSession().createQuery("select model from ProblemFile model where model.isApproved = 'true' and date(model.problemHistory.dateUpdated) >=:startDate and date(model.problemHistory.dateUpdated) <=:endDate and model.problemHistory.problemStatus.status =:status order by model.problemHistory.problemLocation.problemAndProblemSource.problem.identifiedOn desc");
		
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameter("status", status);
		
		return query.list();		
	}
	@SuppressWarnings("unchecked")
	public List<ProblemFile> getAllNoNApprovalImagesBetweenDates(Date startDate,Date endDate,String status)
	{
		Query query = getSession().createQuery("select model from ProblemFile model where model.isApproved ='"+IConstants.REJECTED+"' and date(model.problemHistory.dateUpdated) >=:startDate and date(model.problemHistory.dateUpdated) <=:endDate order by model.problemHistory.problemLocation.problemAndProblemSource.problem.identifiedOn desc");
		
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		//query.setParameter("status", status);
		
		return query.list();		

	}
	@SuppressWarnings("unchecked")
	public List<ProblemFile> getAllPostedImagesBetweenDates(Date startDate,Date endDate,String status)
	{
		Query query = getSession().createQuery("select model from ProblemFile model where date(model.problemHistory.dateUpdated) >=:startDate and date(model.problemHistory.dateUpdated) <=:endDate and model.problemHistory.problemStatus.status =:status order by model.problemHistory.problemLocation.problemAndProblemSource.problem.identifiedOn desc");
		
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameter("status", status);
		
		return query.list();		

	}
	@SuppressWarnings("unchecked")
	public List<ProblemFile> getApprovalImagesForParticularDate(Date particularDate,String status)
	{
		Query query = getSession().createQuery("select model from ProblemFile model where model.isApproved ='true' and date(model.problemHistory.dateUpdated) =:particularDate and model.problemHistory.problemStatus.status =:status order by model.problemHistory.problemLocation.problemAndProblemSource.problem.identifiedOn desc");
		query.setParameter("particularDate", particularDate);
		query.setParameter("status", status);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemFile> getAllNonApprovalImagesForParticularDate(Date particularDate,String status)
	{
		Query query = getSession().createQuery("select model from ProblemFile model where model.isApproved ='Rejected' and date(model.problemHistory.dateUpdated) =:particularDate and model.problemHistory.problemStatus.status =:status order by model.problemHistory.problemLocation.problemAndProblemSource.problem.identifiedOn desc");
		query.setTimestamp("particularDate", particularDate);
		query.setParameter("status", status);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<ProblemFile> getAllImagesForParticularDate(Date particularDate,String status)
	{
		Query query = getSession().createQuery("select model from ProblemFile model where date(model.problemHistory.dateUpdated) =:particularDate and model.problemHistory.problemStatus.status =:status");
		query.setParameter("particularDate", particularDate);
		query.setParameter("status", status);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getNoOfFilesUploadedForAUser(Long problemHistoryId)
	{
		return getHibernateTemplate().find("select count(model.problemFileId) from ProblemFile model where model.problemHistory.problemHistoryId = ?", problemHistoryId);
	}
	public Long getCountOfNewlyPostedImagesByFreeUser()
	{
		Query query = getSession().createQuery("select count(*) from ProblemFile model where model.isApproved is null");
		return (Long)query.uniqueResult();
	}
	
}

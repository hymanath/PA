package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IProblemFilesDAO;
import com.itgrids.partyanalyst.model.ProblemFiles;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemFilesDAO extends GenericDaoHibernate<ProblemFiles,Long> implements IProblemFilesDAO{

	public ProblemFilesDAO()
	{
		super(ProblemFiles.class);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCurrentDateFiles(Date currentDate,Date endDate,String isApproved)
	{
		StringBuilder query = new StringBuilder();
		query.append(" select model.problemFilesId,model.file.fileTitle,model.file.fileDescription,model.problem.title,model.isApproved,model.problem.existingFrom,model.problem.identifiedOn,model.user.firstName,model.user.lastName,model.file.fileName");
		query.append(" from ProblemFiles model where (model.isDelete =:isDelete or model.isDelete is null) and (model.problem.isDelete =:isDelete or model.problem.isDelete is null)");
		if(currentDate != null)
			query.append(" and date(model.insertedTime) >=:currentDate");
		 if(endDate != null)
			query.append(" and date(model.insertedTime) <=:endDate");
		 if(isApproved != null)
			query.append(" and  model.isApproved=:isApproved");
		Query queryObject = getSession().createQuery(query.toString());
		if(currentDate != null)
			queryObject.setParameter("currentDate", currentDate);
		 if(endDate != null)
			queryObject.setParameter("endDate", endDate);
		 if(isApproved != null)
			queryObject.setParameter("isApproved", isApproved);
		
		queryObject.setParameter("isDelete", "false");
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getNoOfFilesUploadedForAUser(Long problemId)
	{
		return getHibernateTemplate().find("select count(model.problemFilesId) from ProblemFiles model where model.problem.problemId =?",problemId);
	}
	
	/*public Long getCountOfNewlyPostedImagesByFreeUser()
	{
		Query query = getSession().createQuery("select count(model.problemFilesId) from ProblemFiles model where (model.isApproved = 'false' or model.isApproved is null) and (model.isDelete is null or model.isDelete = 'false')");
		return (Long) query.uniqueResult();
	}*/
	public Long getCountOfNewlyPostedImagesByFreeUser(Date currentDate)
	{
		Query query = getSession().createQuery("select count(model.problemFilesId) from ProblemFiles model where Date(model.updatedTime) = ? and (model.isDelete is null or model.isDelete = 'false')");
		query.setParameter(0, currentDate);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getProblemRelatedFilesForAUser(Long problemId,Long userId){
		Object[] params = {problemId,userId};
		return getHibernateTemplate().find("select model.file.fileName ,model.file.fileTitle,model.file.fileDescription ,model.file.filePath from ProblemFiles model where model.isApproved='true' and model.isDelete ='false' and model.problem.problemId =? and model.user.userId = ?",params);
	}
	@SuppressWarnings("unchecked")
	public Integer deleteProblemFile(Long problemFileId)
	{
		Query queryObj = getSession().createQuery("delete from ProblemFiles model where model.problemFilesId = :problemFileId");
		queryObj.setParameter("problemFileId", problemFileId);
		return queryObj.executeUpdate();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getProblemFileDetailsByProblemFileId(Long problemFilesId)
	{
		Query query = getSession().createQuery("select model.file.fileId, model.file.fileTitle,model.file.fileDescription,model.problemFilesId from ProblemFiles " +
				" model where model.problemFilesId = :problemFilesId and model.isApproved = :isApproved ");
		query.setParameter("problemFilesId", problemFilesId);
		query.setParameter("isApproved", IConstants.TRUE);
		return query.list();
	}
}

package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFilePathsDAO;
import com.itgrids.partyanalyst.model.FilePaths;

public class FilePathsDAO extends GenericDaoHibernate<FilePaths,Long> implements IFilePathsDAO {

	public FilePathsDAO(){
		super(FilePaths.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getMaxOrderNo()
	{
		return getHibernateTemplate().find("select max(model.orderNo) from FilePaths model");
	}
	public List<Object[]> getProblemRelatedFiles(final Long problemId,final Long userId){
		Object[] params = {problemId,userId};
		return getHibernateTemplate().find("select model.fileSourceLanguage.file.fileTitle,model.fileSourceLanguage.file.fileDescription," +
				"model.filePath from FilePaths model where model.fileSourceLanguage.file.fileId in (select model1.file.fileId from ProblemFiles " +
				" model1 where model1.isApproved='true' and model1.isDelete ='false' and model1.problem.problemId =? and model1.user.userId = ?)" +
				" order by model.fileSourceLanguage.file.fileId desc",params);
	}
	public List<Object[]> getProblemFiles(final Long problemId,final Long userId,final String visibility){

		StringBuilder query = new StringBuilder();
		query.append("select model.fileSourceLanguage.file.fileTitle,model.fileSourceLanguage.file.fileDescription,model.filePath,model1.user.userId,model1.user.firstName,model1.user.lastName " +
				" from FilePaths model,ProblemFiles model1 where model.fileSourceLanguage.file.fileId = model1.file.fileId  and model1.isApproved='true' and model1.isDelete ='false' and model1.problem.problemId = :problemId ");
		if(visibility != null)
			query.append(" and model1.visibility.type = :visibility ");
		if(userId != null)
			query.append(" and model1.user.userId :userId ");
		query.append(" order by model1.updatedTime desc ");
		
		Query queryObj = getSession().createQuery(query.toString());
		
		queryObj.setParameter("problemId", problemId);
		if(visibility != null)
			queryObj.setParameter("visibility", visibility);
		if(userId != null)
		    queryObj.setParameter("userId", userId);
		return queryObj.list();
		
	}
	
	
	public List<Object> getFilePaths(long fileId)
	{
		return getHibernateTemplate().find("select model.filePath from FilePaths model where model.fileSourceLanguage.file.fileId=?",fileId);
		
	}
	

}

package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

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
	public List<Object[]> getProblemRelatedFiles(Long problemId,Long userId){
		Object[] params = {problemId,userId};
		return getHibernateTemplate().find("select model.fileSourceLanguage.file.fileTitle,model.fileSourceLanguage.file.fileDescription," +
				"model.filePath from FilePaths model where model.fileSourceLanguage.file.fileId in (select model1.file.fileId from ProblemFiles " +
				" model1 where model1.isApproved='true' and model1.isDelete ='false' and model1.problem.problemId =? and model1.user.userId = ?)" +
				" order by model.fileSourceLanguage.file.fileId desc",params);
	}

}

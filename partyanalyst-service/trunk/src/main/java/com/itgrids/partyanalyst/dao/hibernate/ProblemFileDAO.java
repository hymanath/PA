package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemFileDAO;
import com.itgrids.partyanalyst.model.CadreProblemDetails;
import com.itgrids.partyanalyst.model.ProblemFile;

public class ProblemFileDAO extends GenericDaoHibernate<ProblemFile, Long>
		implements IProblemFileDAO {

	public ProblemFileDAO() {
		super(ProblemFile.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getProblemImagesBasedHistoryId(Long probHistoryId){
		return getHibernateTemplate().find("select model.file.fileName ,model.file.fileTitle,model.file.fileDescription ,model.file.filePath from ProblemFile model where model.isApproved='true' and model.problemHistory.problemHistoryId =?",probHistoryId);
	}
}

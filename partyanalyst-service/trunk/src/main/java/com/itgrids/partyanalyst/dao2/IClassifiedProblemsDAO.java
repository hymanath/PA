package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.ClassifiedProblems;

public interface IClassifiedProblemsDAO extends GenericDao<ClassifiedProblems,Long>{
	public List<Long> checkIfProblemAlreadyClassified(Long userProblemId);
	public List<ClassifiedProblems> getClassifiedproblemByUserProblemId(Long userProblemId);
}

package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.ProblemProgress;

public interface IProblemProgressDAO extends GenericDao<ProblemProgress,Long>{
	
	public List<ProblemProgress>  getProblemPrograss(Long userProblemId);
	public List<ProblemProgress>  getProblemPrograssDetails(Long userProblemId);
	public List<ProblemProgress> getAllProblemProgressDetails(Long problemId,String visibility);
	public int updateActivityVisibility(Long prblmPrgrssId,Long visibility);
	public List<ProblemProgress> getAllActivitesByProblemId(Long userProblemId);
}

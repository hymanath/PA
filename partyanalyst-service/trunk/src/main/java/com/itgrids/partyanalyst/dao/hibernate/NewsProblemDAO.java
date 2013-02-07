package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INewsProblemDAO;
import com.itgrids.partyanalyst.model.NewsProblem;
import com.itgrids.partyanalyst.utils.IConstants;

public class NewsProblemDAO extends GenericDaoHibernate<NewsProblem,Long> implements INewsProblemDAO {
	
	   public NewsProblemDAO(){
		   super(NewsProblem.class);
		   
	   }
	   
	   public List<Object[]> getProblemIdsByFileIds(List<Long> fileIdsList){
		   
		   
		   Query query = getSession().createQuery("select model.problem.problemId , model.file.fileId from NewsProblem " +
		   		"model where model.file.fileId in(:fileIdsList) and model.isDelete = :isDelete ");
		   
		   query.setParameterList("fileIdsList", fileIdsList);
		   query.setParameter("isDelete", IConstants.FALSE);
		   
		   return query.list();
		   
	   }
	   
	   
	   public List<Long> getCountByProblemId(Long problemId){
		   
		   Query query = getSession().createQuery("select count(model.newsProblemId) from NewsProblem model" +
		   		" where model.problem.problemId = ? ");
		   query.setParameter(0, problemId);
		   
		   return query.list();
		   
	   }
	   
	   public List<NewsProblem> getNewsProblemByProblemId(Long problemId){
		   
		   
		   Query query = getSession().createQuery("select model from NewsProblem model " +
		   		"where model.problem.problemId = ? and model.isDelete =?");
		   
		   query.setParameter(0, problemId);
		   query.setParameter(1, IConstants.FALSE);
		   
		   return query.list();
		   
		   
	   }
}

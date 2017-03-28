package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HomePageQuestion;

public interface IHomePageQuestionDAO extends GenericDao<HomePageQuestion, Long>{
	
	
	public List getQuestionsForThePresentDay(Date currentDate,String isDelete);

}

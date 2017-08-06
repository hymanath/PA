package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.KaizalaQuestions;

public interface IKaizalaQuestionsDAO extends GenericDao<KaizalaQuestions, Long> {
	
	public List<Object[]> getQuestionsForKizalaActionId(Long laizalaActionId);
}
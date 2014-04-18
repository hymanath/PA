package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IKeywordDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IKeywordsService;

public class KeywordsService implements IKeywordsService{
	private IKeywordDAO keywordDAO;

	public IKeywordDAO getKeywordDAO() {
		return keywordDAO;
	}

	public void setKeywordDAO(IKeywordDAO keywordDAO) {
		this.keywordDAO = keywordDAO;
	}
	
	public SelectOptionVO getkeywords(String searchStr,Integer startIndex,Integer maxIndex){
		SelectOptionVO returnVO = new SelectOptionVO();
		List<SelectOptionVO> keywords = new ArrayList<SelectOptionVO>();
		List<Object[]> keywordsList = keywordDAO.getAllKeywordsList(searchStr, startIndex, maxIndex);
		for(Object[] keyword:keywordsList){
			SelectOptionVO vo = new SelectOptionVO();
			vo.setId((Long)keyword[0]);
			vo.setName(keyword[1].toString());
			keywords.add(vo);		
		}
		returnVO.setId(keywordDAO.getAllKeywordsCount(searchStr));
		returnVO.setSelectOptionsList(keywords);
		return returnVO;
	}
}

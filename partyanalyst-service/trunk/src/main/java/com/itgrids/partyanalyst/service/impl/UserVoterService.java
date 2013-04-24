package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IUserVoterCategoryDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IUserVoterService;

public class UserVoterService implements IUserVoterService{

	private static final Logger log = Logger.getLogger(VotersAnalysisService.class);
	private IUserVoterCategoryDAO userVoterCategoryDAO;

	public IUserVoterCategoryDAO getUserVoterCategoryDAO() {
		return userVoterCategoryDAO;
	}

	public void setUserVoterCategoryDAO(IUserVoterCategoryDAO userVoterCategoryDAO) {
		this.userVoterCategoryDAO = userVoterCategoryDAO;
	}
	
	
	
	public List<SelectOptionVO> getUserVoterCategoryList(List<Long> userId)
	{
		List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>(0);
		try{
			if(userId != null && userId.size() > 0)
			{
			List<Object[]> list = userVoterCategoryDAO.getUserCategoriesByUserList(userId);
			if(list != null && list.size() > 0)
				for(Object[] params : list)
				{
					resultList.add(new SelectOptionVO((Long)params[0],params[1].toString()));	
				}
			
		}
		}
		catch(Exception e)
		{
			log.error("error occured in the getUserVoterCategoryList() method in VotersAnalysis" , e) ;
			
		}
		return resultList;
	}
}

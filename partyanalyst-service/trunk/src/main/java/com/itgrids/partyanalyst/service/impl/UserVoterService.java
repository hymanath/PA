package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IUserVoterCategoryDAO;
import com.itgrids.partyanalyst.dao.IVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IUserVoterService;

public class UserVoterService implements IUserVoterService{

	private static final Logger LOG = Logger.getLogger(UserVoterService.class);
	
	private IVoterCategoryValueDAO voterCategoryValueDAO;

	public IVoterCategoryValueDAO getVoterCategoryValueDAO() {
		return voterCategoryValueDAO;
	}

	public void setVoterCategoryValueDAO(
			IVoterCategoryValueDAO voterCategoryValueDAO) {
		this.voterCategoryValueDAO = voterCategoryValueDAO;
	}


	public List<SelectOptionVO> getUserVoterCategoryList(List<Long> userIdsList)
	{
		List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>(0);
		try{
			if(userIdsList != null && userIdsList.size() > 0)
			{
				List<Object[]> list = voterCategoryValueDAO.getUserVoterCategories(userIdsList);
				
				if(list != null && list.size() > 0)
				for(Object[] params : list)
					resultList.add(new SelectOptionVO((Long)params[0],params[1].toString()));	
			}
			return resultList;
		}
		catch(Exception e)
		{
			LOG.error("error occured in the getUserVoterCategoryList() method in VotersAnalysis" , e) ;
			return resultList;
		}
	}
}

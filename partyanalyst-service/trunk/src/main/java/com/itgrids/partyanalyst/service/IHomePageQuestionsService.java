package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.HomePageQuestionsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Registration;

public interface IHomePageQuestionsService {
	
	public boolean saveHomePageQuestions(HomePageQuestionsVO homePageQuestionsVO,Long userId);
	
	public List<HomePageQuestionsVO>  getQuestionsForTheDay();

}

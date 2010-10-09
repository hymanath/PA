package com.itgrids.partyanalyst.dao.hibernate;


import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.utils.IConstants;


public class AnanymousUserDAOHibernateTest extends BaseDaoTestCase {
	
	private IAnanymousUserDAO ananymousUserDAO;
	
	public IAnanymousUserDAO getAnanymousUserDAO() {
		return ananymousUserDAO;
	}

	public void setAnanymousUserDAO(IAnanymousUserDAO ananymousUserDAO) {
		this.ananymousUserDAO = ananymousUserDAO;
	}

	public void testAnonymousUserLogin(){		
		List<AnanymousUser> detailsList = ananymousUserDAO.checkAnonymousUserLogin("ravi","kiran");	
		assertEquals(detailsList.size(), 1);
	}

	public void testAvailabityOfUserNameForAnonymousUser(){		
		List<AnanymousUser> detailsList = ananymousUserDAO.checkForUserNameAvailabiity("ravi");	
		assertEquals(detailsList.size(), 1);
	}
	
}

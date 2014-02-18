package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHHOptionTypeDAO;
import com.itgrids.partyanalyst.dao.IHHOptionsDAO;
import com.itgrids.partyanalyst.dao.IHouseHoldsDAO;
import com.itgrids.partyanalyst.dao.ISurveyQuestionDAO;
import com.itgrids.partyanalyst.model.HouseHolds;

public class HouseHoldsDAOHibernateTest extends BaseDaoTestCase{
	
	private IHouseHoldsDAO houseHoldsDAO;
	
	public IHouseHoldsDAO getHouseHoldsDAO() {
		return houseHoldsDAO;
	}

	public void test(){
		List<HouseHolds> list=houseHoldsDAO.getHouseHoldInfoByPanchayatOrLocalId(null, 83l, "#1-1");
		System.out.println(list.size());
	}

}

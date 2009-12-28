package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFundSourceDAO;
import com.itgrids.partyanalyst.model.FundSource;

public class FundSourceDAOHibernateTest extends BaseDaoTestCase{

	private IFundSourceDAO fundSourceDAO;

	public IFundSourceDAO getFundSourceDAO() {
		return fundSourceDAO;
	}

	public void setFundSourceDAO(IFundSourceDAO fundSourceDAO) {
		this.fundSourceDAO = fundSourceDAO;
	}
	
	/*public void testSave(){
		FundSource fundSource = new FundSource("MLA Funds", "Spent for MLA Second Family", null);
		fundSourceDAO.save(fundSource);
		setComplete();
	}*/
	
	public void testGetAll(){
		List<FundSource> list = fundSourceDAO.getAll();
		assertEquals(1, list.size());
	}
}

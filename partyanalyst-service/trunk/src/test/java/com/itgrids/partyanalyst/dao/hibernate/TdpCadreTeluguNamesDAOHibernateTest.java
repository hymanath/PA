package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITdpCadreTeluguNamesDAO;
import com.itgrids.partyanalyst.model.TdpCadreTeluguNames;

public class TdpCadreTeluguNamesDAOHibernateTest extends BaseDaoTestCase {

	private ITdpCadreTeluguNamesDAO tdpCadreTeluguNamesDAO;

	public ITdpCadreTeluguNamesDAO getTdpCadreTeluguNamesDAO() {
		return tdpCadreTeluguNamesDAO;
	}

	public void setTdpCadreTeluguNamesDAO(
			ITdpCadreTeluguNamesDAO tdpCadreTeluguNamesDAO) {
		this.tdpCadreTeluguNamesDAO = tdpCadreTeluguNamesDAO;
	}
	
	public void test(){
		/*List<TdpCadreTeluguNames> list= tdpCadreTeluguNamesDAO.getAll();
		System.out.println(list.size());*/
		
		List<String> name = tdpCadreTeluguNamesDAO.getTeluguVoterNameByTdpCadreId(94l);
		System.out.println(name);
	}
	
	
}

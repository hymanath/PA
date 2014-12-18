package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDynamicKeysDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class DynamicKeysDAOHibernateTest extends BaseDaoTestCase{

	private IDynamicKeysDAO dynamicKeysDAO;

	public void setDynamicKeysDAO(IDynamicKeysDAO dynamicKeysDAO) {
		this.dynamicKeysDAO = dynamicKeysDAO;
	}
	
	/*public void test()
	{
		dynamicKeysDAO.getAll();
	}*/
	
	public void testGetValueByKey()
	{
		System.out.println(dynamicKeysDAO.getValueByKey(IConstants.SAVE_VERIFY_DATA));
	}
}

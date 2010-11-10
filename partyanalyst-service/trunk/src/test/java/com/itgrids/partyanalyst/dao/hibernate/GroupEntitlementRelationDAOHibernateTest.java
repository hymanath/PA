package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IGroupEntitlementRelationDAO;
import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Entitlement;
import com.itgrids.partyanalyst.utils.IConstants;

public class GroupEntitlementRelationDAOHibernateTest extends BaseDaoTestCase {

	public IGroupEntitlementRelationDAO groupEntitlementRelationDAO;

	public IGroupEntitlementRelationDAO getGroupEntitlementRelationDAO() {
		return groupEntitlementRelationDAO;
	}

	public void setGroupEntitlementRelationDAO(
			IGroupEntitlementRelationDAO groupEntitlementRelationDAO) {
		this.groupEntitlementRelationDAO = groupEntitlementRelationDAO;
	}
	
	public void testGetEntitlementsBasedOnUserGroupId(){
		List result= groupEntitlementRelationDAO.getAllEntitlementsBasedOnUserGroupId(2l);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.println(parms[0]);
			System.out.println(parms[1]);
		}
	}
}

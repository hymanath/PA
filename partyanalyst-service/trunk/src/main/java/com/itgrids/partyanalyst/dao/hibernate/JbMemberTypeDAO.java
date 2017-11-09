package com.itgrids.partyanalyst.dao.hibernate;



import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IJbMemberTypeDAO;
import com.itgrids.partyanalyst.model.JbMemberType;

public class JbMemberTypeDAO extends GenericDaoHibernate<JbMemberType, Long> implements IJbMemberTypeDAO{

	  public JbMemberTypeDAO() {
		 super(JbMemberType.class);
		
	  }

	

}

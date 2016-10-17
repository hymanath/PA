package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRegIssueTypeDAO;
import com.itgrids.partyanalyst.model.BloodDonation;
import com.itgrids.partyanalyst.model.CadreRegIssueType;

public class CadreRegIssueTypeDAO extends GenericDaoHibernate<CadreRegIssueType, Long> implements ICadreRegIssueTypeDAO {
	
	
	public CadreRegIssueTypeDAO(){
		 super(CadreRegIssueType.class);
	}
	public List<Object[]> getCadreRegIssueType(){
		Query query = getSession().createQuery("select model.cadreRegIssueTypeId,model.issueType from CadreRegIssueType model");
		
		return query.list();
	}

}

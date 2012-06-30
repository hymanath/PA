package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemBackupDAO;
import com.itgrids.partyanalyst.model.ProblemBackup;

public class ProblemBackupDAO extends GenericDaoHibernate<ProblemBackup, Long> implements IProblemBackupDAO{

	public ProblemBackupDAO(){
		super(ProblemBackup.class);
	}
	@SuppressWarnings("unchecked")
	public List<String> getProblemReferenceNo(String refNo){
		return getHibernateTemplate().find("select model.referenceNo from Problem model where referenceNo=?", refNo);
		
		
	}
	
	
	
}

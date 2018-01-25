package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPsychometricTestDAO;
import com.itgrids.partyanalyst.model.PsychometricTest;

public class PsychometricTestDAO extends GenericDaoHibernate<PsychometricTest, Long> implements IPsychometricTestDAO{

	public PsychometricTestDAO() {
		super(PsychometricTest.class);
	}

	public List<Object[]> getThomousUrls(){
		Query query = getSession().createQuery("select model.psychometricTestId," +
				"model.testUrl" +
				" from PsychometricTest model" +
				" where model.isUsed = 'N'");
		return query.list();
	}
	
	public Integer  updateExistingRecordForPsychometricTestId(Long  psymetricTestId){
		Query query=getSession().createSQLQuery("update psychometric_test model "
				+ "set model.is_used = 'Y'"
				+ " where model.psychometric_test_id = :psymetricTestId");
		query.setParameter("psymetricTestId", psymetricTestId);
		return query.executeUpdate();
	}
}

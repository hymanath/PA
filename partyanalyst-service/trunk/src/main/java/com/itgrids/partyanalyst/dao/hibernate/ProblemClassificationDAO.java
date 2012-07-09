/* 
 * Copyright (c) 2009 IT Grids Limited.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 23, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import javax.mail.Session;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IProblemClassificationDAO;
import com.itgrids.partyanalyst.model.ProblemClassification;

public class ProblemClassificationDAO extends GenericDaoHibernate<ProblemClassification, Long> implements IProblemClassificationDAO {

	public ProblemClassificationDAO() {
		super(ProblemClassification.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<ProblemClassification> findByClassification(String problemClassification) {
		return getHibernateTemplate().find("from ProblemClassification model where model.classification = ?",problemClassification);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getProblemClassificationId(String problemClassification){
		Query query=getSession().createQuery("select model.problemClassificationId from ProblemClassification model where " +
				"model.classification=?");
		query.setParameter(0, problemClassification);
		return query.list();
	}	
}

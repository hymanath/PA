package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IKaizalaAnswerInfoDAO;
import com.itgrids.partyanalyst.model.KaizalaAnswerInfo;

public class KaizalaAnswerInfoDAO extends GenericDaoHibernate<KaizalaAnswerInfo, Long> implements IKaizalaAnswerInfoDAO {
	public KaizalaAnswerInfoDAO() {
		super(KaizalaAnswerInfo.class); 
	}
	
}
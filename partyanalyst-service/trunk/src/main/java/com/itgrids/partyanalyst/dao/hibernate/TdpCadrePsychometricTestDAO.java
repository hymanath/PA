package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadrePsychometricTestDAO;
import com.itgrids.partyanalyst.model.TdpCadrePsychometricTest;

public class TdpCadrePsychometricTestDAO extends GenericDaoHibernate<TdpCadrePsychometricTest, Long> implements ITdpCadrePsychometricTestDAO{

	public TdpCadrePsychometricTestDAO() {
		super(TdpCadrePsychometricTest.class);
	}
	
	public String getUrlForMemberShipNo(String memberShipNo){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.psychometricTest.testUrl" +
				" from TdpCadrePsychometricTest model" +
				" where model.isDeleted = 'N' and model.psychometricTest.isUsed = 'Y'");
		if(memberShipNo != null && !memberShipNo.isEmpty()){
			sb.append(" and model.tdpCadre.memberShipNo = :memberShipNo");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(memberShipNo != null && !memberShipNo.isEmpty())
			query.setParameter("memberShipNo", memberShipNo);
		
		return (String) query.uniqueResult();
	}

}

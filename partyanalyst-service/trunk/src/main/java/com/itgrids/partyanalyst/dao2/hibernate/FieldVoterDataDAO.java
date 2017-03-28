package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFieldVoterDataDAO;
import com.itgrids.partyanalyst.model.FieldVoterData;

public class FieldVoterDataDAO extends GenericDaoHibernate<FieldVoterData, Long> implements IFieldVoterDataDAO{

	public FieldVoterDataDAO() {
		super(FieldVoterData.class);
		
	}
	
	
	public List<Object[]> getFieldVotersDataByBoothId(Long boothId)
	{
		return getHibernateTemplate().find("select FVD ,BPV.booth from FieldVoterData FVD , BoothPublicationVoter BPV " +
				" where " +
				"FVD.voter.voterId = BPV.voter.voterId and " +
				"BPV.booth.boothId = ?",boothId);
		
		
		
	}

}

package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAreaInchargersVoterMappingDetailsDAO;
import com.itgrids.partyanalyst.model.AreaInchargersVoterMappingDetails;

public class AreaInchargersVoterMappingDetailsDAO extends GenericDaoHibernate<AreaInchargersVoterMappingDetails, Long> implements
		IAreaInchargersVoterMappingDetailsDAO {
	public AreaInchargersVoterMappingDetailsDAO() {
			super(AreaInchargersVoterMappingDetails.class);
		}
	}

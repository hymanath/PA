package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreRegIssueTrackDAO;
import com.itgrids.partyanalyst.model.CadreRegIssueTrack;

public class CadreRegIssueTrackDAO extends GenericDaoHibernate<CadreRegIssueTrack, Long> implements ICadreRegIssueTrackDAO {

	public CadreRegIssueTrackDAO() {
		super(CadreRegIssueTrack.class);
	
	}
}

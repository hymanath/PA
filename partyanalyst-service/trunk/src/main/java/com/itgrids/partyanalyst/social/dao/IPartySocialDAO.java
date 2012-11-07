package com.itgrids.partyanalyst.social.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.social.model.PartySocial;

public interface IPartySocialDAO  extends GenericDao<PartySocial, Long> {
	public List<Object[]> getPartyNames();
}

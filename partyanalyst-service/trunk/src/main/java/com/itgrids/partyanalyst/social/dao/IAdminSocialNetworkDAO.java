package com.itgrids.partyanalyst.social.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.social.model.CandidateSocial;

public interface IAdminSocialNetworkDAO  extends GenericDao<CandidateSocial, Long>{
	public List<Object[]> getAdminDetails();
}

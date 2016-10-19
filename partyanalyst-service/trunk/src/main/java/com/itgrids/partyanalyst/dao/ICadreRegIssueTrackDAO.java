package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRegIssueTrack;

public interface ICadreRegIssueTrackDAO extends GenericDao<CadreRegIssueTrack, Long> {
	
	public List<Object[]> trackingRegIssueByRegIssueId(Long cadreRegIssueId);
}

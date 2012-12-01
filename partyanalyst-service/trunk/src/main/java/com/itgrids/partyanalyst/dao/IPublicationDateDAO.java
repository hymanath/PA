package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PublicationDate;

public interface IPublicationDateDAO extends GenericDao<PublicationDate, Long> {
	public List<PublicationDate> getPublicationDates();
}

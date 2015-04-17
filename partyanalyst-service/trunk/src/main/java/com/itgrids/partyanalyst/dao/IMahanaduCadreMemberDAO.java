package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MahanaduCadreMember;

public interface IMahanaduCadreMemberDAO extends GenericDao<MahanaduCadreMember, Long>{
	public Integer deleteAllCadreDetailsByEvent(Long eventId);
	public MahanaduCadreMember getMahanaduCadreMemberByEventId(Long eventId);
}

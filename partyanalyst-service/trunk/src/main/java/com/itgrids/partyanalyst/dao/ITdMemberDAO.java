package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdMember;

public interface ITdMemberDAO  extends GenericDao<TdMember, Long> {
	
	public List<Object[]> getMembersDetailsBypanchayatId(Long panchayatId);



}

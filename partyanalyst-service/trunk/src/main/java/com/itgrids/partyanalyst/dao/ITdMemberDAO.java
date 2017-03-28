package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdMember;

public interface ITdMemberDAO  extends GenericDao<TdMember, Long> {
	
	public List<Object[]> getMembersDetailsBypanchayatId(Long panchayatId);
	public List<Object[]> getMembersDetailsByBooth(String boothNo,Long constId,int inttial,int max);
    public List<Object[]> getUrbanMembersDetails(Long constituencyId);
	public List<Object[]> getPanchayatsDetailsByConstituencyId(Long constituencyId);
	public List<Object[]> getConstituencyDetails(Long constituencyId);
	public List<Object[]> getCadreDataByYear(Long constituencyId);
}

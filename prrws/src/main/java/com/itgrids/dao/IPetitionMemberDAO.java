package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PetitionMember;

public interface IPetitionMemberDAO extends GenericDao<PetitionMember, Long> {
	public List<Object[]> getRepresentativeSearchDetailsBy(String searchType,String searchValue);

}

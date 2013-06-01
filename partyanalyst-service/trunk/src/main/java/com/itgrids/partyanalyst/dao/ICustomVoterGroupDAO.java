package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CustomVoterGroup;

public interface ICustomVoterGroupDAO extends GenericDao<CustomVoterGroup,Long>{
	
	public List<Object[]> getCustomVoterGroupsByLocationValue(Long userId ,List<Long> locationValues);
	public List<Object[]> getCustomVoterGroupsByLocationValueAndAreaType(Long userId ,List<Long> locationValues , String areaType);	
	public List<Object[]> checkDuplicateGroupName(Long userId,Long locationValue,String name);
	public List<Object[]> getVotersCountBasedOnAgeInGroup(Long userId,Long customGroupId, String age, Long publicationDateId);
	public List<Object[]> getCustomVoterGroupsByLocationValueAndAreaTypeAndConstituencyId(Long userId ,List<Long> locationValues, String areaType,Long constituencyId);



}

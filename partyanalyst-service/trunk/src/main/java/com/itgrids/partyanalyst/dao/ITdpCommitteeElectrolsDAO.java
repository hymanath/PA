package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCommitteeElectrols;

public interface ITdpCommitteeElectrolsDAO  extends GenericDao<TdpCommitteeElectrols, Long>{
	public List<Object[]> getTdpCommitteeElectrolsForTdpCadreIdList(List<Long> tdpCadreIdsList);
	public Long checkUserAlreadyAddedToThisCommittee(Long tdpCadreId,Long levelId,Long levelValue,Long enrollId,Long commitTypeId,Long tdpCommitId);
	public Long checkUserAlreadyAddedToOtherAffiliatedCommittee(Long tdpCadreId,Long levelId,Long levelValue,Long enrollId);
	
	public List<Object[]> getElectrolsForPanchayatsWards(List<Long> locationIds, String locationType);
	public List<Object[]> getElctoralInfoForALocation(Long locationValue);
	public List<Object[]> getElectrolsOfPanchayatAndWards(Long locationId, Long locationType, Long basicCommitteeTypeId);
	public List<Object[]> getElectrolsForPanchayatsWardsWithOutDuplicates(List<Long> locationIds, String locationType, List<Long> tdpCadreIds);
	
	public List<Object[]> getElctoralInfoForALocation(Long locationValue,Long tdpCommitteeEnrollmentId);

}

package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;

public interface IVoterModificationService {
	
	public VoterModificationVO getAddedAndDeletedVotersCountInBetweenPublicationsInALocation(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId);
	
	public List<Long> getVoterPublicationIdsBetweenTwoPublications(Long fromPublicationDateId,Long toPublicationDateId);
	
	public List<Long> getPreviousPublicationIds(Long publicationDateId);
	
	public List<VoterAgeRangeVO> getVoterInfoByPublicationDateList(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId);	
	
}

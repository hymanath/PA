package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationAgeRangeVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.dto.VoterModificationGenderInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;

public interface IVoterModificationService {
	
	public VoterModificationVO getAddedAndDeletedVotersCountInBetweenPublicationsInALocation(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId);
	
	public List<Long> getVoterPublicationIdsBetweenTwoPublications(Long fromPublicationDateId,Long toPublicationDateId);
	
	public List<Long> getPreviousPublicationIds(Long publicationDateId);
	
	public List<VoterAgeRangeVO> getVoterInfoByPublicationDateList(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId);
	
	public VoterModificationGenderInfoVO getGenderWiseVoterModificationsBetweenPublications(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId);
	
	public List<VoterModificationAgeRangeVO> getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId);
	
    public List<VoterModificationGenderInfoVO> getGenderWiseVoterModificationsForEachPublication(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId);
    
    public List<VoterVO> getModifiedVotersInALocationBetweenPublucations(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String status);
	 
	 
	 public VoterModificationVO getSubLevelsVoterModificationDetails(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId);
	 
	 public String getLocationNameByLocationValue(String locationType, Long locationValue);
	 
	 public String getPublicationNameByPublicationDateId(Long publicationDateId);
	 

    
}

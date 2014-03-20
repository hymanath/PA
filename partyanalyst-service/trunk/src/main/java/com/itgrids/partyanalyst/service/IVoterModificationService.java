package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterAdderdOrDeletedRengesInfoVO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.dto.VoterModificationGenderInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationAgeRangeVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;

public interface IVoterModificationService {
	
	public VoterModificationVO getAddedAndDeletedVotersCountInBetweenPublicationsInALocation(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId);
	
	public List<Long> getVoterPublicationIdsBetweenTwoPublications(Long fromPublicationDateId,Long toPublicationDateId);
	
	public List<Long> getPreviousPublicationIds(Long publicationDateId);
	
	public List<VoterAgeRangeVO> getVoterInfoByPublicationDateList(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId);
	
	public VoterModificationGenderInfoVO getGenderWiseVoterModificationsBetweenPublications(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String queryType);
	
	public List<VoterModificationAgeRangeVO> getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String queryType);
	
    public List<VoterModificationGenderInfoVO> getGenderWiseVoterModificationsForEachPublication(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String queryType);
    
    public List<VoterVO> getModifiedVotersInALocationBetweenPublucations(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String status);
	 
	 
	 public VoterModificationVO getSubLevelsVoterModificationDetails(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId);
	 
	 public String getLocationNameByLocationValue(String locationType, Long locationValue);
	 
	 public String getPublicationNameByPublicationDateId(Long publicationDateId);
	 
	 public ResultStatus insertGenderWiseVoterModifInfoInVoterModificationInfoTable(Long constituencyId, Long publicationDateId);
	 
	 public Long getLocalElectionBodyIdByAssemblyLocalElectionBodyId(Long assemblyLocalElectionBodyId);
	 
	 public VoterModificationVO getSubLevelsVoterModificationDetailsByLocationValue(
				String locationType, Long locationValue, Long constituencyId,
				Long fromPublicationDateId, Long toPublicationDateId);
	 
	 public List<VoterVO> getSelectedVotersDetails(VoterModificationVO voterModificationVO);
	 
	 public String getLocationTypeForLocalEleBodyByLocalEleBodyId(Long localEleBodyId);
	 
	 public VoterModificationVO getBoothWiseVoterModificationDetails(String locationType,Long locationValue, Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId);
	 
	 public List<VoterModificationVO> getBoothWiseModificationsCompleteDetails(Long constituencyId, Long locationValue, Long publicationDateId, String locationType);
	 
	 public ResultStatus insertConstituencyBasicData(Long constituencyId,Long publicationId,Long userId);
	 
	 public ResultStatus deleteConstituencyBasicData(Long constituencyId,Long publicationDateId,Long userId);
	 
	 public List<VoterAdderdOrDeletedRengesInfoVO> getReportForVotersAddedOrDeletedVotersForSelectdConstituency(Long constituencyId,Long publicationDateId,String reportType);
	 
	 public SelectOptionVO createPdf(Long constituencyId,Long publicatIonId,String locationType, Long locationValue ,String queryType,String path,String type);
	 
	 public List<SelectOptionVO> insertGenderWiseVoterModifInfoInVoterModificationInfoTableForDistrict(Long districtId, Long publicationDateId);
	 
	 public void createPdfForAddresses(String path);
	 
}

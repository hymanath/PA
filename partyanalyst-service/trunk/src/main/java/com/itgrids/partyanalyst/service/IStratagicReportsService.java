package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itextpdf.text.Document;
import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.dto.AssumptionsVO;
import com.itgrids.partyanalyst.dto.DashBoardResultsVO;
import com.itgrids.partyanalyst.dto.DelimitationEffectVO;
import com.itgrids.partyanalyst.dto.PDFHeadingAndReturnVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsVerVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterDensityWithPartyVO;
import com.itgrids.partyanalyst.dto.VoterModificationGenderInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;


public interface IStratagicReportsService {
	public List<AgeRangeVO> getBoothWiseAddedAndDeletedVoters(Long constituencyId,Long publicationDateId);
	public List<PartyElectionTrendsReportVO> getPreviousTrendsReport(Long constId);
	public List<PartyElectionTrendsReportVO> getPreviousTrendsReportParliament(Long constId);
	public PartyResultsVerVO getZptcMptcResultsOfConstituency(Long constiutencyId);
	public PartyResultsVerVO getMuncipalCorpPrevResultsInGHMC(Long constiutencyId);
	public PartyResultsVerVO getMuncipalCorpPrevResults(Long constiutencyId);
	
	public PartyPositionResultsVO getPartyChanges(Long constituencyId,List<Long> assemblyEleIdsList,List<Long> partiesSelected);
	public VoterModificationVO getSubLevelsVoterModificationDetailsByLocationValue(String locationType, Long locationValue, Long constituencyId,
			Long fromPublicationDateId, Long toPublicationDateId);
	
	public VoterDensityWithPartyVO getVotersCountInPanchayatsForDensity(Long constituencyId,Long publicationId);
	
	//VOTER MODIFICATION REPORT SERVICES
	public PDFHeadingAndReturnVO getVoterInfoByPublicationDateList(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId);
	public PDFHeadingAndReturnVO getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String queryType);
	public VoterModificationGenderInfoVO getGenderWiseVoterModificationsBetweenPublications(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String queryType);
	public PDFHeadingAndReturnVO getGenderWiseVoterModificationsForEachPublication(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String queryType);
	

	public DelimitationEffectVO getDelimationEffectOnConstituency(Long constituencyId,Long partyId);
	
	public AssumptionsVO votersAssumptionsService(Long constituencyId,Long base,Long assured,Long publicationDateId,Long tdpPerc);
	
	public List<SelectOptionVO> getSearchTypeDetails(Long userId,String searchtype,Long cosntituencyId);
	
	public ResultStatus mergePanchayatsToOnePanchayat(Long userId, String searchtype,Long searchTypeValue,Long cosntituencyId,Long panchayatId,List<Long> mergedPanchyatsIds);
	
	public List<SelectOptionVO> getElectionIdsAndYearsByCosntutuencyId(Long electionScopeId,Long constituencyId) ;
	
	public List<SelectOptionVO> getPanchayatDetailsForElectionInCosntituency(Long userId,Long constituencyId,Long elctionId);
	
	public void generatePdfForLocalElectionResults(PartyResultsVerVO prevResults,Document document);
	
	public void generatePDFForVoterInfo(PDFHeadingAndReturnVO pvo,String task,Document document);
	
	public void generatePDFForDensity(VoterDensityWithPartyVO result,Document document);
	
	public void generateBoothWiseAddedDeletedVoters(List<AgeRangeVO> result,Document document);
	
	public void getPDFForSubLevelAddedDeleted(VoterModificationVO result,Document document);
	 public void generatePDFForAssuredTargetVotersBlock(AssumptionsVO result,Document document);
	 public void generatePDFForDelimitationEffect(DelimitationEffectVO result,Document document);
	 
	 public ResultStatus getRecordsCountToCasteContainConsti(Long constituencyId);
	 public void buildAutoStrategy(Long constituencyId);
	 public Long getConstituencyNo(Long constituencyId);
	 public List<DashBoardResultsVO> getPartyResults(Long electionId,String type,String region,Boolean isAlliance);
}

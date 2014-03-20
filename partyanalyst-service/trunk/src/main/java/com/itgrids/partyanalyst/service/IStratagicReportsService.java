package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.dto.DelimitationEffectVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsVerVO;
import com.itgrids.partyanalyst.dto.VoterDensityWithPartyVO;
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
	
	public DelimitationEffectVO getDelimationEffectOnConstituency(Long constituencyId,Long partyId);
}

package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.HHLeaderDetailsVO;
import com.itgrids.partyanalyst.dto.HHQuestionDetailsVO;
import com.itgrids.partyanalyst.dto.HHSurveyVO;
import com.itgrids.partyanalyst.dto.HouseHoldVotersVO;
import com.itgrids.partyanalyst.dto.HouseHoldsReportVO;
import com.itgrids.partyanalyst.dto.HouseHoldsSummaryReportVO;
import com.itgrids.partyanalyst.dto.HouseHoldsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;

public interface IHouseHoldSurveyReportService {
	public List<HHSurveyVO> getHHSurveyQuestionOptions(Long surveyId,Long boothId,String houseNo,Long voterId);
	
	public List<GenericVO> getAllOptionTypes();
	public String createSurveySubCategory(String name,boolean isChild,Long parentId);
	public String saveHouseHoldQuestionDetails(final HHQuestionDetailsVO qstnDtls);
	public List<GenericVO> getSubSurveyTypesByMainTypeId(Long mainTypeId);
	public List<GenericVO> getSurveyTypes();
	public String saveQuestOptnsOfHH(Long boothId,String houseNo,List<HHSurveyVO> questOptsList,Long houseHoldsId);
	public Long saveHouseHoldsVotersDetails(HouseHoldVotersVO votersDetails);

	public String saveMainQuestionDetails(String qtn);
	public String saveSubQuestionDetails(Long id,String subQtn);
	
	public List<SelectOptionVO> getUserVoterCategoryValuesById(Long categoryId);
	public List<SelectOptionVO> getFamilyRelationsList();
	
	public GenericVO getLeaderIdAndMobileNoFromHH(Long voterId);
	public Long getHouseHoldIdOfVoter(Long voterId);
	public List<String> getAllVoterIds();
	public ResultStatus saveLeaderDetails(final HHLeaderDetailsVO leaderDtls);
	public VoterDetailsVO getVoterDetailsByVoterId(String voterIdCardNo);
	
	public HouseHoldsVO getBoothsOrBooksInConstituencyOfLeaderToAssign(Long constituencyId,Long publicationId,Long leaderId,String task,String bookNo,List<Long> boothOrBooks );
	public HouseHoldsSummaryReportVO getReportsOfHouseHolds(HouseHoldsSummaryReportVO hhSummaryReportVO);
	
	public List<GenericVO> getHouseHoldConstituencies();


}
